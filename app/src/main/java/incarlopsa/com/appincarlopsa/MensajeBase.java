package incarlopsa.com.appincarlopsa;

import java.util.Date;

public abstract class MensajeBase extends DataBaseItem{

    //Propiedades
    private Integer idMensaje = null;
    private Integer idPublicacion = null;
    private Integer idUsuario = null;
    private String fecha = null;
    private String hora = null;
    private String mensaje = null;

    //Constructor


    public MensajeBase(Integer idPublicacion, Integer idMensaje, Integer idUsuario, String fecha, String hora, String mensaje) {
        this.idMensaje = idMensaje;
        this.idPublicacion = idPublicacion;
        this.idUsuario = idUsuario;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.hora = hora;
    }

    public MensajeBase(){}

    //Getter/Setter

    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }



    //Equals y Hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MensajeBase that = (MensajeBase) o;

        if (!idMensaje.equals(that.idMensaje)) return false;
        if (!idPublicacion.equals(that.idPublicacion)) return false;
        if (!idUsuario.equals(that.idUsuario)) return false;
        if (!mensaje.equals(that.mensaje)) return false;
        if (!fecha.equals(that.fecha)) return false;
        return hora.equals(that.hora);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idMensaje.hashCode();
        result = 31 * result + idPublicacion.hashCode();
        result = 31 * result + idUsuario.hashCode();
        result = 31 * result + mensaje.hashCode();
        result = 31 * result + fecha.hashCode();
        result = 31 * result + hora.hashCode();
        return result;
    }
}
