package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Nela on 26/10/2017.
 */

private class HiloConexion<T> extends AsyncTask<V, W, X> implements ICodigos{

    //DAO

    //V

    Connection con = null;
    Statement st;

    @Override
    protected V doInBackground(V... params) {

        try{

            Class.forName("com.mysql.jdbc.Driver");
            Log.v("Mysql","carga correcta del driver");
            con = DriverManager.getConnection(SERVIDOR_BBDD, SingleCredenciales.LOGIN, SingleCredenciales.PASSWORD);


            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            int resultado=st.executeUpdate("insert into usuario (Nombre, Contraseña, Email) values ('"+fNombre+"','"+fPassword+"','"+fEMail+"')");

            if (resultado>TODO_OK) {
                Log.v("Mysql","Ingreso correcto");
            }


        } catch (SQLException e) {
            if(e.getErrorCode() == ENTRADA_DUPLICADA){
                Log.v("Mysql","Entrada duplicada");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(con != null){
                try {
                    st.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void result){

        super.onPostExecute(result);
    }
}