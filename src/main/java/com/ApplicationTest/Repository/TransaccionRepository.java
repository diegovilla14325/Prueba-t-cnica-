package com.ApplicationTest.Repository;

import com.ApplicationTest.Entities.PaquetesTiempoAire;
import com.ApplicationTest.Entities.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {
    //Consulta JPQL
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN TRUE ELSE FALSE END FROM Transaccion t WHERE t.numeroTelefono = :numeroTelefono AND t.idPaquete = :idPaquete AND t.fecha > :fecha")
    boolean existsByNumeroTelefonoAndIdPaqueteAndFechaAfter(
            @Param("numeroTelefono") Long numeroTelefono,
            @Param("idPaquete") Integer idPaquete,
            @Param("fecha") LocalDateTime fechaLimite
    );

}
