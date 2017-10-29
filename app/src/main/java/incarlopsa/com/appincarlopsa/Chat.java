package incarlopsa.com.appincarlopsa;

import java.util.ArrayList;
import java.util.Date;

public class Chat extends Topic {


    //Propiedades
    private ArrayList<Mensaje> mensajes = null;

    //Constructores
    public Chat(Integer idTopic, String titulo, Date fechaUltimoUpdate, Integer idUsuario, ArrayList<Mensaje> mensajes) {
        super(idTopic, titulo, fechaUltimoUpdate, idUsuario);
        this.mensajes = mensajes;
    }

    public Chat(Integer idTopic, String titulo, Date fechaUltimoUpdate, Integer idUsuario, Mensaje mensaje) {
        super(idTopic, titulo, fechaUltimoUpdate, idUsuario);
        mensajes = new ArrayList<>();
        mensajes.add(mensaje);
    }

    public Chat(){}

    //Metodo para dar por finalizado el chat
    public void finalizarChat(){
        finalizado = true;
    }

    //Getters y setters
    public ArrayList<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public void setNuevoMensaje(Mensaje mensaje){
        mensajes.add(mensaje);
    }

    //Equals y hash
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Chat chat = (Chat) o;

        return mensajes.equals(chat.mensajes);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + mensajes.hashCode();
        return result;
    }
}