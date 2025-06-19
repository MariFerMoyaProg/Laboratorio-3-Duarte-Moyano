package Biblioteca;

import Enum.TipoUsuario;

public class Lector extends Usuario {

    /// Constructor
    public Lector(String nombre, String dni) {
        super(nombre, dni);
        this.setTipo(TipoUsuario.LECTOR);
    }

    /// Implementacion del método mostrarOpciones()

    @Override
    public void ejecutarMenu() {
        System.out.println("Mostrando menú del Lector...");

    }
}
