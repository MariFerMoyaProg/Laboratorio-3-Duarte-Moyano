package Biblioteca;

public abstract class Usuario {

    /// Atributos
    protected String nombre;
    protected String dni;

    /// Contructor
    public Usuario(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    /// Getters
    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    /// Método Abstracto
    public abstract void mostrarOpciones();

    /// toString
    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
}
