package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOComentario extends DAOBase implements IDAO {

    //Propiedades

    //Constructor


    public DAOComentario() { }

    //Consultas parametrizadas
    private String consultaInsercion = "INSERT INTO comentario SET idPublicacion = ?, idUsuario = ?, fecha = NOW(), comentario = ?";
    private String consultaLecturaPorId = "SELECT idComentario,idPublicacion, idUsuario, "
                        +"DATE_FORMAT(fecha, '%d/%m/%y') AS fecha1, TIME_FORMAT(fecha, '%H:%i') AS hora1, "
                        +"comentario FROM comentario WHERE idComentario = ? ORDER BY fecha DESC";
    private String consultaUpdate = "UPDATE comentario SET idComentario = ?,idPublicacion = ?, idUsuario = ?, fecha = NOW(), comentario = ? WHERE idComentario = ?";

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
    protected void prepararFiltroConsultaRead(Object filtro) {
        consultaSQL = consultaLecturaPorId;
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
        //EN ESTA VERSION NO SE HARAN EDICIONES DE COMENTARIOS
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
        //EN ESTA VERSION NO SE HARAN EDICIONES DE COMENTARIOS
        return null;
    }

    @Override
    public Boolean delete(Object elementoABorrar) { //NO SE BORRAN USUARIOS DESDE NUESTRA APP!
        return null;
    }
}
