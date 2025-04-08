package com.example.sistema_parqueadero_backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Vehículos", description = "Operaciones relacionadas con vehículos")
@RestController
@RequestMapping("/api/vehiculos")
@Tag(name = "Parqueadero", description = "API para la gestión de vehiculos")
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

    // ✅ Buscar por placa
    @GetMapping("/placa/{placa}")
    @Operation(summary = "Obtener vehiculo por placa", description = "Devuelve un vehiculo específico basado en su placa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "vehiculo encontrado"),
            @ApiResponse(responseCode = "404", description = "vehiculo no encontrado")
    })
    public ResponseEntity<Vehiculo> buscarPorPlaca(@PathVariable@Parameter(description = " placa del vehiculo") String placa) {
        Vehiculo vehiculo = vehiculoService.buscarPorPlaca(placa);
        return ResponseEntity.ok(vehiculo);
    }

    // ✅ Buscar por ID
    @GetMapping("/id/{id}")
    @Operation(summary = "Obtener vehiculo por ID", description = "Devuelve un vehiculo específico basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehiculo encontrado"),
            @ApiResponse(responseCode = "404", description = "Vehiculo no encontrado")
    })
    public ResponseEntity<Vehiculo> buscarPorId(@PathVariable @Parameter(description = "ID del vehiculo") String id) {
        Vehiculo vehiculo = vehiculoService.buscarPorId(id);
        return ResponseEntity.ok(vehiculo);
    }

    // ✅ Eliminar por placa
    @DeleteMapping("/placa/{placa}")
    @Operation(summary = "Eliminar un vehiculo", description = "Elimina un vehiculo basado en su placa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vehiculo eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Vehiculo  no encontrado")
    })
    public ResponseEntity<Void> eliminarPorPlaca(@PathVariable @Parameter(description = "Placa del vehiculo") String placa) {
        vehiculoService.eliminarPorPlaca(placa);
        return ResponseEntity.noContent().build();
    }

    // ✅ Eliminar por ID
    @DeleteMapping("/id/{id}")
    @Operation(summary = "Eliminar un vehiculo", description = "Elimina un vehiculo basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vehiculo eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Vehiculo  no encontrado")
    })
    public ResponseEntity<Void> eliminarPorId(@PathVariable @Parameter(description = "ID del vehiculo") String id) {
        vehiculoService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Contar vehículos
    @GetMapping("/total")
    @Operation(summary = "Contar vehículos", description = "Devuelve el número total de vehículos registrados en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Conteo obtenido correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Integer> contarVehiculos() {
        return ResponseEntity.ok(vehiculoService.contarVehiculos());
    }
}