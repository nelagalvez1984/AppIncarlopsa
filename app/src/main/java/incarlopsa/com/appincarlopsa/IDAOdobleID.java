package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;

public interface IDAOdobleID<T> {
    Boolean create(T elementoACrear) throws SQLException;
    T read(Integer id1, Integer id2);
    Boolean update(Integer idOrigen1, Integer idOrigen2, T elementoConQueActualizar);
    Boolean delete(T elementoABorrar);
}