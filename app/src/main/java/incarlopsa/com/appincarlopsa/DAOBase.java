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
        if (parametros[0].equals(DAME_TODOS)) {
            //NO HAY PARAMETROS QUE INCRUSTAR!
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
                    if (parametros[i] instanceof byte[]){ //Es un blob!
                        consulta.setBytes(i+1 , (byte[]) (parametros[i]) );
                        continue;
                    }
                    if (parametros[i] instanceof Boolean){ //Es un boolean!
                        consulta.setBoolean(i+1 , (Boolean) (parametros[i]) );
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

    //Creacion
    public Boolean create(Object elementoACrear) throws SQLException {
        boolean resultado = false;

        conectar();
        prepararCreate(elementoACrear);
        resultado = ejecutarConsultaCreate();
        desconectar();

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
    protected void getRows() throws SQLException{
        numeroResultados = 0;
        resultados.last();
        numeroResultados = resultados.getRow();
        resultados.beforeFirst();
        numeroResultados = 0;
    }

    //Proceso completo de lectura (filtro + consulta)
    public ArrayList<DataBaseItem> read(Object filtro) throws SQLException{
        conectar();
        prepararRead(filtro);
        ejecutarConsultaRead();
        while(resultados.next()) { //Por cada row, meter un objeto al array de resultados
            rellenarObjetos();
        }
        desconectar();
        return resultadoMultiple;
    }

    //Actualizar una fila correspondiente a un ID con un elemento ya relleno
    public Boolean update(Object elementoConQueActualizar) throws SQLException{
        boolean resultado = false;
        conectar();
        prepararUpdate(elementoConQueActualizar);
        resultado = ejecutarConsultaUpdate();
        desconectar();
        return resultado;
    }

    //Borrado
    @Override
    public Boolean delete(Object objetoABorrar) throws SQLException{
        boolean resultado = false;
        conectar();
        prepararDelete(objetoABorrar);
        resultado = ejecutarConsultaDelete();
        desconectar();
        return resultado;
    }

    //Para borrar se usa el mismo chequeo que para el create
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
