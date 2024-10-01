package com.ApplicationTest.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaccion")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Integer idTransaccion;

    @com.fasterxml.jackson.annotation.JsonIgnore
    private LocalDateTime fecha;
    private String datosTarjeta;
    private String email;
    private String codigoPostal;
    private Long numeroTelefono;

    @com.fasterxml.jackson.annotation.JsonIgnore
    private Double monto;  // Esto no aparecerá en el JSON

    private Integer idProveedor;
    private Integer idPaquete;

    @ManyToOne
    @JoinColumn(name = "idPaquete", insertable = false, updatable = false)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private PaquetesTiempoAire paquetesTiempoAire;  // Esto tampoco aparecerá en el JSON

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public PaquetesTiempoAire getPaquetesTiempoAire() {
        return paquetesTiempoAire;
    }

    public void setPaquetesTiempoAire(PaquetesTiempoAire paquetesTiempoAire) {
        this.paquetesTiempoAire = paquetesTiempoAire;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
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

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }
    public void calcularMontoDesdePaquete() {
        if (this.paquetesTiempoAire != null) {
            this.monto = this.paquetesTiempoAire.getMonto();
        }
    }
}


