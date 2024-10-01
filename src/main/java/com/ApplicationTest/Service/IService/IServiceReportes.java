package com.ApplicationTest.Service.IService;

import com.ApplicationTest.Entities.DTOs.TotalVentaPorMontoDTO;
import com.ApplicationTest.Entities.DTOs.TotalVentasProveedorDTO;
import com.ApplicationTest.Entities.DTOs.VentasPorDiaDTO;

import java.util.List;

public interface IServiceReportes {

    VentasPorDiaDTO SalesForDay() throws Exception;
    List<TotalVentasProveedorDTO> SalesForCarrier() throws Exception;
    List<TotalVentaPorMontoDTO> SalesForAmount() throws Exception;
}
