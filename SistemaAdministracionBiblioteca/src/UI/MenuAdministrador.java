package UI;

import Biblioteca.Libro;
import Biblioteca.*;
import Enum.TipoUsuario;
import Excepcion.UsuarioNoEncontradoException;
import Interface.I_MostrableEnMenu;
import Enum.Genero;

import java.util.Scanner;

public class MenuAdministrador implements I_MostrableEnMenu {
    private GestorUsuarios gestorUsuarios;
    private GestorLibros gestorLibros;
    private GestorPrestamo gestorPrestamo;
    private GestorReserva gestorReserva;
    private Scanner scanner;

    public MenuAdministrador(GestorUsuarios gu, GestorLibros gl, GestorPrestamo gp, GestorReserva gr, Scanner scanner) {
        this.gestorUsuarios = gu;
        this.gestorLibros = gl;
        this.gestorPrestamo = gp;
        this.gestorReserva = gr;
        this.scanner = scanner;
    }

    @Override
    public void ejecutarMenu() {
        while (true) {
            System.out.println("\n--- Menú Administrador ---");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Registrar libro");
            System.out.println("4. Listar libros");
            System.out.println("5. Volver");
            System.out.print("Seleccione opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    registrarUsuario();
                    break;
                case "2":
                    listarUsuarios();
                    break;
                case "3":
                    registrarLibro();
                    break;
                case "4":
                    listarLibros();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }

    private void registrarUsuario() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Contacto: ");
        String contacto = scanner.nextLine();
        System.out.println("Tipo (ADMINISTRADOR, BIBLIOTECARIO, LECTOR): ");
        String tipoStr = scanner.nextLine();
        try {
            TipoUsuario tipo = TipoUsuario.valueOf(tipoStr.toUpperCase());
            Usuario usuario = new Usuario(nombre, dni, direccion, contacto, tipo) {
                @Override
                public void ejecutarMenu() {

                }
            };
            gestorUsuarios.agregarUsuario(usuario);
            System.out.println("Usuario registrado con éxito.");
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo de usuario inválido.");
        }
    }

    private void listarUsuarios() {
        System.out.println("\nUsuarios registrados:");
        for (Usuario u : gestorUsuarios.getUsuarios()) {
            System.out.println(u);
        }
    }

    private void registrarLibro() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Género (FICCION, NO_FICCION, CIENCIA, HISTORIA, FANTASIA, BIOGRAFIA, INFANTIL): ");
        String generoStr = scanner.nextLine();
        try {
            Genero genero = Genero.valueOf(generoStr.toUpperCase());
            Libro libro = new Libro(titulo, autor, genero);
            gestorLibros.agregarLibro(libro);
            System.out.println("Libro registrado con éxito.");
        } catch (IllegalArgumentException e) {
            System.out.println("Género inválido.");
        }
    }

    private void listarLibros() {
        System.out.println("\nLibros registrados:");
        for (Libro libro : gestorLibros.getLibros()) {
            System.out.println(libro);
        }
    }
}
