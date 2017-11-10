package incarlopsa.com.appincarlopsa;

import android.content.Context;
import android.widget.Toast;

class SingleTostada implements ICodigos{
    private static final SingleTostada ourInstance = new SingleTostada();

    private Context contexto = null;

    static SingleTostada getInstance() {
        return ourInstance;
    }

    private SingleTostada() {
    }

    public void setContexto(Context contexto){
        this.contexto = contexto;
    }

    public void bienvenida(){
        showTostada(BIENVENIDA);
    }

    public void errorConexionBBDD(){
        showTostada(ERROR_CONEXION_BBDD);
    }

    public void errorDriver(){
        showTostada(ERROR_DRIVER);
    }

    public void errorLogin() { showTostada(ERROR_LOGIN);}

    public void errorIntroducirDatos() { showTostada(ERROR_INTRODUCIR_DATOS);}

    public void errorMensajeVacio() { showTostada(ERROR_MENSAJE_VACIO);}

    public void errorAnuncioVacio() { showTostada(ERROR_ANUNCIO_VACIO);}

    public void errorTituloVacioChat() { showTostada(ERROR_TITULO_VACIO_CHAT);}

    public void errorUsuarioNoElegido() { showTostada(ERROR_USUARIO_NO_ELEGIDO);}

    public void errorNoSePuedeLeerImagen() { showTostada(ERROR_NO_SE_PUEDE_LEER_IMAGEN);}

    public void chatCreadoConExito() { showTostada(CHAT_CREADO_CON_EXITO);}

    public void imagenAnadidaConExito() { showTostada(IMAGEN_ANADIDA_CON_EXITO);}

    public void publicacionAnadidaConExito() { showTostada(PUBLICACION_CREADA_CON_EXITO);}

    public void errorPermisoDenegado() { showTostada(ERROR_PERMISO_DENEGADO);}

    private void showTostada(String mensaje){
        try{
            Toast.makeText(contexto, mensaje, Toast.LENGTH_LONG).show();
        }catch (Exception e){ //¿Tostada incompatible con el sistema?
            e.printStackTrace();
        }
    }

}
