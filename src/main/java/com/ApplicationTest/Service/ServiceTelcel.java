package com.ApplicationTest.Service;

import com.ApplicationTest.Entities.DTOs.TransaccionDTO;
import com.ApplicationTest.Entities.DTOs.TransaccionMapper;
import com.ApplicationTest.Entities.DTOs.TransaccionSaveDTO;
import com.ApplicationTest.Entities.DTOs.TransaccionValidator;
import com.ApplicationTest.Entities.PaquetesTiempoAire;
import com.ApplicationTest.Entities.Transaccion;
import com.ApplicationTest.Repository.PaquetesTiempoAireRepository;
import com.ApplicationTest.Repository.TelcelRepository;
import com.ApplicationTest.Repository.TransaccionRepository;
import com.ApplicationTest.Service.IService.IServiceTelcel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceTelcel implements IServiceTelcel {

    private TransaccionRepository transaccionRepository ;

    private PaquetesTiempoAireRepository paquetesTiempoAireRepository;

    private TelcelRepository telcelRepository;
    private final TransaccionValidator validator =new TransaccionValidator();

    public ServiceTelcel(TelcelRepository telcelRepository, PaquetesTiempoAireRepository paquetesTiempoAireRepository, TransaccionRepository transaccionRepository){
        this.telcelRepository=telcelRepository;
        this.paquetesTiempoAireRepository=paquetesTiempoAireRepository;
        this.transaccionRepository=transaccionRepository;
    }


    @Override
    public Transaccion save(TransaccionSaveDTO transaccionSaveDTO) throws Exception {
        try{
            if(!validator.isValid(transaccionSaveDTO)){
                throw new IllegalArgumentException("Datos no validos");
            }
            boolean isDuplicate = transaccionRepository.existsByNumeroTelefonoAndIdPaqueteAndFechaAfter(
                    transaccionSaveDTO.getNumeroTelefono(),
                    transaccionSaveDTO.getIdPaquete(),
                    LocalDateTime.now().minusMinutes(15)
            );
            if(isDuplicate){
                throw  new RuntimeException("Ya existe una compra con el mismo número y el mismo paquete. Por favor, espera 15 minutos.");
            }

            PaquetesTiempoAire paquetesTiempoAire= paquetesTiempoAireRepository.findPaquetesTiempoAireByIdPaqueteAndIdProveedor(transaccionSaveDTO.getIdProveedor(),transaccionSaveDTO.getIdPaquete())
                    .orElseThrow(() -> new RuntimeException("No existe ese paquete"));

            Transaccion transaccion = new Transaccion();
            transaccion.setFecha(LocalDateTime.now());
            transaccion.setDatosTarjeta(transaccionSaveDTO.getDatosTarjeta());
            transaccion.setEmail(transaccionSaveDTO.getEmail());
            transaccion.setCodigoPostal(transaccionSaveDTO.getCodigoPostal());
            transaccion.setNumeroTelefono(transaccionSaveDTO.getNumeroTelefono());
            transaccion.setMonto(paquetesTiempoAire.getMonto());
            transaccion.setIdProveedor(transaccionSaveDTO.getIdProveedor());
            transaccion.setIdPaquete(transaccionSaveDTO.getIdPaquete());
            return telcelRepository.save(transaccion);

        }
        catch (Exception ex){
            throw new Exception(ex.getMessage());
        }

    }
    public List<TransaccionDTO> getSalesForPeriod(LocalDateTime fechaIni, LocalDateTime fechaFin) throws Exception{
        try{
            List<Transaccion> transacciones = telcelRepository.findByFechaBetweenAndIdProveedor(fechaIni, fechaFin);
            List<TransaccionDTO> transaccionDTO = transacciones.stream().map
                    (transaccion -> TransaccionMapper.mapper.transaccionToTransaccionDto(transaccion)).collect(Collectors.toList());

            return  transaccionDTO;

        }
        catch (Exception ex){
            throw  new Exception(ex.getMessage());
        }
    }

    public List<TransaccionDTO> getSalesForDay() throws Exception{
        try{
            List<Transaccion> transaccions= telcelRepository.findSalesforday();
            List<TransaccionDTO> dto =transaccions.stream().map
                    (transaccion -> TransaccionMapper.mapper.transaccionToTransaccionDto(transaccion)).collect(Collectors.toList());
            return dto;
        }
        catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
