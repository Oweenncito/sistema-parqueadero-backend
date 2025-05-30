package main.repository;

import main.model.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByNombreAndContraseña(String nombre, String contraseña);

        Optional<Usuario> findByCorreo(String correo);
}