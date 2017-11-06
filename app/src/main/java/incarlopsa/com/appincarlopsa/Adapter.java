package incarlopsa.com.appincarlopsa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Nela on 06/11/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<DataBaseItem> listaCabecera;

    public Adapter(ArrayList<DataBaseItem> listaCabecera) {
        this.listaCabecera = listaCabecera;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cabecera_chat, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Chat cabecera = (Chat)listaCabecera.get(position);

        //Recuperar el usuario que ha creado el chat
        ArrayList<DataBaseItem> resultados;
        Usuario usuarioAux = new Usuario();
        Integer idAutor = cabecera.getIdUsuario();
        usuarioAux.setIdUsuario(idAutor);
        HiloParaRead hiloParaRead = new HiloParaRead(new DAOUsuario());
        try {
            resultados = hiloParaRead.execute(usuarioAux).get();
        } catch (Exception e) {
            e.printStackTrace();
        }


        String titulo = cabecera.getTitulo();
        Integer autor = cabecera.getIdUsuario();
        String fechaCreacion = cabecera.getFechaCreacion();
        String horaCreacion = cabecera.getHoraCreacion();
        String fechaUpdate = cabecera.getFechaUltimoUpdate();
        String horaUpdate = cabecera.getHoraUltimoUpdate();

        holder.name.setText(name);
    }

    @Override
    public int getItemCount() {
        return listaCabecera.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.textUserName);
        }
    }

}