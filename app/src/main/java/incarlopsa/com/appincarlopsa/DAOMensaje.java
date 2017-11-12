package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOMensaje extends DAOBase implements IDAO {

    //Consultas parametrizadas
    private String consultaInsercion = "INSERT INTO mensaje SET idChat = ?, idUsuario = ?, mensaje = ?,fecha = NOW(), leidoPorDestino = ?";
    private String consultaLecturaPorId = "SELECT idMensaje, idChat, idUsuario, mensaje, " +
            "DATE_FORMAT(fecha, '%d/%m/%y') AS fechaupdate, TIME_FORMAT(fecha, '%H:%i') AS horacreacion, " +
            "leidoPorDestino FROM mensaje WHERE idChat = ? ORDER BY fecha ASC";
    private String consultaUpdate = "UPDATE mensaje SET idChat = ?, idUsuario = ?, mensaje = ?, leidoPorDestino = ? WHERE idMensaje = ?";
    private String consultaDeleteChat = "DELETE FROM mensaje WHERE idChat = ?";
    private String consultaDeleteMensaje = "DELETE FROM mensaje WHERE idMensaje = ?";

    //CREACION
    //Preparar una consulta de create y cargar sus parametros
    @Override
    protected void prepararCreate(Object elementoAModelar) throws SQLException {
        Mensaje aux = (Mensaje) elementoAModelar;
        consultaSQL = consultaInsercion;
        prepararConsulta(consultaSQL);
        cargarConsulta(aux.getIdPublicacion(),
                aux.getIdUsuario(),
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
        if (aux.getId() != null){ //Borrado de un unico mensaje
            prepararConsulta(consultaDeleteMensaje);
            cargarConsulta(aux.getId());
        }else{ //Borrado del mensaje entero
            prepararConsulta(consultaDeleteChat);
            cargarConsulta(aux.getIdPublicacion());

        }
    }

    //CONTROL DE CONSULTAS CRUD:
    @Override
    public Boolean create(Object elementoACrear) throws SQLException {

        //Crear el mensaje
        Boolean retorno = super.create(elementoACrear);
        actualizarFechaChatPadre(elementoACrear);

        //Ahora crear el mensaje
        return retorno;
    }

    private void actualizarFechaChatPadre(Object elemento) throws SQLException{
        //Actualizar la fecha de su mensaje
        Mensaje auxMensaje = (Mensaje)elemento;
        Chat chatAux = new Chat();
        chatAux.setId(auxMensaje.getIdPublicacion());
        chatAux.setFechaUltimoUpdate(ACTUALIZA_FECHA);
        DAOChat dao = new DAOChat();
        Boolean todoOk = dao.update(chatAux);
        Boolean borrar;
    }

    @Override
    public ArrayList<DataBaseItem> read(Object filtro) throws SQLException {
        //Si quien lee no es el autor, marcarlos como leidos
        Mensaje auxMensaje;
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
    public Boolean update(Object elementoConQueActualizar) throws SQLException{

        //Primero actualizar el item
        Boolean retorno = super.update(elementoConQueActualizar);

        //Despues modificar la fecha de update del mensaje padre
        actualizarFechaChatPadre(elementoConQueActualizar);

        return retorno;
    }

    @Override
    public Boolean delete(Object elementoABorrar) throws SQLException{
        return super.delete(elementoABorrar);
    }
}
