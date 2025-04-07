package com.example.sistema_parqueadero_backend;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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


/**
 *
 * @author judav
 */
@Tag(name = "Estado Parqueadero", description = "Operaciones relacionadas con el parqueadero")
@RestController
@RequestMapping("/api/parqueadero")
@Tag(name = "Parqueadero", description = "API para la gestión de parqueadero")
public class EspacioParqueaderoControlador {
    
    private final EspacioParqueaderoService espacioparqueaderoservice;
    
    @Autowired
    public EspacioParqueaderoControlador(EspacioParqueaderoService espacioparqueaderoservice){
        this.espacioparqueaderoservice = espacioparqueaderoservice;
    }
    
     // ✅ Ocupar nuevo espacio
     @PostMapping
    @Operation(summary = "Nuevo Parqueadero", description = "Verificar si el parqueadero esta ocupado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Parqueadero ocupado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<EspacioParqueadero> guardarEspacio(@RequestBody @Parameter(description = "")EspacioParqueadero espacio) {
        EspacioParqueadero nuevoParqueadero = espacioparqueaderoservice.guardarEspacio(espacio);
        return new ResponseEntity<>(nuevoParqueadero, HttpStatus.CREATED);
    }
    
    // ✅ Obtener todos los parqueaderos
    @GetMapping
    @Operation(summary = "Obtener todos los espacios", description = "Devuelve una lista de todos los espacios.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de espacios obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<EspacioParqueadero>> obtenerTodos() {
        return ResponseEntity.ok(espacioparqueaderoservice.obtenerTodos());
    }
    
     // ✅ Buscar por numero
    @GetMapping("/numero/{numero}")
    @Operation(summary = "Obtener espacios por numero", description = "Devuelve un espacio específico basado en su numero.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Espacio encontrado"),
            @ApiResponse(responseCode = "404", description = "Espacio no encontrado")
    })
    public ResponseEntity<EspacioParqueadero> buscarPorNumero(@PathVariable@Parameter(description = " numero del espacio")int numero) {
        EspacioParqueadero espacioparqueadero = espacioparqueaderoservice.buscarPorNumero(numero);
        return ResponseEntity.ok(espacioparqueadero);
    }
    
      // ✅ Eliminar por numero
    @DeleteMapping("/numero/{numero}")
    @Operation(summary = "Eliminar un espacio", description = "Elimina un espacio basado en su numero.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Espacio eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Espacio  no encontrado")
    })
    public ResponseEntity<Void> eliminarPorNumero(@PathVariable @Parameter(description = "Numero del espacio") int numero) {
        espacioparqueaderoservice.eliminarPorNumero(numero);
        return ResponseEntity.noContent().build();
    }
    
    // ✅ Obtener todos los espacios
    @GetMapping
    @Operation(summary = "Obtener todos los espacios", description = "Devuelve una lista de todos los espacios disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de espacios obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<EspacioParqueadero>> obtenerDisponibles() {
        return ResponseEntity.ok(espacioparqueaderoservice.obtenerDisponibles());
    }
    
   @GetMapping("/tipo/{tipoVehiculo}")
   @Operation(summary = "Obtener por tipo", description = "Devuelve una lista de todos los espacios del tipo indicado.")
   @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
   })
    public ResponseEntity<List<EspacioParqueadero>> obtenerPorTipo(@PathVariable("tipoVehiculo") String tipoVehiculoPermitido) {
    return ResponseEntity.ok(espacioparqueaderoservice.obtenerPorTipo(tipoVehiculoPermitido));
    }
}
