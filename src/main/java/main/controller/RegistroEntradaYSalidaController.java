/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.controller;

import main.service.RegistroEntradaSalidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import main.model.RegistroEntradaSalida;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/registro-entrada-salida")
public class RegistroEntradaYSalidaController {

    private final RegistroEntradaSalidaService servicio;

    public RegistroEntradaYSalidaController(RegistroEntradaSalidaService servicio) {
        this.servicio = servicio;
    }

    // ✅ Obtener todos los registros
    @GetMapping
    @Operation(summary = "Listar todos los registros", description = "Devuelve una lista de todos los registros de entrada y salida.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Registros listados correctamente")
    })
    public ResponseEntity<List<RegistroEntradaSalida>> listarTodos() {
        return ResponseEntity.ok(servicio.listarTodos());
    }

    // ✅ Obtener por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener por ID", description = "Devuelve un registro específico por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Registro encontrado"),
        @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    })
    public ResponseEntity<RegistroEntradaSalida> buscarPorId(@PathVariable String id) {
        RegistroEntradaSalida registro = servicio.buscarPorId(id);
        if (registro != null) {
            return ResponseEntity.ok(registro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Crear nuevo registro
    @PostMapping
    @Operation(summary = "Crear nuevo registro", description = "Crea un nuevo registro de entrada y salida.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Registro creado exitosamente")
    })
    public ResponseEntity<RegistroEntradaSalida> crear(@RequestBody RegistroEntradaSalida nuevo) {
        RegistroEntradaSalida guardado = servicio.guardar(nuevo);
        return ResponseEntity.status(201).body(guardado);
    }

    // ✅ Eliminar por ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar por ID", description = "Elimina un registro según su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Registro eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    })
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        boolean eliminado = servicio.eliminarPorId(id);
        if (eliminado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Buscar por placa
    @GetMapping("/placa/{placa}")
    @Operation(summary = "Buscar por placa", description = "Devuelve los registros asociados a una placa.")
    public ResponseEntity<List<RegistroEntradaSalida>> buscarPorPlaca(@PathVariable String placa) {
        return ResponseEntity.ok(servicio.buscarPorPlaca(placa));
    }

    // ✅ Buscar por rango de fechas
    @GetMapping("/rango")
    @Operation(summary = "Buscar por rango de fechas", description = "Devuelve los registros dentro de un rango de fechas de entrada.")
    public ResponseEntity<List<RegistroEntradaSalida>> buscarPorRangoDeFechas(
            @RequestParam("desde") String desde,
            @RequestParam("hasta") String hasta
    ) {
        LocalDateTime fechaDesde = LocalDateTime.parse(desde);
        LocalDateTime fechaHasta = LocalDateTime.parse(hasta);
        return ResponseEntity.ok(servicio.buscarPorRangoDeFechas(fechaDesde, fechaHasta));
    }
}
 
