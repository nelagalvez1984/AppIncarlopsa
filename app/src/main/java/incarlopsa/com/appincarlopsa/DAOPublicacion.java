package incarlopsa.com.appincarlopsa;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOPublicacion extends DAOBase implements IDAO, ICodigos {

    //Propiedades
    //Consultas parametrizadas
    private String consultaInsercion = "INSERT INTO publicacion SET idUsuario = ?, titulo = ?, fecha = NOW(), ultimoUpdate = NOW()";
    private String consultaLecturaPorId = "SELECT idPublicacion, idUsuario, titulo, DATE_FORMAT(fecha, '%d/%m/%y') AS fechacreacion, " +
            "TIME_FORMAT(fecha, '%H:%i') AS horacreacion, TIME_FORMAT(ultimoUpdate, '%d/%m/%y'') AS fechaupdate " +
            "TIME_FORMAT(ultimoUpdate, '%H:%i') AS horaupdate WHERE idPublicacion = ?";
    private String consultaUpdate = "UPDATE publicacion SET idUsuario = ?, titulo = ?, ultimoUpdate = NOW() WHERE idPublicacion = ?";
    private String consultaActualizarFecha = "UPDATE publicacion SET ultimoUpdate = NOW() WHERE idPublicacion = ?";
    private String consultaDelete = "DELETE FROM publicacion WHERE idPublicacion = ?";
    private String consultaTopics = "SELECT idPublicacion, idUsuario, titulo, DATE_FORMAT(fecha, '%d/%m/%y') AS fechacreacion, " +
            "TIME_FORMAT(fecha, '%H:%i') AS horacreacion, TIME_FORMAT(ultimoUpdate, '%d/%m/%y'') AS fechaupdate " +
            "TIME_FORMAT(ultimoUpdate, '%H:%i') AS horaupdate ORDER BY ultimoUpdate DESC";

    //Constructor
    public DAOPublicacion() {}

    //CREACION
    //Preparar una consulta de create y cargar sus parametros
    @Override
    protected void prepararCreate(Object elementoAModelar) throws SQLException {
        Publicacion aux = (Publicacion) elementoAModelar;
        prepararConsulta(consultaInsercion);
        cargarConsulta(aux.getIdUsuario(),
                aux.getTitulo());
    }

    //LECTURA
    //Tipo de filtro a aplicar a la consulta de lectura
    // (por que campo se tirara para determinar la consulta concreta)
    @Override
    protected void prepararRead(Object filtro) throws SQLException {
        Publicacion aux = (Publicacion)filtro;
        if (aux.getTitulo().equals(DAME_LOS_TOPIC)){ //Se recogen TODAS las publicaciones
            //pero solo las cabeceras
            consultaSQL = consultaTopics;
            prepararConsulta(consultaSQL);
            cargarConsulta();
        }else{
            consultaSQL = consultaLecturaPorId;
            prepararConsulta(consultaSQL);
            cargarConsulta(aux.getId());
        }
    }

    //Rellenar el array de resultados con cada resultado
    @Override
    protected void rellenarObjetos() throws SQLException {
        Publicacion aux = new Publicacion(resultados.getInt(1), //IdPublicacion
                resultados.getInt(2), //IdUsuario
                resultados.getString(3), //Titulo
                resultados.getString(4), //FechaCreacion
                resultados.getString(5), //HoraCreacion
                resultados.getString(6), //FechaUpdate
                resultados.getString(7)); //HoraUpdate
        resultadoMultiple.add(aux);
    }

    //UPDATE
    //Preparar una consulta de update y cargar sus parametros
    @Override
    protected void prepararUpdate(Object elementoAModelar) throws SQLException {
        Publicacion aux = (Publicacion) elementoAModelar;
        if (aux.getTitulo().equals(ACTUALIZA_FECHA)){ //Actualizar fecha!
            consultaSQL = consultaActualizarFecha;
            prepararConsulta(consultaSQL);
            cargarConsulta(aux.getId());
        }else{ //Actualizar normal
            consultaSQL = consultaUpdate;
            prepararConsulta(consultaSQL);
            cargarConsulta(aux.getIdUsuario(),
                    aux.getTitulo(),
                    aux.getId());
        }
    }

    @Override
    protected void prepararDelete(Object elementoAModelar) throws SQLException {
        Publicacion aux = (Publicacion) elementoAModelar;
        prepararConsulta(consultaDelete);
        cargarConsulta(aux.getId());
    }

    //CONTROL DE CONSULTAS CRUD:
    @Override
    public Boolean create(Object elementoACrear) throws SQLException {
        return super.create(elementoACrear);
    }

    @Override
    public ArrayList<DataBaseItem> read(Object filtro) throws SQLException{
        Publicacion auxPublicacion = (Publicacion)filtro;
        ArrayList<DataBaseItem> publicacion = null;
        if (auxPublicacion.getTitulo().equals(DAME_LOS_TOPIC)){ //Solo se devuelve las cabeceras,
                                                                // sin comentarios ni adjuntos
            publicacion = super.read(filtro);
        }else{
            //1.- Leemos la publicacion
            publicacion = super.read(filtro);
            //2.- Leemos los comentarios asociados
            DAOComentario daoComentario = new DAOComentario();
            Comentario auxComentario = new Comentario();
            auxComentario.setIdPublicacion(auxPublicacion.getId());
            ArrayList<DataBaseItem> comentarios = daoComentario.read(auxComentario);
            //3.- Leemos los adjuntos asociados
            DAOPublicacionAdjunto daoPublicacionAdjunto = new DAOPublicacionAdjunto();
            PublicacionAdjunto auxPublicacionAdjunto = new PublicacionAdjunto();
            auxPublicacionAdjunto.setIdPublicacion(auxPublicacion.getId());
            ArrayList<DataBaseItem> adjuntos = daoPublicacionAdjunto.read(auxPublicacionAdjunto);
            //4.- Ensamblaje
            ((Publicacion)publicacion.get(0)).setComentarios(comentarios); //Meterle los comentarios
            ((Publicacion)publicacion.get(0)).setAdjuntos(adjuntos); //Meterle los adjuntos
        }
        //5.- Devolver la publicacion o LOS topic
        return publicacion;
    }

    @Override
    public Boolean update(Object elementoConQueActualizar) {
        return super.update(elementoConQueActualizar);
    }

    @Override
    public Boolean delete(Object elementoABorrar) {
        Publicacion auxPublicacion = (Publicacion)elementoABorrar;
        //1.- Borrar primero sus comentarios
        DAOComentario daoComentario = new DAOComentario();
        Comentario auxComentario = new Comentario();
        auxComentario.setIdPublicacion(auxPublicacion.getId());
        daoComentario.delete(auxComentario);
        //2.- Despues borrar sus adjuntos
        DAOPublicacionAdjunto daoPublicacionAdjunto = new DAOPublicacionAdjunto();
        PublicacionAdjunto auxPublicacionAdjunto = new PublicacionAdjunto();
        auxPublicacionAdjunto.setIdPublicacion(auxPublicacion.getId());
        daoPublicacionAdjunto.delete(auxPublicacionAdjunto);
        //3.- Ahora si, borrar la publicacion
        return super.delete(elementoABorrar);
    }
}
