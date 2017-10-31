package incarlopsa.com.appincarlopsa;

/**
 * Created by Nela on 31/10/2017.
 */

public class PublicacionAdjunto extends DataBaseItem{

    //Propiedades
    Integer idPublicacion = null;
    Integer idAdjunto = null;

    //Constructores
    public PublicacionAdjunto() {
    }

    public PublicacionAdjunto( Integer idPublicacionAdjunto, Integer idPublicacion, Integer idAdjunto){
        this(idPublicacion,idAdjunto);
        this.idAdjunto = idAdjunto;
    }

    public PublicacionAdjunto(Integer idPublicacion, Integer idAdjunto) {
        this.idPublicacion = idPublicacion;
        this.idAdjunto = idAdjunto;
    }

    //Getter/setter
    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public Integer getIdAdjunto() {
        return idAdjunto;
    }

    public void setIdAdjunto(Integer idAdjunto) {
        this.idAdjunto = idAdjunto;
    }


    //Hash y Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PublicacionAdjunto that = (PublicacionAdjunto) o;

        if (!idPublicacion.equals(that.idPublicacion)) return false;
        return idAdjunto.equals(that.idAdjunto);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idPublicacion.hashCode();
        result = 31 * result + idAdjunto.hashCode();
        return result;
    }
}
