package com.example.sistema_parqueadero_backend;
import java.util.UUID;
public class ClienteMensualidad {
    
    public String nombre;
    public String apellido;
    public String id;
  
    public String placaVehiculo;
    
    public ClienteMensualidad() {
    	
    	this.id = UUID.randomUUID().toString();
    }
    
    public ClienteMensualidad(String nombre, String apellido, String placaVehiculo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = UUID.randomUUID().toString();
     
        this.placaVehiculo = placaVehiculo;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getApellido(){
        return apellido;
    }
    
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getPlacaVehiculo(){
        return placaVehiculo;
    }
    
    public void setPlacaVehiculo(String placaVehiculo){
        this.placaVehiculo = placaVehiculo;
    }
}

