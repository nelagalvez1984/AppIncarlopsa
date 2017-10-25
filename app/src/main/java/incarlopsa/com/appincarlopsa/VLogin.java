package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VLogin extends AppCompatActivity implements IVista{

    //Propiedades



    @Override
    public void inicializarVista() {
        //ToDo
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Conexion.class);
        EditText etUsuario = (EditText)findViewById(R.id.etUsuario);
        EditText etPassword = (EditText)findViewById(R.id.etPassword);




    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnconectar = (Button) findViewById(R.id.btnconectar);
        btnconectar.setOnClickListener(this);
        inicializarVista();
    }
}
