package Biblioteca;

import Enum.TipoUsuario;

public class Bibliotecario extends Usuario{

    public Bibliotecario(String nombre, String dni, String direccion, String contacto, TipoUsuario tipo) {
        super(nombre, dni, direccion, contacto, tipo);
    }




    /// Implementacion de método

    @Override
    public void ejecutarMenu() {
        System.out.println("Mostrando menu del Bibliotecario...");
    }

}
