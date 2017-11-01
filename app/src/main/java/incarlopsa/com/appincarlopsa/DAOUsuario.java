package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOUsuario extends DAOBase implements IDAO{

    //Propiedades
    private String consultaInsercion = "INSERT INTO usuario SET nombre = ? "
                                + " , apellidos = ?, dni = ?, tipoEmpleado = ?, foto = ?";
    private String consultaLecturaDameTodos = "SELECT idUsuario, nombre, apellidos, dni, tipoEmpleado, foto "
            + "FROM usuario";
    private String consultaLecturaPorId = "SELECT idUsuario, nombre, apellidos, dni, tipoEmpleado, foto "
                                + "FROM usuario WHERE idUsuario = ?";
    private String consultaLecturaPorTipoEmpleado = "SELECT idUsuario, nombre, apellidos, dni, tipoEmpleado, foto "
            + "FROM usuario WHERE tipoEmpleado = ?";
    private String consultaLecturaPorUsername = "SELECT idUsuario, nombre, apellidos, dni, tipoEmpleado, foto, userName "
            + "FROM usuario WHERE userName = ?";
    private String consultaUpdate = "UPDATE usuario SET nombre = ?, apellidos = ?, dni = ?, "
                                + "tipoEmpleado = ?, foto = ? WHERE idUsuario = ?";
    private String consultaDelete = "DELETE FROM usuario WHERE idUsuario = ?";

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
    @Override
    protected void prepararRead(Object filtro) throws SQLException {
        //Seleccionar tipo de consulta
        if (filtro instanceof String){ //DamelosTodos!
            consultaSQL  = consultaLecturaDameTodos;
            prepararConsulta(consultaSQL);
            cargarConsulta( DAME_TODOS );
        }else{ //Deberia ser un Usuario
            //Comprobar tipos de peticiones. Para ello revisamos los campos
            Usuario usuarioTemporal = (Usuario)filtro;
            if (usuarioTemporal.getTipoEmpleado() != null){ //Aaaamigo, quieren leer por tipo de empleado
                consultaSQL = consultaLecturaPorTipoEmpleado;
                prepararConsulta(consultaSQL);
                cargarConsulta(usuarioTemporal.getTipoEmpleado());
            }else{
                if (usuarioTemporal.getUsername() != null){ //Se esta reclamando el perfil completo
                    consultaSQL = consultaLecturaPorUsername;
                    prepararConsulta(consultaSQL);
                    cargarConsulta(usuarioTemporal.getUsername());
                }else{
                    //Resto de casos. Si no se nos ocurren mas casos, es que sera una lectura por ID
                    consultaSQL = consultaLecturaPorId;
                    prepararConsulta(consultaSQL);
                    cargarConsulta(usuarioTemporal.getId());
                }
            }
        }
    }

    //Rellenar el array de resultados con cada resultado
    protected void rellenarObjetos() throws SQLException{
        Usuario usuarioAux;
        if (consultaSQL.equals(consultaLecturaPorUsername)){ //Hay que meterle el username
            usuarioAux = new Usuario( resultados.getInt(1), //IdUsuario
                    resultados.getString(2), //Nombre
                    resultados.getString(3), //Apellidos
                    resultados.getString(4), //Dni
                    resultados.getString(5), //TipoEmpleado
                    new Foto(resultados.getBlob(6)), //Foto
                    resultados.getString(7) ); //Username
        }else{ //sin username
            usuarioAux = new Usuario( resultados.getInt(1), //IdUsuario
                    resultados.getString(2), //Nombre
                    resultados.getString(3), //Apellidos
                    resultados.getString(4), //Dni
                    resultados.getString(5), //TipoEmpleado
                    new Foto(resultados.getBlob(6))); //Foto
        }
        resultadoMultiple.add(usuarioAux); //Foto (blob))
    }

    //UPDATE
    //Preparar una consulta de update y cargar sus parametros
    protected void prepararUpdate(Object elementoAModelar) throws SQLException{
        Usuario elementoConQueActualizar = (Usuario)elementoAModelar;
        Integer idUsuarioOrigen = elementoConQueActualizar.getIdUsuario();
        prepararConsulta(consultaUpdate);
        cargarConsulta( elementoConQueActualizar.getNombre(),
                elementoConQueActualizar.getApellidos(),
                elementoConQueActualizar.getDni(),
                elementoConQueActualizar.getTipoEmpleado(),
                elementoConQueActualizar.getFoto().getFotoBlob(),
                idUsuarioOrigen);
    }

    //Delete
    @Override
    protected void prepararDelete(Object elementoAModelar) throws SQLException {
        Usuario elementoConQueActualizar = (Usuario)elementoAModelar;
        Integer idUsuarioOrigen = elementoConQueActualizar.getIdUsuario();
        prepararConsulta(consultaDelete);
        cargarConsulta( elementoConQueActualizar.getId());
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
        return super.delete(elementoABorrar);
    }
}
