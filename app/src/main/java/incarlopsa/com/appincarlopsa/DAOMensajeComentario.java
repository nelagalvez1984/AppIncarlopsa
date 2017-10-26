package incarlopsa.com.appincarlopsa;

public class DAOMensajeComentario implements IDAO<MensajeComentario> {

    //Propiedades
    private String nombreTabla = "item_comentario";
    private String nombreIdTabla1 = "idpublicacion";
    private String nombreIdTabla2 = "idcomentario";

    //ToDo

    @Override
    public Boolean create(MensajeComentario elementoACrear) {
        //ToDo
        return null;
    }

    @Override
    public MensajeComentario read() {
        //ToDo
        return null;
    }

    @Override
    public Boolean update(MensajeComentario elementoConQueActualizar) {
        //ToDo
        return null;
    }

    @Override
    public Boolean delete(MensajeComentario elementoABorrar) {
        //ToDo
        return null;
    }
}
