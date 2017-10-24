package incarlopsa.com.appincarlopsa;

public class DAOTipoFichero implements IDAO<TipoFichero> {

    //Propiedades
    private String nombreTabla = "tipofichero";
    private String nombreIdTabla = "idtipofichero";

    //ToDO
    //Esta clase tal vez no se acabe usando. En tal caso modificar la clase "ADJUNTO"

    @Override
    public Boolean create(TipoFichero elementoACrear) {
        //ToDO
        return null;
    }

    @Override
    public TipoFichero read() {
        //ToDO
        return null;
    }

    @Override
    public Boolean update(TipoFichero elementoConQueActualizar) {
        //ToDO
        return null;
    }

    @Override
    public Boolean delete(TipoFichero elementoABorrar) {
        //ToDO
        return null;
    }
}
