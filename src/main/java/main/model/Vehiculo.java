package main.model;

import java.time.LocalDate;
import java.util.UUID;
public class Vehiculo {

	private String id;
	private String placa;
	private String tipo;
	private String marca;
	private String color;
	private LocalDate HoraEntrada;
	
	public Vehiculo() {
	
		this.id = UUID.randomUUID().toString();
	}
	
	public Vehiculo ( String placa, String tipo, String marca, String color) {
		
		this.id = UUID.randomUUID().toString();
		this.placa = placa;
		this.tipo = tipo;
		this.marca = marca;
		this.color = color;
		this.HoraEntrada=LocalDate.now();
		}

	public LocalDate getHoraEntrada() {
		return HoraEntrada;
	}

	public void setHoraEntrada(LocalDate horaEntrada) {
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
