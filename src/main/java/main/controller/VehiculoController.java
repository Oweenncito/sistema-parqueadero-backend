package main.controller;

import java.util.List;

import main.model.Vehiculo;
import main.service.JwtService;
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
import java.util.Optional;


//clase restController de vehiculo aqui es donde se manejan las peticiones HTTPS 
@Tag(name = "Vehículos", description = "Operaciones relacionadas con vehículos")
@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    //llamamos a la clase service de vehiculo 
    private final VehiculoService vehiculoService;
    private final JwtService jwtService;
    //constructor para vehiculocontroller
    @Autowired
    public VehiculoController(VehiculoService vehiculoService, JwtService jwtService) {
        this.jwtService = jwtService;
        this.vehiculoService = vehiculoService;
    }

  //aqui se registra un nuevo vehiculo los @operation y @apiresponse, son notaciones swagger
    @PostMapping
    @Operation(summary = "Registrar un nuevo vehiculo", description = "Registra un nuevo vehiculo con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vehiculo registrado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    //este es el metodo como tal aqui se registra un vehiculo y se guarda en el repository 
    public ResponseEntity<Vehiculo> registrarVehiculo(@RequestBody @Parameter(description = "Datos del Vehiculo a registrar")Vehiculo vehiculo) {
        Vehiculo nuevoVehiculo = vehiculoService.registrarVehiculo(vehiculo);
        return new ResponseEntity<>(nuevoVehiculo, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los vehiculos", description = "Devuelve una lista de todos los vehiculos registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de vehiculos obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    
    //este metodo devuelve una lista de todos los vehiculos 
    public ResponseEntity<List<Vehiculo>> obtenerTodos() {
        return ResponseEntity.ok(vehiculoService.obtenerTodosLosVehiculos());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener vehiculo por ID", description = "Devuelve un vehiculo específico basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehiculo encontrado"),
            @ApiResponse(responseCode = "404", description = "Vehiculo no encontrado")
    })
    //este metodo busca un vehiculo por su id 
    public ResponseEntity<Optional<Vehiculo>> buscarPorId(@PathVariable @Parameter(description = "ID del vehiculo") Integer id) {
        Optional<Vehiculo> vehiculo = vehiculoService.buscarPorId(id);
        return ResponseEntity.ok(vehiculo);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un vehiculo", description = "Elimina un vehiculo basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vehiculo eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Vehiculo  no encontrado")
    })
    //este metodo elimina un vehiculo por su id
    public ResponseEntity<Void> eliminarPorId(@PathVariable @Parameter(description = "ID del vehiculo") Integer id) {
        vehiculoService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    
    //busca un vehiculo por la placa
    @GetMapping("/placa/{placa}")
    public ResponseEntity<Vehiculo> buscarPorPlaca(@PathVariable String placa){
        Vehiculo vehiculo =vehiculoService.buscarPorPlaca(placa);
        return ResponseEntity.ok(vehiculo);
        
    }
    
    //elimina un vehiculo por la placa 
    @DeleteMapping("/placa/{placa}")
    public ResponseEntity<Vehiculo> eliminarPorPlaca(@PathVariable String placa){ 
        vehiculoService.eliminarPorPlaca(placa);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<Vehiculo>> obtenerVehiculosPorUserId(@RequestHeader ("Authorization") String authHeader,@PathVariable Integer user_id) {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Vehiculo> vehiculos = vehiculoService.findAllByUsuario_Id(user_id);
        return ResponseEntity.ok(vehiculos);
    }
}