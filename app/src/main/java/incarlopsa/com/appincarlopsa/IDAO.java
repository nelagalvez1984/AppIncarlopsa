package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Anonymous on 24/10/2017.
 */

public interface IDAO<T> {
    Boolean create(Object elementoACrear) throws SQLException;
    ArrayList<DataBaseItem> read(Object filtro) throws SQLException;
    Boolean update(Object elementoConQueActualizar, Integer idOrigen);
}
