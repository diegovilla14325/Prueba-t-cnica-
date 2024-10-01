package com.ApplicationTest.Repository;

import com.ApplicationTest.Entities.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TelcelRepository extends JpaRepository<Transaccion, Integer> {
    //Query native
    @Query(value = "SELECT * FROM applicationtest.transaccion WHERE fecha BETWEEN :fechaIni AND :fechaFin AND idProveedor =1", nativeQuery = true)
    List<Transaccion> findByFechaBetweenAndIdProveedor(
            @Param("fechaIni") LocalDateTime fechaIni,
            @Param("fechaFin") LocalDateTime fechaFin
    );

    @Query(value = "SELECT * FROM applicationtest.Transaccion WHERE DATE(fecha)=curdate() and idProveedor=1", nativeQuery = true)
    List<Transaccion> findSalesforday();
}
