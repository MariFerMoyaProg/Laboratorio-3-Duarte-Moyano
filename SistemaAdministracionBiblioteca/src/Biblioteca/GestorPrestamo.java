package Biblioteca;

import Excepcion.LibroNoDisponibleException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorPrestamo {
    private List<Prestamo> prestamos;

    public GestorPrestamo() {

        prestamos = new ArrayList<>();
    }

    public void agregarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
        prestamo.getLibro().setDisponible(false);
    }

    public void devolverLibro(Prestamo prestamo) {
        prestamo.getLibro().setDisponible(true);
        prestamos.remove(prestamo);
    }

    public List<Prestamo> getPrestamos() {

        return prestamos;
    }

    public List<Prestamo> prestamosPorUsuario(Usuario usuario) {
        List<Prestamo> resultado = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.getUsuario().equals(usuario)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public boolean libroDisponibleEnPeriodo(Libro libro, LocalDate inicio, LocalDate fin) {
        for (Prestamo p : prestamos) {
            if (p.getLibro().equals(libro)) {
                if (!(fin.isBefore(p.getFechaPrestamo()) || inicio.isAfter(p.getFechaDevolucion()))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void registrarPrestamo(Libro libro, Usuario usuario) throws LibroNoDisponibleException {
        if (!libro.estaDisponible()) {
            throw new LibroNoDisponibleException("El libro no está disponible para préstamo.");
        }
        Prestamo nuevoPrestamo = new Prestamo(libro, usuario, LocalDate.now(), LocalDate.now().plusWeeks(1)); // o con fecha devolución que corresponda
        prestamos.add(nuevoPrestamo);
        libro.setDisponible(false);
    }
}
