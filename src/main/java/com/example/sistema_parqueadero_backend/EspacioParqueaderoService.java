package com.example.sistema_parqueadero_backend;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author judav
 */
@Service
public class EspacioParqueaderoService {
    
    private final EspacioParqueaderoRepository espacioparqueaderorepository;
    
    @Autowired
    public EspacioParqueaderoService (EspacioParqueaderoRepository espacioparqueaderorepository){
        this.espacioparqueaderorepository = espacioparqueaderorepository;
        initSampleData();
    }

    private void initSampleData() {
        EspacioParqueadero espacio1 = new EspacioParqueadero (1, false, "carro");
        EspacioParqueadero espacio2 = new EspacioParqueadero (3, true, "moto");
        espacioparqueaderorepository.save(espacio1);
        espacioparqueaderorepository.save(espacio2);
    }
    
      public EspacioParqueadero guardarEspacio(EspacioParqueadero espacio) {
        return espacioparqueaderorepository.save(espacio);
    }
      
      public EspacioParqueadero buscarPorNumero(int numero) {
        return espacioparqueaderorepository.findById(numero);
    }

    public List<EspacioParqueadero> obtenerTodos() {
        return espacioparqueaderorepository.findAll();
    }

    public boolean eliminarPorNumero(int numero) {
        return espacioparqueaderorepository.deleteById(numero);
    }

    public List<EspacioParqueadero> obtenerDisponibles() {
        return espacioparqueaderorepository.findDisponibles();
    }

   public List<EspacioParqueadero> obtenerPorTipo(String tipoVehiculoPermitido) {
    return espacioparqueaderorepository.findByTipoVehiculo(tipoVehiculoPermitido);
   }
}
