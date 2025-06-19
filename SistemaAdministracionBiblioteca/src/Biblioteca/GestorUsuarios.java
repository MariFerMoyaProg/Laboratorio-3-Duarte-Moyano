package Biblioteca;


import Excepcion.UsuarioNoEncontradoException;
import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {
    private List<Usuario> usuarios;

    public GestorUsuarios() {
        usuarios = new ArrayList<>();
    }

    public boolean agregarUsuario(Usuario usuario) {
        try {
            buscarUsuarioPorDni(usuario.getDni());
            System.out.println("Ya existe un usuario con ese DNI.");
            return false;
        } catch (UsuarioNoEncontradoException e) {
            usuarios.add(usuario);
            return true;
    }}

    public Usuario buscarUsuarioPorDni(String dni) throws UsuarioNoEncontradoException {
        for (Usuario u : usuarios) {
            if (u.getDni().equals(dni)) {
                return u;
            }
        }
        throw new UsuarioNoEncontradoException("No se encontró usuario con DNI: " + dni);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
