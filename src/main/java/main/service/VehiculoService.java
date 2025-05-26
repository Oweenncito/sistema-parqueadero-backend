package main.service;

import java.util.List;
import java.util.NoSuchElementException;

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
    
    
    //este metodo genera unos vehiculos predeterminados ya en la base de datos 
   

    // este metodo Registra un vehículo y lo guarda en el repository 
    public Vehiculo registrarVehiculo(Vehiculo vehiculo) {
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