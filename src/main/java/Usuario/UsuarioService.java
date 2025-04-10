package Usuario;

import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        initSampleData();
    }

    // Crear usuario
    public Usuario saveUser(Usuario usuario) {
        return usuarioRepository.SaveUser(usuario);
    }

    // Obtener todos los usuarios
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // Obtener usuario por ID
    public Optional<Usuario> findByID(String id) {
        return usuarioRepository.findByID(id);
    }

    // Actualizar usuario
    public Optional<Usuario> updateUser(String id, Usuario usuario) {
        return usuarioRepository.updateUser(id, usuario);
    }

    // Eliminar usuario
    public boolean deleteUser(String id) {
        return usuarioRepository.deleteUser(id);
    }

    // Buscar usuarios por nombre y correo
    public List<Usuario> searchUsers(String nombre, String correo) {
        return usuarioRepository.searchUsers(nombre, correo);
    }

    // Inicializar datos de ejemplo
    private void initSampleData() {
        Usuario usuario1 = new Usuario("owenfernando08@gmail.com", "Owen", "456");
        Usuario usuario2 = new Usuario("juanDavid@gmail.com", "Juan David", "789");
        Usuario usuario3 = new Usuario("danielaAguirre@gmail.com", "Daniela", "123");

        usuarioRepository.SaveUser(usuario1);
        usuarioRepository.SaveUser(usuario2);
        usuarioRepository.SaveUser(usuario3);
    }
}
