package main.controller;

import main.model.EspacioParqueadero;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import main.model.Vehiculo;
import main.service.EspacioParqueaderoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author judav
 */
@Tag(name = "Parqueadero", description = "API para la gestión de parqueadero")
@RestController
@RequestMapping("/api/espacio")
public class EspacioParqueaderoController {

  
    private final EspacioParqueaderoService espacioParqueaderoService;

 
    public EspacioParqueaderoController(EspacioParqueaderoService espacioParqueaderoService){
        this.espacioParqueaderoService = espacioParqueaderoService;
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
    public ResponseEntity<EspacioParqueadero> buscarPorNumero(
            @PathVariable @Parameter(description = "Número del espacio") int numero) {
        EspacioParqueadero espacio = espacioParqueaderoService.buscarPorNumero(numero);
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
    @GetMapping("/disponibles")
    @Operation(summary = "Obtener espacios disponibles", description = "Devuelve una lista de todos los espacios disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de espacios obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<EspacioParqueadero>> obtenerDisponibles() {
        return ResponseEntity.ok(espacioParqueaderoService.obtenerDisponibles());
    }



    // ➕ Asignar un vehículo a un espacio (marcar como ocupado)
    @PostMapping("/{numero}/asignar")
    @Operation(summary = "Asignar vehículo a un espacio", description = "Asigna un vehículo a un espacio de parqueadero.")
    public ResponseEntity<EspacioParqueadero> asignarVehiculo(
            @PathVariable int numero,
            @RequestBody @Parameter(description = "Datos del vehículo") Vehiculo vehiculo) {

        EspacioParqueadero espacioActualizado = espacioParqueaderoService.asignarVehiculo(numero, vehiculo);
        if (espacioActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(espacioActualizado);
    }

    // ➕ Liberar un espacio (remover vehículo)
    @PutMapping("/{numero}/liberar")
    @Operation(summary = "Liberar un espacio", description = "Libera un espacio de parqueadero.")
    public ResponseEntity<EspacioParqueadero> liberarEspacio(
            @PathVariable int numero) {

        EspacioParqueadero espacioLiberado = espacioParqueaderoService.liberarEspacio(numero);
        if (espacioLiberado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(espacioLiberado);
    }
    
    @PostMapping("/ingresar/{idEspacio}")
    public EspacioParqueadero ingresarVehiculo(@RequestBody Vehiculo vehiculo, @PathVariable int idEspacio) {
        return espacioParqueaderoService.ingresarVehiculoEnEspacio(idEspacio, vehiculo);
    }

}