package incarlopsa.com.appincarlopsa;

public class SingleCredenciales extends Usuario{

//Singleton con el usuario de la APP! contiene su login, pass, y lo demas

    private static final SingleCredenciales ourInstance = new SingleCredenciales();

    static SingleCredenciales getInstance() {
        return ourInstance;
    }

    private SingleCredenciales() {

    }
}
