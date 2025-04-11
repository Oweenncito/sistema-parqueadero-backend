package main.repository;




import main.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UsuarioRepository {

    private final Map<String, Usuario> baseDeDatos = new HashMap<>();

    // Crear usuario
    public Usuario SaveUser(Usuario usuario) {
        baseDeDatos.put(usuario.getId(), usuario);
        return usuario;
    }

    // Obtener todos los usuarios
    public List<Usuario> findAll() {
        return new ArrayList<>(baseDeDatos.values());
    }

    // Obtener usuario por ID
    public Optional<Usuario> findByID(String id) {
        return Optional.ofNullable(baseDeDatos.get(id));
    }

    // Actualizar usuario
    public Optional<Usuario> updateUser(String id, Usuario usuarioActualizado) {
        if (baseDeDatos.containsKey(id)) {
            usuarioActualizado.setId(id); // Asegurarse de mantener el mismo ID
            baseDeDatos.put(id, usuarioActualizado);
            return Optional.of(usuarioActualizado);
        }
        return Optional.empty();
    }

    // Eliminar usuario
    public boolean deleteUser(String id) {
        return baseDeDatos.remove(id) != null;
    }

    
    public List<Usuario> searchUsers(String nombre, String correo) {
        List<Usuario> resultado = new ArrayList<>();
        for (Usuario usuario : baseDeDatos.values()) {
            boolean coincideNombre = nombre == null || usuario.getNombre().toLowerCase().contains(nombre.toLowerCase());
            boolean coincideCorreo = correo == null || usuario.getCorreo().toLowerCase().contains(correo.toLowerCase());
            if (coincideNombre && coincideCorreo) {
                resultado.add(usuario);
            }
        }
        return resultado;
    }
}