package incarlopsa.com.appincarlopsa;

import android.os.AsyncTask;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;


public class HiloParaCreateUpdate<T,Z> extends AsyncTask<Z, Void, Boolean> implements ICodigos{

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

    public HiloParaCreateUpdate(T dao) {
        this.dao = dao;
    }
    @Override
    protected Boolean doInBackground(Object... parametros) {

        int numParametros = parametros.length; //Si es 1, es una creacion. Si son 2, es un update

        boolean operacionCorrecta = false;
        try{

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("carga correcta del driver");
            if (numParametros == 1){ // Creacion
                operacionCorrecta = ((IDAO<Z>)dao).create((Z)parametros[0]);
                if (operacionCorrecta){
                    System.out.println("Creacion correcta!");
                }
            }else{ // Update
                operacionCorrecta = ((IDAO<Z>)dao).update((Z)parametros[0],
                                                    ((DataBaseItem)(Z)parametros[1]).getId());
                if (operacionCorrecta){
                    System.out.println("Actualizacion correcta!");
                }
            }
        } catch (SQLException e) {
            if(e.getErrorCode() == ENTRADA_DUPLICADA){
                System.out.println("Entrada duplicada");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return operacionCorrecta;
    }

}