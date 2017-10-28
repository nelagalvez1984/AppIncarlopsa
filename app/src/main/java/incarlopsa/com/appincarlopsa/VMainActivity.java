package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.concurrent.ExecutionException;


public class VMainActivity extends AppCompatActivity implements IVista{


    //Propiedades



    @Override
    public void inicializarVista() {
        //ToDO
    }

    @Override
    public void onClick(View v) {
        //ToDO
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Cargar los SingleTons
        SingleConexion conexion = SingleConexion.getInstance();
        SingleCredenciales credenciales = SingleCredenciales.getInstance();
        //Inyectar a las credenciales el usuario y el password
        //sea desde un TextView o en modo test usando los usuarios:
        //USUARIO_TEST_NORMAL y USUARIO_TEST_RRHH
        //junto con los passwords:
        //PASSWORD_TEST_NORMAL y PASSWORD_TEST_RRHH
        credenciales.setLogin(USUARIO_TEST_NORMAL);
        credenciales.setPassword(PASSWORD_TEST_NORMAL);
        //Comprobar si el user//pass son correctos
        if (conexion.conectar() != null) { //Conexion establecida!
            conexion.desconectar(); //Conexion anulada!
            //HACER COSAS
        }


        Usuario usuario = new Usuario(1,"a","b","33434","hola",null);


        //ToDO
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //ToDO
    }

}
