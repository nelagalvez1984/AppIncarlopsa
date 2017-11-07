package incarlopsa.com.appincarlopsa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterMensaje extends RecyclerView.Adapter<AdapterMensaje.ViewHolder> {

    private ArrayList<DataBaseItem> listaMensajes;

    public AdapterMensaje(ArrayList<DataBaseItem> listaCabecera) {
        this.listaMensajes = listaCabecera;
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

        //Recuperar el usuario que ha creado el chat
        ArrayList<DataBaseItem> resultados = new ArrayList<>();

        Usuario usuarioAux = new Usuario();
        Integer idAutor = mensaje.getIdUsuario();
        usuarioAux.setIdUsuario(idAutor);
        HiloParaRead hiloParaRead = new HiloParaRead(new DAOUsuario());
        try {
            resultados = hiloParaRead.execute(usuarioAux).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (resultados.size() > 0){ //deberia serlo
            usuarioAux = (Usuario)resultados.get(0);
        }else{ //Por si acaso
            usuarioAux.setNombre("Desconocido");
            usuarioAux.setApellidos("Desconocido");
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

    @Override
    public int getItemCount() {
        return listaMensajes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView autor;
        private TextView fecha;
        private TextView hora;
        private TextView contenidoMensaje;

        public ViewHolder(View v) {
            super(v);
            autor = (TextView) v.findViewById(R.id.textMensajeAutor);
            fecha = (TextView) v.findViewById(R.id.mensajeFecha);
            hora = (TextView) v.findViewById(R.id.mensajeHora);
            contenidoMensaje = (TextView) v.findViewById(R.id.textMensajeMensaje);

        }
    }

    public interface OnItemClickListener {
        public void onItemClick(DataBaseItem item, int position);
    }
    private AdapterChat.OnItemClickListener mListener;

    public void setOnItemListener(AdapterChat.OnItemClickListener listener) {
        mListener = listener;
    }

}