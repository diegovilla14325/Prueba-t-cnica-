package com.ApplicationTest.Controller;

import com.ApplicationTest.Entities.DTOs.TransaccionDTO;
import com.ApplicationTest.Entities.DTOs.TransaccionSaveDTO;
import com.ApplicationTest.Entities.Transaccion;
import com.ApplicationTest.Service.ServiceTelcel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/telcel")
public class TelcelController {
    private  static final Logger logger =Logger.getLogger(TelcelController.class);
    @Autowired
    private ServiceTelcel serviceTelcel;

    public  TelcelController (ServiceTelcel serviceTelcel){
        this.serviceTelcel=serviceTelcel;
    }

    @PostMapping("/recarga-compra-ahora")
    public ResponseEntity<Transaccion> save(@RequestBody TransaccionSaveDTO transaccion){
        try{
            logger.info("Inicia el proceso de hacer la recarga" + "Fecha: " + LocalDateTime.now() + " Numero Telefono: " +transaccion.getNumeroTelefono());
            Transaccion transaccion1 =serviceTelcel.save(transaccion);
            logger.info("Se ha completado la compra" + "Fecha: " + LocalDateTime.now()+ " Numero Telefono: " +transaccion.getNumeroTelefono() + " Id transaccion: " +transaccion1.getIdTransaccion());
            return new ResponseEntity<>(transaccion1, HttpStatus.OK);

        }
        catch (Exception ex){
            logger.error("Error durante el proceso de compra: " + "Fecha: " + LocalDateTime.now() + " Numero Telefono: " + transaccion.getNumeroTelefono() + " Error: " + ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/VentasPorPeriodo")
    public ResponseEntity<List<TransaccionDTO>> getSalesForPeriod(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fechaIni,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fechaFin){
        try{
            return new ResponseEntity<>(serviceTelcel.getSalesForPeriod(fechaIni,fechaFin),HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


    }
    @GetMapping("/VentasDelDia")
    public ResponseEntity<List<TransaccionDTO>> getSaleForDay(){
        try{
            return new ResponseEntity<>(serviceTelcel.getSalesForDay(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
