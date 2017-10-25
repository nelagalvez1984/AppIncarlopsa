package incarlopsa.com.appincarlopsa;

import java.util.ArrayList;
import java.util.Date;

public class MensajeComentario extends MensajeBase {

    //Propiedades
    private ArrayList<MeAlgo> arrayAlgo;

    //Constructores
    public MensajeComentario(int idPublicacion, int idMensaje, int idUsuario, String mensaje, Date fecha, ArrayList<MeAlgo> algos) {
        super(idPublicacion, idMensaje, idUsuario, mensaje, fecha);
        arrayAlgo = algos;
    }

    public MensajeComentario(int idPublicacion, int idMensaje, int idUsuario, String mensaje, Date fecha) {
        super(idPublicacion, idMensaje, idUsuario, mensaje, fecha);
        arrayAlgo = new ArrayList<>();
    }

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

        MensajeComentario that = (MensajeComentario) o;

        return arrayAlgo != null ? arrayAlgo.equals(that.arrayAlgo) : that.arrayAlgo == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (arrayAlgo != null ? arrayAlgo.hashCode() : 0);
        return result;
    }

    //Funciones
    private Integer contar(String tipo){
        int cont=0;
        for (int i=0; i<arrayAlgo.size();i++)
        {
            if (arrayAlgo.get(i).getTipo().equals(tipo)){
                cont++;
            }
        }
        return cont;
    }

    private ArrayList<MeAlgo> recoger(String tipoAlgo){
        ArrayList<MeAlgo> meAlgos = new ArrayList<>();
        for (int i=0; i<arrayAlgo.size();i++){
            if (arrayAlgo.get(i).getTipo().equals(tipoAlgo)) {
                meAlgos.add(arrayAlgo.get(i));
            }
        }
        return meAlgos;
    }
}