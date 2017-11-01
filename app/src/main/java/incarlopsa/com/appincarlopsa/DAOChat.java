package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOChat extends DAOBase implements IDAO, ICodigos {
    //Propiedades
    //Consultas parametrizadas
    String consultaInsercion = "INSERT INTO chat SET idUsuario = ?, idUsuarioDestino = ?, titulo = ?, fecha = NOW(), " +
            "ultimoUpdate = NOW(), finalizado = ?";
    String consultaLecturaPorId = "SELECT idChat, idUsuario, idUsuarioDestino, titulo, " +
            "TIME_FORMAT(fecha, '%d/%m/%y'') AS fechacreacion, TIME_FORMAT(fecha, '%H:%i') AS horacreacion, " +
            "TIME_FORMAT(ultimoUpdate, '%d/%m/%y'') AS fechaupdate, TIME_FORMAT(ultimoUpdate, '%H:%i') AS horaupdate, " +
            "finalizado WHERE idChat = ?";
    String consultaUpdate = "UPDATE chat SET idUsuario = ?, idUsuarioDestino = ?, titulo = ?, ultimoUpdate = NOW(), " +
            "finalizado = ? WHERE idChat = ?";
    String consultaDelete = "DELETE FROM chat WHERE idChat = ?";
    String consultaTopicsPorAutor = "SELECT idChat, idUsuario, idUsuarioDestino, titulo, " +
            "TIME_FORMAT(fecha, '%d/%m/%y'') AS fechacreacion, TIME_FORMAT(fecha, '%H:%i') AS horacreacion, " +
            "TIME_FORMAT(ultimoUpdate, '%d/%m/%y'') AS fechaupdate, TIME_FORMAT(ultimoUpdate, '%H:%i') AS horaupdate, " +
            "finalizado WHERE idUsuario = ?";
    String consultaTopicsPorDestinatario = "SELECT idChat, idUsuario, idUsuarioDestino, titulo, " +
            "TIME_FORMAT(fecha, '%d/%m/%y'') AS fechacreacion, TIME_FORMAT(fecha, '%H:%i') AS horacreacion, " +
            "TIME_FORMAT(ultimoUpdate, '%d/%m/%y'') AS fechaupdate, TIME_FORMAT(ultimoUpdate, '%H:%i') AS horaupdate, " +
            "finalizado WHERE idUsuarioDestino = ?";

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
        Chat aux = (Chat) filtro;
        SingleCredenciales perfil = SingleCredenciales.getInstance();
        if (aux.getTitulo().equals(DAME_LOS_TOPIC)){
            if (aux.getIdUsuario() == perfil.getIdUsuario()){ //Soy autor
                consultaSQL = consultaTopicsPorAutor;
                prepararConsulta(consultaSQL);
                cargarConsulta(aux.getIdUsuario());
            }else{ //Soy destinatario
                consultaSQL = consultaTopicsPorDestinatario;
                prepararConsulta(consultaSQL);
                cargarConsulta(aux.getIdUsuarioDestino()); // deberia ser igual a perfil.getIdUsuario()
            }
        }else{ //Es una consulta por Id
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
        consultaSQL = consultaUpdate;
        prepararConsulta(consultaSQL);
        cargarConsulta(aux.getIdUsuario(),
                aux.getIdUsuarioDestino(),
                aux.getTitulo(),
                aux.getFinalizado(),
                aux.getId());
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
        //Primero leer el chat
        ArrayList<DataBaseItem> chatRecogido = super.read(filtro);
        //Despues recoger sus mensajes
        DAOMensaje dao = new DAOMensaje();
        Mensaje auxMensaje = new Mensaje();
        auxMensaje.setIdPublicacion(chatRecogido.get(0).getId());
        ArrayList<DataBaseItem> mensajesRecogidos = dao.read(auxMensaje);
        ((Chat)chatRecogido.get(0)).setMensajes(mensajesRecogidos);
        //Devolver el chat completo
        return chatRecogido;
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
        //Ahora borrar el chat
        return super.delete(elementoABorrar);
    }
}
