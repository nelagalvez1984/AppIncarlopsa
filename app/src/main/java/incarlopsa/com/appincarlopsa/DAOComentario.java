package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOComentario extends DAOBase implements IDAO, ICodigos {

    //Propiedades

    //Constructor
    public DAOComentario() { }

    //Consultas parametrizadas
    private String consultaInsercion = "INSERT INTO comentario SET idPublicacion = ?, idUsuario = ?, fecha = NOW(), comentario = ?";
    private String consultaLecturaPorId = "SELECT idComentario,idPublicacion, idUsuario, "
                        +"DATE_FORMAT(fecha, '%d/%m/%y') AS fecha1, TIME_FORMAT(fecha, '%H:%i') AS hora1, "
                        +"comentario FROM comentario WHERE idComentario = ? ORDER BY fecha DESC";
    private String consultaUpdate = "UPDATE comentario SET idPublicacion = ?, idUsuario = ?, fecha = NOW(), comentario = ? WHERE idComentario = ?";
    private String consultaDelete = "DELETE FROM comentario WHERE idComentario = ?";

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
        cargarConsulta(aux.getId());
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
        prepararConsulta(consultaDelete);
        cargarConsulta(aux.getId());
    }

    //CONTROL DE CONSULTAS CRUD:
    @Override
    public Boolean create(Object elementoACrear) throws SQLException {
        return super.create(elementoACrear);
    }

    @Override
    public ArrayList<DataBaseItem> read(Object filtro) throws SQLException {
        return super.read(filtro);
    }

    @Override
    public Boolean update(Object elementoConQueActualizar) {
        Boolean retorno = super.update(elementoConQueActualizar);
        //Ahora actualizamos la publicacion con la fecha actual
        Comentario comentarioAux = (Comentario)elementoConQueActualizar;
        DAOPublicacion dao = new DAOPublicacion();
        Publicacion aux = new Publicacion();
        aux.setId(comentarioAux.getIdPublicacion());
        aux.setFechaUltimoUpdate(ACTUALIZA_FECHA); //SE PASA POR ESTE CAMPO MISMAMENTE
        dao.update(aux);
        return retorno;
    }

    @Override
    public Boolean delete(Object elementoABorrar) {
        //Borrar primero todos los likes asociados
        Comentario comentarioAux = (Comentario)elementoABorrar;
        DAOLikes dao = new DAOLikes();
        MeAlgo aux = new MeGusta(); //da igual si es megusta o medisgusta para este caso
        aux.setIdComentario(comentarioAux.getId());
        dao.delete(aux);
        //Y ahora borrar el comentario
        return super.delete(elementoABorrar);
    }
}
