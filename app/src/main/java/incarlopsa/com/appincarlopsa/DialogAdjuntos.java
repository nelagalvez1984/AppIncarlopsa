package incarlopsa.com.appincarlopsa;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class DialogAdjuntos extends DialogFragment implements ICodigos{

    private RecyclerView recyclerView;
    private AdapterAdjunto adapterAdjunto;
    private SingleTostada tostada = SingleTostada.getInstance();
    private Context contextoPadre = null;
    public static ArrayList<DataBaseItem> listaAdjuntos;
    private SingleGaleria galeria = SingleGaleria.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.dialog_adjuntos, container, false);
        contextoPadre = getActivity();
        try {
            recyclerView = (RecyclerView) v.findViewById(R.id.recyDialogAdjuntos);
            Context contexto = v.getContext();
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(contexto);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            //Cargar los adjuntos
            adapterAdjunto = new AdapterAdjunto(listaAdjuntos);
            adapterAdjunto.setOnItemListener(new AdapterAdjunto.OnItemClickListener() {
                @Override
                public void onItemClick(DataBaseItem item, int position) {
                    Adjunto adjuntoAux = (Adjunto) item;
                    Context contexto = v.getContext();
                    Intent intent = new Intent(contexto, VMostrarImagen.class);
                    galeria.setFotoAdjunta(adjuntoAux);
                    v.getContext().startActivity(intent);
                }
            });
            recyclerView.setAdapter(adapterAdjunto);

        } catch (Exception e) {
            tostada.errorConexionBBDD();
        }

        return v;
    }
}
