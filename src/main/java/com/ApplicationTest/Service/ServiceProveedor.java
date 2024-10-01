package com.ApplicationTest.Service;

import com.ApplicationTest.Entities.Proveedor;
import com.ApplicationTest.Repository.ProveedorRepository;
import com.ApplicationTest.Service.IService.IServiceProveedor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceProveedor implements IServiceProveedor {

    private ProveedorRepository proveedorRepository;

    public ServiceProveedor(ProveedorRepository proveedorRepository){
        this.proveedorRepository=proveedorRepository;
    }
    @Override
    @Transactional
    public List<Proveedor> getAll() throws Exception {
        try{
            List<Proveedor> proveedor =proveedorRepository.findAll();
            return  proveedor;
        }
        catch (Exception ex){
            throw new Exception(ex.getMessage());
        }

    }

    @Override
    @Transactional
    public Proveedor findById(Integer id) throws Exception {
        try{
            Optional<Proveedor> proveedor =proveedorRepository.findById(id);
            return  proveedor.get();
        }
        catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
