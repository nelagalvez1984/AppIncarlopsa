package incarlopsa.com.appincarlopsa;

import java.sql.SQLException;
import java.util.ArrayList;

public class SingleTiposFicherosSoportados implements ICodigos{

    //Singleton con los tipos que soportara la aplicacion!

    private static final SingleTiposFicherosSoportados ourInstance = new SingleTiposFicherosSoportados();

    static SingleTiposFicherosSoportados getInstance() {
        return ourInstance;
    }

    private ArrayList<DataBaseItem> listaFicheros;
    private UNUSED_DAOTipoFichero dao;

    private SingleTiposFicherosSoportados() {
        listaFicheros = new ArrayList<>();
        dao = new UNUSED_DAOTipoFichero();
        try {
            listaFicheros = dao.read(DAME_TODOS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DataBaseItem> getListaFicherosSoportados() {
        return listaFicheros;
    }

}
