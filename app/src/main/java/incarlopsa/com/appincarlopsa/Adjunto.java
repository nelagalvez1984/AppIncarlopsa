package incarlopsa.com.appincarlopsa;

public class Adjunto extends DataBaseItem{

    //Propiedades
    private TipoFichero tipo = null;
    private Integer idTipoFichero = null;
    private String localizacion = null;
    private String nombreAdjunto = null;

    //Constructor
    public Adjunto(TipoFichero tipo, int idAdjunto, int idTipoFichero, String localizacion, String nombreAdjunto) {
        this.tipo = tipo;
        this.id = idAdjunto;
        this.idTipoFichero = idTipoFichero;
        this.localizacion = localizacion;
        this.nombreAdjunto = nombreAdjunto;
    }

    public Adjunto(){}

    //Getter / Setter
    public TipoFichero getTipo() {
        return tipo;
    }

    public void setTipo(TipoFichero tipo) {
        this.tipo = tipo;
    }

    public int getIdTipoFichero() {
        return idTipoFichero;
    }

    public void setIdTipoFichero(int idTipoFichero) {
        this.idTipoFichero = idTipoFichero;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getNombreAdjunto() {
        return nombreAdjunto;
    }

    public void setNombreAdjunto(String nombreAdjunto) {
        this.nombreAdjunto = nombreAdjunto;
    }

    //Equals y Hash
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Adjunto adjunto = (Adjunto) o;

        if (idTipoFichero != adjunto.idTipoFichero) return false;
        if (!tipo.equals(adjunto.tipo)) return false;
        if (!localizacion.equals(adjunto.localizacion)) return false;
        return nombreAdjunto.equals(adjunto.nombreAdjunto);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + tipo.hashCode();
        result = 31 * result + idTipoFichero;
        result = 31 * result + localizacion.hashCode();
        result = 31 * result + nombreAdjunto.hashCode();
        return result;
    }
}
