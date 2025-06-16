package UI;

import Biblioteca.Libro;
import Enum.Genero;
import Interface.I_Buscable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimuladorBusqueda {

    public static void main(String[] args) {
        List<I_Buscable> catalogo = new ArrayList<I_Buscable>();

        catalogo.add(new Libro("Cien años de soledad", "Gabriel García Márquez", Genero.NOVELA));
        catalogo.add(new Libro("El principito", "Antoine de Saint-Exupéry", Genero.FANTASIA));
        catalogo.add(new Libro("1984", "George Orwell", Genero.FICCION));
        catalogo.add(new Libro("Orgullo y prejuicio", "Jane Austen", Genero.ROMANCE));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un término de búsqueda: ");
        String termino = scanner.nextLine();

        boolean encontrado = false;
        for (I_Buscable item : catalogo) {
            if (item.coincideCon(termino)) {
                System.out.println(item);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron coincidencias.");
        }

        scanner.close();
    }
}

