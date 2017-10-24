package incarlopsa.com.appincarlopsa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class VLogin extends AppCompatActivity implements IVista{

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
        setContentView(R.layout.activity_login);
        inicializarVista();
    }
}
