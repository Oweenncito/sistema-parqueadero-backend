package main.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.model.Vehiculo;
import org.springframework.stereotype.Repository;


// clase repository de vehiculos 
@Repository
public class VehiculoRepository {

    
    //base de datos simulada con hashmap 
    private final Map<String, Vehiculo> baseDeDatos = new HashMap<>();
    
    
    //este metodo guarda un vehiculo
    public Vehiculo save(Vehiculo vehiculo) {
        baseDeDatos.put(vehiculo.getId(), vehiculo);
        return vehiculo;
    }
    
    
    //este metodo busca un vehiculo por su id
    public Vehiculo findById(String id) {
        return baseDeDatos.get(id);
    }

    //este metodo devuelve una lista de todos los vehiculos 
    public List<Vehiculo> findAll() {
        return new ArrayList<>(baseDeDatos.values());
    }

    
    //este metodo elimina un vehiculo por su id 
    public boolean deleteById(String id) {
        return baseDeDatos.remove(id) != null;
    }

    
    //este metodo verifica si existe ya un vehiculo con la placa que entro por parametro  
    public boolean existsByPlaca(String placa) {
        return baseDeDatos.values().stream().anyMatch(v -> v.getPlaca() != null && v.getPlaca().equalsIgnoreCase(placa));
    }
    
    //este metodo elimina un vehiculo por su placa 
    public boolean eliminarPorPlaca(String placa) {
        String idAEliminar = null;

        for (Vehiculo vehiculo : baseDeDatos.values()) {
            if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                idAEliminar = vehiculo.getId();
                break;
            }
        }
        if (idAEliminar != null) {
            baseDeDatos.remove(idAEliminar);
            return true; 
        }
        return false; 
}
    
    //este metodo busca un vehiculo por la placa que entra por parametro
    public Vehiculo findByPlaca(String placa) {
        return baseDeDatos.values().stream()
            .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
            .findFirst()
            .orElse(null);
    }
    
    
    //este metodo muestra la cantidad de vehiculos que hay 
    public int count() {
        return baseDeDatos.size();
    }
    
}
