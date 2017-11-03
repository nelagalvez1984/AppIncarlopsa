package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOAdjunto extends DAOBase implements IDAO {

    //Propiedades
    TipoFichero tipoFicheroRecogido = null;

    //Consultas parametrizadas
    private String consultaInsercion = "INSERT INTO adjunto SET idPublicacion = ?, idTipoFichero = ?, localizacion = ? "
            + " , nombre = ?";
    private String consultaLecturaPorId = "SELECT idAdjunto, idPublicacion, idTipoFichero, localizacion, nombre "
            + "FROM adjunto WHERE idPublicacion = ?";
    private String consultaUpdate = "UPDATE adjunto SET idPublicacion = ?, idTipoFichero = ?, localizacion = ?, nombre = ? "
            + "WHERE idAdjunto = ?";
    private String consultaDeleteAdjunto = "DELETE FROM adjunto WHERE idAdjunto = ?";
    private String consultaDeletePublicacion = "DELETE FROM adjunto WHERE idPublicacion = ?";

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
        cargarConsulta(aux.getIdPublicacion());
    }

    //Rellenar el array de resultados con cada resultado
    @Override
    protected void rellenarObjetos() throws SQLException {
        Adjunto adjunto = new Adjunto(resultados.getInt(1),//idAdjunto
                resultados.getInt(2), //idPublicacion
                resultados.getInt(3), // idTipoFichero
                resultados.getString(4), //localizacion
                resultados.getString(5), //nombre
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
        Adjunto elementoABorrar = (Adjunto)elementoAModelar;
        if (elementoABorrar.getIdPublicacion() != null){ //Borrar toda la publicacion
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
        DAOTipoFichero dao = new DAOTipoFichero();
        Adjunto aux = (Adjunto)filtro;
        resultadoMultiple = super.read(filtro); //Buscar todos los adjuntos
        //Ahora se busca su tipo asociado
        ArrayList<DataBaseItem> resultadosTipoFichero;
        for(DataBaseItem item:resultadoMultiple){ //Bucle para asignarle el tipo a cada uno
            Adjunto adjuntoAux = (Adjunto)item;
            TipoFichero tipoAux = new TipoFichero();
            tipoAux.setId(adjuntoAux.getIdTipoFichero()); //Meter el ID del tipo al tipoAux
            resultadosTipoFichero = dao.read(tipoAux); //Solo deberia haber un resultado
            adjuntoAux.setTipo((TipoFichero)resultadosTipoFichero.get(0)); //Asignarle el tipo
        }
        return resultadoMultiple;
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
