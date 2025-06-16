package Biblioteca;

public class Lector extends Usuario {

    /// Constructor
    public Lector(String nombre, String dni) {
        super(nombre, dni);
    }

    /// Implementacion del método mostrarOpciones()

    @Override
    public void ejecutarMenu() {
        System.out.println("Mostrando menú del Lector...");

    }
}
