package incarlopsa.com.appincarlopsa;

import android.os.AsyncTask;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class HiloParaRead extends AsyncTask<Integer, Void, ArrayList<DataBaseItem>> implements ICodigos {

    //CODIGO DE EJEMPLO
    /*
        Usuario usuarioPrueba = new Usuario(1,"a","b","c","d",null);
        Integer idALeer = usuarioPrueba.getIdUsuario();
        HiloParaRead hilo = new HiloParaRead(new DAOUsuario());
        ArrayList<DataBaseItem> resultados;
        try {
            resultados = hilo.execute(idALeer).get();

            //Sacar cada cosa de dentro y modelarla:
            for(DataBaseItem u:resultados){
                ((Usuario)u).toString(); //HACER ALGO CON EL ITEM
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    */

    public void ejemplo(){
        Usuario usuarioPrueba = new Usuario(1,"a","b","c","d",null);
        Integer idALeer = usuarioPrueba.getIdUsuario();
        HiloParaRead hilo = new HiloParaRead(new DAOUsuario());
        ArrayList<DataBaseItem> resultados;
        try {
            resultados = hilo.execute(idALeer).get();

            //Sacar cada cosa de dentro y modelarla:
            for(DataBaseItem u:resultados){
                ((Usuario)u).toString(); //HACER ALGO CON EL ITEM
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Propiedades
    private DAOBase dao;

    public HiloParaRead(DAOBase dao){
        this.dao = dao;
    }

    @Override
    protected ArrayList<DataBaseItem> doInBackground(Integer... parametros) {

        Integer id = parametros[0];
        ArrayList<DataBaseItem> retorno = null;

        try {
            retorno = dao.read(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (retorno != null){
            System.out.println("Lectura correcta!");
        }else{
            System.out.println("No se ha leido nada!");
        }

        return retorno;
    }

}
