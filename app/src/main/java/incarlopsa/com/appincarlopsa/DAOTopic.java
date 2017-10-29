package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTopic<T> extends DAOBase implements IDAO {

    //ToDO

    private T aux;
    private IDAO dao;
    private String nombreTabla;
    private String nombreIdTabla;

    public DAOTopic(){

        if (aux instanceof Publicacion){ // Es un DAOTopic de publicacion
            //La consulta se prepara usando la tabla Publicacion
            nombreTabla = "publicacion";
            nombreIdTabla = "idpublicacion";
        }else{ // Es un DAOTopic de chat
            //La consulta se prepara usando la tabla Chat
            nombreTabla = "chat";
            nombreIdTabla = "idchat";
        }

        //ToDO

    }

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
    public ArrayList<DataBaseItem> read(Object filtro) throws SQLException{
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
