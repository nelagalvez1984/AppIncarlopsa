package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOAdjunto extends DAOBase implements IDAO {

    //Propiedades
    private String nombreTabla = "adjunto";
    private String nombreIdTabla = "idadjunto";

    //Consultas parametrizadas
    private String consultaInsercion = "INSERT INTO adjunto SET idTipoFichero = ?, localizacion = ? "
            + " , nombre = ?";
    private String consultaLecturaPorId = "SELECT idAdjunto,idTipoFichero, localizacion, nombre"
            + "FROM adjunto WHERE idAdjunto = ?";
    private String consultaUpdate = "UPDATE adjunto SET idTipoFichero = ?, localizacion = ?, nombre = ? "
            + "WHERE idTipoFichero = ?";
    private String consultaLeerTodo = "SELECT idAdjunto,idTipoFichero, localizacion, nombre" + "FROM adjunto";

    //CREACION
    //Preparar una consulta de create y cargar sus parametros
    @Override
    protected void prepararCreate(Object elementoAModelar) throws SQLException {
        Adjunto aux = (Adjunto) elementoAModelar;
        prepararConsulta(consultaInsercion);
        cargarConsulta(aux.getIdTipoFichero(), aux.getLocalizacion(), aux.getNombreAdjunto(), aux.getId());
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
