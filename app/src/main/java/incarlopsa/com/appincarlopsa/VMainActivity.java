package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

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

        Usuario usuario = new Usuario(1,"Aqui estoy!","b","33434","hola",null);

        ArrayList<DataBaseItem> array = new ArrayList<>();
        array.add(usuario);
        Usuario sacado = (Usuario)array.get(0);
        Button b = (Button)findViewById(R.id.btnTest);
        b.setText(sacado.getNombre());

        //ToDO
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //ToDO
    }

}
