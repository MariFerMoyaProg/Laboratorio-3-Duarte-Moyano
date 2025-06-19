package Biblioteca;

import Enum.TipoUsuario;


public class Bibliotecario extends Usuario {

    public Bibliotecario(String nombre, String dni, String direccion, String contacto, TipoUsuario tipo) {
        super(nombre, dni, direccion, contacto, tipo);
        this.setTipo(TipoUsuario.BIBLIOTECARIO);
    }

    @Override
    public void ejecutarMenu() {
        System.out.println("Mostrando menú del Bibliotecario...");
    }
}




