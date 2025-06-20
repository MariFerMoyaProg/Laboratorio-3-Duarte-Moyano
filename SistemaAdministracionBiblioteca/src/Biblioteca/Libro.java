package Biblioteca;

import Enum.Genero;
import Interface.I_Buscable;
import Interface.Prestamoable;

public  class Libro implements I_Buscable, Prestamoable {
    private final String titulo;
    private final String autor;
    private final Genero genero;
    private  boolean disponible= true;

    public Libro(String titulo, String autor, Genero genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.disponible = true;
    }

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
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public boolean coincide(String criterio) {
        return titulo.toLowerCase().contains(criterio.toLowerCase()) ||
                autor.toLowerCase().contains(criterio.toLowerCase()) ||
                genero.name().equalsIgnoreCase(criterio);
    }

    public void prestar(){
        if(!disponible){
            System.out.println("El libro ya está prestado");
        } else {
            disponible=false;
            System.out.println("Libro prestado con éxito.");
        }
    }

    @Override
    public void devolver() {
        if (disponible) {
            System.out.println("El libro ya estaba disponible.");
        } else {
            disponible = true;
            System.out.println("Libro devuelto con éxito.");
        }
    }



    @Override
    public String toString() {
        return "[" + genero + "] " + titulo + " - " + autor + (disponible ? " (Disponible)" : " (Prestado)");
    }
}

