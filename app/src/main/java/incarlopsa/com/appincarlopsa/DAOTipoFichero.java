package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;

public class DAOTipoFichero extends DAOBase implements IDAO<TipoFichero> {

    //Esta clase tal vez no se acabe usando. En tal caso modificar la clase "ADJUNTO"

    //Propiedades
    private String nombreTabla = "tipofichero";
    private String nombreIdTabla = "idtipofichero";

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

}
