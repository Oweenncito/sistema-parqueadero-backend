package main.repository;

import main.model.EspacioParqueadero;
import java.util.List;
import main.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author judav
 */

//clase repository de parqueadero 
@Repository
public interface EspacioParqueaderoRepository extends JpaRepository <EspacioParqueadero, Integer>  {
    List<EspacioParqueadero> findAllByUsuario_Id(Integer usuarioId);
}
   


