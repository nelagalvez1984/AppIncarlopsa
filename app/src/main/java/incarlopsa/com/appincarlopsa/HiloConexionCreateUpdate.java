package incarlopsa.com.appincarlopsa;

import android.os.AsyncTask;
import android.util.Log;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.ExecutionException;


public class HiloConexionCreateUpdate<T,Z> extends AsyncTask<Z, Void, Boolean> implements ICodigos{

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

    public HiloConexionCreateUpdate(T dao){
        this.dao = dao;
    }
    @Override
    protected Boolean doInBackground(Z... parametros) {

        int numParametros = parametros.length; //Si es 1, es una creacion. Si son 2, es un update

        Z primerValorRecogido = parametros[0];
        Z segundoValorRecogido = null;
        if (numParametros == 2){
            segundoValorRecogido = parametros[1];
        }
        boolean operacionCorrecta = false;
        try{

            Class.forName("com.mysql.jdbc.Driver");
            Log.v("Mysql","carga correcta del driver");
            if (numParametros == 1){ // Creacion
                operacionCorrecta = ((IDAO<Z>)dao).create(primerValorRecogido);
                if (operacionCorrecta){
                    Log.v("Mysql","Creacion correcta!");
                }
            }else{ // Update

                operacionCorrecta = ((IDAO<Z>)dao).update(((DataBaseItem)primerValorRecogido).getId(),
                                                segundoValorRecogido);
                if (operacionCorrecta){
                    Log.v("Mysql","Actualizacion correcta!");
                }
            }

        } catch (SQLException e) {
            if(e.getErrorCode() == ENTRADA_DUPLICADA){
                Log.v("Mysql","Entrada duplicada");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return operacionCorrecta;
    }

}