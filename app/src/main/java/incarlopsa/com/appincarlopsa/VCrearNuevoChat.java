package incarlopsa.com.appincarlopsa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class VCrearNuevoChat extends AppCompatActivity implements IVista{

    EditText tituloNuevoChat;
    EditText mensajeNuevoChat;
    ImageButton anadirUsuario;
    ImageButton enviar;
    TextView usuarioCazado;
    SingleTostada tostada = SingleTostada.getInstance();

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
        enviar = (ImageButton)findViewById(R.id.enviarNChat);
        usuarioCazado = (TextView)findViewById(R.id.usuarioNChat);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.enviarNChat:
                if (tituloNuevoChat.length()==0){
                    tostada.errorTituloVacioChat();
                }else{
                    if (mensajeNuevoChat.length()==0){
                        tostada.errorMensajeVacio();
                    }else{
                        if (usuarioCazado.length()==0){
                            tostada.errorUsuarioNoElegido();
                        }else{
                            //CREAR CHAT


                            tostada.chatCreadoConExito();
                        }
                    }
                }


        }
    }
}
