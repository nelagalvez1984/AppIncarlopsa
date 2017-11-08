package incarlopsa.com.appincarlopsa;

public class MeGusta extends MeAlgo implements ICodigos{

    //Propiedades
    private String tipo = ME_GUSTA;

    //Constructor
    public MeGusta(Integer idLike, Integer idUsuario, Integer idComentario) {
        super(idLike, idUsuario, idComentario);
    }

    public MeGusta(){}

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