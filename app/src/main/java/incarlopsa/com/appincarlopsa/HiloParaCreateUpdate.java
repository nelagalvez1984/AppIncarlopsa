package incarlopsa.com.appincarlopsa;

import android.os.AsyncTask;
import java.sql.SQLException;

public class HiloParaCreateUpdate extends AsyncTask<Object, Void, Boolean> implements ICodigos{


    //CODIGO DE EJEMPLO DE CREACION // Creacion --> llamada: execute(objetoACrear)
    /*
        Usuario usuarioParaCrear = new Usuario(1,"a","b","c","d",null);
        HiloParaCreateUpdate hilo = new HiloParaCreateUpdate(new DAOUsuario());
        boolean retornoCreacion = false;
        try {
            retornoCreacion = hilo.execute(usuarioParaCrear).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

    //CODIGO DE EJEMPLO DE UPDATE // Update --> llamada: execute(idAModificar, nuevoObjeto)
        Usuario viejoUsuarioLeido = new Usuario(1,"a","b","c","d",null);
        Integer idViejoUsuarioLeido = viejoUsuarioLeido.getIdUsuario();

        //Modificaciones
        Usuario usuarioModificado = viejoUsuarioLeido;
        usuarioModificado.setNombre("NuevoNombre");
        HiloParaCreateUpdate hilo = new HiloParaCreateUpdate(new DAOUsuario());
        boolean retornoCreacion = false;
        try {
            retornoCreacion = hilo.execute(idViejoUsuarioLeido, usuarioModificado).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
     */

    //Propiedades
    private DAOBase dao;

    //Constructor
    public HiloParaCreateUpdate(DAOBase dao) {
        this.dao = dao;
    }

    @Override
    protected Boolean doInBackground(Object... parametrosParaConsulta) {

        int numParametros = parametrosParaConsulta.length; //Si es 1, es una creacion. Si son 2, es un update

        boolean operacionCorrecta = false;
        try{
            if (numParametros == 1){ // Creacion --> [0] = objeto
                operacionCorrecta = (dao.create(parametrosParaConsulta[0]));
                if (operacionCorrecta){
                    System.out.println("Creacion correcta!");
                }else{
                    System.out.println("Creacion rechazada!");
                }
            }else{ // Update --> llamada: execute(nuevo objeto, ids)
                operacionCorrecta = (dao.update( parametrosParaConsulta[0],
                        (Integer)parametrosParaConsulta[1]) );
                if (operacionCorrecta){
                    System.out.println("Actualizacion correcta!");
                }else{
                    System.out.println("Actualizacion rechazada!");
                }
            }
        } catch (SQLException e) {
            if(e.getErrorCode() == ENTRADA_DUPLICADA){
                System.out.println("Entrada duplicada");
            }else{
                e.printStackTrace();
            }
        }
        return operacionCorrecta;
    }

}