package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOComentario extends DAOBase implements IDAO, ICodigos {

    //Consultas parametrizadas
    private String consultaInsercion = "INSERT INTO comentario SET idPublicacion = ?, idUsuario = ?, fecha = NOW(), comentario = ?";
    private String consultaLecturaPorId = "SELECT idComentario,idPublicacion, idUsuario, "
                        +"DATE_FORMAT(fecha, '%d/%m/%y') AS fecha1, TIME_FORMAT(fecha, '%H:%i') AS hora1, "
                        +"comentario FROM comentario WHERE idPublicacion = ? ORDER BY fecha DESC";
    private String consultaUpdate = "UPDATE comentario SET idPublicacion = ?, idUsuario = ?, fecha = NOW(), comentario = ? WHERE idComentario = ?";
    private String consultaDeleteComentario = "DELETE FROM comentario WHERE idComentario = ?";
    private String consultaDeletePublicacion = "DELETE FROM comentario WHERE idPublicacion = ?";

    //CREACION
    //Preparar una consulta de create y cargar sus parametros
    @Override
    protected void prepararCreate(Object elementoAModelar) throws SQLException {

        Comentario aux = (Comentario)elementoAModelar;
        prepararConsulta(consultaInsercion);
        cargarConsulta(aux.getIdPublicacion(),
                        aux.getIdUsuario(),
                        aux.getMensaje());
    }

    //LECTURA
    //Tipo de filtro a aplicar a la consulta de lectura
    // (por que campo se tirara para determinar la consulta concreta)
    @Override
    protected void prepararRead(Object filtro) throws SQLException {
        Comentario aux = (Comentario)filtro;
        consultaSQL = consultaLecturaPorId;
        prepararConsulta(consultaSQL);
        cargarConsulta(aux.getIdPublicacion());
    }

    //Rellenar el array de resultados con cada resultado
    @Override
    protected void rellenarObjetos() throws SQLException {
        Comentario aux = new Comentario(resultados.getInt(1),
                                        resultados.getInt(2),
                                        resultados.getInt(3),
                                        resultados.getString(4),
                                        resultados.getString(5),
                                        resultados.getString(6) );
        resultadoMultiple.add(aux);
    }

    //UPDATE
    //Preparar una consulta de update y cargar sus parametros
    @Override
    protected void prepararUpdate(Object elementoAModelar) throws SQLException {
        Comentario aux = (Comentario) elementoAModelar;
        consultaSQL = consultaUpdate;
        prepararConsulta(consultaUpdate);
        cargarConsulta(aux.getIdPublicacion(),
                      aux.getIdUsuario(),
                      aux.getMensaje(),
                      aux.getId());
    }

    //DELETE
    @Override
    protected void prepararDelete(Object elementoAModelar) throws SQLException {
        Comentario aux = (Comentario)elementoAModelar;
        if (aux.getId() != null){ //Borrar solo ese comentario
            consultaSQL = consultaDeleteComentario;
            prepararConsulta(consultaDeleteComentario);
            cargarConsulta(aux.getId());
        }else{ //Borrar la comentario entera
            consultaSQL = consultaDeleteComentario;
            prepararConsulta(consultaDeletePublicacion);
            cargarConsulta(aux.getId());

        }
    }

    //CONTROL DE CONSULTAS CRUD:
    @Override
    public Boolean create(Object elementoACrear) throws SQLException {
        return super.create(elementoACrear);
    }

    @Override
    public ArrayList<DataBaseItem> read(Object filtro) throws SQLException {
        //Se lee los comentarios
        resultadoMultiple = super.read(filtro);

        //Por cada comentario, se busca sus likes (si tiene)
        DAOLikes daoLikes = new DAOLikes();
        for(DataBaseItem comentario:resultadoMultiple){
            //se crea un "mealgo" (megusta por ejemplo) y se le inyecta el ID del comentario a modo de filtro
            Comentario auxComentario = (Comentario)comentario;
            MeGusta auxMeAlgo = new MeGusta();
            auxMeAlgo.setIdComentario(auxComentario.getId());
            ArrayList<DataBaseItem> resultadoMeAlgo = daoLikes.read(auxMeAlgo); //Ojo, puede estar vacio!
            if (resultadoMeAlgo != null){
                auxComentario.setArrayLikes(resultadoMeAlgo);
            }else{ //Array vacio!
                auxComentario.setArrayLikes(new ArrayList<DataBaseItem>());
            }
        }

        return resultadoMultiple;
    }

    @Override
    public Boolean update(Object elementoConQueActualizar) throws SQLException{
        Boolean retorno = super.update(elementoConQueActualizar);
        //Ahora actualizamos la comentario con la fecha actual
        Comentario comentarioAux = (Comentario)elementoConQueActualizar;
        DAOPublicacion dao = new DAOPublicacion();
        Publicacion auxPublicacion = new Publicacion();
        auxPublicacion.setId(comentarioAux.getIdPublicacion());
        auxPublicacion.setFechaUltimoUpdate(ACTUALIZA_FECHA); //SE PASA POR ESTE CAMPO MISMAMENTE
        dao.update(auxPublicacion);
        return retorno;
    }

    @Override
    public Boolean delete(Object elementoABorrar) throws SQLException{
        //Borrar primero todos los likes asociados
        Comentario comentarioAux = (Comentario)elementoABorrar;
        DAOLikes dao = new DAOLikes();
        MeAlgo auxMeAlgo = new MeGusta(); //da igual si es megusta o medisgusta para este caso
        auxMeAlgo.setIdComentario(comentarioAux.getId());
        dao.delete(auxMeAlgo);
        //Y ahora borrar el comentario
        return super.delete(elementoABorrar);
    }
}
