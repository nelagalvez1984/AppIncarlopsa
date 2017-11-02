package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class VMainActivity extends AppCompatActivity implements IVista{


    //Propiedades
    TESTFactoriaItems factoriaItems = new TESTFactoriaItems();
    Button boton;

    @Override
    public void inicializarVista() {
      //   boton = (Button)findViewById(R.id.btnTest);
         boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boton.setText(factoriaItems.dameCadenaAleatoria());
        //ToDO
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarVista();
        boton.setText(factoriaItems.dameCadenaAleatoria());

        //ToDO
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //ToDO
    }

}
