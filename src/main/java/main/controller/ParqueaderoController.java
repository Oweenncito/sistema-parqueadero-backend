/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.controller;

import main.model.Vehiculo;
import main.service.ParqueaderoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author owenf
 */
@RestController
@RequestMapping("/api/parqueadero")
public class ParqueaderoController {

    @Autowired
    private ParqueaderoService parqueaderoService;

    @PostMapping("/ingresar/{idEspacio}")
    public String ingresarVehiculo(@RequestBody Vehiculo vehiculo, @PathVariable int idEspacio) {
        return parqueaderoService.ingresarVehiculoEnEspacio(vehiculo, idEspacio);
    }

    @DeleteMapping("/retirar/{placa}")
    public String retirarVehiculo(@PathVariable String placa) {
        return parqueaderoService.retirarVehiculoPorPlaca(placa);
    }
}
