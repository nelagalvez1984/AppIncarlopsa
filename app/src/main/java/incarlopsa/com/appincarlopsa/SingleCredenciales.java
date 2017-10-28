package incarlopsa.com.appincarlopsa;

public class SingleCredenciales extends Usuario{

    //Singleton con el usuario de la APP! contiene su login, pass, y lo demas

    //Propiedades
    private String LOGIN;
    private String PASSWORD;
    private static final SingleCredenciales ourInstance = new SingleCredenciales();

    //Constructor
    private SingleCredenciales() {
        LOGIN = "";
        PASSWORD = "";
    }

    //Getter/Setter
    public String getLogin() {
        return LOGIN;
    }

    public void setLogin(String login) {
        this.LOGIN = login;
    }

    public String getPassword() {
        return PASSWORD;
    }

    public void setPassword(String password) {
        this.PASSWORD = password;
    }

    //Get Instance
    static SingleCredenciales getInstance() {
        return ourInstance;
    }

}
