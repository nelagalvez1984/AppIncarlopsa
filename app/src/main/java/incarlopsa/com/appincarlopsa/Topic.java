package incarlopsa.com.appincarlopsa;

import java.util.Date;

public class Topic extends DataBaseItem{


    //Es la cabecera de una comentario o mensaje

    //Propiedades
    private Integer idUsuario = null;
    private String titulo = null;
    private String fechaCreacion = null;
    private String horaCreacion = null;
    protected Boolean finalizado = false;
    private String fechaUltimoUpdate = null;
    private String horaUltimoUpdate = null;


    //Constructor


    public Topic(Integer idTopic, Integer idUsuario, String titulo, String fechaCreacion, String horaCreacion, Boolean finalizado, String fechaUltimoUpdate, String horaUltimoUpdate) {
        this.id = idTopic;
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.horaCreacion = horaCreacion;
        this.finalizado = finalizado;
        this.fechaUltimoUpdate = fechaUltimoUpdate;
        this.horaUltimoUpdate = horaUltimoUpdate;
    }

    public Topic(Integer idTopic, Integer idUsuario, String titulo, String fechaCreacion, String horaCreacion, String fechaUltimoUpdate, String horaUltimoUpdate) {
        this.id = idTopic;
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.horaCreacion = horaCreacion;
        this.fechaUltimoUpdate = fechaUltimoUpdate;
        this.horaUltimoUpdate = horaUltimoUpdate;
    }

    public Topic(){}

    //Getter y Setter

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(String horaCreacion) {
        this.horaCreacion = horaCreacion;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public String getFechaUltimoUpdate() {
        return fechaUltimoUpdate;
    }

    public void setFechaUltimoUpdate(String fechaUltimoUpdate) {
        this.fechaUltimoUpdate = fechaUltimoUpdate;
    }

    public String getHoraUltimoUpdate() {
        return horaUltimoUpdate;
    }

    public void setHoraUltimoUpdate(String horaUltimoUpdate) {
        this.horaUltimoUpdate = horaUltimoUpdate;
    }

    //Equals y hash
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Topic topic = (Topic) o;

        if (!idUsuario.equals(topic.idUsuario)) return false;
        if (!titulo.equals(topic.titulo)) return false;
        if (!fechaCreacion.equals(topic.fechaCreacion)) return false;
        if (!horaCreacion.equals(topic.horaCreacion)) return false;
        if (!finalizado.equals(topic.finalizado)) return false;
        if (!fechaUltimoUpdate.equals(topic.fechaUltimoUpdate)) return false;
        return horaUltimoUpdate.equals(topic.horaUltimoUpdate);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idUsuario.hashCode();
        result = 31 * result + titulo.hashCode();
        result = 31 * result + fechaCreacion.hashCode();
        result = 31 * result + horaCreacion.hashCode();
        result = 31 * result + finalizado.hashCode();
        result = 31 * result + fechaUltimoUpdate.hashCode();
        result = 31 * result + horaUltimoUpdate.hashCode();
        return result;
    }
}