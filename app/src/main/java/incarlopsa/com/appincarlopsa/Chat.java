package incarlopsa.com.appincarlopsa;

import java.util.ArrayList;
import java.util.Date;

public class Chat extends Topic {


    //Propiedades
    private Integer idUsuarioDestino = null;
    private ArrayList<DataBaseItem> mensajes = null;

    //Constructores


    public Chat(Integer idTopic, Integer idUsuario, Integer idUsuarioDestino, String titulo, String fechaCreacion,
                String horaCreacion, String fechaUltimoUpdate, String horaUltimoUpdate, Boolean finalizado,
                ArrayList<DataBaseItem> mensajes) {
        super(idTopic, idUsuario, titulo, fechaCreacion, horaCreacion, finalizado, fechaUltimoUpdate, horaUltimoUpdate);
        this.idUsuarioDestino = idUsuarioDestino;
        this.mensajes = mensajes;
    }

    public Chat(Integer idTopic, Integer idUsuario, Integer idUsuarioDestino, String titulo, String fechaCreacion,
                String horaCreacion, String fechaUltimoUpdate, String horaUltimoUpdate, Boolean finalizado) {
        super(idTopic, idUsuario, titulo, fechaCreacion, horaCreacion, finalizado, fechaUltimoUpdate, horaUltimoUpdate);
        this.idUsuarioDestino = idUsuarioDestino;
        this.mensajes = new ArrayList<>();
    }

    public Chat(){
        mensajes = new ArrayList<>();
    }

    //Metodo para dar por finalizado el mensaje
    public void finalizarChat(){
        finalizado = true;
    }

    //Getters y setters
    public ArrayList<DataBaseItem> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<DataBaseItem> mensajes) {
        this.mensajes = mensajes;
    }

    public void setNuevoMensaje(Mensaje mensaje){
        mensajes.add(mensaje);
    }

    public Integer getIdUsuarioDestino() {
        return idUsuarioDestino;
    }

    public void setIdUsuarioDestino(Integer idUsuarioDestino) {
        this.idUsuarioDestino = idUsuarioDestino;
    }

    //Equals y hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Chat chat = (Chat) o;

        if (!idUsuarioDestino.equals(chat.idUsuarioDestino)) return false;
        return mensajes.equals(chat.mensajes);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idUsuarioDestino.hashCode();
        result = 31 * result + mensajes.hashCode();
        return result;
    }
}