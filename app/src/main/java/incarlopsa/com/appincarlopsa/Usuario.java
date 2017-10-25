package incarlopsa.com.appincarlopsa;

public class Usuario {
    Integer idUsuario;
    String nombre;
    String apellidos;
    String dni;
    String tipoEmpleado;
    Foto foto;
    String ok;

    public Usuario(Integer idUsuario, String nombre, String apellidos, String dni, String tipoEmpleado, Foto foto) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.tipoEmpleado = tipoEmpleado;
        this.foto = foto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (idUsuario != null ? !idUsuario.equals(usuario.idUsuario) : usuario.idUsuario != null)
            return false;
        if (nombre != null ? !nombre.equals(usuario.nombre) : usuario.nombre != null) return false;
        if (apellidos != null ? !apellidos.equals(usuario.apellidos) : usuario.apellidos != null)
            return false;
        if (dni != null ? !dni.equals(usuario.dni) : usuario.dni != null) return false;
        if (tipoEmpleado != null ? !tipoEmpleado.equals(usuario.tipoEmpleado) : usuario.tipoEmpleado != null)
            return false;
        return foto != null ? foto.equals(usuario.foto) : usuario.foto == null;

    }

    @Override
    public int hashCode() {
        int result = idUsuario != null ? idUsuario.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (dni != null ? dni.hashCode() : 0);
        result = 31 * result + (tipoEmpleado != null ? tipoEmpleado.hashCode() : 0);
        result = 31 * result + (foto != null ? foto.hashCode() : 0);
        return result;
    }

    //ToDo

    //Contiene una imagen de tipo Foto
}