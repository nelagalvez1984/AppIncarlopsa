package incarlopsa.com.appincarlopsa;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Anonymous on 01/11/2017.
 */

public class TESTFactoriaItems implements ICodigos {

    //Propiedades
    Random generadorAleatorios;
    MessageDigest hash;
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
    }

    private Integer dameEntero(){
        return (generadorAleatorios.nextInt(1000) + 1)*98765;
    }

    public String dameCadenaAleatoria(){
        byte[] operacion;
        String cadena = "";
        try {
            hash = MessageDigest.getInstance("MD5"); //Inicializar constructor hash
            Integer numAleatorio = dameEntero(); //Pedir un entero
            operacion = hash.digest(numAleatorio.toString().getBytes()); //Crear hash
            int longitud = operacion.length;
            operacion = Base64.decode(operacion,Base64.DEFAULT); //Pasar el hash a base64
            cadena = operacion.toString(); //Pasar el hash a string
            cadena = cadena.substring(3); //obtener a partir del tercer caracter
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return cadena;
    }

    public ArrayList<DataBaseItem> testReadUsuario(Integer filtro){
        usuario = new Usuario();
        usuario.setIdUsuario(filtro);
        try {
            resultados = daoUsuario.read(usuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultados;
    }

    public ArrayList<DataBaseItem> testReadUsuario(String filtro){
        try {
            resultados = daoUsuario.read(filtro);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultados;

    }

    public ArrayList<DataBaseItem> testReadUsuario(Usuario filtro){
        ArrayList<DataBaseItem> resultados = null;
        try {
            resultados = daoUsuario.read(filtro);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultados;
    }

    public Boolean testCreateUsuario(Integer filtro){
        usuario = new Usuario(null,
                                dameCadenaAleatoria(),
                                dameCadenaAleatoria(),
                                dameCadenaAleatoria(),
                                TEST_TIPO_EMPLEADO,
                                foto,
                                dameCadenaAleatoria()
                );
        try {
            retornoTrueFalse = daoUsuario.create(usuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retornoTrueFalse;

    }

}
