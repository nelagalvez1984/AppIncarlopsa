package incarlopsa.com.appincarlopsa;

import android.graphics.BitmapFactory;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class Foto {

    //Para dudas, consultar: https://stackoverflow.com/questions/13854742/byte-array-of-image-into-imageview
    //En caso de duda consultar: https://stackoverflow.com/questions/10513976/how-to-convert-image-into-byte-array-and-byte-array-to-base64-string-in-android
    //Para trabajar con Blobs, mirar: https://docs.oracle.com/javase/7/docs/api/java/sql/Blob.html#setBytes(long,%20byte[])

    //Propiedades
    private Blob foto;

    //Constructores
    public Foto(Blob foto){
        this.foto = foto;
    }

    public Foto(Bitmap fotoBMP){
        setFotoBMP(fotoBMP);
    }

    //Getter / Setter
    public Blob getFotoBlob() {return foto;}

    public byte[] getFotoBytes(){
        byte[] retorno = null;
        try {
            retorno = foto.getBytes(1,(int)foto.length());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return retorno;
        }
    }

    public Bitmap getFotoBMP(){
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeByteArray(getFotoBytes(), 0, (int)foto.length());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bmp;
    }


    public void setFotoBytes(byte[] foto){
        try {
            this.foto.setBytes(1L, foto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setFotoBMP(Bitmap fotoBMP){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        fotoBMP.compress(Bitmap.CompressFormat.JPEG, 100 , baos);
        setFotoBytes(baos.toByteArray());
    }

    public void setFotoBlob(Blob foto) {this.foto = foto;}
}
