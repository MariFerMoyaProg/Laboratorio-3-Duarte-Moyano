
package UI;

import Biblioteca.*;
import Excepcion.LibroNoDisponibleException;
import Excepcion.LibroNoEncontradoExcepcion;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuLector {

    private final GestorLibros gestorLibros;
    private final GestorPrestamo gestorPrestamos;
    private final GestorReserva gestorReservas;
    private final Usuario lector;
    private final Scanner scanner;

    public MenuLector(GestorLibros gestorLibros, GestorPrestamo gestorPrestamos,
                      GestorReserva gestorReservas, Usuario lector, Scanner scanner) {
        this.gestorLibros = gestorLibros;
        this.gestorPrestamos = gestorPrestamos;
        this.gestorReservas = gestorReservas;
        this.lector = lector;
        this.scanner = scanner;
    }

    public void ejecutarMenu() {
        while (true) {
            System.out.println("\n--- Menú Lector ---");
            System.out.println("1. Retirar libro");
            System.out.println("2. Reservar libro para fecha futura");
            System.out.println("3. Volver");
            System.out.print("Seleccione opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    retirarLibro();
                    break;
                case "2":
                    reservarLibroFuturo();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }


    private void reservarLibroFuturo() {
        System.out.print("Ingrese título del libro: ");
        String titulo = scanner.nextLine();
        Libro libro = null;
        try {
            libro = gestorLibros.buscarLibroPorTitulo(titulo);
        } catch (LibroNoEncontradoExcepcion e) {
            throw new RuntimeException(e);
        }

        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }

        System.out.print("Ingrese fecha para reserva (YYYY-MM-DD): ");
        String fechaInput = scanner.nextLine();
        try {
            LocalDate fechaReserva = LocalDate.parse(fechaInput);

            if (gestorReservas.estaReservadoEnFecha(libro, fechaReserva)) {
                System.out.println("El libro ya está reservado en esa fecha.");
            } else {
                gestorReservas.agregarReserva(new Reserva(libro, lector, fechaReserva));
                System.out.println("Reserva realizada con éxito.");
            }
        } catch (Exception e) {
            System.out.println("Fecha inválida o error al realizar la reserva.");
        }
    }


    private void retirarLibro() {
        System.out.print("Ingrese título del libro: ");
        String titulo = scanner.nextLine();
        Libro libro = null;
        try {
            libro = gestorLibros.buscarLibroPorTitulo(titulo);
        } catch (LibroNoEncontradoExcepcion e) {
            throw new RuntimeException(e);
        }

        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }

        if (libro.estaDisponible()) {
            try {
                gestorPrestamos.registrarPrestamo(libro, lector);
                libro.setDisponible(false);
                System.out.println("Libro retirado con éxito.");
            } catch (LibroNoDisponibleException e) {
                System.out.println("El libro no está disponible: " + e.getMessage());
            }
        } else {
            System.out.println("El libro no está disponible. ¿Desea reservarlo? (s/n): ");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                reservarLibroFuturo();
            }
        }
    }
}