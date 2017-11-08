package incarlopsa.com.appincarlopsa;

public class MeDisgusta extends MeAlgo implements ICodigos{

    //Propiedades
    private String tipo = NO_ME_GUSTA;

    //Constructor
    public MeDisgusta(Integer idLike, Integer idUsuario, Integer idComentario) {
        super(idLike, idUsuario, idComentario);
    }

    public MeDisgusta(){}

    //Getter/Setter
    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}