package main.controller;

import java.util.List;

import main.model.Vehiculo;
import main.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Vehículos", description = "Operaciones relacionadas con vehículos")
@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    //constructor para vehiculocontroller
    @Autowired
    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    // ✅ Registrar nuevo vehículo
    @PostMapping
    @Operation(summary = "Registrar un nuevo vehiculo", description = "Registra un nuevo vehiculo con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vehiculo registrado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Vehiculo> registrarVehiculo(@RequestBody @Parameter(description = "Datos del Vehiculo a registrar")Vehiculo vehiculo) {
        Vehiculo nuevoVehiculo = vehiculoService.registrarVehiculo(vehiculo);
        return new ResponseEntity<>(nuevoVehiculo, HttpStatus.CREATED);
    }

    // ✅ Obtener todos los vehículos
    @GetMapping
    @Operation(summary = "Obtener todos los vehiculos", description = "Devuelve una lista de todos los vehiculos registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de vehiculos obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Vehiculo>> obtenerTodos() {
        return ResponseEntity.ok(vehiculoService.obtenerTodosLosVehiculos());
    }

    // ✅ Buscar por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener vehiculo por ID", description = "Devuelve un vehiculo específico basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehiculo encontrado"),
            @ApiResponse(responseCode = "404", description = "Vehiculo no encontrado")
    })
    public ResponseEntity<Vehiculo> buscarPorId(@PathVariable @Parameter(description = "ID del vehiculo") String id) {
        Vehiculo vehiculo = vehiculoService.buscarPorId(id);
        return ResponseEntity.ok(vehiculo);
    }

    // ✅ Eliminar por ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un vehiculo", description = "Elimina un vehiculo basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vehiculo eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Vehiculo  no encontrado")
    })
    public ResponseEntity<Void> eliminarPorId(@PathVariable @Parameter(description = "ID del vehiculo") String id) {
        vehiculoService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

}