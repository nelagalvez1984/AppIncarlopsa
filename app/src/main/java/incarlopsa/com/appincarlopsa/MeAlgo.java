package incarlopsa.com.appincarlopsa;

public abstract class MeAlgo extends DataBaseItem{

    //Propiedades
    protected Integer idUsuario = null;
    protected Integer idComentario = null;
    protected String tipo = "";

    //Constructor
    public MeAlgo(Integer idLike, Integer idUsuario, Integer idComentario) {
        this.id = idLike;
        this.idComentario = idComentario;
        this.idUsuario = idUsuario;
    }

    public MeAlgo(){}

    //Getter/Setter
    public abstract String getTipo();
    public abstract void setTipo(String tipo);

    public Integer getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdComentario() {
        return idComentario;
    }
    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
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