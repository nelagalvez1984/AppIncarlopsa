package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Nela on 31/10/2017.
 */

public class DAOLikes extends DAOBase{

    //Propiedades
    String consultaLecturaPorId = "SELECT idLike, idUsuario, idComentario, tipoLike FROM likes WHERE idComentario = ?";
    String consultaInsercion = "INSERT INTO likes SET idUsuario = ? , idComentario = ? , tipoLike = ?";
    String consultaUpdate = "UPDATE likes idUsuario = ? , idComentario = ? , tipoLike = ? WHERE idLike = ?";

    //Constructores

    public DAOLikes() { }

    //Metodos
    //LECTURA
    @Override
    protected void prepararFiltroConsultaRead(Object filtro) {
        consultaSQL = consultaLecturaPorId;
    }

    @Override
    protected void rellenarObjetos() throws SQLException {
        MeAlgo aux = null;
        if (resultados.getString(4).equals("MeGusta")){
            aux = new MeGusta(resultados.getInt(1),
                                    resultados.getInt(2),
                                    resultados.getInt(3));

        }else{
            if (resultados.getString(4).equals("NoMeGusta")){
                aux = new MeDisgusta(resultados.getInt(1),
                        resultados.getInt(2),
                        resultados.getInt(3));
            }else{
                //NO existen mas casos
            }
        }

        resultadoMultiple.add(aux);

    }

    //CREACION
    @Override
    protected void prepararCreate(Object elementoAModelar) throws SQLException {
        MeAlgo aux = (MeAlgo)elementoAModelar;
        prepararConsulta(consultaInsercion);
        cargarConsulta(aux.getIdUsuario(),
                                aux.getIdComentario(),
                                aux.getTipo());
    }

    //ACTUALIZACION
    @Override
    protected void prepararUpdate(Object elementoAModelar) throws SQLException {
        MeAlgo aux = (MeAlgo)elementoAModelar;
        prepararConsulta(consultaUpdate);
        cargarConsulta(aux.getIdUsuario(),
                                    aux.getIdComentario(),
                                    aux.getTipo(),
                                    aux.getId());
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
    public Boolean delete(Object elementoABorrar) { //HACER!
        return null;
    }
}
