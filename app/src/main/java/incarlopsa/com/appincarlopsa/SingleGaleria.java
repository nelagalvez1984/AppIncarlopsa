package incarlopsa.com.appincarlopsa;

class SingleGaleria {
    private static final SingleGaleria ourInstance = new SingleGaleria();
    private Adjunto fotoAdjunta;

    //Constructor
    private SingleGaleria() {
        fotoAdjunta = null;
    }

    //Get Instance
    static SingleGaleria getInstance() {
        return ourInstance;
    }

    //Getter/Setter
    public Adjunto getFotoAdjunta() {
        return fotoAdjunta;
    }
    public void setFotoAdjunta(Adjunto fotoAdjunta) {
        this.fotoAdjunta = fotoAdjunta;
    }
}
