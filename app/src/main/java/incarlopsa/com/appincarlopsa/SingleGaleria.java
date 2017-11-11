package incarlopsa.com.appincarlopsa;

class SingleGaleria {
    private static final SingleGaleria ourInstance = new SingleGaleria();
    private Adjunto fotoAdjunta;

    static SingleGaleria getInstance() {
        return ourInstance;
    }

    private SingleGaleria() {
        fotoAdjunta = null;
    }

    public Adjunto getFotoAdjunta() {
        return fotoAdjunta;
    }

    public void setFotoAdjunta(Adjunto fotoAdjunta) {
        this.fotoAdjunta = fotoAdjunta;
    }
}
