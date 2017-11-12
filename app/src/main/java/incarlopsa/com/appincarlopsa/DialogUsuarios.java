package incarlopsa.com.appincarlopsa;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class DialogUsuarios extends DialogFragment implements ICodigos{

    private RecyclerView recyclerView;
    private SingleCredenciales credenciales = SingleCredenciales.getInstance();
    private HiloParaRead hiloParaRead;
    private AdapterUsuario adapterUsuario;
    private ArrayList<DataBaseItem> resultadosUsuarios;
    private SingleTostada tostada = SingleTostada.getInstance();
    private Context contextoPadre = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_usuarios, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyDialogUsuarios);
        contextoPadre = getActivity();
        hiloParaRead = new HiloParaRead(new DAOUsuario());
        try {

            resultadosUsuarios = hiloParaRead.execute(DAME_TODOS).get();

            //Retirarse a si mismo de la lista
            Usuario usuarioAux;
            for(DataBaseItem item : resultadosUsuarios){
                usuarioAux = (Usuario)item;
                if (usuarioAux.getIdUsuario() == credenciales.getIdUsuario()){
                    resultadosUsuarios.remove(usuarioAux);
                    break;
                }
            }

            //Comprobar si soy empleado raso (para dejar en la lista solo a RRHH)
            if (credenciales.getTipoEmpleado().equals(EMPLEADO)){

                //Retirar de la lista todos los empleados rasos
                ArrayList<DataBaseItem> usuariosFiltrados = new ArrayList<>();
                for(DataBaseItem item : resultadosUsuarios){
                    usuarioAux = (Usuario)item;
                    if (usuarioAux.getTipoEmpleado().equals(RRHH)){
                        usuariosFiltrados.add(usuarioAux);
                    }
                }
                resultadosUsuarios = usuariosFiltrados;
            }

            if (resultadosUsuarios.size()>0){ //Deberia serlo, debe haber al menos 1 empleado en la empresa
                recyclerView = (RecyclerView)v.findViewById(R.id.recyDialogUsuarios);
                Context contexto = v.getContext();
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(contexto);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                //Cargar los mensajes
                adapterUsuario = new AdapterUsuario(resultadosUsuarios, contextoPadre);
                recyclerView.setAdapter(adapterUsuario);
            }

        } catch (Exception e) {
            tostada.errorConexionBBDD();
        }


        return v;
    }
}
