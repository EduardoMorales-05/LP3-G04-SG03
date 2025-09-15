package exp;

import java.util.NoSuchElementException;

public class RegistroEstudiantes {
    private String[] estudiantes;
    private int contador;

    public RegistroEstudiantes(int capacidad) {
        estudiantes = new String[capacidad];
        contador = 0;
    }

    // 🚨 Excepción interna definida dentro de RegistroEstudiantes
    public static class EspacioBlancoException extends Exception {
        public EspacioBlancoException(String mensaje) {
            super(mensaje);
        }
    }

    public void agregarEstudiante(String nombre) throws EspacioBlancoException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new EspacioBlancoException("El nombre del estudiante no puede estar vacío o ser solo espacios.");
        }

        // Validación: solo letras y espacios
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
        }

        if (contador >= estudiantes.length) {
            throw new IllegalArgumentException("No se puede agregar más estudiantes. Capacidad máxima alcanzada.");
        }

        estudiantes[contador++] = nombre;
    }

    public String buscarEstudiante(String nombre) {
        for (int i = 0; i < contador; i++) {
            if (estudiantes[i].equalsIgnoreCase(nombre)) {
                return estudiantes[i];
            }
        }
        throw new NoSuchElementException("Estudiante '" + nombre + "' no encontrado.");
    }

    public void listarEstudiantes() {
        if (contador == 0) {
            System.out.println(" No hay estudiantes registrados.");
            return;
        }
        System.out.println(" Lista de estudiantes registrados:");
        for (int i = 0; i < contador; i++) {
            System.out.println((i + 1) + ". " + estudiantes[i]);
        }
    }
}
