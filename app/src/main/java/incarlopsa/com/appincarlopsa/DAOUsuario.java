package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOUsuario extends DAOBase implements IDAO{

    //Propiedades
    private String consultaInsercion = "INSERT INTO usuario SET nombre = ? "
                                + " , apellidos = ?, dni = ?, tipoEmpleado = ?, foto = ?";
    private String consultaLecturaDameTodos = "SELECT idusuario, nombre, apellidos, dni, tipoempleado, foto "
            + "FROM usuario";
    private String consultaLecturaPorId = "SELECT idusuario, nombre, apellidos, dni, tipoempleado, foto "
                                + "FROM usuario WHERE idusuario = ?";
    private String consultaLecturaPorTipoEmpleado = "SELECT idusuario, nombre, apellidos, dni, tipoempleado, foto "
            + "FROM usuario WHERE tipoempleado = ?";
    private String consultaLecturaPorUsername = "SELECT idusuario, nombre, apellidos, dni, tipoempleado, foto, username "
            + "FROM usuario WHERE username = ?";
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
        //Seleccionar tipo de consulta
        if (filtro instanceof String){ //DamelosTodos!
            if ( filtro.equals(DAME_TODOS)){ //Dame todos los registros!
                consultaSQL  = consultaLecturaDameTodos;
            }else{
                    //Parametro incorrecto! Bad try! (devolucion estandar, SELECT *, osea, DAME_TODOS )
                    consultaSQL  = consultaLecturaDameTodos;
            }
        }else{ //Deberia ser un Usuario
            //Comprobar tipos de peticiones. Para ello revisamos los campos
            Usuario usuarioTemporal = (Usuario)filtro;
            if (usuarioTemporal.getTipoEmpleado() != null){ //Aaaamigo, quieren leer por tipo de empleado
                consultaSQL = consultaLecturaPorTipoEmpleado;
            }else{
                if (usuarioTemporal.getUsername() != null){ //Se esta reclamando el perfil completo
                    consultaSQL = consultaLecturaPorUsername;
                }else{
                    //Resto de casos. Si no se nos ocurren mas casos, es que sera una lectura por ID
                    consultaSQL = consultaLecturaPorId;
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
