package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOLikes extends DAOBase implements IDAO{

    //Consultas parametrizadas
    String consultaLecturaPorId = "SELECT idLike, idUsuario, idComentario, tipoLike FROM likes WHERE idComentario = ?";
    String consultaVerSiHaVotado = "SELECT idLike, idUsuario, idComentario, tipoLike FROM likes " +
            "WHERE idComentario = ? AND idUsuario = ?";
    String consultaInsercion = "INSERT INTO likes SET idUsuario = ? , idComentario = ? , tipoLike = ?";
    String consultaUpdate = "UPDATE likes SET idUsuario = ? , idComentario = ? , tipoLike = ? WHERE idLike = ?";
    String consultaDeleteComentarioEntero = "DELETE FROM likes WHERE idComentario = ?";
    String consultaDeleteSoloLike = "DELETE FROM likes WHERE idLike = ?";

    //Metodos
    //LECTURA
    @Override
    protected void prepararRead(Object filtro) throws SQLException {
        MeAlgo aux = (MeAlgo)filtro;
        if (aux.getIdUsuario() != null && aux.getIdComentario() != null){ //Comprobar si ese usuario ya ha votado el comentario!
            consultaSQL = consultaVerSiHaVotado;
            prepararConsulta(consultaSQL);
            cargarConsulta(aux.getIdComentario(),
                    aux.getIdUsuario());
        }else{ //Se trata de recuperar los votos del comentario
            consultaSQL = consultaLecturaPorId;
            prepararConsulta(consultaSQL);
            cargarConsulta(aux.getIdComentario());

        }
    }

    @Override
    protected void rellenarObjetos() throws SQLException {
        MeAlgo aux = null;
        if (resultados.getString(4).equals(ME_GUSTA)){
            aux = new MeGusta(resultados.getInt(1),
                                    resultados.getInt(2),
                                    resultados.getInt(3));

        }else{
            if (resultados.getString(4).equals(NO_ME_GUSTA)){
                aux = new MeDisgusta(resultados.getInt(1),
                        resultados.getInt(2),
                        resultados.getInt(3));
            }else{
                //NO existen mas casos
            }
        }

        resultadoMultiple.add(aux);
    }

    //CREACION
    @Override
    protected void prepararCreate(Object elementoAModelar) throws SQLException {
        MeAlgo aux = (MeAlgo)elementoAModelar;
        prepararConsulta(consultaInsercion);
        cargarConsulta(aux.getIdUsuario(),
                                aux.getIdComentario(),
                                aux.getTipo());
    }

    //ACTUALIZACION
    @Override
    protected void prepararUpdate(Object elementoAModelar) throws SQLException {
        prepararConsulta(consultaUpdate);
        if (elementoAModelar instanceof MeGusta){
            MeGusta aux = (MeGusta) elementoAModelar;
            cargarConsulta(aux.getIdUsuario(),
                    aux.getIdComentario(),
                    aux.getTipo(),
                    aux.getId());

        }else{
            MeDisgusta aux = (MeDisgusta) elementoAModelar;
            cargarConsulta(aux.getIdUsuario(),
                    aux.getIdComentario(),
                    aux.getTipo(),
                    aux.getId());

        }
    }

    //DELETE
    @Override
    protected void prepararDelete(Object elementoAModelar) throws SQLException {
        MeAlgo aux = (MeAlgo)elementoAModelar;
        if (aux.idComentario != null){ //Borrado en cadena por borrado del comentario
            consultaSQL = consultaDeleteComentarioEntero;
            prepararConsulta(consultaSQL);
            cargarConsulta(aux.getIdComentario());
        }else{ //Solo borrar el Like
            consultaSQL = consultaDeleteSoloLike;
            prepararConsulta(consultaSQL);
            cargarConsulta(aux.getId());
        }

    }

    //CONTROL DE CONSULTAS CRUD:
    @Override
    public Boolean create(Object elementoACrear) throws SQLException {
        Boolean retorno = false;
        ArrayList<DataBaseItem> resultados;
        //Comprobar si ya ha dado su voto
        resultados = read(elementoACrear);

        if (resultados.size()>0){ //Ya habia votado! (de hecho debe ser size=1)
            retorno = false;
            System.out.println("No se permite votar dos veces!");
        }else{ //aun no ha votado, se permite su voto
            retorno = super.create(elementoACrear);
        }

        return retorno;
    }

    @Override
    public ArrayList<DataBaseItem> read(Object filtro) throws SQLException{
        return super.read(filtro);
    }

    @Override
    public Boolean update(Object elementoConQueActualizar) throws SQLException{
        return super.update(elementoConQueActualizar);
    }

    @Override
    public Boolean delete(Object elementoABorrar) throws SQLException{
        return super.delete(elementoABorrar);
    }
}
