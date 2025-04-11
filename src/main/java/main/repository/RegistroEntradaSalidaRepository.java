package main.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.model.RegistroEntradaSalida;
import org.springframework.stereotype.Repository;

@Repository
public  class RegistroEntradaSalidaRepository{

private Map<String, RegistroEntradaSalida> baseDeDatos = new HashMap<>();

// Guardar un nuevo registro
public RegistroEntradaSalida guardar(RegistroEntradaSalida registro) {
    baseDeDatos.put(registro.getId(), registro);
    return registro;
}

// Buscar un registro por su ID
public RegistroEntradaSalida buscarPorId(String id) {
    return baseDeDatos.get(id);
}

// Listar todos los registros
public List<RegistroEntradaSalida> listarTodos() {
    return new ArrayList<>(baseDeDatos.values());
}

// Eliminar un registro por ID
public boolean eliminarPorId(String id) {
    return baseDeDatos.remove(id) != null;
}

// (Opcional) Buscar por placa del vehículo
public List<RegistroEntradaSalida> buscarPorPlaca(String placa) {
    List<RegistroEntradaSalida> resultados = new ArrayList<>();
    for (RegistroEntradaSalida registro : baseDeDatos.values()) {
        if (registro.getVehiculo() != null && registro.getVehiculo().getPlaca().equalsIgnoreCase(placa)) {
            resultados.add(registro);
        }
    }
    return resultados;
}
public List<RegistroEntradaSalida> buscarPorRangoDeFechas(LocalDateTime desde, LocalDateTime hasta) {
    List<RegistroEntradaSalida> resultados = new ArrayList<>();
    for (RegistroEntradaSalida registro : baseDeDatos.values()) {
        if (registro.getHoraEntrada() != null &&
            (registro.getHoraEntrada().isAfter(desde) || registro.getHoraEntrada().isEqual(desde)) &&
            (registro.getHoraEntrada().isBefore(hasta) || registro.getHoraEntrada().isEqual(hasta))) {
            resultados.add(registro);
        }
    }
    return resultados;
}
}
