package incarlopsa.com.appincarlopsa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOUsuario implements IDAO<Usuario> {

    //Propiedades
    private String nombreTabla = "usuario";
    private String nombreIdTabla = "idusuario";

    //Constructor

    //ToDo

    @Override
    public Boolean create(Usuario elementoACrear) {


        SingleConexion conexion = SingleConexion.getInstance();
        Statement st = null;
        boolean resultado = false;
        try {
            st = conexion.conectar().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultado = st.execute("insert into usuario set nombre='prueba'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conexion.desconectar();
        return null;
    }

    @Override
    public Usuario read(Integer idUsuario) {
        //ToDo
        return null;
    }

    @Override
    public Boolean update(Integer idUsuarioOrigen, Usuario elementoConQueActualizar) {
        //ToDo
        return null;
    }

    @Override
    public Boolean delete(Usuario elementoABorrar) {
        //ToDo
        return null;
    }


}
