package com.ApplicationTest.Service;

import com.ApplicationTest.Entities.DTOs.TotalVentaPorMontoDTO;
import com.ApplicationTest.Entities.DTOs.TotalVentasProveedorDTO;
import com.ApplicationTest.Entities.DTOs.VentasPorDiaDTO;
import com.ApplicationTest.Repository.ReportesRepository;
import com.ApplicationTest.Service.IService.IServiceReportes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceReportes implements IServiceReportes {


    private ReportesRepository reportesRepository;


    public ServiceReportes(ReportesRepository reportesRepository){
        this.reportesRepository=reportesRepository;
    }

    @Override
    public VentasPorDiaDTO SalesForDay() throws  Exception{
        try{
            return reportesRepository.getVentas();
        }
        catch (Exception ex){
            throw new Exception(ex.getMessage());
        }

    }

    @Override
    public List<TotalVentasProveedorDTO> SalesForCarrier() throws Exception {
        try{

            return reportesRepository.getVentasProveedor();
        }
        catch (Exception ex){
            throw new Exception(ex.getMessage());
        }

    }

    @Override
    public List<TotalVentaPorMontoDTO> SalesForAmount() throws Exception {
        try{
            return reportesRepository.getVentasPorMonto();
        }
        catch (Exception ex){
            throw new Exception(ex.getMessage());
        }

    }
}
