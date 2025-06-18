package UI;

import Biblioteca.*;
import Enum.TipoUsuario;
import Excepcion.UsuarioNoEncontradoException;
import Interface.I_MostrableEnMenu;
import Persistencia.ExportadorJson;
import Persistencia.ImportadorJson;
import UI.*;

import java.io.IOException;
import java.util.Scanner;

import static Enum.TipoUsuario.*;

public class MenuPrincipal implements I_MostrableEnMenu {
    private GestorUsuarios gestorUsuarios;
    private GestorLibros gestorLibros;
    private GestorPrestamo gestorPrestamos;
    private GestorReserva gestorReservas;
    private Scanner scanner;

    public MenuPrincipal() {
        gestorUsuarios = new GestorUsuarios();
        gestorLibros = new GestorLibros();
        gestorPrestamos = new GestorPrestamo();
        gestorReservas = new GestorReserva();
        scanner = new Scanner(System.in);

        cargarDatos();
    }

    private void cargarDatos() {
        try {
            gestorUsuarios.getUsuarios().addAll(ImportadorJson.importarUsuarios("usuarios.json"));
            gestorLibros.getLibros().addAll(ImportadorJson.importarLibros("libros.json"));
            gestorPrestamos.getPrestamos().addAll(ImportadorJson.importarPrestamos("prestamos.json", gestorLibros, gestorUsuarios));
            gestorReservas.getReservas().addAll(ImportadorJson.importarReservas("reservas.json", gestorLibros, gestorUsuarios));
        } catch (IOException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
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
                    switch (usuario.getTipo()) {
                        case ADMINISTRADOR:
                            new MenuAdministrador(gestorUsuarios, gestorLibros, gestorPrestamos, gestorReservas, scanner).ejecutarMenu();
                            break;
                        case BIBLIOTECARIO:
                            new UI.MenuMiBibliotecario (gestorLibros, gestorPrestamos, gestorReservas, scanner).ejecutarMenu();

                            break;
                        case LECTOR:
                            new ui.MenuLector(gestorLibros, gestorPrestamos, gestorReservas, usuario, scanner).ejecutarMenu();
                            break;
                        default:
                            System.out.println("Tipo de usuario no reconocido.");
                            break;
                    }
                }
            } else if (opcion.equals("2")) {
                guardarDatos();
                System.out.println("Saliendo...");
                break;
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }
}
