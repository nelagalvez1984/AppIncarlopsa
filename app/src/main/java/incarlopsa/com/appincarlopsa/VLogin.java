package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VLogin extends AppCompatActivity implements IVista{

    //Propiedades
    EditText etUsuario;
    EditText etPassword;
    Button btnconectar;
    SingleCredenciales credenciales;

    @Override
    public void inicializarVista() {
        etUsuario = (EditText)findViewById(R.id.etUsuario);
        etPassword = (EditText)findViewById(R.id.etPassword);
        btnconectar = (Button) findViewById(R.id.btnconectar);
        btnconectar.setOnClickListener(this);
        btnconectar.setEnabled(false);
        credenciales = credenciales.getInstance();
    }

    @Override
    public void onClick(View v) {
     if (etUsuario.length()> 0) {
         //Los dos campos YA estan rellenos

         //Intentar conexion con ambos
         // -> Si true: Rellenas login+password del singleton
         //              Lanzas el intent
         // -> Si false: Toast


         Intent intent = new Intent(this, Conexion.class);
     }else{
         Toast.makeText(getApplicationContext(),
                 "Introduzca nombre y contraseña", Toast.LENGTH_SHORT);


     }




    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {


        return false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarVista();
    }
}
