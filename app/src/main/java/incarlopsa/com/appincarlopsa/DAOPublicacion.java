package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOPublicacion extends DAOBase implements IDAO, ICodigos {

    //Consultas parametrizadas
    private String consultaInsercion = "INSERT INTO publicacion SET idUsuario = ?, titulo = ?, fecha = NOW(), ultimoUpdate = NOW()";
    private String consultaLecturaPorId = "SELECT idPublicacion, idUsuario, titulo, DATE_FORMAT(fecha, '%d/%m/%y') AS fechacreacion, " +
            "TIME_FORMAT(fecha, '%H:%i') AS horacreacion, DATE_FORMAT(ultimoUpdate, '%d/%m/%y') AS fechaupdate, " +
            "TIME_FORMAT(ultimoUpdate, '%H:%i') AS horaupdate FROM publicacion WHERE idPublicacion = ?";
    private String consultaLecturaParaRecuperarId = "SELECT idPublicacion, idUsuario, titulo, DATE_FORMAT(fecha, '%d/%m/%y') AS fechacreacion, " +
            "TIME_FORMAT(fecha, '%H:%i') AS horacreacion, DATE_FORMAT(ultimoUpdate, '%d/%m/%y') AS fechaupdate, " +
            "TIME_FORMAT(ultimoUpdate, '%H:%i') AS horaupdate FROM publicacion WHERE idUsuario = ? ORDER BY fecha DESC";
    private String consultaUpdate = "UPDATE publicacion SET idUsuario = ?, titulo = ?, ultimoUpdate = NOW() WHERE idPublicacion = ?";
    private String consultaActualizarFecha = "UPDATE publicacion SET ultimoUpdate = NOW() WHERE idPublicacion = ?";
    private String consultaDelete = "DELETE FROM publicacion WHERE idPublicacion = ?";
    private String consultaTopics = "SELECT idPublicacion, idUsuario, titulo, DATE_FORMAT(fecha, '%d/%m/%y') AS fechacreacion, " +
            "TIME_FORMAT(fecha, '%H:%i') AS horacreacion, DATE_FORMAT(ultimoUpdate, '%d/%m/%y') AS fechaupdate, " +
            "TIME_FORMAT(ultimoUpdate, '%H:%i') AS horaupdate FROM publicacion ORDER BY ultimoUpdate DESC";

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
            if (aux.getId() == null){ //Recuperar un chat por el resto de campos
                consultaSQL = consultaLecturaParaRecuperarId;
                prepararConsulta(consultaSQL);
                cargarConsulta(aux.getIdUsuario());
            }else{ // Es una consulta por Id
                consultaSQL = consultaLecturaPorId;
                prepararConsulta(consultaSQL);
                cargarConsulta(aux.getId());
            }
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
            //1.- Leemos la comentario
            resultadoMultiple = super.read(filtro);
            if (resultadoMultiple.size()>0){
                if (auxPublicacion.getId() != null){ //Si es null es que estamos recuperando una publicacion
                                                    //de recien creacion, por lo que la estamos recuperando para saber
                                                    //su ID, ergo no tendria comentarios
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
                    ((Publicacion)resultadoMultiple.get(0)).setComentarios(comentarios); //Meterle los comentarios
                    ((Publicacion)resultadoMultiple.get(0)).setAdjuntos(adjuntos); //Meterle los adjuntos
                }

            }
        }
        //5.- Devolver la comentario o LOS topic
        return resultadoMultiple;
    }

    @Override
    public Boolean update(Object elementoConQueActualizar) throws SQLException{
        return super.update(elementoConQueActualizar);
    }

    @Override
    public Boolean delete(Object elementoABorrar) throws SQLException{
        Publicacion auxPublicacion = (Publicacion)elementoABorrar;
        Integer idPublicacion = auxPublicacion.getId();
        //1.- Borrar primero sus comentarios
        borradoEnCascada(new DAOComentario(), new Comentario(), idPublicacion);
        //2.- Borrar sus adjuntos
        borradoEnCascada(new DAOAdjunto(), new Adjunto(), idPublicacion);
        //3.- Ahora si, borrar la comentario
        return super.delete(elementoABorrar);
    }

    private void borradoEnCascada(DAOBase dao, DataBaseItem item, Integer idPublicacion) throws SQLException{

        ArrayList<DataBaseItem> resultadosCascada = new ArrayList<>();

        //Fijar el ID de la comentario
        if (item instanceof Comentario) { // Es un Comentario
            ((Comentario) item).setIdPublicacion(idPublicacion);
        }else { // Es un Adjunto
            ((Adjunto) item).setIdPublicacion(idPublicacion);
        }

        //Leer todos los items con esa idPublicacion
        resultadosCascada = dao.read(item);

        //Borrarlos uno a uno
        for(DataBaseItem a : resultadosCascada){
            dao.delete(a);
        }
    }
}
