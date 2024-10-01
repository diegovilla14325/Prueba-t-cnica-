package com.ApplicationTest.Entities.DTOs;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TotalVentaPorMontoDTO {

    @Id
    private Double ventaTotal;
    private Integer cantidadPaquetesComprados;
    private Double monto;

    public Double getVentaTotal() {
        return ventaTotal;
    }

    public void setVentaTotal(Double ventaTotal) {
        this.ventaTotal = ventaTotal;
    }

    public Integer getCantidadPaquetesComprados() {
        return cantidadPaquetesComprados;
    }

    public void setCantidadPaquetesComprados(Integer cantidadPaquetesComprados) {
        this.cantidadPaquetesComprados = cantidadPaquetesComprados;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
}
