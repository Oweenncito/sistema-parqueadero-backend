package com.example.sistema_parqueadero_backend;

/**
 *
 * @author judav
 */
import java.util.*;
import org.springframework.stereotype.Repository;

@Repository
public class TarifaRepository {

    private final Map<String, Tarifa> baseDeDatos = new HashMap<>();

    // Guardar o actualizar tarifa
    public Tarifa save(Tarifa tarifa) {
        baseDeDatos.put(tarifa.getTipoVehiculo().toLowerCase(), tarifa); // clave: tipoVehiculo
        return tarifa;
    }

    // Buscar por tipo de vehículo
    public Tarifa findByTipoVehiculo(String tipoVehiculo) {
        return baseDeDatos.get(tipoVehiculo.toLowerCase());
    }

    // Eliminar por tipo de vehículo
    public boolean deleteByTipoVehiculo(String tipoVehiculo) {
        return baseDeDatos.remove(tipoVehiculo.toLowerCase()) != null;
    }

    // Verificar si existe una tarifa para ese tipo
    public boolean existsByTipoVehiculo(String tipoVehiculo) {
        return baseDeDatos.containsKey(tipoVehiculo.toLowerCase());
    }

    // Obtener todas las tarifas
    public List<Tarifa> findAll() {
        return new ArrayList<>(baseDeDatos.values());
    }

    // Contar tarifas registradas
    public int count() {
        return baseDeDatos.size();
    }
}
