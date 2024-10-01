package com.ApplicationTest.Entities.DTOs;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TotalVentasProveedorDTO {

    @Id
    private String totalVenta;
    private String nombre;

    public String getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(String totalVenta) {
        this.totalVenta = totalVenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
