package incarlopsa.com.appincarlopsa;

/**
 * Created by Anonymous on 24/10/2017.
 */

public interface IDAO<T> {
    Boolean create(T elementoACrear);
    T read();
    Boolean update(T elementoConQueActualizar);
    Boolean delete(T elementoABorrar);
}
