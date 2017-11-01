package incarlopsa.com.appincarlopsa;

import java.util.Date;

public class Mensaje extends MensajeBase {

    //Propiedades
    private Boolean leidoPorDestino = false;

    //Constructor
    public Mensaje(int idMensaje, int idChat, int idUsuario, String mensaje, String fechaCreacion, String horaCreacion, Boolean leidoPorDestino) {
        super(idMensaje, idChat, idUsuario, mensaje, fechaCreacion, horaCreacion);
        this.leidoPorDestino = leidoPorDestino;
    }

    public Mensaje(int idMensaje, int idChat, int idUsuario, String mensaje, String fechaCreacion, String horaCreacion) {
        super(idMensaje, idChat, idUsuario, mensaje, fechaCreacion, horaCreacion);
        this.leidoPorDestino = false;
    }

    public Mensaje(){}

    //Getter/Setter
    public Boolean getLeidoPorDestino() {
        return leidoPorDestino;
    }

    public void setLeidoPorDestino(Boolean leidoPorDestino) {
        this.leidoPorDestino = leidoPorDestino;
    }

    //Equals y Hash
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Mensaje that = (Mensaje) o;

        return leidoPorDestino.equals(that.leidoPorDestino);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + leidoPorDestino.hashCode();
        return result;
    }
}
