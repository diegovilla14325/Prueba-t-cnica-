package com.ApplicationTest.Repository;

import com.ApplicationTest.Entities.PaquetesTiempoAire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaquetesTiempoAireRepository extends JpaRepository<PaquetesTiempoAire, Integer> {

    @Query("SELECT t FROM PaquetesTiempoAire t where t.idProveedor= :idProveedor AND t.idPaquete= :idPaquete")
    Optional<PaquetesTiempoAire> findPaquetesTiempoAireByIdPaqueteAndIdProveedor(
            @Param("idProveedor") Integer idProveedor,
            @Param("idPaquete") Integer idPaquete
    );

}
