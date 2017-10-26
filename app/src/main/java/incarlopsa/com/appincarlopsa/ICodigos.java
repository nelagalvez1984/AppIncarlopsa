package incarlopsa.com.appincarlopsa;

public interface ICodigos extends ICodigosAntonio,
                                ICodigosDavid,
                                ICodigosEdu,
                                ICodigosJohnatan,
                                ICodigosNela {

    //Codigos iguales o inferiores a 100

    //Codigos universales
    int TODO_OK = 0;
    int CANCELAR = -1;
    int SALIR = -999;
    int ENTRADA_DUPLICADA = 1062;

    String SERVIDOR_BBDD = "127.0.0.1";
    String PUERTO_BBDD = "3306";
    String DIRECCION_BBDD = SERVIDOR_BBDD + ":" + PUERTO_BBDD;

}
