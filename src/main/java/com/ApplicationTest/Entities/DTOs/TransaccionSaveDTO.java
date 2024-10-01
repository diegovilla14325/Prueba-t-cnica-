package com.ApplicationTest.Entities.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class TransaccionSaveDTO {
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Integer idTransaccion;
    @com.fasterxml.jackson.annotation.JsonIgnore
    private LocalDateTime date;
    private String datosTarjeta;
    private String email;
    private String codigoPostal;
    private Long numeroTelefono;
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Double monto;
    private Integer idProveedor;
    private Integer idPaquete;

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDatosTarjeta() {
        return datosTarjeta;
    }

    public void setDatosTarjeta(String datosTarjeta) {
        this.datosTarjeta = datosTarjeta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Long getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(Long numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }
}
