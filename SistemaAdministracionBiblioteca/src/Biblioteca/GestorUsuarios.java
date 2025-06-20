package Biblioteca;

import Excepcion.UsuarioNoEncontradoException;
import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {
    private final List<Usuario> usuarios;

    public GestorUsuarios() {
        usuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario) {
        if (usuario != null) {
            usuarios.add(usuario);
        }
    }

    public Usuario buscarUsuarioPorDni(String dni) throws UsuarioNoEncontradoException {
        if (dni == null) {
            throw new IllegalArgumentException("El DNI no puede ser null");
        }
        ///System.out.println("Buscando usuario con DNI: " + dni);
        for (Usuario u : usuarios) {
            if (u.getDni() != null && u.getDni().equalsIgnoreCase(dni)) {
                return u;
            }
        }
        throw new UsuarioNoEncontradoException("Usuario no encontrado con DNI: " + dni);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario autenticar(String nombre, String contrasena) throws Exception {

        /// Convierte en mayuscula el nombre ingresado
        String nombreMayuscula = nombre.toUpperCase();

        for (Usuario u : usuarios) {
            if (u.getNombre().toUpperCase().equals(nombreMayuscula)) {
                if (u instanceof Administrador) {
                    Administrador admin = (Administrador) u;
                    if (admin.getContrasenia().equals(contrasena)) {
                        return admin;
                    } else {
                        return null;
                    }
                } else {
                    return u;
                }
            }
        }
        return null;
    }
}

