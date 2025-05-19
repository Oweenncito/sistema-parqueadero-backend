package main.model;

/**
 *
 * @author judav
 */
public class EspacioParqueadero {

    //parametros de espacioParqueadero
    private int numero;
    private boolean disponible;
    private String tipoVehiculoPermitido;
    private Vehiculo vehiculo; // eso hara que un vehiculo se guarde en un espacio del parqueadero

    /* Constructor principal el vehiculo no se pasa por parametro al constructor si no que se inicializa dentro de el
    asumiendo que el espacio se inicia sin ningun vehiculo, para despues ser ocupado por uno*/
    public EspacioParqueadero(int numero, boolean disponible, String tipoVehiculoPermitido) {
        this.numero = numero;
        this.disponible = disponible;
        this.tipoVehiculoPermitido = tipoVehiculoPermitido;
        this.vehiculo = null;
    }

    // Getters y Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getTipoVehiculoPermitido() {
        return tipoVehiculoPermitido;
    }

    public void setTipoVehiculoPermitido(String tipoVehiculoPermitido) {
        this.tipoVehiculoPermitido = tipoVehiculoPermitido;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}
