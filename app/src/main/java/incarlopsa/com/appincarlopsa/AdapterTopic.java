package incarlopsa.com.appincarlopsa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterTopic extends RecyclerView.Adapter<AdapterTopic.ViewHolder> implements ICodigos{

    private ArrayList<DataBaseItem> listaCabecera;
    private DAOBase dao;
    private Topic cabecera;
    private Integer claseTopic;

    public AdapterTopic(ArrayList<DataBaseItem> listaCabecera, DAOBase dao, Integer claseTopic) {
        this.listaCabecera = listaCabecera;
        this.dao = dao;
        this.claseTopic = claseTopic;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cabecera, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                if(mListener!=null) {
                    mListener.onItemClick(listaCabecera.get(position), position);
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        cabecera = (Topic)listaCabecera.get(position);
        ArrayList<DataBaseItem> resultados = new ArrayList<>();
        Usuario usuarioAux = new Usuario();
        Integer idAutor;

        //Comprobar de que clase de topic hablamos
        if (claseTopic == TOPIC_CHAT_SALIENTE){ //Lo que se recupera aqui es el destinatario
            idAutor = ((Chat)cabecera).getIdUsuarioDestino();
        }else{ //Es chat entrante o publicacion, se recupera el autor
            idAutor = cabecera.getIdUsuario();
        }
        usuarioAux.setIdUsuario(idAutor);

        //Recuperar el usuario
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

        //Asignaciones
        String titulo = cabecera.getTitulo();
        String autor = usuarioAux.getNombre() + " "
                + usuarioAux.getApellidos();
        String fechaCreacion = cabecera.getFechaCreacion();
        String horaCreacion = cabecera.getHoraCreacion();
        String fechaUpdate = cabecera.getFechaUltimoUpdate();
        String horaUpdate = cabecera.getHoraUltimoUpdate();

        //Actualizar graficamente el holder
        holder.titulo.setText(titulo);
        holder.autor.setText(autor);
        holder.fechaCreacion.setText(fechaCreacion);
        holder.horaCreacion.setText(horaCreacion);
        holder.fechaUpdate.setText(fechaUpdate);
        holder.horaUpdate.setText(horaUpdate);

    }

    @Override
    public int getItemCount() {
        return listaCabecera.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo;
        private TextView autor;
        private TextView fechaCreacion;
        private TextView horaCreacion;
        private TextView fechaUpdate;
        private TextView horaUpdate;

        public ViewHolder(View v) {
            super(v);
            titulo = (TextView) v.findViewById(R.id.editCabeceraChatTitulo);
            autor = (TextView) v.findViewById(R.id.editCabeceraChatAutor);
            fechaCreacion = (TextView) v.findViewById(R.id.editCabeceraChatFechaCreacion);
            horaCreacion = (TextView) v.findViewById(R.id.editCabeceraChatHoraCreacion);
            fechaUpdate = (TextView) v.findViewById(R.id.editCabeceraChatFechaActualizacion);
            horaUpdate = (TextView) v.findViewById(R.id.editCabeceraChatHoraActualizacion);

        }
    }

    public interface OnItemClickListener {
        public void onItemClick(DataBaseItem item, int position);
    }
    private AdapterTopic.OnItemClickListener mListener;

    public void setOnItemListener(AdapterTopic.OnItemClickListener listener) {
        mListener = listener;
    }

    public Integer ultimaPosicion(){
        Integer retorno = 0;
        if (listaCabecera.size()>0){
            retorno = listaCabecera.size()-1;
        }
        return retorno;
    }
}