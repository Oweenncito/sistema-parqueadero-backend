package ApiVehiculos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;



@Repository
public class VehiculoRepository {

    private final Map<String, Vehiculo> baseDeDatos = new HashMap<>();
    
    public Vehiculo save(Vehiculo vehiculo) {
        baseDeDatos.put(vehiculo.getId(), vehiculo);
        return vehiculo;
    }
    
    public Vehiculo findById(String id) {
        return baseDeDatos.get(id);
    }

    public List<Vehiculo> findAll() {
        return new ArrayList<>(baseDeDatos.values());
    }

    public boolean deleteById(String id) {
        return baseDeDatos.remove(id) != null;
    }

    public boolean existsByPlaca(String placa) {
        return baseDeDatos.values().stream()
            .anyMatch(v -> v.getPlaca().equalsIgnoreCase(placa));
    }
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
    public Vehiculo findByPlaca(String placa) {
        return baseDeDatos.values().stream()
            .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
            .findFirst()
            .orElse(null);
    }
    public int count() {
        return baseDeDatos.size();
    }
    
}
