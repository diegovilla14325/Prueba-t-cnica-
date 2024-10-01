package com.ApplicationTest.Controller;

import com.ApplicationTest.Entities.DTOs.TransaccionDTO;
import com.ApplicationTest.Entities.DTOs.TransaccionSaveDTO;
import com.ApplicationTest.Entities.Transaccion;
import com.ApplicationTest.Service.ServiceMovistar;
import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/movistar")
public class MovistarController {

    private ServiceMovistar serviceMovistar;

    private  static  final Logger logger= Logger.getLogger(MovistarController.class);

    public MovistarController(ServiceMovistar serviceMovistar){
        this.serviceMovistar=serviceMovistar;
    }
    @PostMapping("/recarga-compra-ahora")
    public ResponseEntity<Transaccion> save(@RequestBody TransaccionSaveDTO transaccionSaveDTO){
        try{
        logger.info("Se esta iniciando el proceso de hacer la recarga " + " Fecha: " + LocalDateTime.now() + " Numero Telefonico: " + transaccionSaveDTO.getNumeroTelefono());
            Transaccion transaccion=serviceMovistar.save(transaccionSaveDTO);
            logger.info("Se realizo la compra del paquete" + " Fecha: " + LocalDateTime.now() + " Numero Telefonico: " + transaccion.getNumeroTelefono() + " Id Transaccion: " + transaccion.getIdTransaccion());
            return new ResponseEntity<>(transaccion,HttpStatus.OK);
        }
        catch (Exception ex){
         logger.error("Hubo un error al realizar la compra" + " Fecha: " + LocalDateTime.now() + " Numero Telefonico: " + transaccionSaveDTO.getNumeroTelefono() + " Error: " + ex.getMessage());
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/VentasPorPeriodo")
    public ResponseEntity<List<TransaccionDTO>> findSalesByperiod(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fechaIni,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fechaFin) {
        try{
        return new ResponseEntity<>(serviceMovistar.findByPeriod(fechaIni,fechaFin),HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
    @GetMapping("/VentasDelDia")
    public ResponseEntity<List<TransaccionDTO>> findSalesForToday(){
        try{
            return  new ResponseEntity<>(serviceMovistar.findSalesForDay(),HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
}
