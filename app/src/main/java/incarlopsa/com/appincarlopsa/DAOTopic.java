package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;

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
    protected void prepararUpdate(Object elementoAModelar, Integer idUsuarioOrigen) throws SQLException {
        //ToDO
    }

}
