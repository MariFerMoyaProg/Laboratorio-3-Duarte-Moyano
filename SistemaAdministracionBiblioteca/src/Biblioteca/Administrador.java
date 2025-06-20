package Biblioteca;

import Enum.TipoUsuario;

public class Administrador extends Usuario {

    /// Constructor
   // public Administrador(String nombre, String dni,String ) {
     //   super(nombre, dni);
       // this.setTipo(TipoUsuario.ADMINISTRADOR);
    //}

    public Administrador(String nombre, String dni, String direccion, String contacto, String contrasenia) {
        super(nombre, dni, direccion, contacto, TipoUsuario.ADMINISTRADOR, contrasenia);
    }


    // public Administrador(String nombre, String dni, String direccion, String contacto) {
     //   super(nombre, dni, direccion, contacto, TipoUsuario.ADMINISTRADOR);
    //}

    /// Implementacion de metodo
    @Override
    public void ejecutarMenu() {

        System.out.println("Mostrando Menu de Administrador...");
    }
}
