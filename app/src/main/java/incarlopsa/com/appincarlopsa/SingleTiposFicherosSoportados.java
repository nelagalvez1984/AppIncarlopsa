package incarlopsa.com.appincarlopsa;

import java.util.ArrayList;
import java.util.List;

public class SingleTiposFicherosSoportados {

    //Singleton con los tipos que soportara la aplicacion!

    private static final SingleTiposFicherosSoportados ourInstance = new SingleTiposFicherosSoportados();

    static SingleTiposFicherosSoportados getInstance() {
        return ourInstance;
    }

    private List<TipoFichero> listaFicheros;
    private DAOTipoFichero dao;

    private SingleTiposFicherosSoportados() {
        listaFicheros = new ArrayList<>();
        //LEER LOS TIPOS DE FICHEROS DE LA TABLA TIPOFICHERO Y METERLOS EN EL ARRAY
        //HACER LOS METODOS CORRESPONDIENTES PARA DEVOLVER EL FICHERO CORRESPONDIENTE SEGUN UN ID
    }
}
