package com.ApplicationTest.Controller;

import com.ApplicationTest.Entities.DTOs.TransaccionDTO;
import com.ApplicationTest.Entities.DTOs.TransaccionSaveDTO;
import com.ApplicationTest.Entities.Transaccion;
import com.ApplicationTest.Service.ServiceATNT;
import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/AT&T")
public class ATNTController {

    private ServiceATNT serviceATNT;
    private static final Logger logger=Logger.getLogger(ATNTController.class);

    public ATNTController(ServiceATNT serviceATNT){
        this.serviceATNT=serviceATNT;
    }

    @PostMapping("/recarga-compra-ahora")
    public ResponseEntity<Transaccion> save(@RequestBody TransaccionSaveDTO transaccionSaveDTO){
        try{
            logger.info("Empezara el proceso de compra" + " Fecha: " + LocalDateTime.now() + " Numero Telefonico: " + transaccionSaveDTO.getNumeroTelefono());
            Transaccion transaccion= serviceATNT.save(transaccionSaveDTO);
            logger.info("Se ha realizado la compra" + " Fecha: " + LocalDateTime.now() + " Numero Telefonico: " + transaccion.getNumeroTelefono() + " Id Transaccion: " + transaccion.getIdTransaccion());
            return new ResponseEntity<>(transaccion, HttpStatus.OK);
        }
        catch (Exception ex){
            logger.error("No se puedo realizar la compra" + " Fecha: " + LocalDateTime.now() + " Numero Telefonico: " + transaccionSaveDTO.getNumeroTelefono() + " Error: "+ ex.getMessage());
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/VentasPorPeriodo")
    public ResponseEntity<List<TransaccionDTO>> findSalesByPeriod(
         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fechaIni,
         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fechaFin){
        try{
            return new ResponseEntity<>(serviceATNT.getSalesForPeriod(fechaIni,fechaFin),HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/VentasDelDia")
    public ResponseEntity<List<TransaccionDTO>> findSalesForToday(){
        try {
            return new ResponseEntity<>(serviceATNT.getSalesForDay(),HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
