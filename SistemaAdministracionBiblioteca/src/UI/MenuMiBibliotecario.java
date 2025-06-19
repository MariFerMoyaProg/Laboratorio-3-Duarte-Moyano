package UI;

import Biblioteca.*;
import Excepcion.LibroNoDisponibleException;
import Interface.I_MostrableEnMenu;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuMiBibliotecario implements I_MostrableEnMenu {
    private final GestorLibros gestorLibros;
    private final GestorPrestamo gestorPrestamos;
    private final GestorReserva gestorReservas;
    private final Scanner scanner;

    public MenuMiBibliotecario(GestorLibros gl, GestorPrestamo gp, GestorReserva gr, Scanner scanner) {
        this.gestorLibros = gl;
        this.gestorPrestamos = gp;
        this.gestorReservas = gr;
        this.scanner = scanner;
    }

    @Override
    public void ejecutarMenu() {
        while (true) {
            System.out.println("\n--- Menú Bibliotecario ---");
            System.out.println("1. Listar libros");
            System.out.println("2. Realizar préstamo");
            System.out.println("3. Registrar devolución");
            System.out.println("4. Listar préstamos");
            System.out.println("5. Listar reservas");
            System.out.println("6. Ver mis datos");
            System.out.println("7. Volver");
            System.out.print("Seleccione opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    listarLibros();
                    break;
                case "2":
                    realizarPrestamo();
                    break;
                case "3":
                    registrarDevolucion();
                    break;
                case "4":
                    listarPrestamos();
                    break;
                case "5":
                    listarReservas();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }

    private void listarLibros() {
        System.out.println("\nLibros:");
        for (Libro libro : gestorLibros.getLibros()) {
            System.out.println(libro);
        }
    }

    private void realizarPrestamo() {
        try {
            System.out.print("Título del libro a prestar: ");
            String titulo = scanner.nextLine();
            Libro libro = gestorLibros.buscarLibroPorTitulo(titulo);
            if (!libro.estaDisponible()) {
                System.out.println("El libro no está disponible para préstamo.");
                return;
            }

            System.out.print("DNI del usuario que solicita el préstamo: ");
            String dni = scanner.nextLine();

            // Para simplificar se asume que el usuario es lector
            Usuario usuario = new GestorUsuarios().buscarUsuarioPorDni(dni);

            System.out.print("Fecha préstamo (YYYY-MM-DD): ");
            LocalDate fechaPrestamo = LocalDate.parse(scanner.nextLine());
            System.out.print("Fecha devolución (YYYY-MM-DD): ");
            LocalDate fechaDevolucion = LocalDate.parse(scanner.nextLine());

            Prestamo prestamo = new Prestamo(libro, usuario, fechaPrestamo, fechaDevolucion);
            gestorPrestamos.agregarPrestamo(prestamo);
            System.out.println("Préstamo registrado exitosamente.");

        } catch (LibroNoDisponibleException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void registrarDevolucion() {
        System.out.print("Título del libro a devolver: ");
        String titulo = scanner.nextLine();

        Prestamo aEliminar = null;
        for (Prestamo p : gestorPrestamos.getPrestamos()) {
            if (p.getLibro().getTitulo().equalsIgnoreCase(titulo)) {
                aEliminar = p;
                break;
            }
        }

        if (aEliminar != null) {
            gestorPrestamos.devolverLibro(aEliminar);
            System.out.println("Devolución registrada.");
        } else {
            System.out.println("No se encontró préstamo para ese libro.");
        }
    }

    private void listarPrestamos() {
        System.out.println("\nPréstamos actuales:");
        for (Prestamo p : gestorPrestamos.getPrestamos()) {
            System.out.println(p);
        }
    }

    private void listarReservas() {
        System.out.println("\nReservas actuales:");
        for (Reserva r : gestorReservas.getReservas()) {
            System.out.println(r);
        }
    }
}

