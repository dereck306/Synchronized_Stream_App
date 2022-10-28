package DTO;

public class Videos {
    /**
     * declaracion de las variables
     */
    private int id;
    private String nombre;
    private String genero;
    private String fecha;
    private String descripcion;
    private int calificacion;
    private String path;

    public Videos() {
    }

    //Este metodo Crea el modelo Videos con sus variables


    /** constructor de los videos
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @param id recibe un parametro de tipo int
     * @param nombre recibe un parametro de tipo String
     * @param genero recibe un parametro de tipo String
     * @param fecha recibe un parametro de tipo String
     * @param descripcion recibe un parametro de tipo String
     * @param calificacion recibe un parametro de tipo String
     * @param path recibe un parametro de tipo String
     */

    public Videos(int id, String nombre, String genero, String fecha, String descripcion, int calificacion, String path) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.calificacion = calificacion;
        this.path = path;
    }

    /** gets y sets
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @return
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /** delcararion del to String de videos 
     * @author Dereck Rojas, Emilio Valverde, Karen Porras
     * @return
     */
    @Override
    public String toString() {
        return "Videos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", genero='" + genero + '\'' +
                ", fecha='" + fecha + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", calificacion=" + calificacion +
                ", path='" + path + '\'' +
                '}';
    }
}