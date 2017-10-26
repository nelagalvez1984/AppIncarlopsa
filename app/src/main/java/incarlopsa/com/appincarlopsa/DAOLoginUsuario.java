package incarlopsa.com.appincarlopsa;

public class DAOLoginUsuario implements IDAO<Usuario> {

    //Se usa para recuperar el usuario correspondiente a un username (Login)

    //Propiedades
    private String nombreTabla = "loginusuario";
    private String nombreIdTabla = "username";
    private DAOUsuario daoUsuario; //Se delegara en el la recuperacion del usuario

    //Constructor

    //ToDo

    @Override
    public Boolean create(Usuario elementoACrear) { //NO USADO
        return null;
    }

    @Override
    public Usuario read() {
        //ToDo
        return null;
    }

    @Override
    public Boolean update(Usuario elementoConQueActualizar) { //NO USADO
        return null;
    }

    @Override
    public Boolean delete(Usuario elementoABorrar) { //NO USADO
        return null;
    }


}
