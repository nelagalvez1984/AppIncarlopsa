package incarlopsa.com.appincarlopsa;

public class DAOMensajeComentario implements IDAOdobleID<MensajeComentario> {

    //Propiedades
    private String nombreTabla = "comentario";
    private String nombreIdTabla1 = "idpublicacion";
    private String nombreIdTabla2 = "idcomentario";

    //ToDo

    @Override
    public Boolean create(MensajeComentario elementoACrear) {
        //ToDo
        return null;
    }

    @Override
    public MensajeComentario read(Integer idPublicacion, Integer idComentario) {
        //ToDo
        return null;
    }

    @Override
    public Boolean update(Integer idPublicacionOrigen, Integer idComentarioOrigen,  MensajeComentario elementoConQueActualizar) {
        //ToDo
        return null;
    }

    @Override
    public Boolean delete(MensajeComentario elementoABorrar) {
        //ToDo
        return null;
    }
}
