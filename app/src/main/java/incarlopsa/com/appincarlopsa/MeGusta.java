package incarlopsa.com.appincarlopsa;

public class MeGusta extends MeAlgo {

    //Propiedades
    private final String tipo="MeGusta";

    //Constructor
    public MeGusta(Integer idLike, Integer idUsuario, Integer idComentario) {
        super(idLike, idUsuario, idComentario);
    }

    public MeGusta(){}
}