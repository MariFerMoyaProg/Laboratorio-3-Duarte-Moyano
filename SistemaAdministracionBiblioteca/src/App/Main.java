package App;

import Biblioteca.Lector;
import Biblioteca.Libro;

public class Main {
    public static <Persona> void main(String[] args) {
        /// Pequeñas pruebas para ir comprobando funcionamiento

        Libro libro1= new Libro("El Principito", "Saint - Exupéry", "Novela", true);
        System.out.println(libro1);

        Lector lector1=new Lector("Juan Perez", "12345656");
        System.out.println("Bienvenido, " + lector1.getNombre());
        lector1.mostrarOpciones();
    }
}
