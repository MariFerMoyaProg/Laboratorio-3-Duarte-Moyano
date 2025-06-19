package Inicio;

import Biblioteca.*;


public class DatosDePrueba {
    public static void cargarDatosIniciales(GestorUsuarios gestorUsuarios) {

        /// Usuario administrador
        Administrador admin = new Administrador("Admin", "12345678");
        gestorUsuarios.agregarUsuario(admin);

    }
}

