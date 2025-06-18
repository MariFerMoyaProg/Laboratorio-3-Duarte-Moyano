package Biblioteca;


import Excepcion.UsuarioNoEncontradoException;
import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {
    private List<Usuario> usuarios;

    public GestorUsuarios() {
        usuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

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
