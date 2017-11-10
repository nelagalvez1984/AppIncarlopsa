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
/*

            //ToDO pruebas! Eliminar este bloque!
            resultadosUsuarios = new ArrayList<>();
            Usuario temp1 = new Usuario();
            temp1.setNombre("Fulano");
            temp1.setApellidos("Martinez");
            temp1.setTipoEmpleado("Empleado");
            Usuario temp2 = new Usuario();
            temp2.setNombre("Sotano");
            temp2.setApellidos("Fernandez");
            temp2.setTipoEmpleado("Empleado");
            Usuario temp3 = new Usuario();
            temp3.setNombre("Mengano");
            temp3.setApellidos("Velasco");
            temp3.setTipoEmpleado("RRHH");
            Usuario temp4 = new Usuario();
            temp4.setNombre("Tralalá");
            temp4.setApellidos("Galvez");
            temp4.setTipoEmpleado("Admin");
            Usuario temp5 = new Usuario();
            temp5.setNombre("Asuncion");
            temp5.setApellidos("Jimenez");
            temp5.setTipoEmpleado("Empleado");
            Usuario temp6 = new Usuario();
            temp6.setNombre("Marta");
            temp6.setApellidos("Jar");
            temp6.setTipoEmpleado("Empleado");
            Usuario temp7 = new Usuario();
            temp7.setNombre("Pepa");
            temp7.setApellidos("Mendez");
            temp7.setTipoEmpleado("Empleado");
            Usuario temp8 = new Usuario();
            temp8.setNombre("Julia");
            temp8.setApellidos("Andele");
            temp8.setTipoEmpleado("Empleado");
            resultadosUsuarios.add(temp1);
            resultadosUsuarios.add(temp2);
            resultadosUsuarios.add(temp3);
            resultadosUsuarios.add(temp4);
            resultadosUsuarios.add(temp5);
            resultadosUsuarios.add(temp6);
            resultadosUsuarios.add(temp7);
            resultadosUsuarios.add(temp8);
            //TODO AQUI TERMINA LO DE TEST
*/
            if (resultadosUsuarios.size()>0){ //Deberia serlo, debe haber mas de 1 empleado en la empresa
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
