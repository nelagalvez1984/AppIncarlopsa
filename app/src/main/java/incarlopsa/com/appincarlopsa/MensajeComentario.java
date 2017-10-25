package incarlopsa.com.appincarlopsa;

import java.util.ArrayList;
import java.util.Date;

public class MensajeComentario extends MensajeBase {

    ArrayList<MeAlgo> arrayAlgo;

    public MensajeComentario(int idPublicacion, int idMensaje, int idUsuario, String mensaje, Date fecha, ArrayList<MeAlgo> algos) {
        super(idPublicacion, idMensaje, idUsuario, mensaje, fecha);
        arrayAlgo = algos;
    }


    //ToDo

}
