package com.ApplicationTest.Service.IService;

import com.ApplicationTest.Entities.DTOs.TransaccionDTO;
import com.ApplicationTest.Entities.DTOs.TransaccionSaveDTO;
import com.ApplicationTest.Entities.Transaccion;

import java.time.LocalDateTime;
import java.util.List;

public interface IServiceATNT {

    Transaccion save(TransaccionSaveDTO transaccionSaveDTO) throws Exception;

    List<TransaccionDTO> getSalesForPeriod(LocalDateTime fechaIni, LocalDateTime fechaFin) throws  Exception;

    List<TransaccionDTO> getSalesForDay() throws  Exception;
}
