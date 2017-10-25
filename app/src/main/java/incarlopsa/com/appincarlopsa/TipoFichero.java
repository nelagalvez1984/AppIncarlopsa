package incarlopsa.com.appincarlopsa;

public class TipoFichero {


    private int id;
    private String nombre;
    private Boolean mostrable;

    public TipoFichero(int id, String nombre, Boolean mostrable) {
        this.id = id;
        this.nombre = nombre;
        this.mostrable = mostrable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getMostrable() {
        return mostrable;
    }

    public void setMostrable(Boolean mostrable) {
        this.mostrable = mostrable;
    }
}
