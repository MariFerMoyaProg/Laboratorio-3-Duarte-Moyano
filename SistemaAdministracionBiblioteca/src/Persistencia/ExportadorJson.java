package Persistencia;

import Biblioteca.*;
import com.google.gson.*;


import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class ExportadorJson {

    public static void exportarUsuarios(List<Usuario> usuarios, String rutaArchivo) throws IOException {
        try (Writer writer = new FileWriter(rutaArchivo)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(usuarios, writer);
        }
    }

    public static void exportarLibros(List<Libro> libros, String rutaArchivo) throws IOException {
        try (Writer writer = new FileWriter(rutaArchivo)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(libros, writer);
        }
    }

    public static void exportarPrestamos(List<Prestamo> prestamos, String rutaArchivo) throws IOException {
        try (Writer writer = new FileWriter(rutaArchivo)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Prestamo.class, new PrestamoAdapter())
                    .setPrettyPrinting()
                    .create();
            gson.toJson(prestamos, writer);
        }
    }

    public static void exportarReservas(List<Reserva> reservas, String rutaArchivo) throws IOException {
        try (Writer writer = new FileWriter(rutaArchivo)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Reserva.class, new ReservaAdapter())
                    .setPrettyPrinting()
                    .create();
            gson.toJson(reservas, writer);
        }
    }
    // Adaptadores para Gson
    static class PrestamoAdapter implements JsonSerializer<Prestamo> {
        @Override
        public JsonElement serialize(Prestamo p, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject obj = new JsonObject();
            obj.addProperty("libroTitulo", p.getLibro().getTitulo());
            obj.addProperty("usuarioDni", p.getUsuario().getDni());
            obj.addProperty("fechaPrestamo", p.getFechaPrestamo().toString());
            obj.addProperty("fechaDevolucion", p.getFechaDevolucion().toString());
            return obj;
        }
    }

    static class ReservaAdapter implements JsonSerializer<Reserva> {
        @Override
        public JsonElement serialize(Reserva r, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject obj = new JsonObject();
            obj.addProperty("libroTitulo", r.getLibro().getTitulo());
            obj.addProperty("usuarioDni", r.getUsuario().getDni());
            obj.addProperty("fechaReserva", r.getFechaReserva().toString());
            return obj;
        }
    }
}

