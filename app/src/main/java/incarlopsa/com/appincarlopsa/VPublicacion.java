package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class VPublicacion extends AppCompatActivity implements IVista{

    private RecyclerView recycler;
    private ArrayList<DataBaseItem> resultados;
    private ArrayList<DataBaseItem> likesDelAnuncio;
    private ArrayList<DataBaseItem> comentarios;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterComentario adapterComentario;
    private HiloParaRead hiloParaRead;
    private HiloParaCreate hiloParaCreate;
    private Intent intentRecogido;
    private Integer idPublicacion;
    private Boolean verificacionMeGusta;
    private Boolean verificacionMeDisgusta;
    private Resources recursos;
    private ImageButton meGustaAnuncio;
    private ImageButton meDisgustaAnuncio;
    private ImageButton botonEnviar;
    private EditText escribirMensaje;
    private TextView tituloFormulario;
    private TextView anuncioPublicacion;
    private TextView contadorLikes;
    private TextView contadorDislikes;
    private Button botonAdjuntos;
    private SingleTostada tostada = SingleTostada.getInstance();
    private SingleCredenciales credenciales = SingleCredenciales.getInstance();
    private Publicacion publicacionAux;
    private Comentario anuncio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpublicacion);
        inicializarVista();
    }

    @Override
    public void inicializarVista() {

        //Inicializar elementos
        meGustaAnuncio = (ImageButton)findViewById(R.id.imgGustaPublicacion);
        meGustaAnuncio.setOnClickListener(this);
        meDisgustaAnuncio = (ImageButton)findViewById(R.id.imgNogustaPublicacion);
        meDisgustaAnuncio.setOnClickListener(this);
        escribirMensaje = (EditText)findViewById(R.id.txtPublicacionMensaje);
        botonEnviar = (ImageButton)findViewById(R.id.imgEnviarPublicacion);
        botonEnviar.setOnClickListener(this);
        botonAdjuntos = (Button)findViewById(R.id.btnAdjuntosPublicacion);
        botonAdjuntos.setOnClickListener(this);
        tituloFormulario = (TextView)findViewById(R.id.txtTituloPublicacion);
        tostada.setContexto(this);
        resultados = new ArrayList<>();
        recursos = getResources();

        //Recoger el id de la publicacion
        intentRecogido = getIntent();
        idPublicacion = intentRecogido.getIntExtra("idPublicacion",0);
        publicacionAux = new Publicacion();
        publicacionAux.setId(idPublicacion);
        HiloParaRead hilo = new HiloParaRead(new DAOPublicacion());
        try {
            //Recoger la publicacion asociada en la BBDD
            resultados = hilo.execute(publicacionAux).get();
            if (resultados.size()==0){
                throw new Exception();
            }
            publicacionAux = (Publicacion)resultados.get(0);

            //Extraer el anuncio
            comentarios = publicacionAux.getComentarios();
            anuncio = (Comentario)comentarios.get(comentarios.size()-1);
            comentarios.remove(anuncio);

            //Poner titulo a la vista
            tituloFormulario = (TextView)findViewById(R.id.txtTituloPublicacion);
            tituloFormulario.setText(publicacionAux.getTitulo());

            //Poner el anuncio
            anuncioPublicacion = (TextView)findViewById(R.id.txtAnuncioPublicacion);
            anuncioPublicacion.setText(anuncio.getMensaje());

            //Recoger sus likes
            contadorLikes = (TextView)findViewById(R.id.txtContadorGustaPublicacion);
            contadorLikes.setText(anuncio.getNumeroMeGusta().toString());

            //Recoger sus dislikes
            contadorDislikes = (TextView)findViewById(R.id.txtContadorNoGustaPublicacion);
            contadorDislikes.setText(anuncio.getNumeroMeDisgusta().toString());

            //Habilitar o deshabilitar el boton de adjuntos segun si tiene o no
            if (publicacionAux.getAdjuntos().size()==0){ //No hay adjuntos!
                botonAdjuntos.setBackgroundColor(recursos.getColor(R.color.colorGris));
                botonAdjuntos.setEnabled(false);
            }

            //Comprobacion de likes
            likesDelAnuncio = anuncio.getArrayLikes();
            //Comprobar si he votado MeGusta y colorearlo en caso afirmativo
            verificacionMeGusta = verificarLikes(likesDelAnuncio, new MeGusta());
            if (verificacionMeGusta){ //Ya he votado MeGusta
                marcarMeGusta();
            }

            //Comprobar si he votado NoMeGusta y colorearlo en caso afirmativo
            verificacionMeDisgusta = verificarLikes(likesDelAnuncio, new MeDisgusta());
            if (verificacionMeDisgusta){ //Ya he votado MeDisgusta
                marcarMeDisgusta();
            }

            //Recycler y adapter
            recycler = (RecyclerView)findViewById(R.id.recyPublicacionComentarios);
            layoutManager = new LinearLayoutManager(this);
            recycler.setLayoutManager(layoutManager);
            recycler.setItemAnimator(new DefaultItemAnimator());

            //Cargar los mensajes
            adapterComentario = new AdapterComentario(comentarios);
            recycler.setAdapter(adapterComentario);
//            recycler.scrollToPosition(adapterComentario.ultimaPosicion());

        } catch (Exception e) {
            tostada.errorConexionBBDD();
        }

    }

    private void marcarMeDisgusta() {
        meDisgustaAnuncio.setBackgroundColor(recursos.getColor(R.color.colorRojoCabeceras));
        meGustaAnuncio.setEnabled(false);
        meDisgustaAnuncio.setEnabled(false);
    }

    private void marcarMeGusta() {
        meGustaAnuncio.setBackgroundColor(recursos.getColor(R.color.colorVerdeApp));
        meGustaAnuncio.setEnabled(false);
        meDisgustaAnuncio.setEnabled(false);
    }

    private Boolean verificarLikes(ArrayList<DataBaseItem> likesDelAnuncio, MeAlgo comprobacion) {
        MeAlgo meAlgo;
        Boolean meGusta = false;
        Boolean retorno = false;

        if (comprobacion instanceof MeGusta){
            meGusta = true;
        }

        for(DataBaseItem item : likesDelAnuncio){
            meAlgo = (MeAlgo)item;
            if (meAlgo.getIdUsuario() == credenciales.getIdUsuario()){ //Aqui he votado algo
                if (meGusta && meAlgo instanceof MeGusta){ //Si estoy comprobando MeGusta y es MeGusta
                    retorno = true;
                    break;
                }

                if (!meGusta && meAlgo instanceof MeDisgusta){ //Si estoy comprobando MeDisgusta y es MeDisgusta
                    retorno = true;
                    break;
                }
            }
        }

        return retorno;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgEnviarPublicacion: //Boton enviar
                enviarComentario();
                break;
            case R.id.btnAdjuntosPublicacion: //Mostrar los adjuntos
                //ToDo
                break;
            case R.id.imgGustaPublicacion: //Se ha clicado en meGusta
                if (!verificacionMeDisgusta && !verificacionMeGusta){
                    try {
                        introducirMeAlgo(new MeGusta());
                    } catch (Exception e) {
                        tostada.errorConexionBBDD();
                    }
                }
                break;
            case R.id.imgNogustaPublicacion: //Se ha clicado en meDisgusta
                if (!verificacionMeDisgusta && !verificacionMeGusta){
                    try {
                        introducirMeAlgo(new MeDisgusta());
                    } catch (Exception e) {
                        tostada.errorConexionBBDD();
                    }
                }
                break;
        }
    }

    private void introducirMeAlgo(MeAlgo meAlgo) throws Exception {
        meAlgo.setIdComentario(anuncio.getId());
        meAlgo.setIdUsuario(credenciales.getIdUsuario());
        hiloParaCreate = new HiloParaCreate(new DAOLikes());
        Boolean retorno = hiloParaCreate.execute(meAlgo).get();
        if (!retorno){
            throw new Exception();
        }
        if (meAlgo instanceof MeGusta){
            verificacionMeGusta = true;
            marcarMeGusta();
        }else{
            verificacionMeDisgusta = true;
            marcarMeDisgusta();
        }
    }

    private void enviarComentario(){
        hiloParaCreate = new HiloParaCreate(new DAOComentario());
        Comentario comentarioAux = new Comentario();
        comentarioAux.setIdPublicacion(idPublicacion);
        comentarioAux.setIdUsuario(credenciales.getIdUsuario());
        comentarioAux.setMensaje(escribirMensaje.getText().toString());
        try {
            Boolean creacion = hiloParaCreate.execute(comentarioAux).get();
            if (!creacion){
                throw new Exception();
            }
            hiloParaRead = new HiloParaRead(new DAOComentario());
            resultados = hiloParaRead.execute(comentarioAux).get();
            adapterComentario.actualizar(resultados);
            escribirMensaje.setText("");
            //recycler.scrollToPosition(adapterComentario.ultimaPosicion());
        } catch (Exception e) {
            tostada.errorConexionBBDD();
        }
    }
}
