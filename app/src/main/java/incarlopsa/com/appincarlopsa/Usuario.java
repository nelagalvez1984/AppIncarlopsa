package incarlopsa.com.appincarlopsa;

import android.graphics.Bitmap;

import java.sql.Blob;

public class Usuario extends DataBaseItem{

    //Propiedades
    private String nombre = null;
    private String apellidos = null;
    private String dni = null;
    private String tipoEmpleado = null;
    private Foto foto = null;
    private String username = null;

    //Constructores
    public Usuario(Integer idUsuario, String nombre, String apellidos, String dni, String tipoEmpleado, Foto foto) {
        this.id = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.tipoEmpleado = tipoEmpleado;
        this.foto = foto;
    }

    public Usuario(Integer idUsuario, String nombre, String apellidos,
                   String dni, String tipoEmpleado, Foto foto, String username) {
        this.id = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.tipoEmpleado = tipoEmpleado;
        this.foto = foto;
        this.username = username;
    }

    public Usuario(){ }

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

    public void setFotoBytes(Foto foto) {
        this.foto = foto;
    }

    public byte[] getFotoBytes() {
        return foto.getFotoBytes();
    }

    public Bitmap getFotoBMP() {
        return foto.getFotoBMP();
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    //Equals&Hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Usuario usuario = (Usuario) o;

        if (!nombre.equals(usuario.nombre)) return false;
        if (!apellidos.equals(usuario.apellidos)) return false;
        if (!dni.equals(usuario.dni)) return false;
        if (!tipoEmpleado.equals(usuario.tipoEmpleado)) return false;
        if (!foto.equals(usuario.foto)) return false;
        return username.equals(usuario.username);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + nombre.hashCode();
        result = 31 * result + apellidos.hashCode();
        result = 31 * result + dni.hashCode();
        result = 31 * result + tipoEmpleado.hashCode();
        result = 31 * result + foto.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }
}