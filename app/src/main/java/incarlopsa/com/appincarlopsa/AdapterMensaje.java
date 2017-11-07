package incarlopsa.com.appincarlopsa;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterMensaje extends RecyclerView.Adapter<AdapterMensaje.ViewHolder> {

    private ArrayList<DataBaseItem> listaMensajes;
    private SingleCredenciales singleCredenciales = SingleCredenciales.getInstance();
    private ConstraintLayout container;

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

/*
        container = (ConstraintLayout) findViewById(R.id.cardviewMensaje);
        container.setBackgroundColor(0x4CAF50);
        container.setBackgroundColor(getResources().getColor(R.color.blue));
*/
        //Cambiar el color si me escribe alguien
        if (mensaje.getIdUsuario() != singleCredenciales.getIdUsuario()){
            holder.v.setBackgroundColor(Color.parseColor("#A01D871E"));
        }else{
            holder.v.setBackgroundColor(Color.parseColor("#A0FF4081"));
        }

        //Recuperar el usuario que ha creado el chat
        ArrayList<DataBaseItem> resultados = new ArrayList<>();

        Usuario usuarioAux = new Usuario();
        Integer idAutor = mensaje.getIdUsuario();
        usuarioAux.setIdUsuario(idAutor);
        HiloParaRead hiloParaRead = new HiloParaRead(new DAOUsuario());
        try {
            resultados = hiloParaRead.execute(usuarioAux).get();
        } catch (Exception e) {
            SingleTostada singleTostada = SingleTostada.getInstance();
            singleTostada.errorConexionBBDD();
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
    private AdapterChat.OnItemClickListener mListener;

    public void setOnItemListener(AdapterChat.OnItemClickListener listener) {
        mListener = listener;
    }

    public void update(ArrayList<DataBaseItem> nuevaLista){
        listaMensajes.clear();
        listaMensajes.addAll(nuevaLista);
        notifyDataSetChanged();
    }

}