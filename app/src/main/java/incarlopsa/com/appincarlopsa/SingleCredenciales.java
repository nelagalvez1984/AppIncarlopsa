package incarlopsa.com.appincarlopsa;

public class SingleCredenciales extends Usuario {
    private String usuario;
    private String password;

    private SingleCredenciales() {

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//Singleton con el usuario de la APP! contiene su login, pass, y lo demas

    private static final SingleCredenciales ourInstance = new SingleCredenciales();

    static SingleCredenciales getInstance() {
        return ourInstance;
    }

}
