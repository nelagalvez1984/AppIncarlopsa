package incarlopsa.com.appincarlopsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

//Contiene un mensajeComentario con 0-X adjuntos
//Contiene un arraylist de mensajeComentarios


public class Publicacion extends Topic{

    private int idPublicacion;
    MensajeComentario mensajeComentario[];
    ArrayList<MensajeComentario> mensajeComentarios;
    ArrayList<Adjunto> adjuntos;


    //Constructor
    public Publicacion(String titulo, Date fecha, String autor, int idPublicacion, MensajeComentario[] mensajeComentario, ArrayList<MensajeComentario> mensajeComentarios, ArrayList<Adjunto> adjuntos) {
        super(titulo, fecha, autor);
        this.idPublicacion = idPublicacion;
        this.mensajeComentario = mensajeComentario;
        this.mensajeComentarios = mensajeComentarios;
        this.adjuntos = adjuntos;
    }


    //Getters y setters
    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public MensajeComentario[] getMensajeComentario() {
        return mensajeComentario;
    }

    public void setMensajeComentario(MensajeComentario[] mensajeComentario) {
        this.mensajeComentario = mensajeComentario;
    }

    public ArrayList<MensajeComentario> getMensajeComentarios() {
        return mensajeComentarios;
    }

    public void setMensajeComentarios(ArrayList<MensajeComentario> mensajeComentarios) {
        this.mensajeComentarios = mensajeComentarios;
    }

    public ArrayList<Adjunto> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(ArrayList<Adjunto> adjuntos) {
        this.adjuntos = adjuntos;
    }

    //Equals y hash
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publicacion)) return false;
        if (!super.equals(o)) return false;

        Publicacion that = (Publicacion) o;

        if (getIdPublicacion() != that.getIdPublicacion()) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getMensajeComentario(), that.getMensajeComentario())) return false;
        if (getMensajeComentarios() != null ? !getMensajeComentarios().equals(that.getMensajeComentarios()) : that.getMensajeComentarios() != null)
            return false;
        return getAdjuntos() != null ? getAdjuntos().equals(that.getAdjuntos()) : that.getAdjuntos() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getIdPublicacion();
        result = 31 * result + Arrays.hashCode(getMensajeComentario());
        result = 31 * result + (getMensajeComentarios() != null ? getMensajeComentarios().hashCode() : 0);
        result = 31 * result + (getAdjuntos() != null ? getAdjuntos().hashCode() : 0);
        return result;
    }
}


