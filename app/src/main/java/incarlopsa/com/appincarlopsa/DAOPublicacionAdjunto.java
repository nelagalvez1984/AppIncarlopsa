package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOPublicacionAdjunto extends DAOBase implements IDAO{

    //Propiedades
    String consultaLecturaPorId = "SELECT idPublicacionAdjunto,idPublicacion, idAdjunto FROM PublicacionAdjunto WHERE idPublicacion=?";
    String consultaInsercion = "INSERT INTO publicacionadjunto SET idPublicacion = ? , idAdjunto = ?";
    String consultaUpdate = "UPDATE idPublicacionAdjunto SET idPublicacion = ? , idAdjunto = ? WHERE idPublicacionAdjunto = ?";
    String consultaDelete = "DELETE FROM publicacionadjunto WHERE idPublicacionAdjunto = ?";

    //Constructores

    public DAOPublicacionAdjunto() {}

    //Metodos
    //LECTURA
    @Override
    protected void prepararRead(Object filtro) throws SQLException {
        PublicacionAdjunto aux = (PublicacionAdjunto)filtro;
        consultaSQL = consultaLecturaPorId;
        prepararConsulta(consultaSQL);
        cargarConsulta(aux.getIdPublicacion());
    }

    @Override
    protected void rellenarObjetos() throws SQLException {
        PublicacionAdjunto aux;
        aux = new PublicacionAdjunto(resultados.getInt(1), //IdPublicacionAdjunto
                                    resultados.getInt(2),  //IdPublicacion
                                    resultados.getInt(3)); //IdAdjunto
        resultadoMultiple.add(aux);
    }

    //CREACION
    @Override
    protected void prepararCreate(Object elementoAModelar) throws SQLException {
        PublicacionAdjunto aux = (PublicacionAdjunto)elementoAModelar;
        prepararConsulta(consultaInsercion);
        cargarConsulta(aux.getIdPublicacion(),
                        aux.getIdAdjunto());
    }



    //ACTUALIZACION
    @Override
    protected void prepararUpdate(Object elementoAModelar) throws SQLException {
        PublicacionAdjunto aux = (PublicacionAdjunto)elementoAModelar;
        prepararConsulta(consultaUpdate);
        cargarConsulta(aux.getIdPublicacion(),
                        aux.getIdAdjunto(),
                        aux.getId());
    }

    //DELETE
    @Override
    protected void prepararDelete(Object elementoAModelar) throws SQLException {
        PublicacionAdjunto aux = (PublicacionAdjunto)elementoAModelar;
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
        //Resultados del read (resultados de PublicacionAdjunto)
        ArrayList<DataBaseItem> tablaValores;
        tablaValores = super.read(filtro);

        //Array para meter todos los adjuntos correspondientes a esa tabla
        ArrayList<DataBaseItem> resultados;
        resultados = new ArrayList<>();

        //Dao para recoger los adjuntos
        DAOAdjunto dao = new DAOAdjunto();

        //Recorremos la tabla
        for(DataBaseItem aux : tablaValores){
            resultados.addAll(dao.read(aux));
        }

        return resultados;

    }

    @Override
    public Boolean update(Object elementoConQueActualizar) { //NO SE ACTUALIZAN PUBLICACIONES-ADJUNTAS EN NUESTRA APP!
                                                            //PERO AUN ASI DEJO EL CODIGO
        return super.update(elementoConQueActualizar);
    }

    @Override
    public Boolean delete(Object elementoABorrar) {
        PublicacionAdjunto publicacionAdjuntoAux = (PublicacionAdjunto)elementoABorrar;
        DAOAdjunto dao = new DAOAdjunto();
        Adjunto aux = new Adjunto();
        aux.setId(publicacionAdjuntoAux.getIdAdjunto());
        dao.delete(aux); //Borrar el adjunto asociado primeramente
        return super.delete(elementoABorrar); //Y ahora se borra la publicacion-adjunto
    }
}
