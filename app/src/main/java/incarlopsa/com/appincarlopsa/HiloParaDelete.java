package incarlopsa.com.appincarlopsa;

import android.os.AsyncTask;

import java.sql.SQLException;

public class HiloParaDelete extends AsyncTask<Object, Void, Boolean> implements ICodigos{



/*
    //CODIGO DE EJEMPLO DE DELETE //
    Usuario usuarioParaBorrar = new Usuario(1,"a","b","c","d",null);
    HiloParaDelete hilo = new HiloParaCreate(new DAOUsuario());
    boolean retornoDelete = false;
    try {
        retornoDelete = hilo.execute(usuarioParaBorrar).get();
    } catch (Exception e) {
        e.printStackTrace();
    }
*/

    //Propiedades
    private DAOBase dao;
    SingleTostada tostada = SingleTostada.getInstance();

    //Constructor
    public HiloParaDelete(DAOBase dao) {
        this.dao = dao;
    }

    @Override
    protected Boolean doInBackground(Object... parametrosParaConsulta) {

        int numParametros = parametrosParaConsulta.length; //Debe ser 1, si no es un error en la llamada

        boolean operacionCorrecta = false;

            try {
                if (numParametros==1) { //Para los borrados solo puede haber un parametro!
                    operacionCorrecta = (dao.delete(parametrosParaConsulta[0]));
                    if (operacionCorrecta) {
                        System.out.println("Creacion correcta!");
                    } else {
                        System.out.println("No se ha podido borrar!");
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