package Modelo;

public class Libro {
    private String titulo;
    private String autor;
    private String genero;
    private int añoPublicacion;
    private double precio;

    public Libro() {
    }

    public Libro(String titulo, String autor, String genero, int añoPublicacion, double precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.añoPublicacion = añoPublicacion;
        this.precio = precio;
    }
    
    public Libro(String titulo, String autor, String genero, int añoPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.añoPublicacion = añoPublicacion;
    }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }

    public void setAutor(String autor) { this.autor = autor; }

    public String getGenero() { return genero; }

    public void setGenero(String genero) { this.genero = genero; }

    public int getAñoPublicacion() { return añoPublicacion; }

    public void setAñoPublicacion(int añoPublicacion) { this.añoPublicacion = añoPublicacion; }

    public double getPrecio() { return precio; }
    
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public String toString() {
        return "Libro{" + 
                "titulo=" + titulo + 
                ", autor=" + autor + 
                ", genero=" + genero + 
                ", añoPublicacion=" + añoPublicacion + 
                ", precio=" + precio + '}';
    }
}
