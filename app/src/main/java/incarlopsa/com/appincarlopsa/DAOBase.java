package incarlopsa.com.appincarlopsa;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class DAOBase implements IDAO, ICodigos{

    protected SingleConexion conector = SingleConexion.getInstance();
    protected PreparedStatement consulta = null;
    protected Connection conexion;
    protected ResultSet resultados;
    protected Integer numeroResultados;
    protected ArrayList<DataBaseItem> resultadoMultiple;
    protected String consultaSQL = "";

    //Conexion/Desconexion
    protected void conectar() throws SQLException {
        conexion = conector.conectar();
    }

    protected void desconectar() throws SQLException{
        conector.desconectar();
    }

    //Preparar el esqueleto de la consulta
    protected void prepararConsulta(String sentenciaSQL)throws SQLException {
        consulta = conexion.prepareStatement(sentenciaSQL);
    }

    //Rellenar los campos de la consulta
    protected void cargarConsulta(Object... parametros) throws SQLException{
        int x = parametros.length;
        if (parametros[0] instanceof String){
            if ( (parametros[0]).equals(DAME_TODOS)){ //NO HAY PARAMETROS QUE INCRUSTAR!
                //NADA
            }else{
                int acumulador = 0;
                for(int i=0; i<x; i++){

                    if (parametros[i] instanceof Integer){ //Es un entero!
                        consulta.setInt(i+1 , (Integer) (parametros[i]) );
                        continue;
                    }
                    if (parametros[i] instanceof String){ //Es una cadena!
                        consulta.setString(i+1 , (String) (parametros[i]) );
                        continue;
                    }
                    if (parametros[i] instanceof Date){ //Es una fecha!
                        consulta.setDate(i+1 , (Date) (parametros[i]) );
                        continue;
                    }
                    if (parametros[i] instanceof Blob){ //Es un blob!
                        consulta.setBlob(i+1 , (Blob) (parametros[i]) );
                        continue;
                    }
                    if (parametros[i] instanceof Boolean){ //Es un boolean!
                        consulta.setBoolean(i+1 , (Boolean) (parametros[i]) );
                    }
                }
            }
        }

    }

    //Lanzar consulta de insercion
    protected Boolean ejecutarConsultaCreate() throws SQLException{
        int filasAfectadas;
        Boolean retorno = false;
        filasAfectadas = consulta.executeUpdate();
        if (filasAfectadas != 0){
            retorno = true;
        }
        return retorno;
    }

    public Boolean create(Object elementoACrear) throws SQLException {
        boolean resultado = false;
        try {
            conectar();
            prepararCreate(elementoACrear);
            resultado = ejecutarConsultaCreate();
            desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    //Lanzar consulta de actualizacion (identica a la creacion!)
    protected Boolean ejecutarConsultaUpdate() throws SQLException{
        return ejecutarConsultaCreate();
    }

    //Ejecutar la consulta de lectura
    protected void ejecutarConsultaRead() throws SQLException{
        int filasAfectadas = 0;
        resultados = consulta.executeQuery();
        getRows();
        resultados.beforeFirst();
        resultadoMultiple = new ArrayList<>();
    }

    //Contar resultados devueltos
    protected void getRows(){
        numeroResultados = 0;
        try {
            resultados.last();
            numeroResultados = resultados.getRow();
            resultados.beforeFirst();
        }
        catch(Exception ex)  {
            numeroResultados = 0;
        }
    }

    //Proceso completo de lectura (filtro + consulta)
    public ArrayList<DataBaseItem> read(Object filtro) throws SQLException{
        try {
            conectar();
            prepararRead(filtro);
            ejecutarConsultaRead();
            while(resultados.next()) { //Por cada row, meter un objeto al array de resultados
                rellenarObjetos();
            }
            desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultadoMultiple;
    }

    //Actualizar una fila correspondiente a un ID con un elemento ya relleno
    public Boolean update(Object elementoConQueActualizar) {
        boolean resultado = false;
        try {
            conectar();
            prepararUpdate(elementoConQueActualizar);
            resultado = ejecutarConsultaUpdate();
            desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public Boolean delete(Object objetoABorrar) {
        boolean resultado = false;
        try {
            conectar();
            prepararDelete(objetoABorrar);
            resultado = ejecutarConsultaDelete();
            desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    private boolean ejecutarConsultaDelete() throws SQLException {
        return ejecutarConsultaCreate();
    }


    //RELLENAR ESPECIFICAMENTE PARA CADA DAO

    //Rellenar items con la info extraida
    protected abstract void rellenarObjetos() throws SQLException;

    //Preparar la consulta de create
    protected abstract void prepararCreate(Object elementoAModelar) throws SQLException;

    //Preparar la consulta de read
    protected abstract void prepararRead(Object filtro) throws SQLException;

    //Preparar la consulta de update
    protected abstract void prepararUpdate(Object elementoAModelar) throws SQLException;

    //Preparar la consulta de delete
    protected abstract void prepararDelete(Object elementoAModelar) throws SQLException;
}
