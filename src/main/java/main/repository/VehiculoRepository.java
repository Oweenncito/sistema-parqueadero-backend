package main.repository;

import main.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


// clase repository de vehiculos 
@Repository
public interface VehiculoRepository extends JpaRepository <Vehiculo, Integer> {

 Vehiculo findByPlaca(String placa);
 void deleteByPlaca(String placa);
 boolean existsByPlaca(String placa);
 List<Vehiculo> findAllByUsuario_Id(Integer usuarioId);
    
}
