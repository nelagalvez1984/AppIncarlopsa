package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Nela on 31/10/2017.
 */

public class DAOLikes extends DAOBase{

    //Propiedades


    //Constructores

    public DAOLikes() { }

    //Metodos
    //LECTURA
    @Override
    protected void prepararFiltroConsultaRead(Object filtro) {

    }

    @Override
    protected void rellenarObjetos() throws SQLException {

    }

    //CREACION
    @Override
    protected void prepararCreate(Object elementoAModelar) throws SQLException {

    }

    //ACTUALIZACION
    @Override
    protected void prepararUpdate(Object elementoAModelar) throws SQLException {

    }

    //CONTROL DE CONSULTAS CRUD:
    @Override
    public Boolean create(Object elementoACrear) throws SQLException {
        return super.create(elementoACrear);
    }

    @Override
    public ArrayList<DataBaseItem> read(Object filtro) throws SQLException{
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
