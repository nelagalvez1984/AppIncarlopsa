package incarlopsa.com.appincarlopsa;

public abstract class DataBaseItem{

    //SuperClase de los items de un unico ID
    protected Integer id;

    //Getter/Setter
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    //Hash/Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataBaseItem that = (DataBaseItem) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
