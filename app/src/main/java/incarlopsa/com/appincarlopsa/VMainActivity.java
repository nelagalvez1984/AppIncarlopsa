package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import java.util.ArrayList;


public class VMainActivity extends AppCompatActivity implements IVista, ICodigos {

    // propiedad para la barra de herramientas
    // propiedad para la barra de herramientas
    private Toolbar toolbar;

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
    Boolean retornoVerdaderoOFalso = false;


    @Override
    public void inicializarVista() {
        credenciales.setLogin(USUARIO_TEST_NORMAL);
        credenciales.setPassword(PASSWORD_TEST_NORMAL);
        boton1 = (Button) findViewById(R.id.btnTest1);
        boton2 = (Button) findViewById(R.id.btnTest2);
        boton3 = (Button) findViewById(R.id.btnTest3);
        boton4 = (Button) findViewById(R.id.btnTest4);
        boton5 = (Button) findViewById(R.id.btnTest5);
        boton6 = (Button) findViewById(R.id.btnTest6);
        boton7 = (Button) findViewById(R.id.btnTest7);
        boton8 = (Button) findViewById(R.id.btnTest8);

        boton8.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
/*
        tamano = resultados.size();
        Usuario temp = (Usuario)resultados.get(contador);
        contador++;
        contador %= tamano;

        //PARA READ
        boton1.setText(temp.getIdUsuario().toString());
        boton2.setText(temp.getNombre());
        boton3.setText(temp.getApellidos());
        boton4.setText(temp.getDni());
        boton5.setText(temp.getTipoEmpleado());
        boton6.setText(temp.getFoto().toString());
 */
        //PARA CREATE
        boton1.setText(retornoVerdaderoOFalso.toString());
        //boton7.setText(temp.getUsername());

        //ToDO
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // llamamos al toolbar creado
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inicializarVista();

        Usuario u = factoriaItems.testCREARUsuario();


//        Usuario x = new Usuario();
//        x.setIdUsuario(8);

//      resultados = factoriaItems.testReadGenerico(DAME_TODOS, new DAOUsuario());
//      tamano = resultados.size();

//        retornoVerdaderoOFalso = factoriaItems.testCreateGenerico(u,new DAOUsuario());
//        resultados = factoriaItems.testReadGenerico(x, new DAOUsuario());
//        x = (Usuario)resultados.get(0);
//        x.setNombre("HOLA");
//        retornoVerdaderoOFalso = factoriaItems.testUpdateGenerico(x, new DAOUsuario());
//        retornoVerdaderoOFalso = factoriaItems.testDeleteGenerico(x, new DAOUsuario());

//        TipoFichero t = factoriaItems.testCREARTipoFichero();
//        TipoFichero t = new TipoFichero();
//        t.setId(4);
//        resultados = factoriaItems.testReadGenerico(t, new DAOTipoFichero());
//        t = (TipoFichero)resultados.get(0);
//        t.setNombre("HOLA");
//        retornoVerdaderoOFalso = factoriaItems.testUpdateGenerico(t,new DAOTipoFichero());
//        retornoVerdaderoOFalso = factoriaItems.testDeleteGenerico(t,new DAOTipoFichero());

//        Adjunto a = factoriaItems.testCREARAdjunto();
//        a.setIdTipoFichero(3);
//        retornoVerdaderoOFalso = factoriaItems.testCreateGenerico(a, new DAOAdjunto());

/*        TipoFichero t = new TipoFichero();
        t.setId(3);
        resultados = factoriaItems.testReadGenerico(t, new DAOTipoFichero());

        Adjunto a = new Adjunto();
        a.setId(3);
        a.setIdTipoFichero(3);
        a.setTipo((TipoFichero)resultados.get(0));

        resultados = factoriaItems.testReadGenerico(a, new DAOAdjunto());

        Adjunto a2 = (Adjunto)resultados.get(0);
        a2.setLocalizacion("PAMPLONAAA");
        retornoVerdaderoOFalso = factoriaItems.testUpdateGenerico(a2, new DAOAdjunto());

        Adjunto a = new Adjunto();
        a.setId(5);
        retornoVerdaderoOFalso = factoriaItems.testDeleteGenerico(a, new DAOAdjunto());
*/
/*        Comentario p = new Comentario();
        p.setIdPublicacion(2);
        resultados = factoriaItems.testReadGenerico(p, new DAOComentario());

        p = (Comentario)resultados.get(0);
        p.setMensaje("Aqui estoy again!");
        retornoVerdaderoOFalso = factoriaItems.testUpdateGenerico(p, new DAOComentario());
*/
/*
        Publicacion p = factoriaItems.testCREARPublicacion();
        retornoVerdaderoOFalso = factoriaItems.testCreateGenerico(p , new DAOPublicacion());
*/
/*
        Comentario c = new Comentario();
        c.setId(4);
        retornoVerdaderoOFalso = factoriaItems.testDeleteGenerico(c , new DAOComentario());
*/

/*        MeGusta m = new MeGusta();
        m.setIdUsuario(3);
        m.setIdComentario(3);
        retornoVerdaderoOFalso = factoriaItems.testCreateGenerico(m , new DAOLikes());
*/
/*
        Publicacion p = new Publicacion();
        p.setId(2);
        resultados = factoriaItems.testReadGenerico(p , new DAOPublicacion());
        p = (Publicacion)resultados.get(0);
        p.setTitulo("HOLAAAAA");
        retornoVerdaderoOFalso = factoriaItems.testUpdateGenerico(p, new DAOPublicacion());
*/
/*
        Publicacion p = new Publicacion();
        p.setId(6);
        retornoVerdaderoOFalso = factoriaItems.testDeleteGenerico(p, new DAOPublicacion());
*/
/*
        resultados = factoriaItems.testReadGenerico(DAME_LOS_TOPIC , new DAOPublicacion());
        */

        SingleCredenciales singleCredenciales = SingleCredenciales.getInstance();
        singleCredenciales.setIdUsuario(2);
/*
        resultados = factoriaItems.testReadGenerico(DAME_LOS_TOPIC_HACIA_MI, new DAOChat());

        Chat c = new Chat();
        c.setIdUsuario(2);
        c.setIdUsuarioDestino(1);
        c.setTitulo("PERICOOOO, cushame!");
*/


        System.out.print("QUIETOOOL");
        System.out.print("QUIETOOOL-2");
        //ToDO
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //ToDO
    }

    // éste método sirve para crear las opciones del menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //
    @Override
    public boolean onOptionsItemSelected(MenuItem opcionMenu) {
        int id = opcionMenu.getItemId();

        if (id == R.id.mensaje) {
            return true;
        }

        if (id == R.id.chat) {
            return true;
        }
        return super.onOptionsItemSelected(opcionMenu);
    }

}
