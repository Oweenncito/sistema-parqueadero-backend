package main.controller;

import main.dto.LoginDTO;
import main.dto.LoginResponseDTO;
import main.model.Usuario;
import main.service.JwtService;
import main.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;
    @Autowired
    public UsuarioController(UsuarioService usuarioService, JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
    }

    @Operation(summary = "Obtener todos los usuarios")
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @Operation(summary = "Obtener un usuario por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findByID(@PathVariable Integer id) {
        return usuarioService.findByID(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo usuario")
    @PostMapping
    public ResponseEntity<Usuario> saveUser(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.saveUser(usuario));
    }

    @Operation(summary = "Actualizar un usuario existente")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Usuario usuario) {
        Usuario respuesta = usuarioService.actualizarUsuario(usuario);
        return respuesta != null ? ResponseEntity.ok(respuesta) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un usuario")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (usuarioService.findByID(id).isPresent()) {
            usuarioService.deleteUser(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar usuarios por nombre y correo")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> searchUsers(@RequestBody LoginDTO loginDTO) {
        Usuario usuario = usuarioService.searchUsers(loginDTO);
        String token = jwtService.generateJwtToken(usuario);
        return new ResponseEntity<>(new LoginResponseDTO(usuario, token), HttpStatus.OK);
    }


}