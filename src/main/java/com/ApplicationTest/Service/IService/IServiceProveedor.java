package com.ApplicationTest.Service.IService;

import com.ApplicationTest.Entities.Proveedor;

import java.util.List;

public interface IServiceProveedor {
    public List<Proveedor> getAll() throws Exception;
    public  Proveedor findById(Integer id) throws Exception;


}
