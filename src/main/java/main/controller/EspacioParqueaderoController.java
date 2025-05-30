package main.controller;

import main.model.EspacioParqueadero;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import main.service.JwtService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

import main.model.Vehiculo;
import main.service.EspacioParqueaderoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/**
 * @author judav
 */
@Tag(name = "Parqueadero", description = "API para la gestión de parqueadero")
@RestController
@RequestMapping("/api/espacio")
public class EspacioParqueaderoController {


    private final EspacioParqueaderoService espacioParqueaderoService;
    private final JwtService jwtService;

    @Autowired
    public EspacioParqueaderoController(EspacioParqueaderoService espacioParqueaderoService, JwtService jwtService) {
        this.espacioParqueaderoService = espacioParqueaderoService;
        this.jwtService = jwtService;
    }

    // ✅ Ocupar nuevo espacio
    @PostMapping
    @Operation(summary = "Nuevo Parqueadero", description = "Verificar si el parqueadero está ocupado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Parqueadero ocupado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<EspacioParqueadero> guardarEspacio(@RequestBody EspacioParqueadero espacio) {
        System.out.println("Número recibido: " + espacio.getNumero());
        System.out.println("espacio disponible recibido: " + espacio.isDisponible());
        EspacioParqueadero guardado = espacioParqueaderoService.guardarEspacio(espacio);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    // ✅ Obtener todos los parqueaderos
    @GetMapping
    @Operation(summary = "Obtener todos los espacios", description = "Devuelve una lista de todos los espacios.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de espacios obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<EspacioParqueadero>> obtenerTodos() {
        return ResponseEntity.ok(espacioParqueaderoService.obtenerTodos());
    }

    // ✅ Buscar por número
    @GetMapping("/numero/{numero}")
    @Operation(summary = "Obtener espacios por número", description = "Devuelve un espacio específico basado en su número.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Espacio encontrado"),
            @ApiResponse(responseCode = "404", description = "Espacio no encontrado")
    })
    public ResponseEntity<Optional<EspacioParqueadero>> buscarPorNumero(
            @PathVariable @Parameter(description = "Número del espacio") int numero) {
        Optional<EspacioParqueadero> espacio = espacioParqueaderoService.buscarPorNumero(numero);
        if (espacio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(espacio);
    }

    // ✅ Eliminar por número
    @DeleteMapping("/numero/{numero}")
    @Operation(summary = "Eliminar un espacio", description = "Elimina un espacio basado en su número.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Espacio eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Espacio no encontrado")
    })
    public ResponseEntity<Void> eliminarPorNumero(
            @PathVariable @Parameter(description = "Número del espacio") int numero) {
        boolean eliminado = espacioParqueaderoService.eliminarPorNumero(numero);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    // ✅ Obtener todos los espacios disponibles
    @GetMapping("/user/{user_id}")
    @Operation(summary = "Obtener espacios disponibles", description = "Devuelve una lista de todos los espacios disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de espacios obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<EspacioParqueadero>> obtenerUsuario(@RequestHeader("Authorization") String authHeader, @PathVariable @Parameter(description = "ID del usuario") Integer user_id) {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(espacioParqueaderoService.findAllByUserID(user_id));
    }


    // ➕ Liberar un espacio (remover vehículo)
    @PutMapping("/liberar")
    @Operation(summary = "Liberar un espacio", description = "Libera un espacio de parqueadero.")
    public ResponseEntity<EspacioParqueadero> liberarEspacio(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam Integer user_id,
            @RequestParam int numero) {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        EspacioParqueadero espacioLiberado = espacioParqueaderoService.liberarEspacio(user_id, numero);
        if (espacioLiberado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(espacioLiberado);
    }

    @PostMapping("/ingresar")
    public ResponseEntity<EspacioParqueadero> ingresarVehiculo(@RequestHeader("Authorization") String authHeader, @RequestBody Vehiculo vehiculo, @RequestParam Integer user_id, @RequestParam int numero) {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        EspacioParqueadero espacioParqueadero = espacioParqueaderoService.ingresarVehiculoEnEspacio(user_id, numero, vehiculo);
        return new ResponseEntity<>(espacioParqueadero, HttpStatus.OK);
    }

}