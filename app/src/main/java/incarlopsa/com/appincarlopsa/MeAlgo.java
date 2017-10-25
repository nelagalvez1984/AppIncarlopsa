package incarlopsa.com.appincarlopsa;

public abstract class MeAlgo {

    //Propiedades
    protected String tipo = "";
    private Usuario usuario;

    //Constructor
    public MeAlgo(Usuario usuario) {
        this.usuario = usuario;
    }

    //Getter/Setter
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    //Equals y Hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeAlgo meAlgo = (MeAlgo) o;

        if (!tipo.equals(meAlgo.tipo)) return false;
        return usuario.equals(meAlgo.usuario);

    }

    @Override
    public int hashCode() {
        int result = tipo.hashCode();
        result = 31 * result + usuario.hashCode();
        return result;
    }
}