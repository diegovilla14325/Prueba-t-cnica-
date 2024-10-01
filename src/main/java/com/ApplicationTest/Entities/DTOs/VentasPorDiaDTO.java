package com.ApplicationTest.Entities.DTOs;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VentasPorDiaDTO {

    @Id
    private Double ventaTotal;

    public Double getVentaTotal() {
        return ventaTotal;
    }

    public void setVentaTotal(Double ventaTotal) {
        this.ventaTotal = ventaTotal;
    }
}
