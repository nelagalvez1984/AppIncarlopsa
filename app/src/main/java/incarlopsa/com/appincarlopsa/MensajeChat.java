package incarlopsa.com.appincarlopsa;

import java.util.Date;

public class MensajeChat extends MensajeBase {

    private Boolean leidoPorDestino;

    public MensajeChat(int idChat, int idMensaje, int idUsuario, String mensaje, Date fecha, Boolean leidoPorDestino) {
        super(idChat, idMensaje, idUsuario, mensaje, fecha);
        this.leidoPorDestino = leidoPorDestino;
    }


    //ToDo




}
