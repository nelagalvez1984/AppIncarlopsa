package incarlopsa.com.appincarlopsa;

import java.util.ArrayList;
import java.util.Date;


public class Publicacion extends Topic{

    //Propiedades
    private MensajeComentario anuncio;
    private ArrayList<MensajeComentario> comentarios;
    private ArrayList<Adjunto> adjuntos;

    //Constructor

    public Publicacion(Integer idTopic, String titulo, Date fechaUltimoUpdate, Integer idUsuario, MensajeComentario anuncio, ArrayList<MensajeComentario> comentarios, ArrayList<Adjunto> adjuntos) {
        super(idTopic, titulo, fechaUltimoUpdate, idUsuario);
        this.anuncio = anuncio;
        this.comentarios = comentarios;
        this.adjuntos = adjuntos;
    }

    public Publicacion(Integer idTopic, String titulo, Date fechaUltimoUpdate, Integer idUsuario, MensajeComentario anuncio, ArrayList<Adjunto> adjuntos) {
        super(idTopic, titulo, fechaUltimoUpdate, idUsuario);
        this.anuncio = anuncio;
        this.adjuntos = adjuntos;
        comentarios = new ArrayList<>();
    }

    public Publicacion(Integer idTopic, String titulo, Date fechaUltimoUpdate, Integer idUsuario, MensajeComentario anuncio) {
        super(idTopic, titulo, fechaUltimoUpdate, idUsuario);
        this.anuncio = anuncio;
        adjuntos = new ArrayList<>();
        comentarios = new ArrayList<>();
    }

    //Getters y setters
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

        if (!anuncio.equals(that.anuncio)) return false;
        if (!comentarios.equals(that.comentarios)) return false;
        return adjuntos.equals(that.adjuntos);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + anuncio.hashCode();
        result = 31 * result + comentarios.hashCode();
        result = 31 * result + adjuntos.hashCode();
        return result;
    }

}

