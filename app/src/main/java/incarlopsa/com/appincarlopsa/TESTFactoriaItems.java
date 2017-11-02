package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class TESTFactoriaItems implements ICodigos {

    //Propiedades
    Random generadorAleatorios;
    ArrayList<DataBaseItem> resultados = null;
    Boolean retornoTrueFalse = false;

    //Objetos replicables
    private Adjunto adjunto;
    private Chat chat;
    private Comentario comentario;
    private Foto foto;
    private MeGusta meGusta;
    private MeDisgusta meDisgusta;
    private Mensaje mensaje;
    private Publicacion publicacion;
    private PublicacionAdjunto publicacionAdjunto;
    private TipoFichero tipoFichero;
    private Topic topic;
    private Usuario usuario;

    //Hilos
    private HiloParaRead hiloParaRead;
    private HiloParaCreate hiloParaCreate;
    private HiloParaUpdate hiloParaDelete;

    //Daos
    private DAOAdjunto daoAdjunto;
    private DAOChat daoChat;
    private DAOComentario daoComentario;
    private DAOLikes daoLikes;
    private DAOMensaje daoMensaje;
    private DAOPublicacion daoPublicacion;
    private DAOPublicacionAdjunto daoPublicacionAdjunto;
    private DAOTipoFichero daoTipoFichero;
    private DAOUsuario daoUsuario;
    private ArrayList<Character> listaLetras;

    //Constructor: Inicializador de daos
    public TESTFactoriaItems(){
        daoAdjunto = new DAOAdjunto();
        daoChat = new DAOChat();
        daoComentario = new DAOComentario();
        daoLikes = new DAOLikes();
        daoMensaje = new DAOMensaje();
        daoPublicacion = new DAOPublicacion();
        daoPublicacionAdjunto = new DAOPublicacionAdjunto();
        daoTipoFichero = new DAOTipoFichero();
        daoUsuario = new DAOUsuario();
        generadorAleatorios = new Random();
        rellenarListaLetras();
    }

    private void rellenarListaLetras() {
        listaLetras = new ArrayList<>();
        listaLetras.add('A');
        listaLetras.add('B');
        listaLetras.add('C');
        listaLetras.add('D');
        listaLetras.add('E');
        listaLetras.add('F');
        listaLetras.add('G');
        listaLetras.add('H');
        listaLetras.add('I');
        listaLetras.add('J');
        listaLetras.add('K');
        listaLetras.add('L');
        listaLetras.add('M');
        listaLetras.add('N');
        listaLetras.add('O');
        listaLetras.add('P');
        listaLetras.add('Q');
        listaLetras.add('R');
        listaLetras.add('S');
        listaLetras.add('T');
        listaLetras.add('U');
        listaLetras.add('V');
        listaLetras.add('W');
        listaLetras.add('X');
        listaLetras.add('Y');
        listaLetras.add('Z');
    }

    //GENERADORES ALEATORIOS
    private Integer dameEntero(){
        return (generadorAleatorios.nextInt(1000) + 1);
    }

    public String dameCadenaAleatoria(){

        String cadena = "";
        //Determinar numeroDeLetras. Minimo 4, max 8
        int longitudCadena = (dameEntero()%4)+5;
        for(int i=0; i<longitudCadena; i++){
            cadena += listaLetras.get(dameEntero()%26);
        }

        return cadena;
    }

    public Boolean dameBoolAleatorio() { return (dameEntero()%2 == 0)?true:false;}

    //GENERADORES DE OBJETOS CON ALEATORIEDAD
    public Foto testCREARFoto(){
        Foto foto = new Foto();
        byte[] cosa =   dameCadenaAleatoria().getBytes();
        foto.setFotoBytes(cosa);
        return foto;
    }

    public Usuario testCREARUsuario(){
        usuario = new Usuario(null,
                dameCadenaAleatoria(),
                dameCadenaAleatoria(),
                dameCadenaAleatoria(),
                TEST_TIPO_EMPLEADO,
                testCREARFoto(),
                dameCadenaAleatoria()
        );
        return usuario;
    }

    public TipoFichero testCREARTipoFichero(){
        tipoFichero = new TipoFichero(null,
                dameCadenaAleatoria(),
                dameBoolAleatorio());
        return tipoFichero;
    }

    public Adjunto testCREARAdjunto(){
        adjunto = new Adjunto(null,
                            null,
                            dameCadenaAleatoria(),
                            dameCadenaAleatoria(),
                            testCREARTipoFichero());
        return adjunto;
    }


    public ArrayList<DataBaseItem> testReadGenerico(Object filtro, DAOBase dao){
        ArrayList<DataBaseItem> resultados = null;
        try {
            hiloParaRead = new HiloParaRead(dao);
            resultados = hiloParaRead.execute(filtro).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return resultados;
    }

    public Boolean testCreateGenerico(Object filtro, DAOBase dao){
        try {
            hiloParaCreate = new HiloParaCreate(dao);
            retornoTrueFalse = hiloParaCreate.execute(filtro).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return retornoTrueFalse;
    }





    public Boolean testCreateUsuario(Usuario u){
        try {
            retornoTrueFalse = daoUsuario.create(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retornoTrueFalse;
    }



}
