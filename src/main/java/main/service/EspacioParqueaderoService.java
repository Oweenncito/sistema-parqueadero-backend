package main.service;

import main.model.EspacioParqueadero;
import java.util.List;
import main.model.Vehiculo;
import main.repository.EspacioParqueaderoRepository;
import main.repository.EspacioParqueaderoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author judav
 */
@Service
public class EspacioParqueaderoService {
    
    private final EspacioParqueaderoRepository espacioParqueaderoRepository;
    
    @Autowired
    public EspacioParqueaderoService(EspacioParqueaderoRepository espacioParqueaderoRepository){
        this.espacioParqueaderoRepository = espacioParqueaderoRepository;
        initSampleData();
    }

    private void initSampleData() {
        EspacioParqueadero espacio1 = new EspacioParqueadero(1, false, "carro");
        EspacioParqueadero espacio2 = new EspacioParqueadero(3, true, "moto");
        espacioParqueaderoRepository.save(espacio1);
        espacioParqueaderoRepository.save(espacio2);
    }
    
    public EspacioParqueadero guardarEspacio(EspacioParqueadero espacio) {
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

    public List<EspacioParqueadero> obtenerPorTipo(String tipoVehiculoPermitido) {
        return espacioParqueaderoRepository.findByTipoVehiculo(tipoVehiculoPermitido);
    }

    // Nuevo: Asignar vehículo a un espacio (ocupado)
    public EspacioParqueadero asignarVehiculo(int numeroEspacio, Vehiculo vehiculo) {
        return espacioParqueaderoRepository.asignarVehiculo(numeroEspacio, vehiculo);
    }
    
    // Nuevo: Liberar espacio (disponible)
    public EspacioParqueadero liberarEspacio(int numeroEspacio) {
        return espacioParqueaderoRepository.liberarEspacio(numeroEspacio);
    }
}