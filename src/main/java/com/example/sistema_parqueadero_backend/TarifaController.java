package com.example.sistema_parqueadero_backend;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarifas")
@Tag(name = "Tarifa Controller", description = "CRUD de tarifas por tipo de vehículo")
public class TarifaController {

    private final TarifaService tarifaService;

    @Autowired
    public TarifaController(TarifaService tarifaService) {
        this.tarifaService = tarifaService;
    }

    @Operation(summary = "Listar todas las tarifas")
    @ApiResponse(responseCode = "200", description = "Lista de tarifas obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<Tarifa>> listarTarifas() {
        return ResponseEntity.ok(tarifaService.listarTarifas());
    }

    @Operation(summary = "Obtener una tarifa por tipo de vehículo")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tarifa encontrada"),
        @ApiResponse(responseCode = "400", description = "Tipo de vehículo no válido")
    })
    @GetMapping("/{tipoVehiculo}")
    public ResponseEntity<Tarifa> obtenerTarifa(@PathVariable String tipoVehiculo) {
        try {
            Tarifa tarifa = tarifaService.obtenerTarifaPorTipo(tipoVehiculo);
            return ResponseEntity.ok(tarifa);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Crear o actualizar una tarifa")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Tarifa creada o actualizada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<Tarifa> guardarTarifa(@RequestBody Tarifa tarifa) {
        try {
            Tarifa guardada = tarifaService.guardarTarifa(tarifa);
            return ResponseEntity.status(201).body(guardada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Eliminar una tarifa por tipo de vehículo")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tarifa eliminada correctamente"),
        @ApiResponse(responseCode = "400", description = "La tarifa no existe")
    })
    @DeleteMapping("/{tipoVehiculo}")
    public ResponseEntity<Void> eliminarTarifa(@PathVariable String tipoVehiculo) {
        try {
            tarifaService.eliminarTarifa(tipoVehiculo);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}