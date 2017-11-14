package incarlopsa.com.appincarlopsa;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterUsuario extends RecyclerView.Adapter<AdapterUsuario.ViewHolder> implements ICodigos{

    private ArrayList<DataBaseItem> listaUsuarios;
    private RetornoAdaptadorUsuario retornoAdaptadorUsuario;

    public AdapterUsuario(ArrayList<DataBaseItem> listaUsuarios, Context contexto) {
        this.listaUsuarios = listaUsuarios;
        try{
            retornoAdaptadorUsuario = (RetornoAdaptadorUsuario)contexto;
        }catch (Exception e){
            //Si esta bien llamada no deberia salir error
        }
    }

    @Override
    public AdapterUsuario.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.usuario_mostrado, parent, false);
        final AdapterUsuario.ViewHolder viewHolder = new AdapterUsuario.ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() { //Cazar el usuario
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();

                if(mListener!=null) {
                    mListener.onItemClick(listaUsuarios.get(position), position);
                }

                //Devolucion del usuario
                Usuario usuarioAux = (Usuario) listaUsuarios.get(position);
                retornoAdaptadorUsuario.devolverUsuario(usuarioAux); //Aqui se devuelve, metodo callback

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterUsuario.ViewHolder holder, int position) {
        Usuario usuarioAux = (Usuario) listaUsuarios.get(position);

        //Asignaciones
        String nombre = usuarioAux.getNombre();
        String apellidos = usuarioAux.getApellidos();
        String tipoEmpleado = usuarioAux.getTipoEmpleado();

        //Actualizar graficamente el holder
        holder.nombreUsuario.setText(nombre+" "+apellidos);
        holder.tipoEmpleado.setText(tipoEmpleado);

        //Asignar foto
        if (usuarioAux.getFotoBytes() != null){
            Bitmap foto = usuarioAux.getFotoBMP();
            holder.foto.setImageBitmap(foto);
        }else{
            holder.foto.setImageDrawable(holder.v.getResources().getDrawable(R.drawable.foto_usuario_peque));
            holder.foto.setAdjustViewBounds(true);
        }
    }

    public void actualizar(ArrayList<DataBaseItem> nuevaLista){
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombreUsuario;
        private TextView tipoEmpleado;
        private ImageView foto;
        private View v;
        private Resources recursos;

        public ViewHolder(View v) {
            super(v);
            nombreUsuario = (TextView) v.findViewById(R.id.lblAdjuntoNombre);
            tipoEmpleado = (TextView) v.findViewById(R.id.lblUsuarioTipo);
            foto = (ImageView) v.findViewById(R.id.imgAdjuntoMiniatura);
            this.v = v;
            recursos = v.getResources();
        }
    }

    public Integer ultimaPosicion(){
        Integer retorno = 0;
        if (listaUsuarios.size()>0){
            retorno = listaUsuarios.size()-1;
        }
        return retorno;
    }

    public interface OnItemClickListener {
        void onItemClick(DataBaseItem item, int position);
    }
    private AdapterUsuario.OnItemClickListener mListener;

    public void setOnItemListener(AdapterUsuario.OnItemClickListener listener) {
        mListener = listener;
    }

    public interface RetornoAdaptadorUsuario{
        void devolverUsuario(Usuario usuarioRetornado);
    }

}
