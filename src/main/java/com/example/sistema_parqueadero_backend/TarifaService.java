package com.example.sistema_parqueadero_backend;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author judav
 */
@Service
public class TarifaService {
    
    private final TarifaRepository tarifarepository;
      
    @Autowired
    public TarifaService(TarifaRepository tarifarepository){
        this.tarifarepository = tarifarepository;
        initSampleData();
    }       

    private void initSampleData() {
        Tarifa tarifaCarro = new Tarifa ("carro", 2000);
        Tarifa tarifaMoto = new Tarifa ("Moto", 1500);
        
        tarifarepository.save(tarifaCarro);
        tarifarepository.save(tarifaMoto);
    }
    
     public Tarifa guardarTarifa(Tarifa tarifa) {
        if (tarifa.getValorHora() < 0) {
            throw new IllegalArgumentException("El valor por hora no puede ser negativo.");
        }
        return tarifarepository.save(tarifa);
    }

    public Tarifa obtenerTarifaPorTipo(String tipoVehiculo) {
        Tarifa tarifa = tarifarepository.findByTipoVehiculo(tipoVehiculo);
        if (tarifa == null) {
            throw new IllegalArgumentException("No existe una tarifa para el tipo de vehículo: " + tipoVehiculo);
        }
        return tarifa;
    }

    public boolean eliminarTarifa(String tipoVehiculo) {
        if (!tarifarepository.existsByTipoVehiculo(tipoVehiculo)) {
            throw new IllegalArgumentException("No existe una tarifa para eliminar.");
        }
        return tarifarepository.deleteByTipoVehiculo(tipoVehiculo);
    }

    public List<Tarifa> listarTarifas() {
        return tarifarepository.findAll();
    }

    public boolean existeTarifa(String tipoVehiculo) {
        return tarifarepository.existsByTipoVehiculo(tipoVehiculo);
    }   
}
