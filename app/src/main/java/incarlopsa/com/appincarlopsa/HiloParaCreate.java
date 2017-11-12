package incarlopsa.com.appincarlopsa;

import android.os.AsyncTask;

import java.sql.SQLException;

public class HiloParaCreate extends AsyncTask<Object, Void, Boolean> implements ICodigos{



    /*
        // CODIGO DE EJEMPLO DE CREACION //

        Usuario usuarioParaCrear = new Usuario(1,"a","b","c","d",null);
        HiloParaCreate hilo = new HiloParaCreate(new DAOUsuario());
        boolean retornoCreacion = false;
        try {
            retornoCreacion = hilo.execute(usuarioParaCrear).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    */

    //Propiedades
    private DAOBase dao;
    SingleTostada tostada = SingleTostada.getInstance();

    //Constructor
    public HiloParaCreate(DAOBase dao) {
        this.dao = dao;
    }

    @Override
    protected Boolean doInBackground(Object... parametrosParaConsulta) {

        int numParametros = parametrosParaConsulta.length; //Debe ser 1, si no es un error en la llamada

        boolean operacionCorrecta = false;
        try{
            if (numParametros==1) { //Para las escrituras solo puede haber un parametro!
                operacionCorrecta = (dao.create(parametrosParaConsulta[0]));
                if (operacionCorrecta) {
                    System.out.println("Creacion correcta!");
                } else {
                    System.out.println("No se ha podido crear!");
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

        return operacionCorrecta;
    }

}