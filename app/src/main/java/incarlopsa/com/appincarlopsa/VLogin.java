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
    EditText etUsuario;
    EditText etPassword;
    Button btnConectar;
    SingleCredenciales credenciales;
    HiloParaLogin hiloParaLogin;
    Intent intent;

    @Override
    public void inicializarVista() {
        etUsuario = (EditText)findViewById(R.id.etUsuario);
        etPassword = (EditText)findViewById(R.id.etPassword);
        btnConectar = (Button) findViewById(R.id.btnConectar);
        btnConectar.setOnClickListener(this);
        credenciales = SingleCredenciales.getInstance();

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
                        e.printStackTrace();
                    }

                    if (conexionOK) { //El usuario/pass es correcto!
                        intent = new Intent(this, VGeneral.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Usuario y contraseña inválidos", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Introduzca el usuario y contraseña", Toast.LENGTH_SHORT).show();
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
