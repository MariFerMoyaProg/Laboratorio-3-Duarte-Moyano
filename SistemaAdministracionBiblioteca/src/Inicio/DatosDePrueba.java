package Inicio;

import Biblioteca.*;


public class DatosDePrueba {
    public static void cargarDatosIniciales(GestorUsuarios gestorUsuarios) {

        /// Usuario administrador
        Administrador admin = new Administrador("Admin", "1234567","Fantasia","12345","clave123");
        gestorUsuarios.agregarUsuario(admin);

    }
}

