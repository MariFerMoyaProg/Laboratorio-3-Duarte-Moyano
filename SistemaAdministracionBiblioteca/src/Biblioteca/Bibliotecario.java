package Biblioteca;

public class Bibliotecario extends Usuario{

    ///  Constructor

    public Bibliotecario(String nombre, String dni) {
        super(nombre, dni);
    }

    /// Implementacion de método

    @Override
    public void ejecutarMenu() {
        System.out.println("Mostrando menu del Bibliotecario...");
    }

}
