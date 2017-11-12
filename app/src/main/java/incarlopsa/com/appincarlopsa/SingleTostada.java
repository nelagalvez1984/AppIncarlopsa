package incarlopsa.com.appincarlopsa;

import android.content.Context;
import android.widget.Toast;

class SingleTostada implements ICodigos{

    private static final SingleTostada ourInstance = new SingleTostada();
    private Context contexto = null;

    //Constructor
    private SingleTostada() { }

    //Get Instance
    static SingleTostada getInstance() {
        return ourInstance;
    }

    //Getter/Setter
    public void setContexto(Context contexto){
        this.contexto = contexto;
    }

    //Tostadas de error
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
    public void errorPermisoDenegado() { showTostada(ERROR_PERMISO_DENEGADO);}
    public void errorTamanoSuperado() { showTostada(ERROR_TAMANO_SUPERADO);}
    public void errorTamanoTituloSuperado() { showTostada(TAMANO_TITULO_SUPERADO);}
    public void errorNumeroParametrosIncorrecto() { showTostada(NUMERO_PARAMETROS_INCORRECTO);}

    //Tostadas de aserciones
    public void chatCreadoConExito() { showTostada(CHAT_CREADO_CON_EXITO);}
    public void imagenAnadidaConExito() { showTostada(IMAGEN_ANADIDA_CON_EXITO);}
    public void publicacionAnadidaConExito() { showTostada(PUBLICACION_CREADA_CON_EXITO);}
    public void fotoDePerfilActualizada() { showTostadaCorta(FOTO_PERFIL_ACTUALIZADA);}

    //Tostadas informativas
    public void bienvenida(){
        showTostada(BIENVENIDA);
    }
    public void cargando() {showTostadaCorta(CARGANDO);}
    public void conectando() { showTostadaCorta(CONECTANDO);}

    //Tostadoras
    private void showTostadaCorta(String mensaje) {
        try{
            Toast.makeText(contexto, mensaje, Toast.LENGTH_SHORT).show();
        }catch (Exception e){ //¿Tostada incompatible con el sistema?
            e.printStackTrace();
        }
    }

    private void showTostada(String mensaje){
        try{
            Toast.makeText(contexto, mensaje, Toast.LENGTH_LONG).show();
        }catch (Exception e){ //¿Tostada incompatible con el sistema?
            e.printStackTrace();
        }
    }

}
