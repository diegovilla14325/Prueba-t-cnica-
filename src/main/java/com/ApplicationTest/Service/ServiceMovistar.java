package com.ApplicationTest.Service;

import com.ApplicationTest.Entities.DTOs.TransaccionDTO;
import com.ApplicationTest.Entities.DTOs.TransaccionMapper;
import com.ApplicationTest.Entities.DTOs.TransaccionSaveDTO;
import com.ApplicationTest.Entities.DTOs.TransaccionValidator;
import com.ApplicationTest.Entities.PaquetesTiempoAire;
import com.ApplicationTest.Entities.Transaccion;
import com.ApplicationTest.Repository.MovistarRepository;
import com.ApplicationTest.Repository.PaquetesTiempoAireRepository;
import com.ApplicationTest.Repository.TransaccionRepository;
import com.ApplicationTest.Service.IService.IServiceMovistar;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceMovistar implements IServiceMovistar {
    private TransaccionRepository transaccionRepository;
    private PaquetesTiempoAireRepository paquetesTiempoAireRepository;
    private MovistarRepository movistarRepository;
    private final TransaccionValidator validator =new TransaccionValidator();

    public ServiceMovistar(TransaccionRepository transaccionRepository, PaquetesTiempoAireRepository paquetesTiempoAireRepository, MovistarRepository movistarRepository){
        this.transaccionRepository=transaccionRepository;
        this.paquetesTiempoAireRepository=paquetesTiempoAireRepository;
        this.movistarRepository=movistarRepository;

    }
    @Override
    public Transaccion save(TransaccionSaveDTO transaccionSaveDTO) throws Exception {
        try{
            if(!validator.isValid(transaccionSaveDTO)){
                throw new IllegalArgumentException("Datos no validos");
            }
            boolean IsExist= transaccionRepository.existsByNumeroTelefonoAndIdPaqueteAndFechaAfter(
                    transaccionSaveDTO.getNumeroTelefono(),
                    transaccionSaveDTO.getIdPaquete(),
                    LocalDateTime.now().minusMinutes(15)
            );
            if(IsExist){
                throw  new RuntimeException("Ya existe una compra con el mismo número y el mismo paquete. Por favor, espera 15 minutos.");
            }
            PaquetesTiempoAire paquetesTiempoAire=paquetesTiempoAireRepository.findPaquetesTiempoAireByIdPaqueteAndIdProveedor(transaccionSaveDTO.getIdProveedor(),transaccionSaveDTO.getIdPaquete())
                    .orElseThrow(()-> new RuntimeException("No existe ese paquete"));

            Transaccion transaccion= new Transaccion();
            transaccion.setFecha(LocalDateTime.now());
            transaccion.setDatosTarjeta(transaccionSaveDTO.getDatosTarjeta());
            transaccion.setEmail(transaccionSaveDTO.getEmail());
            transaccion.setMonto(paquetesTiempoAire.getMonto());
            transaccion.setCodigoPostal(transaccionSaveDTO.getCodigoPostal());
            transaccion.setNumeroTelefono(transaccionSaveDTO.getNumeroTelefono());
            transaccion.setIdPaquete(transaccionSaveDTO.getIdPaquete());
            transaccion.setIdProveedor(transaccionSaveDTO.getIdProveedor());
            return movistarRepository.save(transaccion);
        }
        catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public List<TransaccionDTO> findByPeriod(LocalDateTime fechaIni, LocalDateTime fechaFin) throws Exception {
        try{
            List<Transaccion> transaccion= movistarRepository.findByPeriod(fechaIni,fechaFin);
            List<TransaccionDTO> transaccionDTOS= transaccion.stream().map
                    (transaccion1 -> TransaccionMapper.mapper.transaccionToTransaccionDto(transaccion1)).collect(Collectors.toList());
            return  transaccionDTOS;
        }
        catch (Exception ex){
            throw new  Exception(ex.getMessage());
        }
    }

    @Override
    public List<TransaccionDTO> findSalesForDay() throws Exception {
        try{
            List<Transaccion> transaccions= movistarRepository.findSalesForToday();
            List<TransaccionDTO> transaccionDTOS= transaccions.stream().map
                    (transaccion -> TransaccionMapper.mapper.transaccionToTransaccionDto(transaccion)).collect(Collectors.toList());
            return transaccionDTOS;
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }

    }
}
