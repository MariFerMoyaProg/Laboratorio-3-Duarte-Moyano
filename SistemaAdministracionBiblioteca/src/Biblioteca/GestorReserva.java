package Biblioteca;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

    public class GestorReserva {
        private List<Reserva> reservas;

        public GestorReserva() {
            reservas = new ArrayList<>();
        }

        public void agregarReserva(Reserva reserva) {
            reservas.add(reserva);
        }

        public List<Reserva> getReservas() {
            return reservas;
        }

        public List<Reserva> reservasPorLibro(Libro libro) {
            List<Reserva> resultado = new ArrayList<>();
            for (Reserva r : reservas) {
                if (r.getLibro().equals(libro)) {
                    resultado.add(r);
                }
            }
            return resultado;
        }

        public boolean estaReservadoEnFecha(Libro libro, LocalDate fecha) {
            for (Reserva reserva : reservas) {
                if (reserva.getLibro().equals(libro) && reserva.getFechaReserva().equals(fecha)) {
                    return true;
                }
            }
            return false;
        }

    }

