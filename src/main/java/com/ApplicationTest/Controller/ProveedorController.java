package com.ApplicationTest.Controller;

import com.ApplicationTest.Entities.Proveedor;
import com.ApplicationTest.Service.ServiceProveedor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    private ServiceProveedor serviceProveedor;
    public ProveedorController(ServiceProveedor serviceProveedor){
        this.serviceProveedor=serviceProveedor;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Proveedor>> getAll(){
        try{
            return new ResponseEntity<>(serviceProveedor.getAll(), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getById(@PathVariable("id") Integer id){
        try{
            return new ResponseEntity<>(serviceProveedor.findById(id), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
