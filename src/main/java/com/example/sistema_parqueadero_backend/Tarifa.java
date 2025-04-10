package com.example.sistema_parqueadero_backend;

/**
 *
 * @author judav
 */
public class Tarifa {
    
    private String tipoVehiculo; // "carro" o "moto"
    private double valorHora;

    public Tarifa(String tipoVehiculo, double valorHora) {
        this.tipoVehiculo = tipoVehiculo;
        this.valorHora = Math.max(0, valorHora); // Asegura que no sea negativo
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = Math.max(0, valorHora);
    }
}


