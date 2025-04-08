package ApiEspacioParqueadero;

/**
 *
 * @author judav
 */
public class EspacioParqueadero {
    
    private int numero;
    private boolean disponible;
    private String tipoVehiculoPermitido;
    
    public EspacioParqueadero (int numero, boolean disponible, String tipoVehiculoPermitido){
        this.numero = numero;
        this.disponible = disponible;
        this.tipoVehiculoPermitido = tipoVehiculoPermitido;
    }

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
    
    
}
