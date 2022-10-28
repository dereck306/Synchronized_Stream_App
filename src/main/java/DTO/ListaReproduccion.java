package DTO;

public class ListaReproduccion {


    private String nombre;
    private String tema;
    private String fecha;
    private int Usuario_identificacion ;

    public ListaReproduccion(String nombre, String tema, String fecha, int usuario_identificacion) {
        this.nombre = nombre;
        this.tema = tema;
        this.fecha = fecha;
        Usuario_identificacion = usuario_identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getUsuario_identificacion() {
        return Usuario_identificacion;
    }

    public void setUsuario_identificacion(int usuario_identificacion) {
        Usuario_identificacion = usuario_identificacion;
    }

    @Override
    public String toString() {
        return "ListaReproduccion{" +
                "nombre='" + nombre + '\'' +
                ", tema='" + tema + '\'' +
                ", fecha='" + fecha + '\'' +
                ", Usuario_identificacion=" + Usuario_identificacion +
                '}';
    }
}
