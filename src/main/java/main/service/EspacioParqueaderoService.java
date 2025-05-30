package main.service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import main.model.EspacioParqueadero;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import main.model.Usuario;
import main.model.Vehiculo;
import main.repository.EspacioParqueaderoRepository;
import main.repository.EspacioParqueaderoRepository;
import main.repository.UsuarioRepository;
import main.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author judav
 */
@Service
public class EspacioParqueaderoService {

    private final EspacioParqueaderoRepository espacioParqueaderoRepository;
    private final VehiculoRepository vehiculoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public EspacioParqueaderoService(EspacioParqueaderoRepository espacioParqueaderoRepository, VehiculoRepository vehiculoRepository, UsuarioRepository usuarioRepository) {
        this.espacioParqueaderoRepository = espacioParqueaderoRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public EspacioParqueadero guardarEspacio(EspacioParqueadero espacio) {
        return espacioParqueaderoRepository.save(espacio);
    }

    public Optional<EspacioParqueadero> buscarPorNumero(int numero) {
        return espacioParqueaderoRepository.findById(numero);
    }

    public List<EspacioParqueadero> obtenerTodos() {
        return espacioParqueaderoRepository.findAll();
    }

    public boolean eliminarPorNumero(Integer id) {
        Optional<EspacioParqueadero> espacio = espacioParqueaderoRepository.findById(id);
        if (espacio.isPresent()) {
            espacioParqueaderoRepository.deleteById(id);
            return true;
        }
        return false;
    }
    // Nuevo: Liberar espacio (disponible)
    public EspacioParqueadero liberarEspacio(Integer user_id, int numero) {
        List<EspacioParqueadero> espacio = espacioParqueaderoRepository.findAllByUsuario_Id(user_id);
        for (EspacioParqueadero espacioParqueadero : espacio) {
            if (espacioParqueadero.getNumero() == numero) {
                espacioParqueadero.setDisponible(true);
                espacioParqueadero.setVehiculo(null);
                return espacioParqueaderoRepository.save(espacioParqueadero);
            }
        }
        return null;
    }

    public List<EspacioParqueadero> findAllByUserID(Integer id) {
        List<EspacioParqueadero> espacios = espacioParqueaderoRepository.findAllByUsuario_Id(id);
        Usuario user = usuarioRepository.findById(id).get();
        if (espacios.size() != 16) {
            for (int i = espacios.size(); i < 16; i++) {
                EspacioParqueadero espacio = new EspacioParqueadero();
                espacio.setNumero(i + 1);
                espacio.setDisponible(true);
                espacio.setVehiculo(null);
                espacio.setUsuario(user);
                espacioParqueaderoRepository.save(espacio);
            }
            return espacioParqueaderoRepository.findAllByUsuario_Id(id);
        }
        return espacios;
    }

    public EspacioParqueadero ingresarVehiculoEnEspacio(Integer userId, int numero, Vehiculo vehiculo) {
        Optional<Usuario> user = usuarioRepository.findById(userId);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        vehiculo.setUsuario(user.get());
        vehiculo.setHoraEntrada(LocalDateTime.now());
        List<EspacioParqueadero> espacios = espacioParqueaderoRepository.findAllByUsuario_Id(userId);
        for (EspacioParqueadero espacio : espacios) {
            if (espacio.getNumero() == numero) {
                espacio.setVehiculo(vehiculo);
                espacio.setDisponible(false);
                vehiculoRepository.save(vehiculo);
                return espacioParqueaderoRepository.save(espacio);
            }
        }
        return null;
    }
}
