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

    private void showTostada(String mensaje){
        Toast.makeText(contexto, mensaje, Toast.LENGTH_SHORT).show();
    }

}
