package Enum;

public enum TipoUsuario {
        ADMINISTRADOR("Administrador"),
        BIBLIOTECARIO("Bibliotecario"),
        LECTOR("Lector");

        private final String descripcion;

         TipoUsuario(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

