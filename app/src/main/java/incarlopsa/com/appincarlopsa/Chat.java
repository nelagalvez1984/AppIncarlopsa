package incarlopsa.com.appincarlopsa;

import java.util.ArrayList;
import java.util.Date;

public class Chat extends Topic {


    //Contiene arrayList de MensajeChat

    ArrayList<MensajeChat> mensajeChats;

    public Chat(String titulo, Date fecha, String autor, MensajeChat mensaje) {
        super(titulo, fecha, autor);
        mensajeChats = new ArrayList<>();
        mensajeChats.add(mensaje);
    }

    //metodo para abrir o cerrar el chat
    public void finalizarChat(){
        finalizado = true;
    }

    //constructor
    public Chat(String titulo, Date fecha, String autor, ArrayList<MensajeChat> mensajeChats) {
        super(titulo, fecha, autor);
        this.mensajeChats = mensajeChats;
    }

    //getters y setters
    public ArrayList<MensajeChat> getMensajeChats() {
        return mensajeChats;
    }

    public void setMensajeChats(ArrayList<MensajeChat> mensajeChats) {
        this.mensajeChats = mensajeChats;
    }


    //equals y hash
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chat)) return false;
        if (!super.equals(o)) return false;

        Chat chat = (Chat) o;

        return getMensajeChats() != null ? getMensajeChats().equals(chat.getMensajeChats()) : chat.getMensajeChats() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getMensajeChats() != null ? getMensajeChats().hashCode() : 0);
        return result;
    }

}