package Biblioteca;

import Interface.I_Buscable;

import Interface.Prestamoable;
import org.omg.PortableServer.IMPLICIT_ACTIVATION_POLICY_ID;

import Enum.Genero;



public class Libro implements I_Buscable, Prestamoable {

    /// Establecimos los atributos de la clase

    private  String titulo;
    private  String autor;
    private  Genero genero;
    private  boolean disponible;

    /// Constructor
    public Libro(String titulo, String autor, Genero genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.disponible = true;
    }

    /// Geterrs
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public Genero getGenero() {
        return genero;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    /// Setters

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    ///  metodos especificos

    public void prestar(){
        if(!disponible){/// en el caso de que nuestro libro no este dispoonible envia mensaje avisando
            System.out.println("El libro ya está prestado");
        }else{
            disponible=false; /// si está disponible modifica el estado y lo asigna a prestado
            System.out.println("Libro prestado con éxito.");
        }
    }

    public void devolver(){
        if(disponible){
            System.out.println("El libro ya estaba disponible");
        }else{
            disponible= true; /// cambia el estado para futuros prestamos
            System.out.println("Libro devuelto con éxito.");
        }
    }

    /// Implementación método Buscable
    public boolean coincideCon(String termino){
        return titulo.toLowerCase().contains(termino) || autor.toLowerCase().contains(termino) || genero.name().toLowerCase().contains(termino);
    }

    /// Metodo toString
    @Override
    public String toString() {
        return "\"" + titulo + "\" por " + autor + " [" + genero + "] - " +
                (disponible ? "Disponible" : "Prestado");
    }
}
