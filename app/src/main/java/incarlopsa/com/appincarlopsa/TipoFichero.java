package incarlopsa.com.appincarlopsa;

public class TipoFichero {

    //Propiedades
    private int id;
    private String nombre;
    private Boolean mostrable = true;

    //Constructor
    public TipoFichero(int id, String nombre, Boolean mostrable) {
        this.id = id;
        this.nombre = nombre;
        this.mostrable = mostrable;
    }

    //Constructor
    public TipoFichero(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.mostrable = true;
    }

    //Getter/Setter
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

    //Equals y Hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoFichero that = (TipoFichero) o;

        if (id != that.id) return false;
        if (!nombre.equals(that.nombre)) return false;
        return mostrable.equals(that.mostrable);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nombre.hashCode();
        result = 31 * result + mostrable.hashCode();
        return result;
    }
}