package incarlopsa.com.appincarlopsa;

public interface ICodigos extends ICodigosAntonio,
                                ICodigosDavid,
                                ICodigosEdu,
        ICodigosJonatan,
                                ICodigosNela {

    //Codigos iguales o inferiores a 100

    //Codigos universales
    int TODO_OK = 0;
    int CANCELAR = -1;
    int SALIR = -999;
    int ENTRADA_DUPLICADA = 1062;

    String DAME_TODOS = "DAME_TODOS";
    String ACTUALIZA_FECHA = "ACTUALIZA_FECHA";
    String DAME_LOS_TOPIC = "DAME_LOS_TOPIC";
    String SERVIDOR_BBDD = "10.20.32.116";
    String PUERTO_BBDD = "3306";
    String BBDD = "incarlopsa";
    String DIRECCION_BBDD_RAIZ = "jdbc:mysql://"+SERVIDOR_BBDD + ":" + PUERTO_BBDD
                            +"/"+BBDD;
    String DIRECCION_BBDD_USUARIO = "?user=";
    String DIRECCION_BBDD_PASSWORD = "&password=";

    String USUARIO_TEST_NORMAL = "TESTUSER";
    String PASSWORD_TEST_NORMAL = "TESTUSER";
    String USUARIO_TEST_RRHH = "TESTRRHH";
    String PASSWORD_TEST_RRHH = "TESTRRHH";

}
