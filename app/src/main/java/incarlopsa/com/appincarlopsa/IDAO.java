package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;

/**
 * Created by Anonymous on 24/10/2017.
 */

public interface IDAO<T> {
    Boolean create(T elementoACrear) throws SQLException;
    T read(Integer id);
    Boolean update(T elementoConQueActualizar);
    Boolean delete(T elementoABorrar);
}
