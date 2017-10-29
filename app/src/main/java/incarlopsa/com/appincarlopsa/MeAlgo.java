package incarlopsa.com.appincarlopsa;

public abstract class MeAlgo extends DataBaseItem{

    //Propiedades
    protected String tipo = "";
    protected Integer idUsuario = null;

    //Constructor
    public MeAlgo(Integer idUsuario, Integer idComentario) {
        this.id = idComentario;
        this.idUsuario = idUsuario;
    }

    public MeAlgo(){}

    //Getter/Setter
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    //Equals y Hash


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MeAlgo meAlgo = (MeAlgo) o;

        return tipo.equals(meAlgo.tipo);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + tipo.hashCode();
        return result;
    }
}