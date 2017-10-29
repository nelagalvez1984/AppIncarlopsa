package incarlopsa.com.appincarlopsa;

import java.util.Date;

public abstract class MensajeBase extends DataBaseItem{

    //Propiedades
    private Integer idMensaje = null;
    private Integer idUsuario = null;
    private String mensaje = null;
    private Date fecha = null;

    //Constructor
    public MensajeBase(Integer idChatOPublicacion, Integer idMensaje, Integer idUsuario, String mensaje, Date fecha) {
        this.id = idChatOPublicacion;
        this.idMensaje = idMensaje;
        this.idUsuario = idUsuario;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public MensajeBase(){}

    //Getter/Setter
    public Integer getIdMensaje() { return idMensaje; }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //Equals y Hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MensajeBase that = (MensajeBase) o;

        if (!id.equals(that.id)) return false;
        if (!idMensaje.equals(that.idMensaje)) return false;
        if (!idUsuario.equals(that.idUsuario)) return false;
        if (!mensaje.equals(that.mensaje)) return false;
        return fecha.equals(that.fecha);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + idMensaje.hashCode();
        result = 31 * result + idUsuario.hashCode();
        result = 31 * result + mensaje.hashCode();
        result = 31 * result + fecha.hashCode();
        return result;
    }
}
