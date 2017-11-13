package incarlopsa.com.appincarlopsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class SingleConexion implements ICodigos{
    private static final SingleConexion ourInstance = new SingleConexion();

    private SingleCredenciales credenciales;
    private Connection conexion = null;
    private String URLServidor;
    SingleTostada singleTostada = SingleTostada.getInstance();

    //Constructor
    private SingleConexion() { }

    //Get Instance
    static SingleConexion getInstance() {
        return ourInstance;
    }

    //Recuperar la conexion
    public Connection conectar() { return conexion; }

    //Crear una conexion (se emplea solo al inicio)
    public Connection conexionInicial() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("carga correcta del driver");
        } catch (Exception e) {
            singleTostada.errorDriver();
        }
        credenciales = SingleCredenciales.getInstance();
        URLServidor = DIRECCION_BBDD_RAIZ;
        String loginUsuario = credenciales.getLogin();
        String passwordUsuario = credenciales.getPassword();
        conexion = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection(URLServidor, loginUsuario, passwordUsuario);
            System.out.println("Conexion establecida!");
        } catch (Exception e) {
            singleTostada.errorConexionBBDD();
        }

        return conexion;
    }

    //Metodo simbolico
    public void desconectar(){
        // Nada
    }

    //Desconexion del sistema
    public void desconexionDelSistema(){
        if (conexion != null){
            try {
                conexion.close();
                credenciales.limpiarCredenciales();
                System.out.println("Desconexion!");
                conexion = null;
            } catch (SQLException e) {
                singleTostada.errorConexionBBDD();
            }
        }
    }
}
