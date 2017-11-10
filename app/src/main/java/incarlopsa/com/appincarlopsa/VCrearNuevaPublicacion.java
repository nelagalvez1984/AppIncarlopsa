package incarlopsa.com.appincarlopsa;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VCrearNuevaPublicacion extends AppCompatActivity implements IVista{
    private EditText tituloNuevaPublicacion;
    private EditText anuncioNuevaPublicacion;
    private ImageView anadirAdjunto;
    private ImageButton enviar;
    private TextView adjuntosAgregados;
    private SingleCredenciales credenciales = SingleCredenciales.getInstance();
    private SingleTostada tostada = SingleTostada.getInstance();
    private HiloParaCreate hiloParaCreate;
    private HiloParaRead hiloParaRead;
    private ArrayList<DataBaseItem> resultadosPublicaciones;
    private ArrayList<DataBaseItem> listaAdjuntos;
    private static final int READ_REQUEST_CODE = 42;
    private static String filePath = "";
    private static Uri uri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vcrear_nueva_publicacion);
        inicializarVista();
    }

    @Override
    public void inicializarVista() {
        tostada.setContexto(this);
        listaAdjuntos = new ArrayList<>();
        tituloNuevaPublicacion = (EditText)findViewById(R.id.editNuevaPublicacionTitulo);
        anuncioNuevaPublicacion = (EditText)findViewById(R.id.editNuevaPublicacionAnuncio);
        anadirAdjunto = (ImageView)findViewById(R.id.imgNuevaPublicacionBotonAnadir);
        anadirAdjunto.setOnClickListener(this);
        enviar = (ImageButton)findViewById(R.id.btnNuevaPublicacionEnviar);
        enviar.setOnClickListener(this);
        adjuntosAgregados = (TextView)findViewById(R.id.txtNuevaPublicacionAdjuntos);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNuevaPublicacionEnviar:
                if (tituloNuevaPublicacion.length()==0){
                    tostada.errorTituloVacioChat();
                }else{
                    if (anuncioNuevaPublicacion.length()==0){
                        tostada.errorAnuncioVacio();
                    }else{

                        try{
                            //Crear la publicacion
                            Boolean todoOK = false;
                            Publicacion publicacionAux = new Publicacion();
                            publicacionAux.setTitulo(anuncioNuevaPublicacion.getText().toString());
                            publicacionAux.setIdUsuario(credenciales.getIdUsuario());
                            publicacionAux.setId(null);
                            hiloParaCreate = new HiloParaCreate(new DAOPublicacion());
                            todoOK = hiloParaCreate.execute(publicacionAux).get();
                            if (!todoOK){
                                throw new Exception();
                            }

                            //Recoger la publicacion
                            hiloParaRead = new HiloParaRead(new DAOPublicacion());
                            resultadosPublicaciones = hiloParaRead.execute(publicacionAux).get();

                            if (resultadosPublicaciones.size() == 0){
                                throw new Exception();
                            }

                            //Recoger solo la primera publicacion
                            publicacionAux = (Publicacion)resultadosPublicaciones.get(0);

                            //Crear el primer comentario
                            Comentario comentarioAux = new Comentario();
                            comentarioAux.setMensaje(anuncioNuevaPublicacion.getText().toString());
                            comentarioAux.setIdPublicacion(publicacionAux.getId());
                            hiloParaCreate = new HiloParaCreate(new DAOComentario());
                            todoOK = hiloParaCreate.execute(comentarioAux).get();
                            if (!todoOK){
                                throw new Exception();
                            }

                            //Crear los adjuntos
                            Adjunto adjuntoAux;
                            for(DataBaseItem item:listaAdjuntos){
                                adjuntoAux = (Adjunto)item;
                                adjuntoAux.setIdPublicacion(publicacionAux.getId());
                                hiloParaCreate = new HiloParaCreate(new DAOAdjunto());
                                todoOK = hiloParaCreate.execute(adjuntoAux).get();
                                if (!todoOK){
                                    throw new Exception();
                                }
                            }

                            tostada.publicacionAnadidaConExito();
                            finish();
                        }catch(Exception e){
                            tostada.errorConexionBBDD();
                        }
                    }
                }
                break;
            case R.id.imgNuevaPublicacionBotonAnadir:
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, READ_REQUEST_CODE);

                //Añadir adjuntos

//                showDialog();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                //Recoger path
                uri = data.getData();

                try {
                    dameElPath();
                    //Recoger la foto
                    if (filePath.length()==0){
                        throw new Exception();
                    }
                    File file = new File(filePath);
                    Boolean siONo = file.canRead();
                    byte[] bytes = FileUtils.readFileToByteArray(file);

                    //Crear el adjunto
                    Adjunto adjunto = new Adjunto();
                    adjunto.setNombreAdjunto(file.getName());
                    adjunto.setFoto(new Foto(bytes));
                    listaAdjuntos.add(adjunto);

                    //Adjunto creado
                    tostada.imagenAnadidaConExito();
                    String textoAnterior = adjuntosAgregados.getText().toString();
                    if (adjuntosAgregados.length()>0){
                        adjuntosAgregados.setText(textoAnterior+="\n"+file.getName());
                    }else{
                        adjuntosAgregados.setText(file.getName());
                    }

                } catch (Exception e) {
                    tostada.errorNoSePuedeLeerImagen();
                }
            }
        }
    }
/*
    public void dameElPath(Context context, Uri uri) {

        ActivityCompat.requestPermissions(VCrearNuevaPublicacion.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                CODIGO_DEVOLUCION_PATH);
    }
*/
/*
    public void onRequestPermissionsResult(int requestCode,
                                               String permissions[],
                                               int[] grantResults) {
        switch (requestCode) {
            case CODIGO_DEVOLUCION_PATH: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

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

                } else {
                    //Permiso denegado!
                    filePath = "";
                }

            }
        }

    }
*/
    public void dameElPath(){
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

}