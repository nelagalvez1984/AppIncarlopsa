package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements IVista{

    //Propiedades




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void inicializarVista() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

}
