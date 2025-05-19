package main.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;



//clase vehiculo
public class Vehiculo {
            
    //sus parametros 
	private String id;
	private String placa;
	private String tipo;
	private String marca;
	private String color;
	private LocalDateTime HoraEntrada;
	
        //constructor sin parametros donde solo se inicializan el id y la hora de entrada 
	public Vehiculo() {
		this.id = UUID.randomUUID().toString();
		this.HoraEntrada = LocalDateTime.now();
	}
	
        
        /*constructor con parametros aqui no se pasan por parametro el id y la hora de entrada, solo se inicializan 
        dentro del constructor
        */
	public Vehiculo (String placa, String tipo, String marca, String color) {
		this.id = UUID.randomUUID().toString();
		this.placa = placa;
		this.tipo = tipo;
		this.marca = marca;
		this.color = color;
		this.HoraEntrada = LocalDateTime.now();
	}

        //getters y setters 
	public LocalDateTime getHoraEntrada() {
		return HoraEntrada;
	}

	public void setHoraEntrada(LocalDateTime horaEntrada) {
		HoraEntrada = horaEntrada;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	
	
	
}
