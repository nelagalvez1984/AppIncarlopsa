package incarlopsa.com.appincarlopsa;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;


public class HiloTEST<T,Z> extends AsyncTask<Z, Void, Boolean> implements ICodigos{

    private void ejemploLlamada(){
        //CODIGO DE EJEMPLO
        Usuario usuarioPrueba = new Usuario(1,"a","b","c","d",null);
        HiloTEST<DAOUsuario,Usuario> hilo = new HiloTEST<>(new DAOUsuario());
        boolean retornoCreacion = false;
        try {
            retornoCreacion = hilo.execute(usuarioPrueba).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        /*     public HiloParaCreateUpdate(T dao) throws IllegalAccessException, InstantiationException {
        T var= (T) Object.class.newInstance();
        this.dao = dao;
        } */
    }

    private T dao;

    public HiloTEST(T dao) {
        super();
        this.dao = dao;
    }
    @Override
    protected Boolean doInBackground(Object... parametros) {

        int numParametros = parametros.length;
        Z dao = (Z)(parametros[0]);

        /*
        DAOUsuario dao2 = new DAOUsuario();
        dao2.create(new Usuario());
*/
        return false;
    }

}