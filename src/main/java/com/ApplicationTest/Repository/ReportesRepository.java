package com.ApplicationTest.Repository;

import com.ApplicationTest.Entities.DTOs.TotalVentaPorMontoDTO;
import com.ApplicationTest.Entities.DTOs.TotalVentasProveedorDTO;
import com.ApplicationTest.Entities.DTOs.VentasPorDiaDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ReportesRepository  {
    @PersistenceContext
    private EntityManager entityManager;

    public VentasPorDiaDTO getVentas(){

        String query="SELECT SUM((Select Monto from applicationtest.paquetestiempoaire pt where pt.idPaquete=t.idPaquete)) as VentaTotal FROM applicationtest.transaccion t where DATE(t.fecha)=curdate()";

        return (VentasPorDiaDTO) entityManager.createNativeQuery(query,VentasPorDiaDTO.class).getSingleResult();

    }


    public List<TotalVentasProveedorDTO> getVentasProveedor(){
        String query="SELECT sum((Select Monto from applicationtest.paquetestiempoaire pt where pt.idPaquete=t.idPaquete)) as totalVenta, p.Nombre FROM applicationtest.transaccion t Inner join applicationtest.proveedor p on p.idProveedor=t.idProveedor where DATE(t.fecha)=curdate() Group by Nombre";
        return entityManager.createNativeQuery(query,TotalVentasProveedorDTO.class).getResultList();
    }

    public List<TotalVentaPorMontoDTO> getVentasPorMonto(){
        String query="SELECT sum(pt.monto) as ventaTotal,count(pt.monto) as cantidadPaquetesComprados, pt.monto FROM applicationtest.transaccion t INNER JOIN applicationtest.paquetestiempoaire pt on pt.idPaquete=t.idPaquete where DATE(t.fecha)=curdate() GROUP BY pt.monto";
        return entityManager.createNativeQuery(query, TotalVentaPorMontoDTO.class).getResultList();
    }






}
