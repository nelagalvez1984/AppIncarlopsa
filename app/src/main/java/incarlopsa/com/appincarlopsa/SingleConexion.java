package incarlopsa.com.appincarlopsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Nela on 26/10/2017.
 */

class SingleConexion implements ICodigos{
    private static final SingleConexion ourInstance = new SingleConexion();

    Connection con = null;
    String IPServidor;
    String PuertoServidor;

    static SingleConexion getInstance() {
        return ourInstance;
    }

    private SingleConexion() {
        IPServidor = SERVIDOR_BBDD;
        PuertoServidor = PUERTO_BBDD;
    }

    public Connection conectar(){
        con = null;

        try {
            con = DriverManager.getConnection(SERVIDOR_BBDD, SingleCredenciales.LOGIN, SingleCredenciales.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public void desconectar(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
