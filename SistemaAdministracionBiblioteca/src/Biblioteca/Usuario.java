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
    protected String contrasenia;


    public Usuario(String nombre, String dni, String direccion, String contacto, TipoUsuario tipo, String contrasenia) {

        this.nombre = nombre.toUpperCase();

        if (!dni.matches("\\d{7,8}")) {
            throw new IllegalArgumentException("El DNI debe contener 7 u 8 dígitos");}
        this.dni = dni;
        this.direccion = direccion;
        this.contacto = contacto;
        this.tipo = tipo;
        this.contrasenia = contrasenia;
    }



    public Usuario(String nombre, String dni) {
        if (!dni.matches("\\d{7,8}")) {
            throw new IllegalArgumentException("El DNI debe contener 7 u 8 dígitos");
        }
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = "No especificada";
        this.contacto = "No especificado";
        this.tipo = null; // o asignar un valor por defecto si querés
    }

    /// Constructor
    ///public Usuario(String nombre, String dni) {
       /// if (!dni.matches("\\d{7,8}")) {
          ///  throw new IllegalArgumentException("El DNI debe contener 7 u 8 dígitos");
        //}
        //this.nombre = nombre;
        //this.dni = dni;
        //this.direccion = "No especificada";
        //this.contacto = "No especificado";
        //this.tipo = null; // o asignar un valor por defecto si querés
    //}

    ///public Usuario(String nombre, String dni, String direccion, String contacto, TipoUsuario tipo) {
       // this.nombre = nombre;
        //this.dni = dni;
        //this.direccion = direccion;
        //this.contacto = contacto;
        //this.tipo = tipo;
    //}



    /// Getters
    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }
    public TipoUsuario getTipo() {
        return tipo;
    }

    public String getContrasenia() {
        return contrasenia;
    }


    public String getContacto() {
        return contacto;
    }

    public void setTipo (TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
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
