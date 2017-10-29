package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDAO {
    Boolean create(Object elementoACrear) throws SQLException;
    ArrayList<DataBaseItem> read(Object filtro) throws SQLException;
    Boolean update(Object elementoConQueActualizar);
    Boolean delete(Object objetoABorrar);
}
