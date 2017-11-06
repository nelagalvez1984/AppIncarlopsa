package incarlopsa.com.appincarlopsa;

import android.os.AsyncTask;

import java.sql.SQLException;

/**
 * Created by Nela on 02/11/2017.
 */

public class HiloParaDelete extends AsyncTask<Object, Void, Boolean> implements ICodigos{


    //CODIGO DE EJEMPLO DE CREACION // Creacion --> llamada: execute(objetoACrear)
/*
    Usuario usuarioParaCrear = new Usuario(1,"a","b","c","d",null);
    HiloParaCreate hilo = new HiloParaCreate(new DAOUsuario());
    boolean retornoCreacion = false;
    try {
        retornoCreacion = hilo.execute(usuarioParaCrear).get();
    } catch (Exception e) {
        e.printStackTrace();
    }
*/

    //Propiedades
    private DAOBase dao;

    //Constructor
    public HiloParaDelete(DAOBase dao) {
        this.dao = dao;
    }

    @Override
    protected Boolean doInBackground(Object... parametrosParaConsulta) {

        int numParametros = parametrosParaConsulta.length; //Debe ser 1, si no es un error en la llamada

        boolean operacionCorrecta = false;

        if (numParametros==1) { //Para los borrados solo puede haber un parametro!
            operacionCorrecta = (dao.delete(parametrosParaConsulta[0]));
            if (operacionCorrecta) {
                System.out.println("Creacion correcta!");
            } else {
                System.out.println("No se ha podido borrar!");
            }
        }else{ //Error en la llamada!
            return null;
        }

        return operacionCorrecta;
    }

}