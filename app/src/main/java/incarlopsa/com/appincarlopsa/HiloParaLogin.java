package incarlopsa.com.appincarlopsa;

import android.os.AsyncTask;

import java.sql.Connection;

public class HiloParaLogin extends AsyncTask<Object, Void, Boolean> implements ICodigos{

    //Propiedades
    SingleConexion conector = SingleConexion.getInstance();;
    Connection conexion;
    SingleTostada tostada = SingleTostada.getInstance();

    @Override
    protected Boolean doInBackground(Object... parametrosParaConsulta) {

        boolean operacionCorrecta = false;

        //Conectarse
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