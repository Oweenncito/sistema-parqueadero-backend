package ApiRegistroEntradaSalida;

import java.time.LocalDateTime;

import java.util.UUID;

import ApiVehiculos.Vehiculo;

public class RegistroEntradaSalida {
	
    private String id;
    private Vehiculo vehiculo;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;
    private double totalAPagar;
    
    public RegistroEntradaSalida () {
    	
    	this.id = UUID.randomUUID().toString();
    			
    }
    
   public RegistroEntradaSalida ( Vehiculo vehiculo, LocalDateTime horaEntrada, LocalDateTime horaSalida, double totalAPagar ) {
    	
	   this.id = UUID.randomUUID().toString();
	   this. vehiculo = vehiculo;
	   this.horaEntrada = horaEntrada;
	   this.horaSalida = horaSalida;
	   this.totalAPagar = totalAPagar;
	   
    
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public Vehiculo getVehiculo() {
	return vehiculo;
}

public void setVehiculo(Vehiculo vehiculo) {
	this.vehiculo = vehiculo;
}

public LocalDateTime getHoraEntrada() {
	return horaEntrada;
}

public void setHoraEntrada(LocalDateTime horaEntrada) {
	this.horaEntrada = horaEntrada;
}

public LocalDateTime getHoraSalida() {
	return horaSalida;
}

public void setHoraSalida(LocalDateTime horaSalida) {
	this.horaSalida = horaSalida;
}

public double getTotalAPagar() {
	return totalAPagar;
}

public void setTotalAPagar(double totalAPagar) {
	this.totalAPagar = totalAPagar;
}
   
}