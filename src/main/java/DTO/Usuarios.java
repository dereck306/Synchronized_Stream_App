package DTO;

public class Usuarios {

    private int identificacion;

    private String usuario;

    private String contrasena;

    private String apellido;

    private String nombre;

    private String avatar;

    public Usuarios(int identificacion, String usuario, String contrasena, String apellido, String nombre, String avatar) {
        this.identificacion = identificacion;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.apellido = apellido;
        this.nombre = nombre;
        this.avatar = avatar;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "identificacion=" + identificacion +
                ", usuario='" + usuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
