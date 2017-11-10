package incarlopsa.com.appincarlopsa;

public interface ICodigos extends ICodigosAntonio,
                                ICodigosDavid,
                                ICodigosJonatan {

    //Codigos iguales o inferiores a 100

    //Codigos universales
    int TODO_OK = 0;
    int CANCELAR = -1;
    int SALIR = -999;
    int ENTRADA_DUPLICADA = 1062;

    //Codigos para TEST de consultas
    String TEST_TIPO_EMPLEADO = "Empleado";

    //Codigos para consultas
    String DAME_TODOS = "DAME_TODOS";
    String ACTUALIZA_FECHA = "ACTUALIZA_FECHA";
    String DAME_LOS_TOPIC = "DAME_LOS_TOPIC";  //ESTE ES PARA PUBLICACIONES
    String DAME_LOS_TOPIC_DESDE_MI= "DAME_LOS_TOPIC_DESDE_MI";
    String DAME_LOS_TOPIC_HACIA_MI = "DAME_LOS_TOPIC_HACIA_MI";

    //Codigos para conexion a la BBDD
//    String SERVIDOR_BBDD = "192.168.0.101";
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

    //Codigos para control de excepciones
    String ERROR_CONEXION_BBDD = "No se ha podido establecer la conexión con la base de datos";
    String ERROR_DRIVER = "Error en la carga del driver";
    String ERROR_LOGIN = "Usuario y contraseña inválidos";
    String ERROR_INTRODUCIR_DATOS = "Introduzca el usuario y contraseña";

    //Tostadas de chats/publicaciones
    String ERROR_MENSAJE_VACIO = "No pueden enviarse mensajes vacíos";
    String ERROR_ANUNCIO_VACIO = "No se pueden enviar anuncios vacíos";
    String ERROR_TITULO_VACIO_CHAT = "Debe introducir un título/asunto para el chat";
    String ERROR_USUARIO_NO_ELEGIDO = "No ha especificado el destinatario del chat";
    String CHAT_CREADO_CON_EXITO = "Chat creado con éxito!";

    //Tostadas variopintas
    String BIENVENIDA = "Bienvenid@ a IncarlopsAPP!";
    String USUARIO_DESCONOCIDO = "Usuario desconocido";

    //Tipos Likes
    String ME_GUSTA = "MeGusta";
    String NO_ME_GUSTA = "MeDisgusta";

    //Tostadas de ficheros
    String ERROR_NO_SE_PUEDE_LEER_IMAGEN = "No se puede leer la imagen del dispositivo";
    String IMAGEN_ANADIDA_CON_EXITO = "Imagen añadida con éxito";

}
