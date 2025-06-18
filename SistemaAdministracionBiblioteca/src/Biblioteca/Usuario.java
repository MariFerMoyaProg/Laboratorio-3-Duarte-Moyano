package Biblioteca;

import Interface.I_MostrableEnMenu;
import Enum.TipoUsuario;

public abstract class Usuario implements I_MostrableEnMenu {

    /// Atributos
    protected String nombre;
    protected String dni;
    protected String direccion;
    protected String contacto;
    private TipoUsuario tipo;

    public Usuario(String nombre, String dni, String direccion, String contacto, TipoUsuario tipo) {
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.contacto = contacto;
        this.tipo = tipo;
    }

    /// Contructor
    public Usuario(String nombre, String dni) {

       if(!dni.matches("//d{7,8}")){
           throw new IllegalArgumentException("El DNI debe contener 7 u 8 digitos");
       }
        this.nombre = nombre;
        this.dni = dni;
        this.direccion=direccion;
        this.contacto= contacto;
    }



    /// Getters
    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getContacto() {
        return contacto;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }


    /// Método Abstracto
    public abstract void ejecutarMenu();


    /// toString
    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }


}
