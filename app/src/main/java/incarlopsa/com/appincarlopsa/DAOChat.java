package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOChat extends DAOBase implements IDAO, ICodigos {
    //Propiedades
    //Consultas parametrizadas
    private String consultaInsercion = "INSERT INTO chat SET idUsuario = ?, idUsuarioDestino = ?, titulo = ?, fecha = NOW(), " +
            "ultimoUpdate = NOW(), finalizado = ?";
    private String consultaLecturaPorId = "SELECT idChat, idUsuario, idUsuarioDestino, titulo, " +
            "DATE_FORMAT(fecha, '%d/%m/%y') AS fechacreacion, TIME_FORMAT(fecha, '%H:%i') AS horacreacion, " +
            "DATE_FORMAT(ultimoUpdate, '%d/%m/%y') AS fechaupdate, TIME_FORMAT(ultimoUpdate, '%H:%i') AS horaupdate, " +
            "finalizado FROM chat WHERE idChat = ?";
    private String consultaUpdate = "UPDATE chat SET idUsuario = ?, idUsuarioDestino = ?, titulo = ?, ultimoUpdate = NOW(), " +
            "finalizado = ? WHERE idChat = ?";
    private String consultaActualizarFecha = "UPDATE chat SET ultimoUpdate = NOW() WHERE idChat = ?";
    private String consultaDelete = "DELETE FROM chat WHERE idChat = ?";
    private String consultaTopicsPorAutor = "SELECT idChat, idUsuario, idUsuarioDestino, titulo, " +
            "DATE_FORMAT(fecha, '%d/%m/%y') AS fechacreacion, TIME_FORMAT(fecha, '%H:%i') AS horacreacion, " +
            "DATE_FORMAT(ultimoUpdate, '%d/%m/%y') AS fechaupdate, TIME_FORMAT(ultimoUpdate, '%H:%i') AS horaupdate, " +
            "finalizado FROM chat WHERE idUsuario = ? AND finalizado = false";
    private String consultaTopicsPorDestinatario = "SELECT idChat, idUsuario, idUsuarioDestino, titulo, " +
            "DATE_FORMAT(fecha, '%d/%m/%y') AS fechacreacion, TIME_FORMAT(fecha, '%H:%i') AS horacreacion, " +
            "DATE_FORMAT(ultimoUpdate, '%d/%m/%y') AS fechaupdate, TIME_FORMAT(ultimoUpdate, '%H:%i') AS horaupdate, " +
            "finalizado FROM chat WHERE idUsuarioDestino = ? AND finalizado = false";

    //Constructor

    public DAOChat() {
    }

    //CREACION
    //Preparar una consulta de create y cargar sus parametros
    @Override
    protected void prepararCreate(Object elementoAModelar) throws SQLException {
        Chat aux = (Chat) elementoAModelar;
        prepararConsulta(consultaInsercion);
        cargarConsulta(aux.getIdUsuario(),
                aux.getIdUsuarioDestino(),
                aux.getTitulo(),
                false);
    }

    //LECTURA
    //Tipo de filtro a aplicar a la consulta de lectura
    // (por que campo se tirara para determinar la consulta concreta)

    @Override
    protected void prepararRead(Object filtro) throws SQLException{

        SingleCredenciales perfil = SingleCredenciales.getInstance();
        if (filtro instanceof String){ //DAME LOS TOPIC

            if (filtro.equals(DAME_LOS_TOPIC_DESDE_MI)){ //Soy autor
                consultaSQL = consultaTopicsPorAutor;
            }else { //Soy destinatario  (DAME_LOS_TOPIC_HACIA_MI)
                consultaSQL = consultaTopicsPorDestinatario;
            }
            prepararConsulta(consultaSQL);

            cargarConsulta(perfil.getIdUsuario());

        }else{ //Es una consulta por Id
            Chat aux = (Chat) filtro;
            consultaSQL = consultaLecturaPorId;
            prepararConsulta(consultaSQL);
            cargarConsulta(aux.getId());
        }
    }

    //Rellenar el array de resultados con cada resultado
    @Override
    protected void rellenarObjetos() throws SQLException {
        Chat aux = new Chat(resultados.getInt(1), //IdChat
                resultados.getInt(2), //IdUsuario
                resultados.getInt(3), //IdUsuarioDestino
                resultados.getString(4), //Titulo
                resultados.getString(5), //FechaCreacion
                resultados.getString(6), //HoraCreacion
                resultados.getString(7), //FechaUpdate
                resultados.getString(8), //HoraUpdate
                resultados.getBoolean(9)); //Finalizado
        resultadoMultiple.add(aux);
    }

    //UPDATE
    @Override
    protected void prepararUpdate(Object elementoAModelar) throws SQLException {
        Chat aux = (Chat) elementoAModelar;
        if (aux.getFechaUltimoUpdate().equals(ACTUALIZA_FECHA)){ //Actualizar fecha!
            consultaSQL = consultaActualizarFecha;
            prepararConsulta(consultaSQL);
            cargarConsulta(aux.getId());
        }else{ //Actualizar normal
            consultaSQL = consultaUpdate;
            prepararConsulta(consultaSQL);
            cargarConsulta(aux.getIdUsuario(),
                aux.getIdUsuarioDestino(),
                aux.getTitulo(),
                aux.getFinalizado(),
                aux.getId());
        }
    }

    //DELETE
    @Override
    protected void prepararDelete(Object elementoAModelar) throws SQLException {
        Chat aux = (Chat)elementoAModelar;
        prepararConsulta(consultaDelete);
        cargarConsulta(aux.getId());
    }

    //CONTROL DE CONSULTAS CRUD:
    @Override
    public Boolean create(Object elementoACrear) throws SQLException {
        return super.create(elementoACrear);
    }

    @Override
    public ArrayList<DataBaseItem> read(Object filtro) throws SQLException {
        //¿Recoger topics o recoger tod.o el mensaje?
        if (filtro instanceof String){ //Solo se devuelve las cabeceras, (DAME_LOS_TOPIC)
            // sin comentarios ni adjuntos
            resultadoMultiple = super.read(filtro);
        }else {
            //Primero leer el mensaje
            resultadoMultiple = super.read(filtro);
            //Despues recoger sus mensajes
            DAOMensaje dao = new DAOMensaje();
            Mensaje auxMensaje = new Mensaje();
            auxMensaje.setIdPublicacion(resultadoMultiple.get(0).getId());
            ArrayList<DataBaseItem> mensajesRecogidos = dao.read(auxMensaje);
            ((Chat) resultadoMultiple.get(0)).setMensajes(mensajesRecogidos);
            //Devolver el mensaje completo
        }
        return resultadoMultiple;
    }

    @Override
    public Boolean update(Object elementoConQueActualizar) {
        return super.update(elementoConQueActualizar);
    }

    @Override
    public Boolean delete(Object elementoABorrar) {
        Chat auxChat = (Chat)elementoABorrar;
        //Primero borrar sus mensajes
        DAOMensaje dao = new DAOMensaje();
        Mensaje auxMensaje = new Mensaje();
        auxMensaje.setIdPublicacion(auxChat.getId());
        dao.delete(auxMensaje);
        //Ahora borrar el mensaje
        return super.delete(elementoABorrar);
    }
}
