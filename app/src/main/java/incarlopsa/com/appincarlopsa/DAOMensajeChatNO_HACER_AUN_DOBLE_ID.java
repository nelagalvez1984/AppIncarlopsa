package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOMensajeChatNO_HACER_AUN_DOBLE_ID extends DAOBase implements IDAOMultipleID<Mensaje> {

    //Propiedades
    private String nombreTabla = "mensaje";
    private String nombreIdTabla1 = "idchat";
    private String nombreIdTabla2 = "idmensaje";

    //Consultas parametrizadas
    private String consultaInsercion; //ToDO
    private String consultaLecturaPorId; //ToDO
    private String consultaUpdate; //ToDO

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
    protected void prepararUpdate(Object elementoAModelar, Integer idUsuarioOrigen) throws SQLException {
        //ToDO
    }

    @Override
    public ArrayList<DataBaseItem> read(Integer... ids) throws SQLException {
        return null;
    }

    @Override
    public Boolean update(Object elementoConQueActualizar, Integer... ids) {
        return null;
    }
}
