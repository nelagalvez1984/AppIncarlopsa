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
    private ArrayList<DataBaseItem> resultadosUsuarios;
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
    private SingleTostada tostada = SingleTostada.getInstance();
    private SingleCredenciales credenciales = SingleCredenciales.getInstance();
    private Usuario autor;
    private Usuario destino;
    private Integer idAutor;
    private Integer idDestino;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vchat);
        inicializarVista();
    }

    @Override
    public void inicializarVista() {

        tostada.setContexto(this);
        resultados = new ArrayList<>();

        //Recoger el chat
        recycler = (RecyclerView)findViewById(R.id.recyclerChats);
        intentRecogido = getIntent();
        idChat = intentRecogido.getIntExtra("idChat",0);
        tituloChat = intentRecogido.getStringExtra("tituloChat");
        idAutor = intentRecogido.getIntExtra("idAutor",0);
        idDestino = intentRecogido.getIntExtra("idDestino",0);

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
            if (resultados.size()>0){
                //Recoger los usuarios
                resultadosUsuarios = new ArrayList<>();
                autor = new Usuario();
                destino = new Usuario();
                //Recoger el autor
                hiloParaRead = new HiloParaRead(new DAOUsuario());
                autor.setIdUsuario(idAutor);
                resultadosUsuarios = hiloParaRead.execute(autor).get();
                if (resultadosUsuarios.size()>0){
                    autor = (Usuario)resultadosUsuarios.get(0);
                }else{
                    throw new Exception();
                }
                //Recoger el destino
                hiloParaRead = new HiloParaRead(new DAOUsuario());
                destino.setIdUsuario(idDestino);
                resultadosUsuarios = hiloParaRead.execute(destino).get();
                if (resultadosUsuarios.size()>0){
                    destino = (Usuario)resultadosUsuarios.get(0);
                }else{
                    throw new Exception();
                }

                adapterMensaje = new AdapterMensaje(resultados, autor, destino);
                recycler.setAdapter(adapterMensaje);
                recycler.scrollToPosition(adapterMensaje.ultimaPosicion());
            }

        } catch (Exception e) {
            tostada.errorConexionBBDD();
        }

        //Campo de texto y boton enviar
        botonEnviar = (ImageButton)findViewById(R.id.buttonChatEnviar);
        botonEnviar.setOnClickListener(this);
        escribirMensaje = (TextView)findViewById(R.id.edtChatMensaje);
    }

    @Override
    public void onClick(View view) {
        //Boton enviar
        hiloParaCreate = new HiloParaCreate(new DAOMensaje());
        Mensaje mensajeAux = new Mensaje();
        mensajeAux.setIdPublicacion(idChat);
        mensajeAux.setIdUsuario(credenciales.getIdUsuario());
        mensajeAux.setMensaje(escribirMensaje.getText().toString());
        try {
            Boolean creacion = hiloParaCreate.execute(mensajeAux).get();
            if (!creacion){
                throw new Exception();
            }
            hiloParaRead = new HiloParaRead(new DAOMensaje());
            resultados = hiloParaRead.execute(mensajeAux).get();
            adapterMensaje.actualizar(resultados);
            escribirMensaje.setText("");
            recycler.scrollToPosition(adapterMensaje.ultimaPosicion());
        } catch (Exception e) {
            tostada.errorConexionBBDD();
        }
    }
}
