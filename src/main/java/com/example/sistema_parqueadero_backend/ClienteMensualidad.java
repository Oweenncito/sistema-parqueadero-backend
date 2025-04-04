package com.example.sistema_parqueadero_backend;
import java.util.UUID;
public class ClienteMensualidad {
    
    public String nombre;
    public String apellido;
    public String id;
    public String celular;
    public String marca;
    public String placaVehiculo;
    
    public ClienteMensualidad() {
    	
    	this.id = UUID.randomUUID().toString();
    }
    
    public ClienteMensualidad(String nombre, String apellido, String celular, String marca, String placaVehiculo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = UUID.randomUUID().toString();
        this.celular = celular;
        this.marca = marca;
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
    
    public void setCedula(String id){
        this.id = id;
    }
    
    public String getCelular(){
        return celular;
    }
    
    public void setCelular(String celular){
        this.celular = celular;
    }
    
    public String getPlacaVehiculo(){
        return placaVehiculo;
    }
    
    public void setPlacaVehiculo(String placaVehiculo){
        this.placaVehiculo = placaVehiculo;
    }
}

