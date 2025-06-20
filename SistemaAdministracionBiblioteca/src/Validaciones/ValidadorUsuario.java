package Validaciones;

import Excepcion.NombreInvalidoException;


/// Al momento de registrar un usuario no va a permitir que su nombre contenga caracteres especiales
/// o numeros


public class ValidadorUsuario {

    public static void validarNombre(String nombre) throws NombreInvalidoException {
        if (nombre == null || !nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            throw new NombreInvalidoException("El nombre solo debe contener letras y espacios.");
        }
    }
}
