package main.repository;

import main.model.EspacioParqueadero;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.model.Vehiculo;
import org.springframework.stereotype.Repository;

/**
 *
 * @author judav
 */

//clase repository de parqueadero 
@Repository
public class EspacioParqueaderoRepository {
   
    //esta es la base de datos donde se guardan los espacios del parqueadero con los vehiculos
    private final Map<Integer, EspacioParqueadero> baseDeDatos = new HashMap<>();
    
    // Guardar o actualizar un espacio (ahora actualiza si ya existe)
    public EspacioParqueadero save(EspacioParqueadero espacio){
        System.out.println("el numero que entro es: " + espacio.getNumero());
        System.out.println("el espacio que entro es:" + espacio.isDisponible());
         baseDeDatos.put(espacio.getNumero(), espacio);
        return espacio;
    }
    
    // este metodo busca el espacio del parqueadero por su numero
    public EspacioParqueadero findById(int numero){
        return baseDeDatos.get(numero);
    }
    
    // este metodo Lista todos los espacios en el parqueadero 
    public List<EspacioParqueadero> findAll(){
        return new ArrayList<>(baseDeDatos.values());
    }
    
    // este metodo Elimina un espacio en el parqueadero por su número 
    public boolean deleteById(int numero){
        return baseDeDatos.remove(numero) != null;
    }
    
    // este metodo Busca los  espacios disponibles que no tienen un vehiculo dentro de el
    public List<EspacioParqueadero> findDisponibles(){
       List<EspacioParqueadero> disponibles = new ArrayList<>();
       for(EspacioParqueadero ep : baseDeDatos.values()){
           if(ep.isDisponible()){
               disponibles.add(ep);
           }
       }
       return disponibles;
    }
    
    // Buscar espacios por tipo de vehículo permitido
 
    
    // Asignar vehículo a un espacio (marcar ocupado)
    public EspacioParqueadero asignarVehiculo(int numeroEspacio, Vehiculo vehiculo) {
        EspacioParqueadero espacio = baseDeDatos.get(numeroEspacio);
        if (espacio != null && espacio.isDisponible()) {
            espacio.setVehiculo(vehiculo);
            espacio.setDisponible(false);
            baseDeDatos.put(numeroEspacio, espacio);
            return espacio;
        }
        return null; // espacio no existe o ya ocupado
    }
    
    // Liberar espacio (quitar vehículo y marcar disponible)
   public EspacioParqueadero liberarEspacio(int numeroEspacio) {
        EspacioParqueadero espacio = baseDeDatos.get(numeroEspacio);
        if (espacio != null && !espacio.isDisponible()) {
            espacio.setVehiculo(null);
            espacio.setDisponible(true);
            baseDeDatos.put(numeroEspacio, espacio);
            return espacio;
        }
        return null; // espacio no existe o ya está libre
    }
}
