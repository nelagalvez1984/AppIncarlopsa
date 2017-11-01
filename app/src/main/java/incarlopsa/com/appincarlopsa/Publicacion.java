package incarlopsa.com.appincarlopsa;

import java.util.ArrayList;
import java.util.Date;


public class Publicacion extends Topic{

    //Propiedades
    private ArrayList<DataBaseItem> comentarios = null;
    private ArrayList<DataBaseItem> adjuntos = null;

    //Constructor


    public Publicacion(Integer idTopic, Integer idUsuario, String titulo, String fechaCreacion, String horaCreacion, String fechaUltimoUpdate, String horaUltimoUpdate, ArrayList<DataBaseItem> adjuntos) {
        super(idTopic, idUsuario, titulo, fechaCreacion, horaCreacion, fechaUltimoUpdate, horaUltimoUpdate);
        this.adjuntos = adjuntos;
        this.comentarios = new ArrayList<>();
    }

    public Publicacion(Integer idTopic, Integer idUsuario, String titulo, String fechaCreacion, String horaCreacion, String fechaUltimoUpdate, String horaUltimoUpdate, ArrayList<DataBaseItem> comentarios, ArrayList<DataBaseItem> adjuntos) {
        super(idTopic, idUsuario, titulo, fechaCreacion, horaCreacion, fechaUltimoUpdate, horaUltimoUpdate);
        this.comentarios = comentarios;
        this.adjuntos = adjuntos;
    }

    public Publicacion(Integer idTopic, Integer idUsuario, String titulo, String fechaCreacion, String horaCreacion, String fechaUltimoUpdate, String horaUltimoUpdate) {
        super(idTopic, idUsuario, titulo, fechaCreacion, horaCreacion, fechaUltimoUpdate, horaUltimoUpdate);
        this.adjuntos = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    public Publicacion(){}

    //Getters y setters
    public ArrayList<DataBaseItem> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<DataBaseItem> comentarios) {
        this.comentarios = comentarios;
    }

    public ArrayList<DataBaseItem> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(ArrayList<DataBaseItem> adjuntos) {
        this.adjuntos = adjuntos;
    }

    //Equals y hash


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Publicacion that = (Publicacion) o;

        if (!comentarios.equals(that.comentarios)) return false;
        return adjuntos.equals(that.adjuntos);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + comentarios.hashCode();
        result = 31 * result + adjuntos.hashCode();
        return result;
    }
}

