package Biblioteca;

public class Libro {

    /// Establecimos los atributos de la clase

    private  String titulo;
    private  String autor;
    private  String genero;
    private  boolean disponible;

    /// Constructor
    public Libro(String titulo, String autor, String genero, boolean disponible) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.disponible = true;
    }

    /// Geterrs
    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    /// Setters
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /// Metodo toString
    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", genero='" + genero + '\'' +
                ", disponible=" + disponible +
                '}';
    }
}
