package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTipoFichero extends DAOBase implements IDAO {

    //Esta clase tal vez no se acabe usando. En tal caso modificar la clase "ADJUNTO"

    //Propiedades
    private String nombreTabla = "tipofichero";
    private String nombreIdTabla = "idtipofichero";

    //Consultas parametrizadas
    private String consultaInsercion = "INSERT INTO tipofichero SET nombre = ? "
            + " , mostrable = ?";
    private String consultaLecturaPorId = "SELECT idTipoFichero, nombre, mostrable"
            + "FROM tipofichero WHERE idTipoFichero = ?";
    private String consultaUpdate = "UPDATE tipofichero SET nombre = ?, mostrable = ? "
            + "WHERE idTipoFichero = ?";
    private String consultaLeerTodo = "SELECT idTipoFichero, nombre, mostrable" + "FROM tipofichero";

    //CREACION
    //Preparar una consulta de create y cargar sus parametros
    @Override
    protected void prepararCreate(Object elementoAModelar) throws SQLException {
        TipoFichero aux = (TipoFichero) elementoAModelar;
        prepararConsulta(consultaInsercion);
        cargarConsulta(aux.getNombre(), aux.getMostrable(), aux.getId()
        );
    }

    //LECTURA
    //Tipo de filtro a aplicar a la consulta de lectura
    // (por que campo se tirara para determinar la consulta concreta)
    @Override
    protected void prepararFiltroConsultaRead(Object filtro) {
        if (filtro instanceof String) {
            if (filtro.equals(DAME_TODOS)) {
                consultaSQL = consultaLeerTodo;
            }
        } else {
            if (filtro instanceof TipoFichero) {
                consultaSQL = consultaLecturaPorId;
            }
        }
    }

    //Rellenar el array de resultados con cada resultado
    @Override
    protected void rellenarObjetos() throws SQLException {
        TipoFichero tipoFichero = new TipoFichero(resultados.getInt(1),//idTipoFichero
                resultados.getString(2), //nombre
                resultados.getBoolean(3)); //mostrable
        resultadoMultiple.add(tipoFichero);
    }

    //UPDATE
    //Preparar una consulta de update y cargar sus parametros
    @Override
    protected void prepararUpdate(Object elementoAModelar) throws SQLException {
        TipoFichero elementoConQueActualizar = (TipoFichero) elementoAModelar;
        Integer idTipoFichero = elementoConQueActualizar.getId();
        prepararConsulta(consultaUpdate);
        cargarConsulta(elementoConQueActualizar.getNombre(),
                        elementoConQueActualizar.getMostrable(),
                        idTipoFichero);
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
