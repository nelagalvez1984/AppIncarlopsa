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


    static SingleConexion getInstance() {
        return ourInstance;
    }

    private SingleConexion() { }

    public Connection conectar() {
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

    public void desconectar(){

        if (conexion != null){
            try {
                conexion.close();
                System.out.println("Desconexion!");
            } catch (SQLException e) {
                singleTostada.errorConexionBBDD();
            }
        }

    }

}
