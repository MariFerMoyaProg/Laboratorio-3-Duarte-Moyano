package Biblioteca;

import Enum.TipoUsuario;


public class Bibliotecario extends Usuario {

    public Bibliotecario(String nombre, String dni, String direccion, String contacto, String contrasenia) {
        super(nombre, dni, direccion, contacto, TipoUsuario.BIBLIOTECARIO, contrasenia);
    }

    @Override
    public void ejecutarMenu() {
        System.out.println("Mostrando menú del Bibliotecario...");
    }
}




