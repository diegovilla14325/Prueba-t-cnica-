package com.ApplicationTest.Entities.DTOs;

public class TransaccionValidator {
    public boolean isValid(TransaccionSaveDTO transaccionSaveDTO) throws Exception{
        return validarTarjeta(transaccionSaveDTO.getDatosTarjeta()) &&
                validarEmail(transaccionSaveDTO.getEmail()) &&
                validarNumeroTelefonico(transaccionSaveDTO.getNumeroTelefono()) &&
                validarCodigoPostal(transaccionSaveDTO.getCodigoPostal()) &&
                validaridProveedor(transaccionSaveDTO.getIdProveedor()) &&
                validaridPaquete(transaccionSaveDTO.getIdPaquete());

    }
    public boolean validarTarjeta(String tarjeta) throws Exception{
        if(tarjeta ==null){
            throw new Exception("La tarjeta es obligatorio");
        }
        if( tarjeta.length() !=16){
            throw new Exception("La tarjeta debe ser de 16 digitos");
        }
        return true;
    }
    public boolean validarEmail(String email) throws Exception{
        if(email ==null){
            throw new Exception("El correo electronico es obligatorio");
        }
        if(!email.contains("hotmail") && !email.contains("gmail") && !email.contains("outlook")){
            throw new Exception("El correo electronico no es valido");
        }
        return true;
    }
    public boolean validarNumeroTelefonico(Long numeroTelefonico) throws Exception{
        if(numeroTelefonico ==null){
            throw new Exception("El número de telefono es obligatorio");
        }
        String numeroTelefonicoString=String.valueOf(numeroTelefonico);
        if(numeroTelefonicoString.length() !=10){
            throw new Exception("El número telefonico debe ser de 10 digitos");
        }
        return true;
    }
    public boolean validarCodigoPostal(String codigoPostal) throws Exception{
        if(codigoPostal.isEmpty()){
            throw new Exception("El codigo postal es obligatorio");
        }
        return true;
    }
    public boolean validaridProveedor(Integer idProveedor) throws  Exception{
        if(idProveedor==null){
            throw new Exception("El proveedor es obligatorio");
        }
        return true;
    }
    public boolean validaridPaquete(Integer idPaquete) throws  Exception{
        if(idPaquete == null){
            throw new Exception("El paquete es obligatorio");
        }
        return true;
    }
}
