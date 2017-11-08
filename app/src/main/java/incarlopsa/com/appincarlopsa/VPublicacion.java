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
import java.util.concurrent.ExecutionException;

public class VPublicacion extends AppCompatActivity implements IVista{

    private RecyclerView recycler;
    private ArrayList<DataBaseItem> resultados;
    private ArrayList<DataBaseItem> resultadosUsuarios;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterMensaje adapterMensaje;
    private HiloParaRead hiloParaRead;
    private HiloParaCreate hiloParaCreate;
    private Intent intentRecogido;
    private Integer idPublicacion;

    private String tituloChat;
    private ImageButton botonEnviar;
    private TextView escribirMensaje;
    private TextView tituloFormulario;
    private TextView anuncioPublicacion;
    private TextView contadorLikes;
    private TextView contadorDislikes;
    private SingleTostada tostada = SingleTostada.getInstance();
    private SingleCredenciales credenciales = SingleCredenciales.getInstance();
    private Usuario autor;
    private Usuario destino;
    private Integer idAutor;
    private Integer idDestino;
    private Publicacion publicacionAux;
    private Comentario anuncio;


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

        //Recoger la publicacion
        intentRecogido = getIntent();
        idPublicacion = intentRecogido.getIntExtra("idPublicacion",0);
        publicacionAux = new Publicacion();
        publicacionAux.setId(idPublicacion);
        HiloParaRead hilo = new HiloParaRead(new DAOPublicacion());
        try {

            resultados = hilo.execute(publicacionAux).get();
            publicacionAux = (Publicacion)resultados.get(0);

            //Extraer el anuncio
            anuncio = (Comentario)resultados.get(resultados.size()-1);
            resultados.remove(anuncio);

            //Poner titulo a la vista
            tituloFormulario = (TextView)findViewById(R.id.txtTituloPublicacion);
            tituloFormulario.setText(publicacionAux.getTitulo());

            //Poner el anuncio
            anuncioPublicacion = (TextView)findViewById(R.id.txtAnuncioPublicacion);
            anuncioPublicacion.setText(anuncio.getMensaje());

            //Recoger sus likes
            contadorLikes = (TextView)findViewById(R.id.txtContadorGustaPublicacion);
            contadorLikes.setText(anuncio.getNumeroMeGusta());

            //Recoger sus dislikes
            contadorDislikes = (TextView)findViewById(R.id.txtContadorNoGustaPublicacion);
            contadorDislikes.setText(anuncio.getNumeroMeDisgusta());

            //Habilitar o deshabilitar el boton de adjuntos segun si tiene o no

            //Colorear el megusta o el nomegusta si han sido marcados por el usuario

            //Recycler y adapter



            recycler = (RecyclerView)findViewById(R.id.recyPublicacionComentarios);

            layoutManager = new LinearLayoutManager(this);
            recycler.setLayoutManager(layoutManager);
            recycler.setItemAnimator(new DefaultItemAnimator());




            //Cargar los mensajes
            adapterMensaje = new AdapterMensaje(resultados, autor, destino);
            recycler.setAdapter(adapterMensaje);
            recycler.scrollToPosition(adapterMensaje.ultimaPosicion());


            //Campo de texto y boton enviar
            botonEnviar = (ImageButton)findViewById(R.id.imgEnviarPublicacion);
            botonEnviar.setOnClickListener(this);
            escribirMensaje = (TextView)findViewById(R.id.edtChatMensaje);


        } catch (Exception e) {
            tostada.errorConexionBBDD();
        }


    }

    @Override
    public void onClick(View view) {
        //Boton enviar
        hiloParaCreate = new HiloParaCreate(new DAOMensaje());
        Mensaje m = new Mensaje();
        m.setIdPublicacion(idPublicacion);
        m.setIdUsuario(credenciales.getIdUsuario());
        m.setMensaje(escribirMensaje.getText().toString());
        try {
            Boolean creacion = hiloParaCreate.execute(m).get();
            hiloParaRead = new HiloParaRead(new DAOMensaje());
            resultados = hiloParaRead.execute(m).get();
            adapterMensaje.actualizar(resultados);
            escribirMensaje.setText("");
            recycler.scrollToPosition(adapterMensaje.ultimaPosicion());
        } catch (Exception e) {
            tostada.errorConexionBBDD();
        }

    }
}
