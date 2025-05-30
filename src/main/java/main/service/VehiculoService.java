package main.service;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import main.model.Vehiculo;
import main.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//clase service de vehiculo
@Service
public class VehiculoService {

    //llamamos al repository de vehiculo 
    private final VehiculoRepository vehiculoRepository;


    /*lo pasamos por parametro en el constructor y lo inicializamos para poder interactuar entre el repositorio 
      y el servicio
    */
    @Autowired
    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;

    }
    public Vehiculo registrarVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    // Obtener todos los vehículos
    public List<Vehiculo> obtenerTodosLosVehiculos() {
        return vehiculoRepository.findAll();
    }

    // Buscar por ID
    public Optional<Vehiculo> buscarPorId(Integer id) {
        return vehiculoRepository.findById(id);

    }

    public List<Vehiculo> findAllByUsuario_Id(Integer id) {
        return vehiculoRepository.findAllByUsuario_Id(id);
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
    public void eliminarPorId(Integer id) {
        if (!vehiculoRepository.existsById(id)) {
            throw new EntityNotFoundException("No se encontró el vehículo con ID: " + id);
        }
        vehiculoRepository.deleteById(id);

    }

    public boolean existsByPlaca(String placa) {
        return vehiculoRepository.existsByPlaca(placa);
    }

    // Eliminar por placa
    public void eliminarPorPlaca(String placa) {
        vehiculoRepository.deleteByPlaca(placa);
    }

    // Contar vehículos
    public int contarVehiculos() {
        return (int) vehiculoRepository.count();
    }

}