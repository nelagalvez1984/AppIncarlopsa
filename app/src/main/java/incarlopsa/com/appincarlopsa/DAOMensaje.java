package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOMensaje extends DAOBase implements IDAO {

    //Propiedades
    private String nombreTabla = "mensaje";
    private String nombreIdTabla1 = "idchat";
    private String nombreIdTabla2 = "idmensaje";

    //Consultas parametrizadas
    private String consultaInsercion = "INSERT INTO mensaje SET idUsuario = ?, mensaje = ?,fecha = ?,leidopordestino = ?";
    private String consultaLecturaPorId = "SELECT idMensaje, idChat, idUsuario, mensaje, fecha, leidoPorDestino FROM mensaje WHERE idMensaje";
    private String consultaUpdate = "UPDATE mensaje SET idUsuario = ?, mensaje = ?,fecha = ?,leidopordestino = ? WHERE idMensaje";

    //CREACION
    //Preparar una consulta de create y cargar sus parametros
    @Override
    protected void prepararCreate(Object elementoAModelar) throws SQLException {
        //ToDO
    }

    //LECTURA
    //Tipo de filtro a aplicar a la consulta de lectura
    // (por que campo se tirara para determinar la consulta concreta)
    @Override
    protected void prepararFiltroConsultaRead(Object filtro) {
        //ToDO
    }

    //Rellenar el array de resultados con cada resultado
    @Override
    protected void rellenarObjetos() throws SQLException {
        //ToDO
    }

    //UPDATE
    //Preparar una consulta de update y cargar sus parametros
    @Override
    protected void prepararUpdate(Object elementoAModelar) throws SQLException {
        //ToDO
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
        return super.update(elementoConQueActualizar);
    }

    @Override
    public Boolean delete(Object elementoABorrar) { //NO SE BORRAN USUARIOS DESDE NUESTRA APP!
        return null;
    }
}
