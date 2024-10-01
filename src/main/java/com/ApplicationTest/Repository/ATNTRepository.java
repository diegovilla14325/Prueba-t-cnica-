package com.ApplicationTest.Repository;

import com.ApplicationTest.Entities.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ATNTRepository extends JpaRepository<Transaccion, Integer> {

    @Query(value = "SELECT * FROM applicationtest.Transaccion where fecha between :fechaIni and :fechaFin and idProveedor=3",nativeQuery = true)
    List<Transaccion> SalesByPeriod(
            @Param("fechaIni")LocalDateTime fechaIni,
            @Param("fechaFin") LocalDateTime fechaFin);

    @Query(value = "SELECT * FROM applicationtest.Transaccion where DATE(fecha)=curdate() and idProveedor=3",nativeQuery = true)
    List<Transaccion> SalesForToday();
}
