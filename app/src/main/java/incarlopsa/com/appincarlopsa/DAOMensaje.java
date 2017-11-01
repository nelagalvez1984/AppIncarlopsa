package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOMensaje extends DAOBase implements IDAO {

    //Propiedades
    //Consultas parametrizadas
    private String consultaInsercion = "INSERT INTO mensaje SET idUsuario = ?, mensaje = ?,fecha = NOW(),leidoPorDestino = ?";
    private String consultaLecturaPorId = "SELECT idMensaje, idChat, idUsuario, mensaje, " +
            "TIME_FORMAT(fecha, '%H:%i') AS horacreacion, DATE_FORMAT(fecha, '%d/%m/%y') AS fechaupdate, " +
            "leidoPorDestino FROM mensaje WHERE idChat = ? ORDER BY fecha DESC";
    private String consultaUpdate = "UPDATE mensaje SET idChat = ?, idUsuario = ?, mensaje = ?, leidoPorDestino = ? WHERE idMensaje = ?";
    private String consultaDelete = "DELETE FROM mensaje WHERE idMensaje = ?";

    //Constructor
    public DAOMensaje() { }

    //CREACION
    //Preparar una consulta de create y cargar sus parametros
    @Override
    protected void prepararCreate(Object elementoAModelar) throws SQLException {
        Mensaje aux = (Mensaje) elementoAModelar;
        prepararConsulta(consultaInsercion);
        cargarConsulta(aux.getIdUsuario(),
                aux.getMensaje(),
                false);
    }

    //LECTURA
    //Tipo de filtro a aplicar a la consulta de lectura
    // (por que campo se tirara para determinar la consulta concreta)
    @Override
    protected void prepararRead(Object filtro) throws SQLException {
        Mensaje aux = (Mensaje)filtro;
        consultaSQL = consultaLecturaPorId;
        prepararConsulta(consultaSQL);
        cargarConsulta(aux.getIdPublicacion());
    }

    //Rellenar el array de resultados con cada resultado
    @Override
    protected void rellenarObjetos() throws SQLException {
        Mensaje aux = new Mensaje(resultados.getInt(1), //IdMensaje
                resultados.getInt(2), //IdChat
                resultados.getInt(3), //IdUsuario
                resultados.getString(4), //Mensaje
                resultados.getString(5), //FechaCreacion
                resultados.getString(6), //HoraCreacion
                resultados.getBoolean(7)); //LeidoPorDestino
        resultadoMultiple.add(aux);
    }

    //UPDATE
    //Preparar una consulta de update y cargar sus parametros
    @Override
    protected void prepararUpdate(Object elementoAModelar) throws SQLException {
        Mensaje aux = (Mensaje) elementoAModelar;
        consultaSQL = consultaUpdate;
        prepararConsulta(consultaSQL);
        cargarConsulta(aux.getIdPublicacion(),
                        aux.getIdUsuario(),
                        aux.getMensaje(),
                        aux.getLeidoPorDestino(),
                        aux.getId());
    }

    //DELETE
    @Override
    protected void prepararDelete(Object elementoAModelar) throws SQLException {
        Mensaje aux = (Mensaje)elementoAModelar;
        prepararConsulta(consultaDelete);
        cargarConsulta(aux.getId());
    }

    //CONTROL DE CONSULTAS CRUD:
    @Override
    public Boolean create(Object elementoACrear) throws SQLException {
        //Actualizar la fecha de su chat

        return super.create(elementoACrear);
    }

    @Override
    public ArrayList<DataBaseItem> read(Object filtro) throws SQLException {
        //Si quien lee no es el autor, marcarlos como leidos
        Mensaje auxMensaje = null;
        ArrayList<DataBaseItem> resultados = super.read(filtro);
        SingleCredenciales perfil = SingleCredenciales.getInstance();
        DAOMensaje dao = new DAOMensaje();
        for(DataBaseItem item : resultados){ //Chequear si alguno de ellos es mio
            auxMensaje = (Mensaje)item;
            if ( auxMensaje.getIdUsuario() != perfil.getIdUsuario() ){ //Este mensaje NO es mio
                if ( auxMensaje.getLeidoPorDestino() == false){ //Y NO lo habia leido hasta ahora
                    auxMensaje.setLeidoPorDestino(true);
                    dao.update(auxMensaje); //Lo marco como leido
                }
            }
        }
        //Ya todos han sido marcados como leidos si corresponde
        return resultados;
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
