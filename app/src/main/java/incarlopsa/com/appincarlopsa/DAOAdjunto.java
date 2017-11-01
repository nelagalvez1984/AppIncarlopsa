package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOAdjunto extends DAOBase implements IDAO {

    //Propiedades
    TipoFichero tipoFicheroRecogido = null;

    //Consultas parametrizadas
    private String consultaInsercion = "INSERT INTO adjunto SET idTipoFichero = ?, localizacion = ? "
            + " , nombre = ?";
    private String consultaLecturaPorId = "SELECT idAdjunto,idTipoFichero, localizacion, nombre "
            + "FROM adjunto WHERE idAdjunto = ?";
    private String consultaUpdate = "UPDATE adjunto SET idTipoFichero = ?, localizacion = ?, nombre = ? "
            + "WHERE idAdjunto = ?";
    private String consultaDelete = "DELETE FROM adjunto WHERE idAdjunto = ?";

    //CREACION
    //Preparar una consulta de create y cargar sus parametros
    @Override
    protected void prepararCreate(Object elementoAModelar) throws SQLException {
        Adjunto aux = (Adjunto) elementoAModelar;
        prepararConsulta(consultaInsercion);
        cargarConsulta(aux.getIdTipoFichero(), aux.getLocalizacion(), aux.getNombreAdjunto());
    }

    //LECTURA
    //Tipo de filtro a aplicar a la consulta de lectura
    // (por que campo se tirara para determinar la consulta concreta)
    @Override
    protected void prepararRead(Object filtro) throws SQLException {
        Adjunto aux = (Adjunto)filtro;
        consultaSQL = consultaLecturaPorId;
        prepararConsulta(consultaSQL);
        cargarConsulta(aux.getId());
    }

    //Rellenar el array de resultados con cada resultado
    @Override
    protected void rellenarObjetos() throws SQLException {
        Adjunto adjunto = new Adjunto(resultados.getInt(1),//idAdjunto
                resultados.getInt(2), // idTipoFichero
                resultados.getString(3), //localizacion
                resultados.getString(4), //nombre
                tipoFicheroRecogido); //El tipo de fichero al que corresponde
        resultadoMultiple.add(adjunto);
    }

    //UPDATE
    //Preparar una consulta de update y cargar sus parametros
    @Override
    protected void prepararUpdate(Object elementoAModelar) throws SQLException {
        Adjunto elementoConQueActualizar = (Adjunto)elementoAModelar;
        prepararConsulta(consultaUpdate);
        cargarConsulta(elementoConQueActualizar.getIdTipoFichero(),
                        elementoConQueActualizar.getLocalizacion(),
                        elementoConQueActualizar.getNombreAdjunto(),
                        elementoConQueActualizar.getId());
    }

    //DELETE
    @Override
    protected void prepararDelete(Object elementoAModelar) throws SQLException {
        Adjunto elementoConQueActualizar = (Adjunto)elementoAModelar;
        prepararConsulta(consultaDelete);
        cargarConsulta(elementoConQueActualizar.getId());
    }

    //CONTROL DE CONSULTAS CRUD:
    @Override
    public Boolean create(Object elementoACrear) throws SQLException {
        return super.create(elementoACrear);
    }

    @Override
    public ArrayList<DataBaseItem> read(Object filtro) throws SQLException {
        DAOTipoFichero dao = new DAOTipoFichero();
        Adjunto aux = (Adjunto)filtro;
        ArrayList<DataBaseItem> resultadosTipoFichero = dao.read(aux.getIdTipoFichero());
        tipoFicheroRecogido = (TipoFichero)resultadosTipoFichero.get(0); //Solo queremos el primero
                                                                        // porque solo habra uno!
        return super.read(filtro);
    }

    @Override
    public Boolean update(Object elementoConQueActualizar) {
        return super.update(elementoConQueActualizar);
    }

    @Override
    public Boolean delete(Object elementoABorrar) {
        return super.delete(elementoABorrar);
    }
}
