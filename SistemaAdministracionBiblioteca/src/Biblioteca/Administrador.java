package Biblioteca;

public class Administrador extends Usuario {


    /// Cobnstructor
    public Administrador(String nombre, String dni) {
        super(nombre, dni);
    }

    /// Implementacion de metodo
    @Override
    public void ejecutarMenu() {
        System.out.println("Mostrando Menu de Administrador...");
    }
}
