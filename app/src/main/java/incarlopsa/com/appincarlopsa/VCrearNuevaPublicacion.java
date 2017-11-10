package incarlopsa.com.appincarlopsa;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
    EditText tituloNuevaPublicacion;
    EditText anuncioNuevaPublicacion;
    ImageView anadirAdjunto;
    ImageButton enviar;
    TextView adjuntosAgregados;
    SingleCredenciales credenciales = SingleCredenciales.getInstance();
    SingleTostada tostada = SingleTostada.getInstance();
    HiloParaCreate hiloParaCreate;
    HiloParaRead hiloParaRead;
    ArrayList<DataBaseItem> resultados;
    ArrayList<DataBaseItem> listaAdjuntos;
    private static final int READ_REQUEST_CODE = 42;

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
                            //Crear Publicacion


/*
                            Chat chatAux = new Chat();
                            chatAux.setId(null);
                            chatAux.setIdUsuario(credenciales.getIdUsuario());
                            chatAux.setIdUsuarioDestino(usuarioRetornado.getIdUsuario());
                            chatAux.setTitulo(tituloNuevoChat.getText().toString());
                            Boolean todoOK = false;
                            hiloParaCreate = new HiloParaCreate(new DAOChat());
                            todoOK = hiloParaCreate.execute(chatAux).get();
                            if (!todoOK){
                                throw new Exception();
                            }
                            //Recuperar el ID del chat recien creado
                            hiloParaRead = new HiloParaRead(new DAOChat());
                            resultados = hiloParaRead.execute(chatAux).get();
                            if (resultados.size()==0){
                                throw new Exception();
                            }
                            //El primer elemento del array debe contener el chat mas nuevo de todos
                            //es decir, el recien creado
                            chatAux = (Chat)resultados.get(0);
                            Mensaje mensaje = new Mensaje();
                            mensaje.setIdUsuario(credenciales.getIdUsuario());
                            mensaje.setIdPublicacion(chatAux.getId());
                            mensaje.setLeidoPorDestino(false);
                            mensaje.setMensaje(mensajeNuevoChat.getText().toString());

                            //Crear el mensaje asociado al chat
                            hiloParaCreate = new HiloParaCreate(new DAOMensaje());
                            todoOK = hiloParaCreate.execute(mensaje).get();
                            if (!todoOK){
                                throw new Exception();
                            }

                            tostada.chatCreadoConExito();
*/
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
            Uri uri = null;
            if (data != null) {
                uri = data.getData();
                String path = RealPath.dameElPath(this, uri);
                File file = new File(path);
                Boolean siONo = file.canRead();
                try {

                    byte[] bytes = FileUtils.readFileToByteArray(file);
                    Adjunto adjunto = new Adjunto();
                    adjunto.setNombreAdjunto(file.getName());
                    adjunto.setFoto(new Foto(bytes));
                    listaAdjuntos.add(adjunto);
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

    void showDialog() {
        // Create the fragment and show it as a dialog.
        dialogUsuarios = new DialogUsuarios();
        dialogUsuarios.show(getFragmentManager(), "dialog");
    }

    @Override
    public void devolverUsuario(Usuario usuarioRetornado) {
        //Ya tenemos el usuario!
        this.usuarioRetornado = usuarioRetornado;
        usuarioCazado.setText(usuarioRetornado.getNombre()
                +" "+usuarioRetornado.getApellidos()
                +"\n"+usuarioRetornado.getTipoEmpleado());
//        fotoUsuario.setImageBitmap(this.usuarioRetornado.getFotoBMP());
        dialogUsuarios.dismiss();
    }

*/

}