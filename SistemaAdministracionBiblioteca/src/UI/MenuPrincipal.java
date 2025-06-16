package UI;

import Biblioteca.*;

import java.util.Scanner;

public class MenuPrincipal {

    /// Creación de método para mostrar las opciones iniciales de logueo

    public static void mostrar(){

        Scanner sc = new Scanner(System.in);

        /// Muestro por pantalla las opciones al usuario
        System.out.println("========== Sistema de Biblioteca ========");
        System.out.println("Seleccione tipo de usuario: ");
        System.out.println(" 1 - Administrador");
        System.out.println(" 2 - Bibliotecario");
        System.out.println(" 3 - Usuario (Lector)");
        System.out.println("Ingrese una opción: ");

        int opcion= sc.nextInt();
        sc.nextLine();

        Usuario usuario = null;

        System.out.println("Ingrese su nombre: ");
        String nombre= sc.nextLine();

        System.out.println("Ingrese su DNI (8 dígitos): ");
        String dni = sc.nextLine();

        try{
            if (opcion == 1){
                usuario= new Administrador (nombre, dni);
            }else if (opcion == 2){
                usuario = new Bibliotecario (nombre, dni);
            } else if (opcion == 3) {
                usuario= new Lector(nombre,dni);
            }else{
                System.out.println("Opción inválida");
                return;
            }

            ///System.out.println("\nBienvenido/a, ") + usuario.usuario.getNombre());
            ///usuario,mostrarOpciones();

        }catch (IllegalArgumentException ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }
}
