/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.service;

import java.util.List;
import main.model.EspacioParqueadero;
import main.model.Vehiculo;
import main.repository.EspacioParqueaderoRepository;
import main.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author owenf
 */
@Service
public class ParqueaderoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private EspacioParqueaderoRepository espacioParqueaderoRepository;

    public String ingresarVehiculoEnEspacio(Vehiculo vehiculo, int idEspacio) {
        EspacioParqueadero espacio = espacioParqueaderoRepository.findById(idEspacio);

        if (espacio == null) {
            return "El espacio no existe.";
        }

        if (espacio.getVehiculo()!= null) {
            return "El espacio ya está ocupado.";
        }

        if (vehiculoRepository.existsByPlaca(vehiculo.getPlaca())) {
            return "Ya hay un vehículo con esa placa.";
        }

        vehiculoRepository.save(vehiculo);
        espacio.setVehiculo(vehiculo);
        return "Vehículo ingresado correctamente en el espacio.";
    }

    public String retirarVehiculoPorPlaca(String placa) {
        Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa);

        if (vehiculo == null) {
            return "Vehículo no encontrado.";
        }

        List<EspacioParqueadero> espacios = espacioParqueaderoRepository.findAll();
        for (EspacioParqueadero espacio : espacios) {
            if (espacio.getVehiculo()!= null &&
                espacio.getVehiculo().getPlaca().equalsIgnoreCase(placa)) {
                espacio.setVehiculo(null);
                vehiculoRepository.eliminarPorPlaca(placa);
                return "Vehículo retirado y espacio liberado correctamente.";
            }
        }

        return "Vehículo no estaba asignado a ningún espacio.";
    }
}

