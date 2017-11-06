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

public class VLogin extends AppCompatActivity implements IVista{

    //Propiedades
    EditText etUsuario;
    EditText etPassword;
    Button btnConectar;
    SingleCredenciales credenciales;
    SingleConexion conector;
    Connection conexion;
    Intent intent;

    @Override
    public void inicializarVista() {
        etUsuario = (EditText)findViewById(R.id.etUsuario);
        etPassword = (EditText)findViewById(R.id.etPassword);
        btnConectar = (Button) findViewById(R.id.btnconectar);
        btnConectar.setOnClickListener(this);
        btnConectar.setEnabled(false);
        credenciales = SingleCredenciales.getInstance();
        conector = SingleConexion.getInstance();
    }

    @Override
    public void onClick(View v) {

        credenciales.setLogin(etUsuario.getText().toString());
        credenciales.setPassword(etPassword.getText().toString());

        Boolean conexionOK = false;

        conector.conectar();

        //ToDO
        //1.- INTENTAR CONEXION (devolver la condicion a la variable conexionOK


        if (conexionOK){
            //2.- Conseguir el usuario correspondiente a ese login
            //3.- Enchufar sus datos al credenciales
  //          intent = new Intent(this, destino.class); //AUN NO ESTA ESCRITA ESA VISTA!!!
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),
                    "Introduzca nombre y contraseña", Toast.LENGTH_SHORT);
        }
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (etUsuario.length()> 0 && etPassword.length() >0) {
            btnConectar.setEnabled(true);
        }else{
            btnConectar.setEnabled(false);
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarVista();
    }
}
