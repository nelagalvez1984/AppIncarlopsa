package incarlopsa.com.appincarlopsa;

import java.util.Date;

public class Topic extends DataBaseItem{


    //Es la cabecera de una publicacion o chat

    //Propiedades
    private String titulo;
    private Date fechaUltimoUpdate;
    private Integer idUsuario;
    protected Boolean finalizado = false;

    //Constructor
    public Topic(Integer idTopic, String titulo, Date fechaUltimoUpdate, Integer idUsuario) {
        this.id = idTopic;
        this.titulo = titulo;
        this.fechaUltimoUpdate = fechaUltimoUpdate;
        this.idUsuario = idUsuario;
    }

    //Getter y Setter

    public Integer getIdTopic() {
        return getId();
    }

    public void setIdTopic(Integer idTopic) { setId(idTopic); }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaUltimoUpdate() {
        return fechaUltimoUpdate;
    }

    public void setFechaUltimoUpdate(Date fechaUltimoUpdate) { this.fechaUltimoUpdate = fechaUltimoUpdate; }

    public Integer getAutor() {
        return idUsuario;
    }

    public void setAutor(Integer autor) {
        this.idUsuario = autor;
    }

    //Equals y hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Topic topic = (Topic) o;

        if (!titulo.equals(topic.titulo)) return false;
        if (!fechaUltimoUpdate.equals(topic.fechaUltimoUpdate)) return false;
        if (!idUsuario.equals(topic.idUsuario)) return false;
        return finalizado.equals(topic.finalizado);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + titulo.hashCode();
        result = 31 * result + fechaUltimoUpdate.hashCode();
        result = 31 * result + idUsuario.hashCode();
        result = 31 * result + finalizado.hashCode();
        return result;
    }
}