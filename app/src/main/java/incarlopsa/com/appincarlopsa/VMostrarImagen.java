package incarlopsa.com.appincarlopsa;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.Serializable;

public class VMostrarImagen extends AppCompatActivity implements IVista{

    ImageView foto;
    SingleGaleria galeria = SingleGaleria.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vmostrar_imagen);
    }

    @Override
    public void inicializarVista() {
        foto = (ImageView)findViewById(R.id.MostrarImagenFoto);
        foto.setOnClickListener(this);
        Adjunto adjuntoAux = galeria.getFotoAdjunta();
        foto.setImageBitmap(adjuntoAux.getFoto().getFotoBMP());
    }

    @Override
    public void onClick(View view) {
        //Hacer cosas con la foto al hacer click

    }
}
