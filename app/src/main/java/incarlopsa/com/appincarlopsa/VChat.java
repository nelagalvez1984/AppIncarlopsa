package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class VChat extends AppCompatActivity implements IVista{

    private RecyclerView recycler;
    private ArrayList<DataBaseItem> resultados;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterMensaje adapterMensaje;
    private HiloParaRead hiloParaRead;
    private HiloParaCreate hiloParaCreate;
    private Intent intentRecogido;
    private Integer idChat;
    private String tituloChat;
    private ImageButton botonEnviar;
    private TextView escribirMensaje;
    private TextView tituloFormulario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vchat);
        inicializarVista();
    }

    @Override
    public void inicializarVista() {
        //Recoger el chat
        recycler = (RecyclerView)findViewById(R.id.recyclerChats);
        intentRecogido = getIntent();
        idChat = intentRecogido.getIntExtra("idChat",0);
        tituloChat = intentRecogido.getStringExtra("tituloChat");
        tituloFormulario = (TextView)findViewById(R.id.txtChatTitulo);
        tituloFormulario.setText(tituloChat);

        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());

        //Cargar los mensajes
        hiloParaRead = new HiloParaRead(new DAOMensaje());
        Mensaje m = new Mensaje();
        m.setIdPublicacion(idChat);

        try {
            resultados = hiloParaRead.execute(m).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        adapterMensaje = new AdapterMensaje(resultados);
        recycler.setAdapter(adapterMensaje);

        //Campo de texto y boton enviar
        botonEnviar = (ImageButton)findViewById(R.id.buttonChatEnviar);
        botonEnviar.setOnClickListener(this);
        escribirMensaje = (TextView)findViewById(R.id.edtChatMensaje);
    }

    @Override
    public void onClick(View view) {
        //Boton enviar


    }
}
