package incarlopsa.com.appincarlopsa;

public class DAOMensajeChat implements IDAO<MensajeChat> {

    //Propiedades
    private String nombreTabla = "mensaje";
    private String nombreIdTabla1 = "idchat";
    private String nombreIdTabla2 = "idmensaje";

    @Override
    public Boolean create(MensajeChat elementoACrear) {
        //ToDo
        return null;
    }

    @Override
    public MensajeChat read(Integer idMensaje) {
        //ToDo
        return null;
    }

    @Override
    public Boolean update(Integer idMensajeOrigen, MensajeChat elementoConQueActualizar) {
        //ToDo
        return null;
    }

    @Override
    public Boolean delete(MensajeChat elementoABorrar) {
        //ToDo
        return null;
    }
}
