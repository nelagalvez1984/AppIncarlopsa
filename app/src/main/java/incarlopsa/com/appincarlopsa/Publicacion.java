package incarlopsa.com.appincarlopsa;

import java.util.ArrayList;
import java.util.Date;


public class Publicacion extends Topic{

    //Propiedades
    private int idPublicacion;
    private MensajeComentario anuncio;
    private ArrayList<MensajeComentario> comentarios;
    private ArrayList<Adjunto> adjuntos;

    //Constructor

    public Publicacion(Integer idTopic, String titulo, Date fechaUltimoUpdate, Integer idUsuario, int idPublicacion, MensajeComentario anuncio, ArrayList<MensajeComentario> comentarios, ArrayList<Adjunto> adjuntos) {
        super(idTopic, titulo, fechaUltimoUpdate, idUsuario);
        this.idPublicacion = idPublicacion;
        this.anuncio = anuncio;
        this.comentarios = comentarios;
        this.adjuntos = adjuntos;
    }

    public Publicacion(Integer idTopic, String titulo, Date fechaUltimoUpdate, Integer idUsuario, int idPublicacion, MensajeComentario anuncio, ArrayList<Adjunto> adjuntos) {
        super(idTopic, titulo, fechaUltimoUpdate, idUsuario);
        this.idPublicacion = idPublicacion;
        this.anuncio = anuncio;
        this.adjuntos = adjuntos;
        comentarios = new ArrayList<>();
    }

    public Publicacion(Integer idTopic, String titulo, Date fechaUltimoUpdate, Integer idUsuario, int idPublicacion, MensajeComentario anuncio) {
        super(idTopic, titulo, fechaUltimoUpdate, idUsuario);
        this.idPublicacion = idPublicacion;
        this.anuncio = anuncio;
        adjuntos = new ArrayList<>();
        comentarios = new ArrayList<>();
    }

    //Getters y setters

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public MensajeComentario getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(MensajeComentario anuncio) {
        this.anuncio = anuncio;
    }

    public ArrayList<MensajeComentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<MensajeComentario> comentarios) {
        this.comentarios = comentarios;
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
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Publicacion that = (Publicacion) o;

        if (idPublicacion != that.idPublicacion) return false;
        if (!anuncio.equals(that.anuncio)) return false;
        if (comentarios != null ? !comentarios.equals(that.comentarios) : that.comentarios != null)
            return false;
        return adjuntos != null ? adjuntos.equals(that.adjuntos) : that.adjuntos == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idPublicacion;
        result = 31 * result + anuncio.hashCode();
        result = 31 * result + (comentarios != null ? comentarios.hashCode() : 0);
        result = 31 * result + (adjuntos != null ? adjuntos.hashCode() : 0);
        return result;
    }
}

