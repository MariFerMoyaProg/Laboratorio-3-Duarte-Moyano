package Biblioteca;

import Enum.TipoUsuario;

public class Administrador extends Usuario {

    /// Constructor
    public Administrador(String nombre, String dni) {
        super(nombre, dni);
        this.setTipo(TipoUsuario.ADMINISTRADOR);
    }


    @Override
    public void ejecutarMenu() {
        System.out.println("Mostrando Menu de Administrador...");
    }
}
