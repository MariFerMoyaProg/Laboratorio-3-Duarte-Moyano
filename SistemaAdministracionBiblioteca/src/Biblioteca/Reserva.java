package Biblioteca;

import java.time.LocalDate;

public class Reserva {
    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaReserva;

    public Reserva(Libro libro, Usuario usuario, LocalDate fechaReserva) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaReserva = fechaReserva;
    }

    public Libro getLibro() {
        return libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    @Override
    public String toString() {
        return "Reserva: " + libro.getTitulo() + " por " + usuario.getNombre() + " el " + fechaReserva;
    }
}


