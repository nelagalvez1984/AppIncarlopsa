package incarlopsa.com.appincarlopsa;

import android.os.AsyncTask;
import android.util.Log;

import java.util.concurrent.ExecutionException;

public class HiloConexionReadUno<T,Z> extends AsyncTask<Integer, Void, Z> implements ICodigos {

    private void ejemploLlamada(){
        //CODIGO DE EJEMPLO
        Usuario usuarioPrueba = new Usuario(1,"a","b","c","d",new Foto("045450544".getBytes()));
        HiloConexionCreateUpdate<DAOUsuario,Usuario> hilo = new HiloConexionCreateUpdate<>(new DAOUsuario());
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

    public HiloConexionReadUno(T dao){
        this.dao = dao;
    }

    @Override
    protected Z doInBackground(Integer... parametros) {

        Integer id = parametros[0];
        Z retorno = null;

        boolean operacionCorrecta = false;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.v("Mysql","carga correcta del driver");
            retorno = ((IDAO<Z>)dao).read(id);
            if (retorno != null){
                Log.v("Mysql","Lectura correcta!");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return retorno;
    }

}
