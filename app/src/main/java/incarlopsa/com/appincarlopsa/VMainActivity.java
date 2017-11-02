package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class VMainActivity extends AppCompatActivity implements IVista, ICodigos{


    //Propiedades
    SingleCredenciales credenciales = SingleCredenciales.getInstance();
    TESTFactoriaItems factoriaItems = new TESTFactoriaItems();
    ArrayList<DataBaseItem> resultados;
    Button boton1;
    Button boton2;
    Button boton3;
    Button boton4;
    Button boton5;
    Button boton6;
    Button boton7;
    Button boton8;
    Integer tamano;
    Integer contador = 0;

    @Override
    public void inicializarVista() {
        credenciales.setLogin(USUARIO_TEST_NORMAL);
        credenciales.setPassword(PASSWORD_TEST_NORMAL);
        boton1 = (Button)findViewById(R.id.btnTest1);
        boton2 = (Button)findViewById(R.id.btnTest2);
        boton3 = (Button)findViewById(R.id.btnTest3);
        boton4 = (Button)findViewById(R.id.btnTest4);
        boton5 = (Button)findViewById(R.id.btnTest5);
        boton6 = (Button)findViewById(R.id.btnTest6);
        boton7 = (Button)findViewById(R.id.btnTest7);
        boton8 = (Button)findViewById(R.id.btnTest8);

         boton8.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Usuario temp = (Usuario)resultados.get(contador);
        contador++;
        contador%=tamano;

        boton1.setText(temp.getIdUsuario());
        boton2.setText(temp.getNombre());
        boton3.setText(temp.getApellidos());
        boton4.setText(temp.getDni());
        boton5.setText(temp.getTipoEmpleado());
        boton6.setText(temp.getFoto().toString());
        boton7.setText(temp.getUsername());

        //ToDO
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarVista();

        resultados = factoriaItems.testReadUsuario(DAME_TODOS);
        tamano = resultados.size();

        //ToDO
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //ToDO
    }

}
