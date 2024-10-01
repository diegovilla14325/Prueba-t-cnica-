package com.ApplicationTest.Controller;

import com.ApplicationTest.Entities.DTOs.TotalVentaPorMontoDTO;
import com.ApplicationTest.Entities.DTOs.TotalVentasProveedorDTO;
import com.ApplicationTest.Entities.DTOs.VentasPorDiaDTO;
import com.ApplicationTest.Service.ServiceReportes;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReportesController {

    private ServiceReportes serviceReportes;

    private static final Logger logger= Logger.getLogger(ReportesController.class);

    public ReportesController(ServiceReportes serviceReportes){
        this.serviceReportes=serviceReportes;
    }

    @GetMapping("/totalVentas")
    public ResponseEntity<VentasPorDiaDTO> SalesForDay(){
        try{
            VentasPorDiaDTO ventasPorDia=serviceReportes.SalesForDay();
            logger.info("Se ha generado el reporte.");
            return new ResponseEntity<>(ventasPorDia, HttpStatus.OK);

        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/totalVentasProveedor")
    public ResponseEntity<List<TotalVentasProveedorDTO>> SalesForCarrier(){
        try{
            List<TotalVentasProveedorDTO> totalVentasProveedors=serviceReportes.SalesForCarrier();
            logger.info("Se ha generado el reporte.");
            return new ResponseEntity<>(totalVentasProveedors,HttpStatus.OK);
        }
        catch (Exception ex){
            logger.error("Error al obtener la lista" + ex.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
    @GetMapping("/totalVentasPorMonto")
    public ResponseEntity<List<TotalVentaPorMontoDTO>> SalesForToday(){
        try{
            List<TotalVentaPorMontoDTO> totalVentaPorMontoDTOS= serviceReportes.SalesForAmount();
            logger.info("Se ha generado el reporte.");
            return new ResponseEntity<>(totalVentaPorMontoDTOS,HttpStatus.OK);
        }
        catch (Exception ex){
            logger.error("Error al obtener la lista" + " " + ex.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
