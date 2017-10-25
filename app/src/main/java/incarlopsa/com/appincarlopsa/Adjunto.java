package incarlopsa.com.appincarlopsa;

public class Adjunto {

    private TipoFichero tipo;

    //ToDo
    private int idadjunto;
    private int idtipofichero;
    private String localizacion;
    private String nombreadjunto;

    public Adjunto(TipoFichero tipo, int idadjunto, int idtipofichero, String localizacion, String nombreadjunto) {
        this.tipo = tipo;
        this.idadjunto = idadjunto;
        this.idtipofichero = idtipofichero;
        this.localizacion = localizacion;
        this.nombreadjunto = nombreadjunto;
    }

    public TipoFichero getTipo() {
        return tipo;
    }

    public void setTipo(TipoFichero tipo) {
        this.tipo = tipo;
    }

    public int getIdadjunto() {
        return idadjunto;
    }

    public void setIdadjunto(int idadjunto) {
        this.idadjunto = idadjunto;
    }

    public int getIdtipofichero() {
        return idtipofichero;
    }

    public void setIdtipofichero(int idtipofichero) {
        this.idtipofichero = idtipofichero;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getNombreadjunto() {
        return nombreadjunto;
    }

    public void setNombreadjunto(String nombreadjunto) {
        this.nombreadjunto = nombreadjunto;
    }
}
