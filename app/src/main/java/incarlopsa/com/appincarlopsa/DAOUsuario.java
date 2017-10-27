package incarlopsa.com.appincarlopsa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOUsuario extends DAOBase implements IDAO<Usuario>{

    //Propiedades
    private String consultaInsercion = "INSERT INTO usuario SET nombre = ? "
                                + " , apellidos = ?, dni = ?, tipoEmpleado = ?";
    private String consultaLecturaPorId = "SELECT idusuario, nombre, apellidos, dni, tipoempleado, foto "
                                + "FROM usuario WHERE idusuario = ?";
    private String consultaLecturaPorTipoEmpleado = "SELECT idusuario, nombre, apellidos, dni, tipoempleado, foto "
            + "FROM usuario WHERE tipoempleado = ?";
    private String consultaUpdate = "UPDATE usuario SET nombre = ?, apellidos = ?, dni = ?, "
                                + "tipoempleado = ?, foto = ? WHERE idusuario = ?";

    //CREACION
    //Preparar una consulta de create y cargar sus parametros
    protected void prepararCreate(Object elementoAModelar) throws SQLException{
        Usuario elementoACrear = (Usuario)elementoAModelar;
        prepararConsulta(consultaInsercion);
        cargarConsulta( elementoACrear.getNombre(),
                elementoACrear.getApellidos(),
                elementoACrear.getDni(),
                elementoACrear.getTipoEmpleado(),
                elementoACrear.getFoto().getFotoBlob());
    }

    //LECTURA
    //Tipo de filtro a aplicar a la consulta de lectura
    // (por que campo se tirara para determinar la consulta concreta)
    protected void prepararFiltroConsultaRead(Object filtro) {
        if (filtro instanceof Integer){
            consultaSQL = consultaLecturaPorId;
        }else{
            if (filtro instanceof String){
                consultaSQL = consultaLecturaPorTipoEmpleado;
            }
        }
    }

    //Rellenar el array de resultados con cada resultado
    protected void rellenarObjetos() throws SQLException{

        resultadoMultiple.add(new Usuario( resultados.getInt(1), //IdUsuario
                                            resultados.getString(2), //Nombre
                                            resultados.getString(3), //Apellidos
                                            resultados.getString(4), //Dni
                                            resultados.getString(5), //TipoEmpleado
                                            new Foto(resultados.getBlob(6)))); //Foto (blob))
    }

    //UPDATE
    //Preparar una consulta de update y cargar sus parametros
    protected void prepararUpdate(Object elementoAModelar, Integer idUsuarioOrigen) throws SQLException{
        Usuario elementoConQueActualizar = (Usuario)elementoAModelar;
        prepararConsulta(consultaUpdate);
        cargarConsulta( elementoConQueActualizar.getNombre(),
                elementoConQueActualizar.getApellidos(),
                elementoConQueActualizar.getDni(),
                elementoConQueActualizar.getTipoEmpleado(),
                elementoConQueActualizar.getFoto().getFotoBlob(),
                idUsuarioOrigen);
    }

}
