package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.util.concurrent.ExecutionException;

public class VLogin extends AppCompatActivity implements IVista{

    //Propiedades
    private EditText etUsuario;
    private EditText etPassword;
    private Button btnConectar;
    private SingleCredenciales credenciales;
    private HiloParaLogin hiloParaLogin;
    private Intent intent;
    private SingleTostada tostada = SingleTostada.getInstance();

    @Override
    public void inicializarVista() {
        etUsuario = (EditText)findViewById(R.id.etUsuario);
        etPassword = (EditText)findViewById(R.id.etPassword);
        btnConectar = (Button) findViewById(R.id.btnConectar);
        btnConectar.setOnClickListener(this);
        credenciales = SingleCredenciales.getInstance();
        tostada.setContexto(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnConectar:
                if (etUsuario.length()> 0 && etPassword.length() >0) {
                    credenciales.setLogin(etUsuario.getText().toString());
                    credenciales.setPassword(etPassword.getText().toString());

                    //                credenciales.setLogin(USUARIO_TEST_NORMAL);
                    //                credenciales.setPassword(PASSWORD_TEST_NORMAL);

                    Boolean conexionOK = false;

                    //1.- INTENTAR CONEXION
                    hiloParaLogin = new HiloParaLogin();
                    try {
                        conexionOK = hiloParaLogin.execute().get();
                    } catch (Exception e) {
                        tostada.errorConexionBBDD();
                    }

                    if (conexionOK) { //El usuario/pass es correcto!
                        intent = new Intent(this, VGeneral.class);
                        startActivity(intent);
                    } else {
                        tostada.errorLogin();
                    }
                }else{
                    tostada.errorIntroducirDatos();
                }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarVista();
    }
}
