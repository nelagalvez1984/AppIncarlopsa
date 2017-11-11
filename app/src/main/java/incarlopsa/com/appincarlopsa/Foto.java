package incarlopsa.com.appincarlopsa;

import android.graphics.BitmapFactory;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class Foto implements Parcelable{

    //Para dudas, consultar: https://stackoverflow.com/questions/13854742/byte-array-of-image-into-imageview
    //En caso de duda consultar: https://stackoverflow.com/questions/10513976/how-to-convert-image-into-byte-array-and-byte-array-to-base64-string-in-android
    //Para trabajar con Blobs, mirar: https://docs.oracle.com/javase/7/docs/api/java/sql/Blob.html#setBytes(long,%20byte[])

    //Propiedades
    private byte[] foto = null;

    //Constructores
    public Foto(byte[] foto){
        this.foto = foto;
    }
    public Foto(Bitmap fotoBMP){
        setFotoBMP(fotoBMP);
    }
    public Foto(){ setFotoEstandar(); }

    //Getter / Setter
    public Bitmap getFotoBMP(){
        Bitmap bmp = BitmapFactory.decodeByteArray(foto, 0, (int)foto.length);
        return bmp;
    }

    public byte[] getFotoBytes(){return foto;}

    public void setFotoEstandar(){
        File resume = new File("@drawable\foto_usuario_estandar,png");
        byte[] fileContent = new byte[(int) resume.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(resume);

            fileInputStream.read(fileContent);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFotoBytes(byte[] foto){
        this.foto = foto;
    }

    public void setFotoBMP(Bitmap fotoBMP){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        fotoBMP.compress(Bitmap.CompressFormat.JPEG, 100 , baos);
        setFotoBytes(baos.toByteArray());
    }

    private Foto(Parcel in){
        readFromParcel(in);
    }

    //Metodos parcelables
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeByteArray(foto);
    }

    public static final Parcelable.Creator<Foto> CREATOR
            = new Parcelable.Creator<Foto>() {
        public Foto createFromParcel(Parcel in) {
            return new Foto(in);
        }

        public Foto[] newArray(int size) {
            return new Foto[size];
        }
    };

    private void readFromParcel(Parcel in){
        foto = in.createByteArray();
    }

}
