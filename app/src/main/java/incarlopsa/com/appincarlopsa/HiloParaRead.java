package incarlopsa.com.appincarlopsa;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class HiloParaRead<T,Z> extends AsyncTask<Integer, Void, ArrayList<DataBaseItem>> implements ICodigos {

    private void ejemploLlamada(){
        //CODIGO DE EJEMPLO
        Usuario usuarioPrueba = new Usuario(1,"a","b","c","d",null);
        HiloParaCreateUpdate<DAOUsuario,Usuario> hilo = new HiloParaCreateUpdate<>(new DAOUsuario());
        boolean retornoCreacion = false;
        try {
            retornoCreacion = hilo.execute(usuarioPrueba).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private T dao;

    public HiloParaRead(T dao){
        this.dao = dao;
    }

    @Override
    protected ArrayList<DataBaseItem> doInBackground(Integer... parametros) {

        Integer id = parametros[0];
        ArrayList<DataBaseItem> retorno = null;

        boolean operacionCorrecta = false;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.v("Mysql","carga correcta del driver");
            try {
                retorno = ((IDAO<Z>)dao).read(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (retorno != null){
                Log.v("Mysql","Lectura correcta!");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return retorno;
    }

}
