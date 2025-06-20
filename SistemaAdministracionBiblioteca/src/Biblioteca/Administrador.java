package Biblioteca;

import Enum.TipoUsuario;

public class Administrador extends Usuario {

    public Administrador(String nombre, String dni, String direccion, String contacto, String contrasenia) {
        super(nombre, dni, direccion, contacto, TipoUsuario.ADMINISTRADOR, contrasenia);
    }


    @Override
    public void ejecutarMenu() {

        System.out.println("Mostrando Menu de Administrador...");
    }
}
