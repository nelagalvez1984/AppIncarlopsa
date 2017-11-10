package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOAdjunto extends DAOBase implements IDAO {

    //Propiedades

    //Consultas parametrizadas
    private String consultaInsercion = "INSERT INTO adjunto SET idPublicacion = ?, binario = ? "
            + " , nombre = ?";
    private String consultaLecturaPorId = "SELECT idAdjunto, idPublicacion, binario, nombre "
            + "FROM adjunto WHERE idPublicacion = ?";
    private String consultaUpdate = "UPDATE adjunto SET idPublicacion = ?, binario = ?, nombre = ? "
            + "WHERE idAdjunto = ?";
    private String consultaDeleteAdjunto = "DELETE FROM adjunto WHERE idAdjunto = ?";
    private String consultaDeletePublicacion = "DELETE FROM adjunto WHERE idPublicacion = ?";

    //CREACION
    //Preparar una consulta de create y cargar sus parametros
    @Override
    protected void prepararCreate(Object elementoAModelar) throws SQLException {
        Adjunto aux = (Adjunto) elementoAModelar;
        prepararConsulta(consultaInsercion);
        cargarConsulta(aux.getIdPublicacion(), aux.getFoto().getFotoBytes(), aux.getNombreAdjunto());
    }

    //LECTURA
    //Tipo de filtro a aplicar a la consulta de lectura
    // (por que campo se tirara para determinar la consulta concreta)
    @Override
    protected void prepararRead(Object filtro) throws SQLException {
        Adjunto aux = (Adjunto)filtro;
        consultaSQL = consultaLecturaPorId;
        prepararConsulta(consultaSQL);
        cargarConsulta(aux.getIdPublicacion());
    }

    //Rellenar el array de resultados con cada resultado
    @Override
    protected void rellenarObjetos() throws SQLException {
        Adjunto adjunto = new Adjunto(resultados.getInt(1),//idAdjunto
                resultados.getInt(2), //idPublicacion
                new Foto(resultados.getBytes(3)), // binario
                resultados.getString(4)); //Nombre del fichero
        resultadoMultiple.add(adjunto);
    }

    //UPDATE
    //Preparar una consulta de update y cargar sus parametros
    @Override
    protected void prepararUpdate(Object elementoAModelar) throws SQLException {
        Adjunto elementoConQueActualizar = (Adjunto)elementoAModelar;
        prepararConsulta(consultaUpdate);
        cargarConsulta(elementoConQueActualizar.getIdPublicacion(),
                        elementoConQueActualizar.getFoto().getFotoBytes(),
                        elementoConQueActualizar.getNombreAdjunto(),
                        elementoConQueActualizar.getId());
    }

    //DELETE
    @Override
    protected void prepararDelete(Object elementoAModelar) throws SQLException {
        Adjunto elementoABorrar = (Adjunto)elementoAModelar;
        if (elementoABorrar.getIdPublicacion() != null){ //Borrar toda la comentario
            consultaSQL = consultaDeletePublicacion;
            prepararConsulta(consultaSQL);
            cargarConsulta(elementoABorrar.getIdPublicacion());
        }else{ //Borrar solo ese adjunto
            consultaSQL = consultaDeleteAdjunto;
            prepararConsulta(consultaSQL);
            cargarConsulta(elementoABorrar.getId());
        }
    }

    //CONTROL DE CONSULTAS CRUD:
    @Override
    public Boolean create(Object elementoACrear) throws SQLException {
        return super.create(elementoACrear);
    }

    @Override
    public ArrayList<DataBaseItem> read(Object filtro) throws SQLException {
        return resultadoMultiple = super.read(filtro);
    }

    @Override
    public Boolean update(Object elementoConQueActualizar) throws SQLException{
        return super.update(elementoConQueActualizar);
    }

    @Override
    public Boolean delete(Object elementoABorrar) throws SQLException{
        return super.delete(elementoABorrar);
    }
}
