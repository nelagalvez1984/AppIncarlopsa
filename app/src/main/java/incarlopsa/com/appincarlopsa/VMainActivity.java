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
        SingleCredenciales credenciales = SingleCredenciales.getInstance();
        credenciales.setLogin(USUARIO_TEST_NORMAL);
        credenciales.setPassword(PASSWORD_TEST_NORMAL);
/*        SingleConexion conexion = SingleConexion.getInstance();
        conexion.conectar();
        conexion.desconectar();
*/

        HiloConexionCreateUpdate<DAOUsuario,Usuario> hilo;
        try {
            Boolean retorno = new HiloTEST<>(new DAOUsuario()).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //ToDO
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //ToDO
    }

}
