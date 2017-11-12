package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.view.View;

public interface IVista extends View.OnClickListener,
                                   ICodigos {

    void inicializarVista(); // Inicializar los elementos de la vista

}
