package incarlopsa.com.appincarlopsa;

import android.graphics.BitmapFactory;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

public class Foto {

    //Para dudas, consultar: https://stackoverflow.com/questions/13854742/byte-array-of-image-into-imageview
    //En caso de duda consultar: https://stackoverflow.com/questions/10513976/how-to-convert-image-into-byte-array-and-byte-array-to-base64-string-in-android

    //Propiedades
    private byte[] foto;

    //Constructores
    public Foto(byte[] foto){
        this.foto = foto;
    }

    public Foto(Bitmap fotoBMP){
        setFotoBMP(fotoBMP);
    }

    //Getter / Setter
    public byte[] getFotoBytes(){
        return foto;
    }

    public void setFotoBytes(byte[] foto){
        this.foto = foto;
    }

    public Bitmap getFotoBMP(){
        Bitmap bmp = BitmapFactory.decodeByteArray(foto, 0, foto.length);
        return bmp;
    }

    public void setFotoBMP(Bitmap fotoBMP){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        fotoBMP.compress(Bitmap.CompressFormat.JPEG, 100 , baos);
        foto = baos.toByteArray();
    }
}
