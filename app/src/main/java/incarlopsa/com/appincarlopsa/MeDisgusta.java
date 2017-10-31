package incarlopsa.com.appincarlopsa;

public class MeDisgusta extends MeAlgo {

    //Propiedades
    private final String tipo="MeDisgusta";

    //Constructor
    public MeDisgusta(Integer idLike, Integer idUsuario, Integer idComentario) {
        super(idLike, idUsuario, idComentario);
    }


    public MeDisgusta(){}
}