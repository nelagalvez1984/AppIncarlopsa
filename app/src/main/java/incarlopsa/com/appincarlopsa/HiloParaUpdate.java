package incarlopsa.com.appincarlopsa;

import android.os.AsyncTask;

import java.sql.SQLException;

public class HiloParaUpdate extends AsyncTask<Object, Void, Boolean> implements ICodigos{



    /*
        // CODIGO DE EJEMPLO DE UPDATE //

        Usuario viejoUsuarioLeido = new Usuario(1,"a","b","c","d",null);
        Integer idViejoUsuarioLeido = viejoUsuarioLeido.getIdUsuario();

        //Modificaciones
        Usuario usuarioModificado = viejoUsuarioLeido;
        usuarioModificado.setNombre("NuevoNombre");
        HiloParaUpdate hilo = new HiloParaUpdate(new DAOUsuario());
        boolean retornoCreacion = false;
        try {
            retornoCreacion = hilo.execute(idViejoUsuarioLeido, usuarioModificado).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    */

    //Propiedades
    private DAOBase dao;
    SingleTostada tostada = SingleTostada.getInstance();

    //Constructor
    public HiloParaUpdate(DAOBase dao) {
        this.dao = dao;
    }

    @Override
    protected Boolean doInBackground(Object... parametrosParaConsulta) {

        int numParametros = parametrosParaConsulta.length; //Debe ser 1, si no es un error en la llamada

        boolean operacionCorrecta = false;

            try {
                if (numParametros == 1){ //Para los updates solo puede haber un parametro!
                    operacionCorrecta = (dao.update(parametrosParaConsulta[0]) );
                    if (operacionCorrecta){
                        System.out.println("Actualizacion correcta!");
                    }else{
                        System.out.println("Actualizacion rechazada!");
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