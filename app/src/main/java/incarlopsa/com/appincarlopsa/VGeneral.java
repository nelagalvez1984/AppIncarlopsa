package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class VGeneral extends AppCompatActivity implements IVista, ICodigos {

    //Propiedades
    private SingleCredenciales credenciales = SingleCredenciales.getInstance();
    private TESTFactoriaItems factoriaItems = new TESTFactoriaItems();

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private ImageView fondo;
    private Intent intent;
    private SingleTostada tostada = SingleTostada.getInstance();
    private ArrayList<DataBaseItem> resultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tostada.setContexto(this);

        //todo RETIRAR ESTO, ESTA DE PRUEBAS!
        credenciales.setLogin(USUARIO_TEST_NORMAL);
        credenciales.setPassword(PASSWORD_TEST_NORMAL);
        credenciales.setUsername(USUARIO_TEST_NORMAL);
        //1.- INTENTAR CONEXION
        HiloParaLogin hiloParaLogin = new HiloParaLogin();
        try {
            hiloParaLogin.execute().get();
        } catch (Exception e) {
            tostada.errorConexionBBDD();
        }

        HiloParaRead hilo = new HiloParaRead(new DAOUsuario());
        resultados = new ArrayList<>();
        try {
            resultados = hilo.execute(credenciales).get();
            if (resultados.size()>0){
                Usuario u = (Usuario)resultados.get(0);
                credenciales.setIdUsuario(u.getIdUsuario());
                credenciales.setNombre(u.getNombre());
                credenciales.setApellidos(u.getApellidos());
                credenciales.setDni(u.getDni());
                credenciales.setFotoBytes(u.getFoto());
            }else{
                tostada.errorConexionBBDD();
            }

        } catch (Exception e) {
            tostada.errorConexionBBDD();
        }


        //TODO aqui sigue con naturalidad

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
                                drawerLayout.closeDrawer(GravityCompat.START);
                                intent = new Intent(VGeneral.this, VCabeceraPublicacion.class);
                                startActivity(intent);
                                return true;
                            case R.id.item_navigation_drawer_chats: //Ver chats
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                intent = new Intent(VGeneral.this, VCabeceraChat.class);
                                startActivity(intent);
                                return true;
                            case R.id.item_navigation_drawer_nuevo_chat: //Crear mensaje
                                menuItem.setChecked(true);
                                //Hacer cosas
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_nueva_publicacion: //Crear comentario
                                menuItem.setChecked(true);
                                //Hacer cosas
                                drawerLayout.closeDrawer(GravityCompat.START);
                                intent = new Intent(VGeneral.this, TESTSettingsActivity.class);
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
    /* éstas líneas son necesarias para el permiso de descargar archivos de manera externa pero no sé cómo va la cosa*/
                // el enlace es el de abajo
                // https://androidstudiofaqs.com/tutoriales/dar-permisos-a-aplicaciones-en-android-studio
                /*
+    int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
+    if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
+
+    {
+
+        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
+                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
+
+        } else {
+
+            ActivityCompat.requestPermissions(this,
+                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
+                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
+        }
+    }
+
+    @Override
+    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
+        switch (requestCode) {
+            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
+                // If request is cancelled, the result arrays are empty.
+                if (grantResults.length >
+                        && grantResults[] == PackageManager.PERMISSION_GRANTED) {
+
+                } else {
+
+                }
+                return;
+            }
+        }
+    }
+    */
                /* hasta aquí*/

}
