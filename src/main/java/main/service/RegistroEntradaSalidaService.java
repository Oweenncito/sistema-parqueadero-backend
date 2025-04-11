package main.service;

import main.model.RegistroEntradaSalida;
import main.model.Vehiculo;

/**
 *
 * @author judav
 */
import java.time.LocalDateTime;
import java.util.List;

import main.repository.RegistroEntradaSalidaRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistroEntradaSalidaService {

    private final RegistroEntradaSalidaRepository registroEntradaSalidaRepository;

    public RegistroEntradaSalidaService(RegistroEntradaSalidaRepository registroEntradaSalidaRepository) {
        this.registroEntradaSalidaRepository = registroEntradaSalidaRepository;
        initSampleData();
    }

    private void initSampleData() {
    // Vehículo de ejemplo con los 4 parámetros
    Vehiculo vehiculo1 = new Vehiculo("ABC123", "Moto", "Yamaha", "Rojo");

    // Fechas de entrada y salida usando LocalDateTime
    LocalDateTime horaEntrada = LocalDateTime.of(2025, 4, 10, 11, 41);
    LocalDateTime horaSalida = LocalDateTime.of(2025, 4, 10, 12, 0);

    RegistroEntradaSalida registro1 = new RegistroEntradaSalida(horaEntrada, horaSalida, 10000.0, vehiculo1);

    // Guardar el registro en el repositorio
    registroEntradaSalidaRepository.guardar(registro1);
}

    public RegistroEntradaSalida guardar(RegistroEntradaSalida registro) {
        return registroEntradaSalidaRepository.guardar(registro);
    }

    public RegistroEntradaSalida buscarPorId(String id) {
        return registroEntradaSalidaRepository.buscarPorId(id);
    }

    public List<RegistroEntradaSalida> listarTodos() {
        return registroEntradaSalidaRepository.listarTodos();
    }

    public boolean eliminarPorId(String id) {
        return registroEntradaSalidaRepository.eliminarPorId(id);
    }

    public List<RegistroEntradaSalida> buscarPorPlaca(String placa) {
        return registroEntradaSalidaRepository.buscarPorPlaca(placa);
    }

    public List<RegistroEntradaSalida> buscarPorRangoDeFechas(LocalDateTime desde, LocalDateTime hasta) {
        return registroEntradaSalidaRepository.buscarPorRangoDeFechas(desde, hasta);
    }
}
