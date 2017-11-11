package incarlopsa.com.appincarlopsa;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
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
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;

public class VGeneral extends AppCompatActivity implements IVista, ICodigos {

    //Propiedades
    private SingleCredenciales credenciales = SingleCredenciales.getInstance();
    private TESTFactoriaItems factoriaItems = new TESTFactoriaItems();

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private ImageView fondo;
    private ImageView fotoUsuario;
    private Intent intent;
    private SingleTostada tostada = SingleTostada.getInstance();
    private ArrayList<DataBaseItem> resultados;
    NavigationView navigationView;
    private Foto fotoAux;
    private String filePath = "";
    private Uri uri;
    private HiloParaUpdate hiloParaUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vgeneral_empleado);
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
                credenciales.setTipoEmpleado(u.getTipoEmpleado());
                credenciales.setFotoBytes(u.getFoto());
            }else{
                tostada.errorConexionBBDD();
            }

        } catch (Exception e) {
            tostada.errorConexionBBDD();
        }


        //TODO aqui sigue con naturalidad

        inicializarVista();

    }

    @Override
    public void inicializarVista() {

        //Fijar el layout segun el tipo de empleado
        switch (credenciales.getTipoEmpleado()){
            case RRHH:
                setContentView(R.layout.activity_vgeneral_rrhh);
                break;
            case ADMIN:
                setContentView(R.layout.activity_vgeneral_admin);
                break;
            default: //Empleado
                setContentView(R.layout.activity_vgeneral_empleado);
                break;
        }

        //Fijar el navigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        if (navigationView != null) {
            setupDrawer(navigationView);
        }

        setupDrawer(navigationView);

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

        //Poner si procede la foto de usuario en el drawer

        View headerView = navigationView.inflateHeaderView(R.layout.navigation_drawer_header);
        fotoUsuario = (ImageView)headerView.findViewById(R.id.drawerFotoUsuario);
        fotoUsuario.setOnClickListener(this);
        Foto fotoAux = credenciales.getFoto();
        if (fotoAux.getFotoBytes() != null){ //Si ha personalizado la foto, ponerla, si no dejar la default
            fotoUsuario.setImageBitmap(fotoAux.getFotoBMP());
        }
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
                            case R.id.item_navigation_drawer_nuevo_chat: //Crear chat
                                menuItem.setChecked(true);
                                //Hacer cosas
                                drawerLayout.closeDrawer(GravityCompat.START);
                                intent = new Intent(VGeneral.this, VCrearNuevoChat.class);
                                startActivity(intent);
                                return true;
                            case R.id.item_navigation_drawer_nueva_publicacion: //Crear publicacion
                                menuItem.setChecked(true);
                                //Hacer cosas
                                drawerLayout.closeDrawer(GravityCompat.START);
                                intent = new Intent(VGeneral.this, VCrearNuevaPublicacion.class);
                                startActivity(intent);
                                return true;
                            case R.id.item_navigation_drawer_administracion: //Cosas de administrador
                                //TODO
                                return true;
                        }
                        return true;
                    }
                });
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
            case R.id.drawerFotoUsuario:
                intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, CODIGO_DEVOLUCION_FOTO);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_DEVOLUCION_FOTO && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                //Recoger path
                uri = data.getData();
                agregarAdjunto();

            }
        }
        tostada.setContexto(this);
    }

    public void agregarAdjunto() {

        ActivityCompat.requestPermissions(VGeneral.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                CODIGO_DEVOLUCION_PATH);
    }


    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults) {
        switch (requestCode) {
            case CODIGO_DEVOLUCION_PATH: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    recogerPath();
                    recogerFoto();

                } else {
                    filePath = "";
                    tostada.errorPermisoDenegado();
                }

            }
        }
    }


    private void recogerPath() {
        // Image pick from recent
        String wholeID = DocumentsContract.getDocumentId(uri);

        // Split at colon, use second item in the array
        String id = wholeID.split(":")[1];

        String[] column = {MediaStore.Images.Media.DATA};

        // where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = this.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, new String[]{id}, null);

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
    }

    private void recogerFoto() {
        try {

            //Recoger la foto
            if (filePath.length()==0){
                throw new EXCFotoNoSePuedeLeer();
            }
            File file = new File(filePath);
            Boolean siONo = file.canRead();
            byte[] bytes = FileUtils.readFileToByteArray(file);
            Integer longitud = bytes.length;
            if (longitud>1048576){//Maximo de 1MB
                throw new EXCTamanoSuperado();
            }

            //Crear la Foto
            fotoAux = new Foto(bytes);

            //Preparar un usuario auxiliar al que volcar las credenciales y nueva foto
            Usuario usuarioAux = new Usuario();
            usuarioAux.setIdUsuario(credenciales.getIdUsuario());
            usuarioAux.setNombre(credenciales.getNombre());
            usuarioAux.setApellidos(credenciales.getApellidos());
            usuarioAux.setTipoEmpleado(credenciales.getTipoEmpleado());
            usuarioAux.setDni(credenciales.getDni());
            usuarioAux.setUsername(credenciales.getUsername());
            usuarioAux.setFotoBytes(fotoAux);

            //Actualizar la imagen del usuario en la BBDD
            Boolean todoOk = false;
            hiloParaUpdate = new HiloParaUpdate(new DAOUsuario());
            todoOk = hiloParaUpdate.execute(usuarioAux).get();
            if (!todoOk){
                throw new EXCErrorBBDD();
            }

            //Actualizar la imagen del drawer
            fotoUsuario.setImageBitmap(fotoAux.getFotoBMP());

            //Foto actualizada
            tostada.fotoDePerfilActualizada();

        } catch (EXCTamanoSuperado e) {
            tostada.errorTamanoSuperado();
        } catch (EXCFotoNoSePuedeLeer e) {
            tostada.errorNoSePuedeLeerImagen();
        } catch (EXCErrorBBDD e){
            tostada.errorConexionBBDD();
        } catch (Exception e){
            tostada.errorConexionBBDD();
        }
    }



}
