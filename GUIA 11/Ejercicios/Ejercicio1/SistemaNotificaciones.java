package lp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// --- CLASES DEL PATRÓN OBSERVER ---

// Clase Notificación (El objeto que se envía)
class Notificacion {
    private String mensaje;
    private LocalDateTime fecha;

    public Notificacion(String mensaje) {
        this.mensaje = mensaje;
        this.fecha = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return "[" + fecha.format(formatter) + "] " + mensaje;
    }
}

// Interfaz Observador
interface Observador {
    void recibir(Notificacion notificacion);
    String getNombre();
}

// Clase Usuario (Observador Concreto)
class Usuario implements Observador {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void recibir(Notificacion notificacion) {
        System.out.println("[Usuario] " + nombre + " recibio: " + notificacion);
    }
}

// Clase SistemaNotificaciones 
public class SistemaNotificaciones {
    private List<Observador> suscriptores = new ArrayList<>();

    // Métodos del Patrón Observer
    public void suscribir(Observador usuario) {
        if (!suscriptores.contains(usuario)) {
            suscriptores.add(usuario);
            System.out.println("[Sistema] " + usuario.getNombre() + " se ha suscrito.");
        } else {
            System.out.println("[Sistema] " + usuario.getNombre() + " ya esta suscrito.");
        }
    }

    public void desuscribir(Observador usuario) {
        if (suscriptores.remove(usuario)) {
            System.out.println("[Sistema] " + usuario.getNombre() + " se ha desuscrito.");
        } else {
            System.out.println("[Sistema] " + usuario.getNombre() + " no estaba suscrito.");
        }
    }

    public void notificar(String mensaje) {
        Notificacion notificacion = new Notificacion(mensaje);
        System.out.println("\n[Sistema] Enviando notificacion a " + suscriptores.size() + " suscriptores...");
        for (Observador obs : suscriptores) {
            obs.recibir(notificacion);
        }
        System.out.println("--------------------------------------------------");
    }

    // --- MENÚ INTERACTIVO (MAIN) ---
    public static void main(String[] args) {
        SistemaNotificaciones sistema = new SistemaNotificaciones();
        List<Usuario> usuariosRegistrados = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        System.out.println("==========================================");
        System.out.println("   SISTEMA DE NOTIFICACIONES (OBSERVER)   ");
        System.out.println("==========================================");

        while (opcion != 0) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Suscribir usuario");
            System.out.println("3. Desuscribir usuario");
            System.out.println("4. Enviar notificación");
            System.out.println("5. Ver usuarios registrados");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); 
            } else {
                System.out.println("Entrada inválida.");
                scanner.next();
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del usuario: ");
                    String nombre = scanner.nextLine();
                    usuariosRegistrados.add(new Usuario(nombre));
                    System.out.println("Usuario '" + nombre + "' registrado en el sistema.");
                    break;

                case 2:
                    if (usuariosRegistrados.isEmpty()) {
                        System.out.println("No hay usuarios registrados.");
                        break;
                    }
                    System.out.println("Seleccione usuario para suscribir:");
                    for (int i = 0; i < usuariosRegistrados.size(); i++) {
                        System.out.println((i + 1) + ". " + usuariosRegistrados.get(i).getNombre());
                    }
                    int idxSus = scanner.nextInt() - 1;
                    if (idxSus >= 0 && idxSus < usuariosRegistrados.size()) {
                        sistema.suscribir(usuariosRegistrados.get(idxSus));
                    } else {
                        System.out.println("Opción inválida.");
                    }
                    break;

                case 3:
                    if (usuariosRegistrados.isEmpty()) {
                        System.out.println("No hay usuarios registrados.");
                        break;
                    }
                    System.out.println("Seleccione usuario para desuscribir:");
                    for (int i = 0; i < usuariosRegistrados.size(); i++) {
                        System.out.println((i + 1) + ". " + usuariosRegistrados.get(i).getNombre());
                    }
                    int idxDes = scanner.nextInt() - 1;
                    if (idxDes >= 0 && idxDes < usuariosRegistrados.size()) {
                        sistema.desuscribir(usuariosRegistrados.get(idxDes));
                    } else {
                        System.out.println("Opción inválida.");
                    }
                    break;

                case 4:
                    System.out.print("Mensaje de la notificación: ");
                    String msg = scanner.nextLine();
                    sistema.notificar(msg);
                    break;

                case 5:
                    System.out.println("--- Usuarios Registrados ---");
                    for (Usuario u : usuariosRegistrados) {
                        System.out.println("- " + u.getNombre());
                    }
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}

