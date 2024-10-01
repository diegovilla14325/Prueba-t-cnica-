package com.ApplicationTest.Entities.DTOs;

import com.ApplicationTest.Entities.Transaccion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransaccionMapper {

    TransaccionMapper mapper = Mappers.getMapper(TransaccionMapper.class);

    @Mapping(source = "fecha",target = "date")
    TransaccionDTO transaccionToTransaccionDto(Transaccion transaccion);

    @Mapping(source = "date", target = "fecha")
    Transaccion transaccionDtoToTransaccion(TransaccionDTO transaccionDTO);
}
