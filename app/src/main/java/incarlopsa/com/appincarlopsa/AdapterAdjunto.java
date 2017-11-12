package incarlopsa.com.appincarlopsa;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class AdapterAdjunto extends RecyclerView.Adapter<AdapterAdjunto.ViewHolder> implements ICodigos{

    private ArrayList<DataBaseItem> listaAdjuntos;
    private SingleTostada tostada = SingleTostada.getInstance();

    public AdapterAdjunto(ArrayList<DataBaseItem> listaAdjuntos) {
        this.listaAdjuntos = listaAdjuntos;
    }

    @Override
    public AdapterAdjunto.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adjunto_mostrado, parent, false);
        final AdapterAdjunto.ViewHolder viewHolder = new AdapterAdjunto.ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() { //Cazar el usuario
            @Override
            public void onClick(View v) {

                int position = viewHolder.getAdapterPosition();

                if(mListener!=null) {
                    mListener.onItemClick(listaAdjuntos.get(position), position);
                }

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterAdjunto.ViewHolder holder, int position) {
        Adjunto adjuntoAux = (Adjunto) listaAdjuntos.get(position);

        //Asignaciones
        String nombreAdjunto = adjuntoAux.getNombreAdjunto();
        Integer tamanoAdjunto = (adjuntoAux.getFoto().getFotoBytes().length)/1000;
        String tamanoFormateado = "";
        if (tamanoAdjunto >= 1000){//Se trata de megas
            tamanoAdjunto = (tamanoAdjunto/1000);
            tamanoFormateado = tamanoAdjunto.toString()+"MB";
        }else{
            tamanoFormateado = tamanoAdjunto.toString()+"KB";
        }
        Bitmap foto = adjuntoAux.getFoto().getFotoBMP();

        holder.nombreAdjunto.setText(nombreAdjunto);
        holder.tamanoAdjunto.setText(tamanoFormateado);
        holder.imagenMiniatura.setImageBitmap(foto);

    }

    public void actualizarAumentandoLista(){
        notifyDataSetChanged();
    }


    public void actualizar(ArrayList<DataBaseItem> nuevaLista){
        listaAdjuntos.clear();
        listaAdjuntos.addAll(nuevaLista);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaAdjuntos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombreAdjunto;
        private TextView tamanoAdjunto;
        private ImageView imagenMiniatura;
        private View v;

        public ViewHolder(View v) {
            super(v);
            nombreAdjunto = (TextView) v.findViewById(R.id.lblAdjuntoNombre);
            tamanoAdjunto = (TextView) v.findViewById(R.id.lblAdjuntoTamano);
            imagenMiniatura = (ImageView) v.findViewById(R.id.imgAdjuntoMiniatura);
            this.v = v;
        }
    }

    public Integer ultimaPosicion(){
        Integer retorno = 0;
        if (listaAdjuntos.size()>0){
            retorno = listaAdjuntos.size()-1;
        }
        return retorno;
    }

    public interface OnItemClickListener {
        void onItemClick(DataBaseItem item, int position);
    }
    private AdapterAdjunto.OnItemClickListener mListener;

    public void setOnItemListener(AdapterAdjunto.OnItemClickListener listener) {
        mListener = listener;
    }

}
