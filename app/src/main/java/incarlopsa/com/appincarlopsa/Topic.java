package incarlopsa.com.appincarlopsa;

import java.util.Date;

public class Topic {


    //Es la cabecera de una publicacion o chat

    //Contiene:
    //Titulo
    //Fecha del ultimo update
    //Autor

    //Propiedades
    private String titulo;
    private Date fecha;
    private String Autor;
    protected Boolean finalizado=false;

    //Constructor
    public Topic(String titulo, Date fecha, String autor) {
        this.titulo = titulo;
        this.fecha = fecha;
        Autor = autor;
    }


    //Getter y Setter


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }


    //Equals y hash


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Topic)) return false;

        Topic topic = (Topic) o;

        if (getTitulo() != null ? !getTitulo().equals(topic.getTitulo()) : topic.getTitulo() != null)
            return false;
        if (getFecha() != null ? !getFecha().equals(topic.getFecha()) : topic.getFecha() != null)
            return false;
        return getAutor() != null ? getAutor().equals(topic.getAutor()) : topic.getAutor() == null;

    }

    @Override
    public int hashCode() {
        int result = getTitulo() != null ? getTitulo().hashCode() : 0;
        result = 31 * result + (getFecha() != null ? getFecha().hashCode() : 0);
        result = 31 * result + (getAutor() != null ? getAutor().hashCode() : 0);
        return result;
    }
}
