package incarlopsa.com.appincarlopsa;

public class DAOTopic<T> implements IDAO<T> {

    //ToDO

    private T aux;
    private IDAO<T> dao;
    private String nombreTabla;
    private String nombreIdTabla;

    public DAOTopic(){

        if (aux.getClass().getSimpleName().equals("Publicacion")){ // Es un DAOTopic de publicacion
            //La consulta se prepara usando la tabla Publicacion
            nombreTabla = "publicacion";
            nombreIdTabla = "idpublicacion";
        }else{ // Es un DAOTopic de chat
            //La consulta se prepara usando la tabla Chat
            nombreTabla = "chat";
            nombreIdTabla = "idchat";
        }

        //ToDO

    }

    @Override
    public Boolean create(T elementoACrear) {
        //ToDO
        return null;
    }

    @Override
    public T read(Integer idPublicacion_o_idChat) {
        //ToDO
        return null;
    }

    @Override
    public Boolean update(Integer idPublicacion_o_ChatOrigen, T elementoConQueActualizar) {
        //ToDO
        return null;
    }

    @Override
    public Boolean delete(T elementoABorrar) {
        //ToDO
        return null;
    }
}
