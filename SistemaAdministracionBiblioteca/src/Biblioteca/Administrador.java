package Biblioteca;

public class Administrador extends Usuario {


    /// Constructor
    public Administrador(String nombre, String dni) {
        super(nombre, dni);
    }


    @Override
    public void ejecutarMenu() {
        System.out.println("Mostrando Menu de Administrador...");
    }
}
