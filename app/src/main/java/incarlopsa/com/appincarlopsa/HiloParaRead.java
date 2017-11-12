package incarlopsa.com.appincarlopsa;

import android.os.AsyncTask;
import java.sql.SQLException;
import java.util.ArrayList;

public class HiloParaRead extends AsyncTask<Object, Void, ArrayList<DataBaseItem>> implements ICodigos {


    /*
        // CODIGO DE EJEMPLO DE LECTURA //

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

    //Propiedades
    private DAOBase dao;
    SingleTostada tostada = SingleTostada.getInstance();

    public HiloParaRead(DAOBase dao){
        this.dao = dao;
    }

    @Override
    protected ArrayList<DataBaseItem> doInBackground(Object... parametrosParaConsulta) {

        int numParametros = parametrosParaConsulta.length; //Debe ser 1, si no es un error en la llamada

        ArrayList<DataBaseItem> retorno = new ArrayList<>();

        try {
            if (numParametros==1) { //Para las escrituras solo puede haber un parametro!
                retorno = dao.read(parametrosParaConsulta[0]);
                if (retorno != null) {
                    System.out.println("Lectura correcta!");
                } else {
                    System.out.println("No se ha leido nada!");
                    throw new EXCErrorBBDD();
                }
            }else{ //Error en la llamada!
                throw new EXCNumParametrosIncorrecto();
            }
        }catch(EXCNumParametrosIncorrecto e) {
            tostada.errorNumeroParametrosIncorrecto();
        }catch(EXCErrorBBDD e) {
            tostada.errorConexionBBDD();
        }catch(SQLException e) {
            tostada.errorConexionBBDD();
        }

        return retorno;
    }

}
