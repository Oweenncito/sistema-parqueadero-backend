package com.example.sistema_parqueadero_backend;

import java.time.LocalDateTime;

public class Factura {
	   private Cliente placaVehiculo;
	    private LocalDateTime horaIngreso;
	    private LocalDateTime horaSalida;
	    private double totalPagar;

	    public Factura(Cliente placaVehiculo, LocalDateTime horaIngreso, LocalDateTime horaSalida, double totalPagar) {
	        this.placaVehiculo = placaVehiculo;
	        this.horaIngreso = horaIngreso;
	        this.horaSalida = horaSalida;
	        this.totalPagar = totalPagar;
	    }

	    public Cliente getPlacaVehiculo() {
	        return placaVehiculo;
	    }

	    public void setPlacaVehiculo(Cliente placaVehiculo) {
	        this.placaVehiculo = placaVehiculo;
	    }

	    public LocalDateTime getHoraIngreso() {
	        return horaIngreso;
	    }

	    public void setHoraIngreso(LocalDateTime horaIngreso) {
	        this.horaIngreso = horaIngreso;
	    }

	    public LocalDateTime getHoraSalida() {
	        return horaSalida;
	    }

	    public void setHoraSalida(LocalDateTime horaSalida) {
	        this.horaSalida = horaSalida;
	    }

	    public double getTotalPagar() {
	        return totalPagar;
	    }

	    public void setTotalPagar(double totalPagar) {
	        this.totalPagar = totalPagar;
	    }
	   
	}
