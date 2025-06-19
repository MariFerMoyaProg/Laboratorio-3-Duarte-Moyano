package UI;

import Biblioteca.*;
import Enum.TipoUsuario;
import Excepcion.UsuarioNoEncontradoException;
import Interface.I_MostrableEnMenu;
import Persistencia.ExportadorJson;
import Persistencia.ImportadorJson;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;



public class MenuPrincipal implements I_MostrableEnMenu {
    private final GestorUsuarios gestorUsuarios;
    private final GestorLibros gestorLibros;
    private final GestorPrestamo gestorPrestamos;
    private final GestorReserva gestorReservas;
    private final Scanner scanner;

    public MenuPrincipal() {
        gestorUsuarios = new GestorUsuarios();
        gestorLibros = new GestorLibros();
        gestorPrestamos = new GestorPrestamo();
        gestorReservas = new GestorReserva();
        scanner = new Scanner(System.in);

        cargarDatos();
        /// crea administrador para iniciar sistema
        crearAdministradorPorDefecto();

    }


    private void cargarDatos() {


        try {
            List<Usuario> usuariosDesdeJson = ImportadorJson.importarUsuarios("usuarios.json");
            gestorUsuarios.getUsuarios().clear();///limpia si ya habia datos
            gestorUsuarios.getUsuarios().addAll(ImportadorJson.importarUsuarios("usuarios.json"));
            List<Libro> librosDesdeJson = ImportadorJson.importarLibros("libros.json");
            gestorLibros.getLibros().clear();
            gestorLibros.getLibros().addAll(ImportadorJson.importarLibros("libros.json"));
            gestorPrestamos.getPrestamos().addAll(ImportadorJson.importarPrestamos("prestamos.json", gestorLibros, gestorUsuarios));
            gestorReservas.getReservas().addAll(ImportadorJson.importarReservas("reservas.json", gestorLibros, gestorUsuarios));
        } catch (IOException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }

    /// Crea un administrador por defecto para poder iniciar funcionamiento de programa
    private void crearAdministradorPorDefecto() {
        // Verifica si ya existe un administrador con ese DNI
        boolean existeAdmin = gestorUsuarios.getUsuarios().stream()
                .anyMatch(u -> u instanceof Administrador && u.getDni().equals("12345678"));

        if (!existeAdmin) {
            Administrador admin = new Administrador("Admin", "12345678");
            gestorUsuarios.agregarUsuario(admin);
            System.out.println("Administrador por defecto creado (DNI: 12345678).");
        }
    }


    private void guardarDatos() {
        try {
            ExportadorJson.exportarUsuarios(gestorUsuarios.getUsuarios(), "usuarios.json");
            ExportadorJson.exportarLibros(gestorLibros.getLibros(), "libros.json");
            ExportadorJson.exportarPrestamos(gestorPrestamos.getPrestamos(), "prestamos.json");
            ExportadorJson.exportarReservas(gestorReservas.getReservas(), "reservas.json");
        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }

    private Usuario login() {
        System.out.println("Ingrese DNI:");
        String dni = scanner.nextLine();
        try {
            Usuario usuario = gestorUsuarios.buscarUsuarioPorDni(dni);
            if (usuario == null) {
                System.out.println("Usuario no encontrado.");
                return null;
            }
            System.out.println("Bienvenido, " + usuario.getNombre());
            return usuario;
        } catch (UsuarioNoEncontradoException e) {
            System.out.println("Usuario no encontrado.");
            return null;
        }
    }


    @Override
    public void ejecutarMenu() {
        while (true) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Salir");
            System.out.print("Seleccione opción: ");
            String opcion = scanner.nextLine();

            if (opcion.equals("1")) {
                Usuario usuario = login();
                if (usuario != null) {
                    TipoUsuario tipo = usuario.getTipo();
                    if (tipo != null) {
                        switch (tipo) {
                            case ADMINISTRADOR:
                                new MenuAdministrador(gestorUsuarios, gestorLibros, gestorPrestamos, gestorReservas, scanner).ejecutarMenu();
                                break;
                            case BIBLIOTECARIO:
                                new UI.MenuMiBibliotecario(gestorLibros, gestorPrestamos, gestorReservas, scanner).ejecutarMenu();
                                break;
                            case LECTOR:
                                new UI.MenuLector(gestorLibros, gestorPrestamos, gestorReservas, usuario, scanner).ejecutarMenu();
                                break;
                            default:
                                System.out.println("Tipo de usuario no reconocido.");
                                break;
                        }
                    } else {
                        System.out.println("El tipo de usuario es nulo.");
                    }
                } else {
                    System.out.println("Inicio de sesión fallido. Verifique sus credenciales.");
                }
            } else if (opcion.equals("2")) {
                System.out.println("Saliendo...");
                break;  // Salir del while y terminar el método
            } else {
                System.out.println("Opción inválida. Por favor, intente nuevamente.");
            }
        }
    }

}

