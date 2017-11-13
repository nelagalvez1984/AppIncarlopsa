package incarlopsa.com.appincarlopsa;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class VCrearNuevoChat extends AppCompatActivity implements IVista,
                                                            AdapterUsuario.RetornoAdaptadorUsuario{

    EditText tituloNuevoChat;
    EditText mensajeNuevoChat;
    ImageButton anadirUsuario;
    ImageButton enviar;
    ImageButton fotoUsuario;
    TextView usuarioCazado;
    Usuario usuarioRetornado = null;
    DialogUsuarios dialogUsuarios;
    SingleCredenciales credenciales = SingleCredenciales.getInstance();
    SingleTostada tostada = SingleTostada.getInstance();
    HiloParaCreate hiloParaCreate;
    HiloParaRead hiloParaRead;
    ArrayList<DataBaseItem> resultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vcrear_nuevo_chat);
        inicializarVista();
    }

    @Override
    public void inicializarVista() {
        tostada.setContexto(this);
        tituloNuevoChat = (EditText)findViewById(R.id.editTituloNChat);
        mensajeNuevoChat = (EditText)findViewById(R.id.mensajeNChat);
        anadirUsuario = (ImageButton)findViewById(R.id.addUsuarioNChat);
        anadirUsuario.setOnClickListener(this);
        enviar = (ImageButton)findViewById(R.id.enviarNChat);
        enviar.setOnClickListener(this);
        usuarioCazado = (TextView)findViewById(R.id.usuarioNChat);
        fotoUsuario = (ImageButton)findViewById(R.id.imgNuevoChatFoto);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.enviarNChat: // Boton enviar
                if (tituloNuevoChat.length()==0){
                    tostada.errorTituloVacioChat();
                }else{
                    if (mensajeNuevoChat.length()==0){
                        tostada.errorMensajeVacio();
                    }else{
                        if (usuarioRetornado == null){
                            tostada.errorUsuarioNoElegido();
                        }else{

                            try{

                                // Comprobar que el titulo es inferior a determinada cantidad de caracteres
                                if (tituloNuevoChat.length()>TAMANO_MAXIMO_TITULO){
                                    throw new EXCTamanoSuperado();
                                }

                                //Crear chat
                                Chat chatAux = new Chat();
                                chatAux.setId(null);
                                chatAux.setIdUsuario(credenciales.getIdUsuario());
                                chatAux.setIdUsuarioDestino(usuarioRetornado.getIdUsuario());
                                chatAux.setTitulo(tituloNuevoChat.getText().toString());
                                Boolean todoOK = false;
                                hiloParaCreate = new HiloParaCreate(new DAOChat());
                                todoOK = hiloParaCreate.execute(chatAux).get();
                                if (!todoOK){
                                    throw new EXCErrorBBDD();
                                }

                                //Recuperar el ID del chat recien creado
                                hiloParaRead = new HiloParaRead(new DAOChat());
                                resultados = hiloParaRead.execute(chatAux).get();
                                if (resultados.size()==0){
                                    throw new EXCErrorBBDD();
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
                                    throw new EXCErrorBBDD();
                                }

                                tostada.chatCreadoConExito();
                                finish();
                            }catch(EXCErrorBBDD e){
                                tostada.errorConexionBBDD();
                            }catch (EXCTamanoSuperado e){
                                tostada.errorTamanoTituloSuperado();
                            }catch (Exception e){
                                tostada.errorConexionBBDD();
                            }
                        }
                    }
                }
                break;
            case R.id.addUsuarioNChat:
                showDialogUsuarios();
        }
    }


    void showDialogUsuarios() {
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
        if (usuarioRetornado.getFotoBytes() != null){

            fotoUsuario.setAdjustViewBounds(true);
            fotoUsuario.setImageBitmap(this.usuarioRetornado.getFotoBMP());
        }else{
            fotoUsuario.setImageDrawable(getDrawable(R.mipmap.foto));
        }
        dialogUsuarios.dismiss();
    }

}
