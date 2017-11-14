package incarlopsa.com.appincarlopsa;

public interface ICodigos {

    //Codigos universales
    int CODIGO_DEVOLUCION_PATH = 100;
    int CODIGO_DEVOLUCION_FOTO = 42;

    //Codigos para TEST de consultas
    String TEST_TIPO_EMPLEADO = "Empleado";
//    String cosa = getResources().getString(R.string.app_name);

    //Codigos para consultas
    String DAME_TODOS = "DAME_TODOS";
    String ACTUALIZA_FECHA = "ACTUALIZA_FECHA";
    String DAME_LOS_TOPIC = "DAME_LOS_TOPIC";  //ESTE ES PARA PUBLICACIONES
    String DAME_LOS_TOPIC_DESDE_MI= "DAME_LOS_TOPIC_DESDE_MI";
    String DAME_LOS_TOPIC_HACIA_MI = "DAME_LOS_TOPIC_HACIA_MI";

    //Codigos para conexion a la BBDD
//    String SERVIDOR_BBDD = "192.168.0.101";
    String SERVIDOR_BBDD = "192.168.1.100";
//    String SERVIDOR_BBDD = "10.20.32.116";
    String PUERTO_BBDD = "3306";
    String BBDD = "incarlopsa";
    String DIRECCION_BBDD_RAIZ = "jdbc:mysql://"+SERVIDOR_BBDD + ":" + PUERTO_BBDD
                            +"/"+BBDD;

    String USUARIO_TEST_NORMAL = "TESTUSER";
    String PASSWORD_TEST_NORMAL = "TESTUSER";
    String USUARIO_TEST_RRHH = "TESTRRHH";
    String PASSWORD_TEST_RRHH = "TESTRRHH";

    //Tipos de usuario
    String EMPLEADO = "Empleado";
    String RRHH = "RRHH";
    String ADMIN = "Admin";

    //Diferenciador para topics
    Integer TOPIC_CHAT_ENTRANTE = 0;
    Integer TOPIC_CHAT_SALIENTE = 1;
    Integer TOPIC_PUBLICACION = 0;

    //Limites del sistema
    Integer TAMANO_MAXIMO_TITULO = 50; //Caracteres
    Integer TAMANO_MAXIMO_FICHERO = 1048576; //Maximo de 1MB

    //Codigos para control de excepciones
    String ERROR_CONEXION_BBDD = "No se ha podido establecer la conexión con la base de datos";
    String ERROR_DRIVER = "Error en la carga del driver";
    String ERROR_LOGIN = "Usuario y contraseña inválidos";
    String ERROR_INTRODUCIR_DATOS = "Introduzca el usuario y contraseña";
    String NUMERO_PARAMETROS_INCORRECTO = "Llamada a función incorrecta! revisar el código";

    //Tostadas de chats/publicaciones
    String ERROR_MENSAJE_VACIO = "No pueden enviarse mensajes vacíos";
    String ERROR_ANUNCIO_VACIO = "No se pueden enviar anuncios vacíos";
    String ERROR_TITULO_VACIO_CHAT = "Debe introducir un título/asunto para el chat";
    String ERROR_USUARIO_NO_ELEGIDO = "No ha especificado el destinatario del chat";
    String CHAT_CREADO_CON_EXITO = "Chat creado con éxito!";
    String PUBLICACION_CREADA_CON_EXITO = "Publicación creada con éxito!";
    String TAMANO_TITULO_SUPERADO = "No se pueden hacer títulos con tamaño superior a " +
            ""+TAMANO_MAXIMO_TITULO.toString()+" caracteres";

    //Tostadas variopintas
    String BIENVENIDA = "Bienvenid@ a IncarlopsAPP!";
    String USUARIO_DESCONOCIDO = "Usuario desconocido";
    String CARGANDO = "Cargando...";
    String CONECTANDO = "Conectando...";

    //Tipos Likes
    String ME_GUSTA = "MeGusta";
    String NO_ME_GUSTA = "MeDisgusta";

    //Tostadas de ficheros
    String ERROR_NO_SE_PUEDE_LEER_IMAGEN = "No se puede leer la imagen del dispositivo";
    String IMAGEN_ANADIDA_CON_EXITO = "Imagen añadida con éxito";
    String ERROR_PERMISO_DENEGADO = "Permiso de lectura denegado";
    String ERROR_TAMANO_SUPERADO = "No se admiten imágenes de tamaño superior a 1MB";
    String FOTO_PERFIL_ACTUALIZADA = "Foto de perfil actualizada";

}
