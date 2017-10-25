package incarlopsa.com.appincarlopsa;

import java.util.Date;

public class MensajeBase {

    //ToDo
    private int id;
    private int idMensaje;
    private int idUsuario;
    private String mensaje;
    private Date fecha;

    public MensajeBase(int id, int idMensaje, int idUsuario, String mensaje, Date fecha) {
        this.id = id;
        this.idMensaje = idMensaje;
        this.idUsuario = idUsuario;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
