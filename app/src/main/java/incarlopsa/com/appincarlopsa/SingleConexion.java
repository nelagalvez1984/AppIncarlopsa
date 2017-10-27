package incarlopsa.com.appincarlopsa;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Nela on 26/10/2017.
 */

class SingleConexion implements ICodigos{
    private static final SingleConexion ourInstance = new SingleConexion();

    Connection con = null;
    String URLServidor;

    static SingleConexion getInstance() {
        return ourInstance;
    }

    private SingleConexion() { }

    public Connection conectar(){
        URLServidor = DIRECCION_BBDD_RAIZ;
        String loginUsuario = SingleCredenciales.LOGIN;
        String passwordUsuario = SingleCredenciales.PASSWORD;
        con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(URLServidor, loginUsuario , passwordUsuario);
            Log.v("Mysql","Me conecte!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return con;
    }

    public void desconectar(){
        try {
            con.close();
            Log.v("Mysql","Me desconecte!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
