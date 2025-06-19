package Biblioteca;


import Excepcion.LibroNoDisponibleException;
import java.util.ArrayList;
import java.util.List;

    public class GestorLibros {
        private final List<Libro> libros;

        public GestorLibros() {
            libros = new ArrayList<>();
        }

        public void agregarLibro(Libro libro) {
            libros.add(libro);
        }

        public List<Libro> buscarLibros(String criterio) {
            List<Libro> encontrados = new ArrayList<>();
            for (Libro libro : libros) {
                if (libro.coincide(criterio)) {
                    encontrados.add(libro);
                }
            }
            return encontrados;
        }

        public Libro buscarLibroPorTitulo(String titulo) throws LibroNoDisponibleException {
            for (Libro libro : libros) {
                if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                    return libro;
                }
            }
            throw new LibroNoDisponibleException("No se encontró el libro con título: " + titulo);
        }

        public List<Libro> getLibros() {
            return libros;
        }
    }


