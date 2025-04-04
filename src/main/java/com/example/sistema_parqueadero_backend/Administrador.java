package com.example.sistema_parqueadero_backend;

import java.util.UUID;

public class Administrador {
    
    private String id;
    private String nombre;
    private String correo;
    private String telefono;
    
    public Administrador () {
    	
    	this.id = UUID.randomUUID().toString();
    }
    
    public Administrador( String nombre, String correo, String telefono) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
