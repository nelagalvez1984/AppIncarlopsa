package incarlopsa.com.appincarlopsa;

import java.util.ArrayList;
import java.util.Date;

public class Comentario extends MensajeBase {

    //Propiedades
    private ArrayList<MeAlgo> arrayLikes = null;

    //Constructores
    public Comentario(int idPublicacion, int idMensaje, int idUsuario, String mensaje, Date fecha, ArrayList<MeAlgo> algos) {
        super(idPublicacion, idMensaje, idUsuario, mensaje, fecha);
        arrayLikes = algos;
    }

    public Comentario(int idPublicacion, int idMensaje, int idUsuario, String mensaje, Date fecha) {
        super(idPublicacion, idMensaje, idUsuario, mensaje, fecha);
        arrayLikes = new ArrayList<>();
    }

    public Comentario(){}

    //Getter/Setter
    Integer getNumeroMeGusta(){
        return contar("MeGusta");
    }

    Integer getNumeroMeDisgusta(){
        return contar("MeDisgusta");
    }

    ArrayList<MeAlgo> getMeGusta(){
        return recoger("MeGusta");
    }

    ArrayList<MeAlgo> getMeDisgusta(){
        return recoger("MeDisgusta");
    }

    //Equals y Hash
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Comentario that = (Comentario) o;

        return arrayLikes != null ? arrayLikes.equals(that.arrayLikes) : that.arrayLikes == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (arrayLikes != null ? arrayLikes.hashCode() : 0);
        return result;
    }

    //Funciones
    private Integer contar(String tipo){
        int cont=0;
        for (int i=0; i<arrayLikes.size();i++)
        {
            if (arrayLikes.get(i).getTipo().equals(tipo)){
                cont++;
            }
        }
        return cont;
    }

    private ArrayList<MeAlgo> recoger(String tipoAlgo){
        ArrayList<MeAlgo> meAlgos = new ArrayList<>();
        for (int i=0; i<arrayLikes.size();i++){
            if (arrayLikes.get(i).getTipo().equals(tipoAlgo)) {
                meAlgos.add(arrayLikes.get(i));
            }
        }
        return meAlgos;
    }
}