package incarlopsa.com.appincarlopsa;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class AdapterComentario extends RecyclerView.Adapter<AdapterComentario.ViewHolder> implements ICodigos{

    private ArrayList<DataBaseItem> listaComentarios;
    private SingleCredenciales credenciales = SingleCredenciales.getInstance();
    private HiloParaRead hiloParaRead;
    private ArrayList<DataBaseItem> resultadosUsuarios;
    private SingleTostada tostada = SingleTostada.getInstance();

    public AdapterComentario(ArrayList<DataBaseItem> listaCabecera) {
        this.listaComentarios = listaCabecera;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comentario, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                if(mListener!=null) {
                    mListener.onItemClick(listaComentarios.get(position), position);
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comentario comentario = (Comentario) listaComentarios.get(position);

        //Recuperar el usuario que ha creado el comentario
        ArrayList<DataBaseItem> resultados = new ArrayList<>();

        //Recuperar el nombre del usuario
        String nombreUsuario;
        if (comentario.getIdUsuario() == credenciales.getIdUsuario()){
            nombreUsuario = credenciales.getNombre()+" "+credenciales.getApellidos();
        }else{ //Por si acaso
            Usuario usuarioAux = new Usuario();
            usuarioAux.setIdUsuario(comentario.getIdUsuario());
            hiloParaRead = new HiloParaRead(new DAOUsuario());
            try {
                resultadosUsuarios = hiloParaRead.execute(usuarioAux).get();
                if (resultadosUsuarios.size()>0){
                    usuarioAux = (Usuario)resultadosUsuarios.get(0);
                    nombreUsuario = usuarioAux.getNombre()+" "+usuarioAux.getApellidos();
                }else{
                    nombreUsuario = USUARIO_DESCONOCIDO;
                }
            } catch (Exception e) {
                tostada.errorConexionBBDD();
                nombreUsuario = USUARIO_DESCONOCIDO;
            }
        }

        //Comprobar si se ha votado MeGusta o MeDisgusta y en caso afirmativo colorearlo


        //Resto de asignaciones
        String fecha = comentario.getFecha();
        String hora = comentario.getHora();
        String comentarioDicho = comentario.getMensaje();
        String numLikes = comentario.getNumeroMeGusta().toString();
        String numDislikes = comentario.getNumeroMeDisgusta().toString();

        holder.nombreUsuario.setText(nombreUsuario);
        holder.fecha.setText(fecha);
        holder.hora.setText(hora);
        holder.comentarioDicho.setText(comentarioDicho);
        holder.numLikes.setText(numLikes);
        holder.numDislikes.setText(numDislikes);

    }

    public void actualizar(ArrayList<DataBaseItem> nuevaLista){
        listaComentarios.clear();
        listaComentarios.addAll(nuevaLista);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaComentarios.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombreUsuario;
        private TextView fecha;
        private TextView hora;
        private TextView comentarioDicho;
        private TextView numLikes;
        private TextView numDislikes;
        private ImageView fotoMeGusta;
        private ImageView fotoMeDisgusta;
        private View v;

        public ViewHolder(View v) {
            super(v);
            nombreUsuario = (TextView) v.findViewById(R.id.txtComentarioUsuario);
            fecha = (TextView) v.findViewById(R.id.txtComentarioFecha);
            hora = (TextView) v.findViewById(R.id.txtComentarioHora);
            comentarioDicho = (TextView) v.findViewById(R.id.txtComentarioComentario);
            numLikes = (TextView) v.findViewById(R.id.txtComentarioNumLikes);
            numDislikes = (TextView) v.findViewById(R.id.txtComentarioNumDislikes);
            this.v = v;
        }
    }

    public Integer ultimaPosicion(){
        Integer retorno = 0;
        if (listaComentarios.size()>0){
            retorno = listaComentarios.size()-1;
        }
        return retorno;
    }

    public interface OnItemClickListener {
        public void onItemClick(DataBaseItem item, int position);
    }
    private AdapterComentario.OnItemClickListener mListener;

    public void setOnItemListener(AdapterComentario.OnItemClickListener listener) {
        mListener = listener;
    }

}