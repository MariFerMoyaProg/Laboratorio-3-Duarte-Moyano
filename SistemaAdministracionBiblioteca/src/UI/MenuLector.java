
package UI;

import Biblioteca.*;
import Excepcion.LibroNoDisponibleException;
import Interface.I_MostrableEnMenu;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuLector implements I_MostrableEnMenu {
    private final GestorLibros gestorLibros;
    private final GestorPrestamo gestorPrestamos;
    private final GestorReserva gestorReservas;
    private final Usuario usuario;
    private final Scanner scanner;

    public MenuLector(GestorLibros gl, GestorPrestamo gp, GestorReserva gr, Usuario usuario, Scanner scanner) {
        this.gestorLibros = gl;
        this.gestorPrestamos = gp;
        this.gestorReservas = gr;
        this.usuario = usuario;
        this.scanner = scanner;
    }

    @Override
    public void ejecutarMenu() {
        while (true) {
            System.out.println("\n--- Menú Lector ---");
            System.out.println("1. Buscar libros");
            System.out.println("2. Ver mis préstamos");
            System.out.println("3. Reservar libro");
            System.out.println("4. Volver");
            System.out.print("Seleccione opción: ");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    buscarLibros();
                    break;
                case "2":
                    verMisPrestamos();
                    break;
                case "3":
                    reservarLibro();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }

    private void buscarLibros() {
        System.out.print("Ingrese criterio de búsqueda: ");
        String criterio = scanner.nextLine();

        List<Libro> encontrados = gestorLibros.buscarLibros(criterio);
        if (encontrados.isEmpty()) {
            System.out.println("No se encontraron libros que coincidan.");
        } else {
            System.out.println("Libros encontrados:");
            for (Libro libro : encontrados) {
                System.out.println(libro);
            }
        }
    }

    private void verMisPrestamos() {
        System.out.println("Tus préstamos:");
        List<Prestamo> prestamos = gestorPrestamos.prestamosPorUsuario(usuario);
        if (prestamos.isEmpty()) {
            System.out.println("No tienes préstamos activos.");
        } else {
            for (Prestamo p : prestamos) {
                System.out.println(p);
            }
        }
    }

    private void reservarLibro() {
        try {
            System.out.print("Título del libro para reservar: ");
            String titulo = scanner.nextLine();
            Libro libro = gestorLibros.buscarLibroPorTitulo(titulo);
            if (!libro.estaDisponible()) {
                Reserva reserva = new Reserva(libro, usuario, LocalDate.now());
                gestorReservas.agregarReserva(reserva);
                System.out.println("Reserva realizada exitosamente.");
            } else {
                System.out.println("El libro está disponible, no es necesario reservar.");
            }
        } catch (LibroNoDisponibleException e) {
            System.out.println(e.getMessage());
        }
    }
}
