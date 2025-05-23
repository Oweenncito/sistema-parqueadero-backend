package main.service;

import main.model.EspacioParqueadero;
import java.util.List;
import main.model.Vehiculo;
import main.repository.EspacioParqueaderoRepository;
import main.repository.EspacioParqueaderoRepository;
import main.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author judav
 */
@Service
public class EspacioParqueaderoService {
    
 
    private final EspacioParqueaderoRepository espacioParqueaderoRepository;
    private VehiculoRepository vehiculoRepository;
    
   @Autowired
    public EspacioParqueaderoService(EspacioParqueaderoRepository espacioParqueaderoRepository){
        this.espacioParqueaderoRepository = espacioParqueaderoRepository;
        initSampleData();
       
    }

    
    public EspacioParqueadero guardarEspacio(EspacioParqueadero espacio) {
        System.out.println("el numero que entro es: " + espacio.getNumero());
        System.out.println("el espacio que entro es:" + espacio.isDisponible());
        return espacioParqueaderoRepository.save(espacio);
    }
      
    public EspacioParqueadero buscarPorNumero(int numero) {
        return espacioParqueaderoRepository.findById(numero);
    }

    public List<EspacioParqueadero> obtenerTodos() {
        return espacioParqueaderoRepository.findAll();
    }

    public boolean eliminarPorNumero(int numero) {
        return espacioParqueaderoRepository.deleteById(numero);
    }

    public List<EspacioParqueadero> obtenerDisponibles() {
        return espacioParqueaderoRepository.findDisponibles();
    }

    // Nuevo: Asignar vehículo a un espacio (ocupado)
    public EspacioParqueadero asignarVehiculo(int numeroEspacio, Vehiculo vehiculo) {
        return espacioParqueaderoRepository.asignarVehiculo(numeroEspacio, vehiculo);
    }
    
    // Nuevo: Liberar espacio (disponible)
    public EspacioParqueadero liberarEspacio(int numeroEspacio) {
        return espacioParqueaderoRepository.liberarEspacio(numeroEspacio);
    }
          
    public void initSampleData(){
        
        EspacioParqueadero espacioUno = new EspacioParqueadero(1, false);
        EspacioParqueadero espacioDos = new EspacioParqueadero(2, false);
        
        espacioParqueaderoRepository.save(espacioUno);
        espacioParqueaderoRepository.save(espacioDos);
        
    }
    
    
    public EspacioParqueadero ingresarVehiculoEnEspacio(int idEspacio, Vehiculo vehiculo) {
    // Validar que el vehículo no sea null
    if (vehiculo == null) {
        throw new IllegalArgumentException("El vehículo no puede ser nulo");
    }

   EspacioParqueadero espacio = espacioParqueaderoRepository.findById(idEspacio);
   if (espacio == null) {
    throw new RuntimeException("El espacio no existe");
   }
    if (espacio.getVehiculo() != null) {
        throw new RuntimeException("El espacio ya está ocupado");
    }

    if (vehiculoRepository.existsByPlaca(vehiculo.getPlaca())) {
        throw new RuntimeException("Ya hay un vehículo con esa placa");
    }

    vehiculoRepository.save(vehiculo);
    espacio.setVehiculo(vehiculo);
    espacioParqueaderoRepository.save(espacio); // No olvidar guardar el espacio actualizado
    
    return espacio;
}
}