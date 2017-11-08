package incarlopsa.com.appincarlopsa;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.SQLException;

public class HiloParaLogin extends AsyncTask<Object, Void, Boolean> implements ICodigos{


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
    SingleConexion conector;
    Connection conexion;
    SingleTostada tostada = SingleTostada.getInstance();

    @Override
    protected Boolean doInBackground(Object... parametrosParaConsulta) {

        boolean operacionCorrecta = false;


        conector = SingleConexion.getInstance();
        if (conector.conectar() == null){
            conexion = conector.conexionInicial();
        }

        if (conexion != null){
            operacionCorrecta = true;
            tostada.bienvenida();
            System.out.println("Login correcto!");
        }else{
            tostada.errorLogin();
            System.out.println("Login incorrecto!");
        }

        return operacionCorrecta;
    }

}