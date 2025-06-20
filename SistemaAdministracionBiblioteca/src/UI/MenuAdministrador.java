package UI;

import Biblioteca.Libro;
import Biblioteca.*;
import Enum.TipoUsuario;
import Interface.I_MostrableEnMenu;
import Enum.Genero;
import Persistencia.ExportadorJson;

import java.io.IOException;
import java.util.Scanner;

public class MenuAdministrador implements I_MostrableEnMenu {
    private final GestorUsuarios gestorUsuarios;
    private final GestorLibros gestorLibros;
    private final GestorPrestamo gestorPrestamo;
    private final GestorReserva gestorReserva;
    private final Scanner scanner;

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
            guardarDatos1();
        }
    }

    private void registrarUsuario() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("DNI (7 u 8 dígitos): ");
        String dni = scanner.nextLine();
        if (!dni.matches("\\d{7,8}")) {
            System.out.println("Error: El DNI debe contener 7 u 8 dígitos numéricos.");
            return;
        }

        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        System.out.print("Contacto: ");
        String contacto = scanner.nextLine();

        System.out.print("Tipo de usuario (ADMINISTRADOR, BIBLIOTECARIO, LECTOR): ");
        String tipoStr = scanner.nextLine().toUpperCase();

        System.out.print("Contrasenia: ");
        String contrasenia = scanner.nextLine();

        Usuario usuario = null;

        try {
            TipoUsuario tipo = TipoUsuario.valueOf(tipoStr);

            switch (tipo) {
                case ADMINISTRADOR:
                    usuario = new Administrador(nombre, dni,direccion,contacto,contrasenia);
                    break;
                case BIBLIOTECARIO:
                    usuario = new Bibliotecario(nombre, dni, direccion, contacto, contrasenia);
                    break;
                case LECTOR:
                    usuario = new Lector(nombre, dni,direccion,contacto,contrasenia);
                    break;
                default:
                    System.out.println("Tipo de usuario inválido.");
                    return;
            }

            if (usuario != null) {
                gestorUsuarios.agregarUsuario(usuario);
                System.out.println("Usuario registrado con éxito.");
               /// guardarDatos1();
            } else {
                System.out.println("No se pudo crear el usuario.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Tipo de usuario inválido.");
        } catch (Exception ex) {
            System.out.println("Error al registrar usuario: " + ex.getMessage());
        }
    }

    private void listarUsuarios() {
        System.out.println("\nUsuarios registrados:");
        for (Usuario u : gestorUsuarios.getUsuarios()) {
            System.out.println("Nombre: " + u.getNombre() + ", DNI: " + u.getDni() + ", Tipo: " + u.getTipo().getDescripcion());
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
            ///guardarDatos1();
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

private void guardarDatos1() {
    try {
        ExportadorJson.exportarUsuarios(gestorUsuarios.getUsuarios(), "usuarios.json");
        ExportadorJson.exportarLibros(gestorLibros.getLibros(), "libros.json");
        ExportadorJson.exportarPrestamos(gestorPrestamo.getPrestamos(), "prestamos.json");
        ExportadorJson.exportarReservas(gestorReserva.getReservas(), "reservas.json");
    } catch (IOException e) {
        System.out.println("Error al guardar datos: " + e.getMessage());
    }
}}