package incarlopsa.com.appincarlopsa;

/**
 * Created by Nela on 26/10/2017.
 */

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HiloConexionCreate<T,Z> extends AsyncTask<ArrayList<Z>, Void, Void> implements ICodigos{

    private void ejemploLlamada(){
        //CODIGO DE EJEMPLO
        ArrayList<Usuario> arrayUsuario = new ArrayList<>();
        arrayUsuario.add(new Usuario());
        HiloConexionCreate<DAOUsuario,Usuario> prueba = new HiloConexionCreate<>().execute(arrayUsuario);
    }

    private void ejemploConexion(){
        //CODIGO DE EJEMPLO
        con = conexion.conectar();
    }

    //DAO

    IDAO<Z> dao;
    Connection con = null;
    Statement st;
    SingleConexion conexion = SingleConexion.getInstance();

    @Override
    protected Void doInBackground(ArrayList<Z>... parametros) {

        ArrayList<Z> array = parametros[0];

        try{

            Class.forName("com.mysql.jdbc.Driver");
            Log.v("Mysql","carga correcta del driver");

            for(int i=0; i<array.size(); i++){
                if (dao.create(array.get(i))){
                    Log.v("Mysql","Creacion correcta!");
                }
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