package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOPublicacion extends DAOBase implements IDAO, ICodigos {

    //Propiedades
    //Consultas parametrizadas
    private String consultaInsercion = "INSERT INTO comentario SET idUsuario = ?, titulo = ?, fecha = NOW(), ultimoUpdate = NOW()";
    private String consultaLecturaPorId = "SELECT idPublicacion, idUsuario, titulo, DATE_FORMAT(fecha, '%d/%m/%y') AS fechacreacion, " +
            "TIME_FORMAT(fecha, '%H:%i') AS horacreacion, DATE_FORMAT(ultimoUpdate, '%d/%m/%y') AS fechaupdate, " +
            "TIME_FORMAT(ultimoUpdate, '%H:%i') AS horaupdate FROM comentario WHERE idPublicacion = ?";
    private String consultaUpdate = "UPDATE comentario SET idUsuario = ?, titulo = ?, ultimoUpdate = NOW() WHERE idPublicacion = ?";
    private String consultaActualizarFecha = "UPDATE comentario SET ultimoUpdate = NOW() WHERE idPublicacion = ?";
    private String consultaDelete = "DELETE FROM comentario WHERE idPublicacion = ?";
    private String consultaTopics = "SELECT idPublicacion, idUsuario, titulo, DATE_FORMAT(fecha, '%d/%m/%y') AS fechacreacion, " +
            "TIME_FORMAT(fecha, '%H:%i') AS horacreacion, DATE_FORMAT(ultimoUpdate, '%d/%m/%y') AS fechaupdate, " +
            "TIME_FORMAT(ultimoUpdate, '%H:%i') AS horaupdate FROM comentario ORDER BY ultimoUpdate DESC";

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
        if (filtro instanceof String){ //Se recogen TODAS las publicaciones(DAME_LOS_TOPIC)
                                       //pero solo las cabeceras
            consultaSQL = consultaTopics;
            prepararConsulta(consultaSQL);
            // No se hace --->   cargarConsulta();   <--- Porque NO hay nada que cargar
        }else{
            Publicacion aux = (Publicacion)filtro;
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
        if (aux.getFechaUltimoUpdate().equals(ACTUALIZA_FECHA)){ //Actualizar fecha!
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
        Publicacion auxPublicacion;
        if (filtro instanceof String){ //Solo se devuelve las cabeceras, (DAME_LOS_TOPIC)
            // sin comentarios ni adjuntos
            resultadoMultiple = super.read(filtro);
        }else{
            auxPublicacion = (Publicacion)filtro;
            ArrayList<DataBaseItem> publicacion = null;
            //1.- Leemos la comentario
            resultadoMultiple = super.read(filtro);
            if (resultadoMultiple.size()>0){
                //2.- Leemos los comentarios asociados
                DAOComentario daoComentario = new DAOComentario();
                Comentario auxComentario = new Comentario();
                auxComentario.setIdPublicacion(auxPublicacion.getId());
                ArrayList<DataBaseItem> comentarios = new ArrayList<>();
                comentarios.addAll(daoComentario.read(auxComentario));

                //3.- Leemos los adjuntos asociados
                DAOAdjunto daoAdjunto = new DAOAdjunto();
                Adjunto auxAdjunto = new Adjunto();
                auxAdjunto.setIdPublicacion(auxPublicacion.getId());
                ArrayList<DataBaseItem> adjuntos = new ArrayList<>();
                adjuntos.addAll(daoAdjunto.read(auxAdjunto));

                //4.- Ensamblaje
                ((Publicacion)publicacion.get(0)).setComentarios(comentarios); //Meterle los comentarios
                ((Publicacion)publicacion.get(0)).setAdjuntos(adjuntos); //Meterle los adjuntos
            }
        }
        //5.- Devolver la comentario o LOS topic
        return resultadoMultiple;
    }

    @Override
    public Boolean update(Object elementoConQueActualizar) {
        return super.update(elementoConQueActualizar);
    }

    @Override
    public Boolean delete(Object elementoABorrar) {
        Publicacion auxPublicacion = (Publicacion)elementoABorrar;
        Integer idPublicacion = auxPublicacion.getId();
        //1.- Borrar primero sus comentarios
        borradoEnCascada(new DAOComentario(), new Comentario(), idPublicacion);
        //2.- Borrar sus adjuntos
        borradoEnCascada(new DAOAdjunto(), new Adjunto(), idPublicacion);
        //3.- Ahora si, borrar la comentario
        return super.delete(elementoABorrar);
    }

    private void borradoEnCascada(DAOBase dao, DataBaseItem item, Integer idPublicacion){

        ArrayList<DataBaseItem> resultadosCascada = new ArrayList<>();

        //Fijar el ID de la comentario
        if (item instanceof Comentario) { // Es un Comentario
            ((Comentario) item).setIdPublicacion(idPublicacion);
        }else { // Es un Adjunto
            ((Adjunto) item).setIdPublicacion(idPublicacion);
        }

        //Leer todos los items con esa idPublicacion
        try {
            resultadosCascada = dao.read(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Borrarlos uno a uno
        for(DataBaseItem a : resultadosCascada){
            dao.delete(a);
        }

    }

}
