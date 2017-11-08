package incarlopsa.com.appincarlopsa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterMensaje extends RecyclerView.Adapter<AdapterMensaje.ViewHolder> {

    private ArrayList<DataBaseItem> listaMensajes;
    private Usuario usuarioAutor;
    private Usuario usuarioDestino;

    public AdapterMensaje(ArrayList<DataBaseItem> listaCabecera, Usuario usuarioAutor, Usuario usuarioDestino) {
        this.listaMensajes = listaCabecera;
        this.usuarioAutor = usuarioAutor;
        this.usuarioDestino = usuarioDestino;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mensaje, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                if(mListener!=null) {
                    mListener.onItemClick(listaMensajes.get(position), position);
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Mensaje mensaje = (Mensaje)listaMensajes.get(position);

/*
        //Cambiar el color si me escribe alguien
        if (mensaje.getIdUsuario() != singleCredenciales.getIdUsuario()){
            holder.v.setBackgroundColor(Color.parseColor("#A01D871E"));
        }else{
            holder.v.setBackgroundColor(Color.parseColor("#A0FF4081"));
        }
*/
        //Recuperar el usuario que ha creado el chat
        ArrayList<DataBaseItem> resultados = new ArrayList<>();

        Usuario usuarioAux;
        if (mensaje.getIdUsuario() == usuarioAutor.getIdUsuario()){
            usuarioAux = usuarioAutor;
        }else{ //Por si acaso
            usuarioAux = usuarioDestino;
        }

        String fecha = mensaje.getFecha();
        String hora = mensaje.getHora();
        String contenidoMensaje = mensaje.getMensaje();
        String autor = usuarioAux.getNombre() + " "
                + usuarioAux.getApellidos();

        holder.fecha.setText(fecha);
        holder.hora.setText(hora);
        holder.contenidoMensaje.setText(contenidoMensaje);
        holder.autor.setText(autor);

    }

    public void actualizar(ArrayList<DataBaseItem> nuevaLista){
        listaMensajes.clear();
        listaMensajes.addAll(nuevaLista);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaMensajes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView autor;
        private TextView fecha;
        private TextView hora;
        private TextView contenidoMensaje;
        private View v;


        public ViewHolder(View v) {
            super(v);
            autor = (TextView) v.findViewById(R.id.textMensajeAutor);
            fecha = (TextView) v.findViewById(R.id.mensajeFecha);
            hora = (TextView) v.findViewById(R.id.mensajeHora);
            contenidoMensaje = (TextView) v.findViewById(R.id.textMensajeMensaje);
            this.v = v;
        }
    }

    public Integer ultimaPosicion(){
        Integer retorno = 0;
        if (listaMensajes.size()>0){
            retorno = listaMensajes.size()-1;
        }
        return retorno;
    }

    public interface OnItemClickListener {
        public void onItemClick(DataBaseItem item, int position);
    }
    private AdapterMensaje.OnItemClickListener mListener;

    public void setOnItemListener(AdapterMensaje.OnItemClickListener listener) {
        mListener = listener;
    }


}