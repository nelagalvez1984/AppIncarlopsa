package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDAOMultipleID<T> {
    Boolean create(Object elementoACrear) throws SQLException;
    ArrayList<DataBaseItem> read(Integer... ids) throws SQLException;
    Boolean update(Object elementoConQueActualizar, Integer... ids);
}