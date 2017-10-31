package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Nela on 31/10/2017.
 */

public class DAOPublicacionAdjunto extends DAOBase {

    //Propiedades
    String consultaLecturaPorId = "SELECT idPublicacionAdjunto,idPublicacion, idAdjunto FROM PublicacionAdjunto WHERE idPublicacion=?";
    String consultaInsercion = "INSERT INTO publicacionadjunto SET idPublicacion = ? , idAdjunto = ?";
    String consultaUpdate = "UPDATE idPublicacionAdjunto SET idPublicacion = ? , idAdjunto = ? WHERE idPublicacionAdjunto = ?";

    //Constructores

    public DAOPublicacionAdjunto() {}

    //Metodos
    //LECTURA
    @Override
    protected void prepararFiltroConsultaRead(Object filtro) {
        //Seleccionar tipo de consulta
        consultaSQL = consultaLecturaPorId;
    }

    @Override
    protected void rellenarObjetos() throws SQLException {
        PublicacionAdjunto aux;
        aux = new PublicacionAdjunto(resultados.getInt(1),
                                    resultados.getInt(2),
                                    resultados.getInt(3));
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
        return null;
    }

    @Override
    public Boolean delete(Object elementoABorrar) { //NO SE BORRAN PUBLICACIONES-ADJUNTAS EN NUESTRA APP!
        return null;
    }
}
