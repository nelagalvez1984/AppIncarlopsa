package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VMainActivity extends AppCompatActivity implements IVista{


    //Propiedades



    @Override
    public void inicializarVista() {
        //ToDO
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,VLogin.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(this);

        //ToDO
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //ToDO
    }

}
