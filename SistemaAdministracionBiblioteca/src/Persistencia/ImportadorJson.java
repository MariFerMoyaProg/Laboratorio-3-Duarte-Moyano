package Persistencia;

import Biblioteca.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ImportadorJson {

    public static List<Usuario> importarUsuarios(String rutaArchivo) throws IOException {
        try (Reader reader = new FileReader(rutaArchivo)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Usuario>>() {}.getType();
            List<Usuario> usuarios = gson.fromJson(reader, listType);
            return usuarios != null ? usuarios : new ArrayList<Usuario>();
        } catch (FileNotFoundException e) {
            return new ArrayList<Usuario>();
        }
    }

    public static List<Libro> importarLibros(String rutaArchivo) throws IOException {
        try (Reader reader = new FileReader(rutaArchivo)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Libro>>() {}.getType();
            List<Libro> libros = gson.fromJson(reader, listType);
            return libros != null ? libros : new ArrayList<Libro>();
        } catch (FileNotFoundException e) {
            return new ArrayList<Libro>();
        }
    }

    public static List<Prestamo> importarPrestamos(String rutaArchivo, GestorLibros gestorLibros, GestorUsuarios gestorUsuarios) throws IOException {
        try (Reader reader = new FileReader(rutaArchivo)) {
            Gson gson = new Gson();
            JsonArray array = JsonParser.parseReader(reader).getAsJsonArray();
            List<Prestamo> prestamos = new ArrayList<>();
            for (JsonElement elem : array) {
                JsonObject obj = elem.getAsJsonObject();
                String libroTitulo = obj.get("libroTitulo").getAsString();
                String usuarioDni = obj.get("usuarioDni").getAsString();
                LocalDate fechaPrestamo = LocalDate.parse(obj.get("fechaPrestamo").getAsString());
                LocalDate fechaDevolucion = LocalDate.parse(obj.get("fechaDevolucion").getAsString());
                Libro libro = gestorLibros.buscarLibroPorTitulo(libroTitulo);
                Usuario usuario = gestorUsuarios.buscarUsuarioPorDni(usuarioDni);
                prestamos.add(new Prestamo(libro, usuario, fechaPrestamo, fechaDevolucion));
                libro.setDisponible(false);
            }
            return prestamos;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (Excepcion.LibroNoDisponibleException | Excepcion.UsuarioNoEncontradoException e) {
            throw new IOException("Error al importar préstamos: " + e.getMessage());
        }
    }

    public static List<Reserva> importarReservas(String rutaArchivo, GestorLibros gestorLibros, GestorUsuarios gestorUsuarios) throws IOException {
        try (Reader reader = new FileReader(rutaArchivo)) {
            Gson gson = new Gson();
            JsonArray array = JsonParser.parseReader(reader).getAsJsonArray();
            List<Reserva> reservas = new ArrayList<>();
            for (JsonElement elem : array) {
                JsonObject obj = elem.getAsJsonObject();
                String libroTitulo = obj.get("libroTitulo").getAsString();
                String usuarioDni = obj.get("usuarioDni").getAsString();
                LocalDate fechaReserva = LocalDate.parse(obj.get("fechaReserva").getAsString());
                Libro libro = gestorLibros.buscarLibroPorTitulo(libroTitulo);
                Usuario usuario = gestorUsuarios.buscarUsuarioPorDni(usuarioDni);
                reservas.add(new Reserva(libro, usuario, fechaReserva));
            }
            return reservas;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (Excepcion.LibroNoDisponibleException | Excepcion.UsuarioNoEncontradoException e) {
            throw new IOException("Error al importar reservas: " + e.getMessage());
        }
    }
}

