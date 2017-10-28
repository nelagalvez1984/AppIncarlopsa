package incarlopsa.com.appincarlopsa;

import android.graphics.Bitmap;

import java.sql.Blob;

public class Usuario extends DataBaseItem{

    //Propiedades
    private String nombre;
    private String apellidos;
    private String dni;
    private String tipoEmpleado;
    private Foto foto;

    //Constructores
    public Usuario(Integer idUsuario, String nombre, String apellidos, String dni, String tipoEmpleado, Foto foto) {
        this.id = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.tipoEmpleado = tipoEmpleado;
        this.foto = foto;
    }

    public Usuario(){}

    //Getter/Setter
    public Integer getIdUsuario() {
        return getId();
    }

    public void setIdUsuario(Integer idUsuario) {
        setId(idUsuario);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public Blob getFotoBlob() {
        return foto.getFotoBlob();
    }

    public Bitmap getFotoBMP() {
        return foto.getFotoBMP();
    }

    public void setFotoBlob(Blob foto) {
        this.foto.setFotoBlob(foto);
    }

    //Equals&Hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Usuario usuario = (Usuario) o;

        if (!nombre.equals(usuario.nombre)) return false;
        if (apellidos != null ? !apellidos.equals(usuario.apellidos) : usuario.apellidos != null)
            return false;
        if (!dni.equals(usuario.dni)) return false;
        if (!tipoEmpleado.equals(usuario.tipoEmpleado)) return false;
        return foto != null ? foto.equals(usuario.foto) : usuario.foto == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + nombre.hashCode();
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + dni.hashCode();
        result = 31 * result + tipoEmpleado.hashCode();
        result = 31 * result + (foto != null ? foto.hashCode() : 0);
        return result;
    }

}