package com.ApplicationTest.Service;

import com.ApplicationTest.Entities.DTOs.TransaccionDTO;
import com.ApplicationTest.Entities.DTOs.TransaccionMapper;
import com.ApplicationTest.Entities.DTOs.TransaccionSaveDTO;
import com.ApplicationTest.Entities.DTOs.TransaccionValidator;
import com.ApplicationTest.Entities.PaquetesTiempoAire;
import com.ApplicationTest.Entities.Transaccion;
import com.ApplicationTest.Repository.ATNTRepository;
import com.ApplicationTest.Repository.PaquetesTiempoAireRepository;
import com.ApplicationTest.Repository.TransaccionRepository;
import com.ApplicationTest.Service.IService.IServiceATNT;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceATNT implements IServiceATNT {

    private PaquetesTiempoAireRepository paquetesTiempoAireRepository;
    private TransaccionRepository transaccionRepository;
    private ATNTRepository atntRepository;

    private final TransaccionValidator validator =new TransaccionValidator();

    public ServiceATNT(ATNTRepository atntRepository, PaquetesTiempoAireRepository paquetesTiempoAireRepository, TransaccionRepository transaccionRepository){
        this.atntRepository=atntRepository;
        this.paquetesTiempoAireRepository=paquetesTiempoAireRepository;
        this.transaccionRepository=transaccionRepository;
    }
    @Override
    public Transaccion save(TransaccionSaveDTO transaccionSaveDTO) throws Exception {
        try{
            if(!validator.isValid(transaccionSaveDTO)){
                throw new IllegalArgumentException("Datos no validos");
            }
            boolean Exist=transaccionRepository.existsByNumeroTelefonoAndIdPaqueteAndFechaAfter(
                    transaccionSaveDTO.getNumeroTelefono(),
                    transaccionSaveDTO.getIdPaquete(),
                    LocalDateTime.now().minusMinutes(15));
            if(Exist){
                throw new RuntimeException("Ya existe una compra con el mismo número y el mismo paquete. Por favor, espera 15 minutos.");
            }
            PaquetesTiempoAire paquetesTiempoAire=paquetesTiempoAireRepository.findPaquetesTiempoAireByIdPaqueteAndIdProveedor(transaccionSaveDTO.getIdProveedor(),transaccionSaveDTO.getIdPaquete())
                    .orElseThrow(() -> new RuntimeException("No existe ese paquete"));

            Transaccion transaccion =new Transaccion();
             transaccion.setEmail(transaccionSaveDTO.getEmail());
             transaccion.setFecha(LocalDateTime.now());
             transaccion.setCodigoPostal(transaccionSaveDTO.getCodigoPostal());
             transaccion.setDatosTarjeta(transaccionSaveDTO.getDatosTarjeta());
             transaccion.setMonto(paquetesTiempoAire.getMonto());
             transaccion.setIdProveedor(transaccionSaveDTO.getIdProveedor());
             transaccion.setIdPaquete(transaccionSaveDTO.getIdPaquete());
             transaccion.setNumeroTelefono(transaccionSaveDTO.getNumeroTelefono());
            return atntRepository.save(transaccion);
        }
        catch (Exception ex){
            throw  new RuntimeException(ex.getMessage());
        }

    }

    @Override
    public List<TransaccionDTO> getSalesForPeriod(LocalDateTime fechaIni, LocalDateTime fechaFin) throws Exception {
        try{
            List<Transaccion> transaccions=atntRepository.SalesByPeriod(fechaIni,fechaFin);
            List<TransaccionDTO> transaccionDTOS=transaccions.stream().map
                    (transaccion -> TransaccionMapper.mapper.transaccionToTransaccionDto(transaccion)).collect(Collectors.toList());
            return transaccionDTOS;
        }
        catch (Exception ex){
            throw new RuntimeException("No hay ventas en ese periodo");
        }
    }

    @Override
    public List<TransaccionDTO> getSalesForDay() throws Exception {
        try{
            List<Transaccion> transaccions=atntRepository.SalesForToday();
            List<TransaccionDTO> transaccionDTOS=transaccions.stream().map(
                    transaccion -> TransaccionMapper.mapper.transaccionToTransaccionDto(transaccion)).collect(Collectors.toList());
            return transaccionDTOS;
        }
        catch (Exception ex){
            throw new RuntimeException("No se han encontrado ventas");
        }
    }
}
