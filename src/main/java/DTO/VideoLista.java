package DTO;

public class VideoLista {

    private String nombre;
    private String Path;

    public VideoLista(String nombre, String path) {
        this.nombre = nombre;
        Path = path;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    @Override
    public String toString() {
        return "VideoLista{" +
                "nombre='" + nombre + '\'' +
                ", Path='" + Path + '\'' +
                '}';
    }
}
