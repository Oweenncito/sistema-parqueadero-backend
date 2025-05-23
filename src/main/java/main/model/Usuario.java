package main.model;

import java.util.UUID;

public class Usuario {

    private String correo;
    private String nombre;
    private String id;
    private String contraseña;

    public Usuario() {

        this.id = UUID.randomUUID().toString();

    }

    public Usuario(String correo, String nombre, String contraseña) {

        this.id = UUID.randomUUID().toString();
        this.correo = correo;
        this.nombre = nombre;
        this.contraseña = contraseña;

    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}
