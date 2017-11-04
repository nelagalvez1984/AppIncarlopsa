package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class VMainActivity extends AppCompatActivity implements IVista, ICodigos {

    //Propiedades
    private SingleCredenciales credenciales = SingleCredenciales.getInstance();
    private TESTFactoriaItems factoriaItems = new TESTFactoriaItems();

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private ImageView fondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupDrawer(navigationView);
        }

        setupDrawer(navigationView);

        inicializarVista();

    }

    private void setupDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_publicaciones: //Ver publicaciones
                                menuItem.setChecked(true);
                                //Hacer cosas
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_chats: //Ver chats
                                menuItem.setChecked(true);
                                //Hacer cosas
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_nuevo_chat: //Crear chat
                                menuItem.setChecked(true);
                                //Hacer cosas
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_nueva_publicacion: //Crear publicacion
                                menuItem.setChecked(true);
                                //Hacer cosas
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent = new Intent(VMainActivity.this, SettingsActivity.class);
                                startActivity(intent);
                                return true;
                        }
                        return true;
                    }
                });
    }

    @Override
    public void inicializarVista() {

        credenciales.setLogin(USUARIO_TEST_NORMAL);
        credenciales.setPassword(PASSWORD_TEST_NORMAL);

        //Fondo
        fondo = (ImageView)findViewById(R.id.fondoInicial);
        fondo.setOnClickListener(this);

        //Sustituir toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ActionBar
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);


        //Fijar el drawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View v) {
        //Al hacer click en el fondo, sacar el menu
        switch (v.getId()){
            case R.id.fondoInicial:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }


    }

    private void tests(){
        /*
        // llamamos al toolbar creado
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inicializarVista();

        Usuario u = factoriaItems.testCREARUsuario();
*/

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

        /*
/*
        resultados = factoriaItems.testReadGenerico(DAME_LOS_TOPIC_HACIA_MI, new DAOChat());
*/
/*
        Chat c = new Chat();
        c.setIdUsuario(singleCredenciales.getIdUsuario()); // 2
        c.setIdUsuarioDestino(1);
        c.setTitulo("PERICOOOO, cushame!");

        retornoVerdaderoOFalso = factoriaItems.testCreateGenerico(c,new DAOChat());
*//*

        Mensaje m = new Mensaje();
        m.setIdPublicacion(5);
        m.setIdUsuario(3);
        m.setMensaje("Aqui venimoooooos 2");

        retornoVerdaderoOFalso = factoriaItems.testCreateGenerico(m , new DAOMensaje());
*/
/*
        Mensaje m = new Mensaje();
        m.setIdPublicacion(5);
        m.setId(7);
        m.setMensaje("Ya no wapi");
        m.setIdUsuario(4);
        m.setLeidoPorDestino(false);

        retornoVerdaderoOFalso = factoriaItems.testUpdateGenerico(m, new DAOMensaje());
*/
/*

        Chat c = new Chat();
        c.setId(5);
        retornoVerdaderoOFalso = factoriaItems.testDeleteGenerico(c , new DAOChat());
*/
/*
        resultados = factoriaItems.testReadGenerico(c , new DAOChat());

        c = (Chat)resultados.get(0);
        c.setTitulo("Lo he cambiado!");
        retornoVerdaderoOFalso = factoriaItems.testUpdateGenerico(c, new DAOChat());
*/
    }

}
