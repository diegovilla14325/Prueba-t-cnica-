package com.ApplicationTest.Service.IService;

import com.ApplicationTest.Entities.DTOs.TransaccionDTO;
import com.ApplicationTest.Entities.DTOs.TransaccionSaveDTO;
import com.ApplicationTest.Entities.Transaccion;

import java.time.LocalDateTime;
import java.util.List;

public interface IServiceMovistar {
    public Transaccion save (TransaccionSaveDTO transaccionsaveDTO) throws  Exception ;

    public List<TransaccionDTO> findByPeriod(LocalDateTime fechaIni, LocalDateTime fechaFin) throws  Exception;

    public List<TransaccionDTO> findSalesForDay() throws Exception;

}
