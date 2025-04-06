package com.example.sistema_parqueadero_backend;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
        initSampleData();
        }
    
    public void initSampleData() {
        Vehiculo vehiculo1 = new Vehiculo("TBI67G", "MOTO", "AKT", "NEGRO MATE");
        Vehiculo vehiculo2 = new Vehiculo("ALV17E", "MOTO", "UNI-K", "NEGRO");
        Vehiculo vehiculo3 = new Vehiculo("DGH45E",  "CARRO",  "CHEVROLET", "BLANCO");
        Vehiculo vehiculo4 = new Vehiculo("FTJ67G", "CARRO", "TWINGO", "GRIS");
       
        vehiculoRepository.save(vehiculo1);
        vehiculoRepository.save(vehiculo2);
        vehiculoRepository.save(vehiculo3);
        vehiculoRepository.save(vehiculo4);

    }

    // Registrar vehículo
    public Vehiculo registrarVehiculo(Vehiculo vehiculo) {
        if (vehiculoRepository.existsByPlaca(vehiculo.getPlaca())) {
            throw new IllegalArgumentException("Ya existe un vehículo con la placa: " + vehiculo.getPlaca());
        }
        return vehiculoRepository.save(vehiculo);
    }

    // Obtener todos los vehículos
    public List<Vehiculo> obtenerTodosLosVehiculos() {
        return vehiculoRepository.findAll();
    }

    // Buscar por ID
    public Vehiculo buscarPorId(String id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id);
        if (vehiculo == null) {
            throw new NoSuchElementException("Vehículo no encontrado con ID: " + id);
        }
        return vehiculo;
    }

    // Buscar por placa
    public Vehiculo buscarPorPlaca(String placa) {
        Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa);
        if (vehiculo == null) {
            throw new NoSuchElementException("Vehículo no encontrado con placa: " + placa);
        }
        return vehiculo;
    }

    // Eliminar por ID
    public void eliminarPorId(String id) {
        boolean eliminado = vehiculoRepository.deleteById(id);
        if (!eliminado) {
            throw new NoSuchElementException("No se pudo eliminar. Vehículo no encontrado con ID: " + id);
        }
    }

    // Eliminar por placa
    public void eliminarPorPlaca(String placa) {
        boolean eliminado = vehiculoRepository.eliminarPorPlaca(placa);
        if (!eliminado) {
            throw new NoSuchElementException("No se pudo eliminar. Vehículo no encontrado con placa: " + placa);
        }
    }

    // Contar vehículos
    public int contarVehiculos() {
        return vehiculoRepository.count();
    }
    
}