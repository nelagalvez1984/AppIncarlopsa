package incarlopsa.com.appincarlopsa;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Nela on 06/11/2017.
 */

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


    //Constructor
    public HiloParaLogin() {

    }

    @Override
    protected Boolean doInBackground(Object... parametrosParaConsulta) {

        boolean operacionCorrecta = false;
        conector = SingleConexion.getInstance();
        conexion = conector.conectar();

        if (conexion != null){
            operacionCorrecta = true;
            conector.desconectar();
            System.out.println("Login correcto!");
        }else{
            System.out.println("Login incorrecto!");
        }

        return operacionCorrecta;
    }

}