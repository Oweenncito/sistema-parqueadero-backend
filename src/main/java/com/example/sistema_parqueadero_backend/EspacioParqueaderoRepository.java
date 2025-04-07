package com.example.sistema_parqueadero_backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author judav
 */

@Repository
public class EspacioParqueaderoRepository {
   
    private final Map<Integer, EspacioParqueadero> baseDeDatos = new HashMap<>();
    
    //Guardar y actualizar un espacio
    public EspacioParqueadero save(EspacioParqueadero espacio){
        baseDeDatos.put(espacio.getNumero(), espacio);
        return espacio;
    }
    
    //buscar por numero (id)
    public EspacioParqueadero findById(int numero){
        return baseDeDatos.get(numero);
    }
    
    public List<EspacioParqueadero> findAll(){
        return new ArrayList<>(baseDeDatos.values());
    }
    
    //Eliminar por numero (ID)
    public boolean deleteById(int numero){
        return baseDeDatos.remove(numero) != null;
    }
    
    public List<EspacioParqueadero> findDisponibles(){
       List<EspacioParqueadero> disponibles = new ArrayList<>();
       for(EspacioParqueadero ep : baseDeDatos.values()){
           if(ep.isDisponible()){
               disponibles.add(ep);
           }
       }
       return disponibles;
    }
    
   public List<EspacioParqueadero> findByTipoVehiculo(String tipoVehiculo) {
    List<EspacioParqueadero> lista = new ArrayList<>();
    for (EspacioParqueadero espacio : baseDeDatos.values()) {
        if (espacio.getTipoVehiculoPermitido().equalsIgnoreCase(tipoVehiculo)) {
            lista.add(espacio);
        }
    }
    return lista;
}


}
