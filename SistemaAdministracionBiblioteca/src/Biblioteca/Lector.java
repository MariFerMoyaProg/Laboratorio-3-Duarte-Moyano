package Biblioteca;

public class Lector extends Usuario {

    /// Constructor
    public Lector(String nombre, String dni) {
        super(nombre, dni);
    }

    /// Implementacion del método mostrarOpciones()

    @Override
    public void mostrarOpciones() {
        System.out.println("1 - Buscar Libro");
        System.out.println("2 - Solicitar préstamo");
        System.out.println("3 - Devolver Libro");
        System.out.println("4 - Reservar Libro");
    }
}
