package Persistencia;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class PersistenciaJson<T> {
    private final String archivo;
    private final Gson gson;

    public PersistenciaJson(String archivo) {
        this.archivo = archivo;
        this.gson = new Gson();
    }

    public void guardar(List<T> lista) throws IOException {
        try (FileWriter writer = new FileWriter(archivo)) {
            gson.toJson(lista, writer);
        }
    }

    public List<T> cargar(Type tipoLista) throws IOException {
        try (FileReader reader = new FileReader(archivo)) {
            return gson.fromJson(reader, tipoLista);
        }
    }
}
