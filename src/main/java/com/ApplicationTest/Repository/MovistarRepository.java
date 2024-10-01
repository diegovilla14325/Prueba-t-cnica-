package com.ApplicationTest.Repository;

import com.ApplicationTest.Entities.Transaccion;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovistarRepository extends JpaRepository<Transaccion, Integer> {

    @Query(value = "Select * from applicationtest.Transaccion where fecha between :fechaIni and :fechaFin and idProveedor=2", nativeQuery = true)
     List<Transaccion> findByPeriod( @Param("fechaIni") LocalDateTime fechaIni,
            @Param("fechaFin") LocalDateTime fechaFin);

    @Query(value = "Select * from applicationtest.Transaccion where DATE(fecha)=CURDATE() and idProveedor=2", nativeQuery = true)
    List<Transaccion> findSalesForToday();
}
