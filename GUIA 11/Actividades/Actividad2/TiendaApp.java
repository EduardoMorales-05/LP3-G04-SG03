package lp;

import java.util.Scanner;
import java.util.InputMismatchException;

public class TiendaApp {
    private static Scanner scanner = new Scanner(System.in);
    private static ModuloNotificaciones notificaciones = new ModuloNotificaciones();
    private static ModuloVentas ventas = new ModuloVentas();
    private static ModuloControl control = new ModuloControl();

    public static void main(String[] args) {
        int opcion = -1;
        do {
            mostrarMenuPrincipal();
            opcion = leerOpcion();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    menuNotificaciones();
                    break;
                case 2:
                    menuVentas();
                    break;
                case 3:
                    menuControl();
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
        scanner.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n=== TIENDA INTELIGENTE - MENÚ PRINCIPAL ===");
        System.out.println("1. Módulo de Notificaciones (Observer)");
        System.out.println("2. Módulo de Ventas (Strategy)");
        System.out.println("3. Módulo de Control (Command)");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int leerOpcion() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next(); 
            return -1;
        }
    }

    private static void menuNotificaciones() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- MÓDULO NOTIFICACIONES ---");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Enviar Oferta Global");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = leerOpcion();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del cliente: ");
                    String nombre = scanner.nextLine();
                    notificaciones.registrarNuevoCliente(nombre);
                    break;
                case 2:
                    System.out.print("Texto de la oferta: ");
                    String oferta = scanner.nextLine();
                    notificaciones.lanzarPromocion(oferta);
                    break;
                case 0: break;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    private static void menuVentas() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- MÓDULO VENTAS ---");
            System.out.println("1. Realizar Venta");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = leerOpcion();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    try {
                        System.out.print("Producto: ");
                        String prod = scanner.nextLine();
                        System.out.print("Precio Unitario: ");
                        double precio = scanner.nextDouble();
                        System.out.print("Cantidad: ");
                        int cant = scanner.nextInt();
                        scanner.nextLine(); 
                        System.out.println("Tipo de Cliente/Evento (normal, vip, flash, liquidacion): ");
                        String tipo = scanner.nextLine();
                        
                        ventas.realizarVenta(prod, precio, cant, tipo);
                    } catch (InputMismatchException e) {
                        System.out.println("Error en entrada de datos.");
                        scanner.nextLine();
                    }
                    break;
                case 0: break;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    private static void menuControl() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- MÓDULO CONTROL ---");
            System.out.println("1. Iniciar Sistemas Visuales");
            System.out.println("2. Apagar Sistemas Visuales");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = leerOpcion();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    control.iniciarSistemaVisual();
                    break;
                case 2:
                    control.apagarSistemaVisual();
                    break;
                case 0: break;
                default: System.out.println("Opción inválida.");
            }
        }
    }
}
