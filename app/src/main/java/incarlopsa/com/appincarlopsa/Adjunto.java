package incarlopsa.com.appincarlopsa;

import android.os.Parcel;
import android.os.Parcelable;

public class Adjunto extends DataBaseItem implements Parcelable{

    //Propiedades
    private Integer idPublicacion = null;
    private Foto foto = null;
    private String nombreAdjunto = null;

    //Constructores
    public Adjunto(Integer idAdjunto, Integer idPublicacion, Foto foto, String nombreAdjunto) {
        this.id = idAdjunto;
        this.idPublicacion = idPublicacion;
        this.foto = foto;
        this.nombreAdjunto = nombreAdjunto;
    }

    public Adjunto(){}

    private Adjunto(Parcel in){
        readFromParcel(in);
    }

    //Getter / Setter
    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public String getNombreAdjunto() {
        return nombreAdjunto;
    }

    public void setNombreAdjunto(String nombreAdjunto) {
        this.nombreAdjunto = nombreAdjunto;
    }

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }


    //Equals y Hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Adjunto adjunto = (Adjunto) o;

        if (!idPublicacion.equals(adjunto.idPublicacion)) return false;
        if (!foto.equals(adjunto.foto)) return false;
        return nombreAdjunto.equals(adjunto.nombreAdjunto);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idPublicacion.hashCode();
        result = 31 * result + foto.hashCode();
        result = 31 * result + nombreAdjunto.hashCode();
        return result;
    }

    //Metodos parcelables
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeInt(idPublicacion);
        out.writeParcelable(foto,0);
        out.writeString(nombreAdjunto);
    }

    public static final Parcelable.Creator<Adjunto> CREATOR
            = new Parcelable.Creator<Adjunto>() {
        public Adjunto createFromParcel(Parcel in) {
            return new Adjunto(in);
        }

        public Adjunto[] newArray(int size) {
            return new Adjunto[size];
        }
    };

    private void readFromParcel(Parcel in){
        id = in.readInt();
        idPublicacion = in.readInt();
        foto = in.readParcelable(Foto.class.getClassLoader());
        nombreAdjunto = in.readString();

    }
}
