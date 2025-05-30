package main.service;

import jakarta.persistence.EntityNotFoundException;
import main.dto.LoginDTO;
import main.dto.LoginResponseDTO;
import main.model.Usuario;
import main.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;

    }

    // Crear usuario
    public Usuario saveUser(Usuario usuario) {
        Optional<Usuario> aux = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (aux.isPresent()){
            throw new IllegalArgumentException("El correo ya se encuentra registrado");
        }
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        return usuarioRepository.save(usuario);
    }

    // Obtener todos los usuarios
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // Obtener usuario por ID
    public Optional<Usuario> findByID(Integer id) {
        return usuarioRepository.findById(id);
    }

    // Actualizar usuario
    public Usuario actualizarUsuario(Usuario usuario) {
        if (usuarioRepository.existsById(usuario.getId())) {
            return usuarioRepository.save(usuario); // Actualiza
        } else {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
    }

    // Eliminar usuario
    public void deleteUser(Integer id) {
        usuarioRepository.deleteById(id);
    }

    // Buscar usuarios por nombre y correo
    public Usuario searchUsers(LoginDTO loginDTO) {
        Optional<Usuario> aux = usuarioRepository.findByCorreo(loginDTO.getUser());
        if (aux.isEmpty()){
            throw new EntityNotFoundException("No se ha encontrado el usuario");
        }
        if (!passwordEncoder.matches(loginDTO.getPassword(), aux.get().getContraseña())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }
        return aux.get();
    }

}
