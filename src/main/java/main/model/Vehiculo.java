package main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

//clase vehiculo
@Entity
@Table(name = "Vehiculos")
public class Vehiculo {

    //sus parametros 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
    @Column(nullable = false)
    private String placa;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
    private String marca;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private LocalDateTime HoraEntrada;

    public Vehiculo() {

    }

    //constructor sin parametros donde solo se inicializan el id y la hora de entrada 

    /*constructor con parametros aqui no se pasan por parametro el id y la hora de entrada, solo se inicializan 
        dentro del constructor
     */
    public Vehiculo(String placa, String tipo, String marca, String color) {
        this.placa = placa;
        this.tipo = tipo;
        this.marca = marca;
        this.color = color;
        this.HoraEntrada = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDateTime getHoraEntrada() {
        return HoraEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        HoraEntrada = horaEntrada;
    }
}

