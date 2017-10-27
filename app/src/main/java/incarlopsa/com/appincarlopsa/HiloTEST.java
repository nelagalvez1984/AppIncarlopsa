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
        Usuario usuarioPrueba = new Usuario(1,"a","b","c","d",new Foto("045450544".getBytes()));
        HiloTEST<DAOUsuario,Usuario> hilo = new HiloTEST<>(new DAOUsuario());
        boolean retornoCreacion = false;
        try {
            retornoCreacion = hilo.execute(usuarioPrueba).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        /*     public HiloConexionCreateUpdate(T dao) throws IllegalAccessException, InstantiationException {
        T var= (T) Object.class.newInstance();
        this.dao = dao;
        } */
    }

    private T dao;

    public HiloTEST(T dao) {
        this.dao = dao;
    }
    @Override
    protected Boolean doInBackground(Z... parametros) {



        DAOUsuario dao2 = new DAOUsuario();
        dao2.create(new Usuario());

        return false;
    }

}