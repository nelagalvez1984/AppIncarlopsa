package incarlopsa.com.appincarlopsa;

public class SingleCredenciales extends Usuario {

    //Singleton con el usuario de la APP! contiene su login, pass, y lo demas

    //Propiedades
    private String login;
    private String password;
    private static final SingleCredenciales ourInstance = new SingleCredenciales();

    //Constructor
    private SingleCredenciales() {
        login = null;
        password = null;
    }

    //Getter/Setter
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }

    //Get Instance
    static SingleCredenciales getInstance() {
        return ourInstance;
    }

}
