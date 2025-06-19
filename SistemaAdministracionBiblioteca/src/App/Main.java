package App;

import Biblioteca.*;
import UI.MenuPrincipal;
import Inicio.DatosDePrueba;

public class Main {
    public static void main(String[] args) {
        GestorUsuarios gestorUsuario = new GestorUsuarios();
        GestorLibros gestorLibros = new GestorLibros();
        GestorPrestamo gestorPrestamos = new GestorPrestamo();
        GestorReserva gestorReserva = new GestorReserva();

        DatosDePrueba.cargarDatosIniciales(gestorUsuario);

        MenuPrincipal menu = new MenuPrincipal();
        menu.ejecutarMenu();
    }
}
