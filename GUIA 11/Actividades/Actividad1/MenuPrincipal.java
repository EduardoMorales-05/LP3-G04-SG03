package lp;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class MenuPrincipal {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenuPrincipal();
            opcion = leerOpcion();
            scanner.nextLine();
            System.out.println("------------------------------------");

            switch (opcion) {
                case 1:
                    probarObserver();
                    break;
                case 2:
                    probarStrategy();
                    break;
                case 3:
                    probarCommand();
                    break;
                case 0:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
            System.out.println("------------------------------------\n");

        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n===== MENÚ DE PRÁCTICA DE PATRONES =====");
        System.out.println("1. Probar Patrón Observer (Notificaciones)");
        System.out.println("2. Probar Patrón Strategy (Cálculo de Descuentos)");
        System.out.println("3. Probar Patrón Command (Control Remoto)");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int leerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada no válida. Por favor, ingrese un número.");
            scanner.next();
            System.out.print("Seleccione una opción: ");
        }
        return scanner.nextInt();
    }


    //PRUEBA DEL PATRÓN OBSERVER (INTERACTIVO)

    private static void probarObserver() {
        System.out.println("--- PRUEBA DE OBSERVER (Sistema de Notificaciones) ---");
        SistemaNotificaciones sistema = new SistemaNotificaciones();
        List<Usuario> usuariosDisponibles = new ArrayList<>();
        
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- MENÚ OBSERVER ---");
            System.out.println("1. Crear nuevo usuario");
            System.out.println("2. Suscribir usuario");
            System.out.println("3. Desuscribir usuario");
            System.out.println("4. Enviar notificación");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            opcion = leerOpcion();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del usuario: ");
                    String nombre = scanner.nextLine();
                    usuariosDisponibles.add(new Usuario(nombre));
                    System.out.println("Usuario '" + nombre + "' creado.");
                    break;
                case 2:
                    if (usuariosDisponibles.isEmpty()) {
                        System.out.println("No hay usuarios creados.");
                        break;
                    }
                    System.out.println("Seleccione usuario a suscribir:");
                    for (int i = 0; i < usuariosDisponibles.size(); i++) {
                        System.out.println((i + 1) + ". " + usuariosDisponibles.get(i).nombre);
                    }
                    int idxSus = leerOpcion() - 1;
                    if (idxSus >= 0 && idxSus < usuariosDisponibles.size()) {
                        sistema.suscribir(usuariosDisponibles.get(idxSus));
                    } else {
                        System.out.println("Opción inválida.");
                    }
                    break;
                case 3:
                     if (usuariosDisponibles.isEmpty()) {
                        System.out.println("No hay usuarios creados.");
                        break;
                    }
                    System.out.println("Seleccione usuario a desuscribir:");
                    for (int i = 0; i < usuariosDisponibles.size(); i++) {
                        System.out.println((i + 1) + ". " + usuariosDisponibles.get(i).nombre);
                    }
                    int idxDes = leerOpcion() - 1;
                    if (idxDes >= 0 && idxDes < usuariosDisponibles.size()) {
                        sistema.desuscribir(usuariosDisponibles.get(idxDes));
                    } else {
                        System.out.println("Opción inválida.");
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el mensaje de la notificación: ");
                    String mensaje = scanner.nextLine();
                    sistema.enviarNotificacion(mensaje);
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // PRUEBA DEL PATRÓN STRATEGY (INTERACTIVO)

    private static void probarStrategy() {
        System.out.println("--- PRUEBA DE STRATEGY (Calculadora de Descuentos) ---");
        CalculadoraPrecios calculadora = new CalculadoraPrecios();
        
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- MENÚ STRATEGY ---");
            System.out.println("1. Calcular nuevo precio");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            opcion = leerOpcion();
            scanner.nextLine(); 

            if (opcion == 1) {
                try {
                    System.out.print("Ingrese el precio base total: ");
                    double precioBase = scanner.nextDouble();
                    System.out.print("Ingrese la cantidad de productos: ");
                    int cantidad = scanner.nextInt();
                    System.out.print("Ingrese el precio del producto más bajo: ");
                    double precioMasBajo = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Seleccione la estrategia de descuento:");
                    System.out.println("1. Sin Descuento");
                    System.out.println("2. Descuento Fijo (10%)");
                    System.out.println("3. Descuento Porcentual (30% si >= 2)");
                    System.out.println("4. Descuento Acumulado (50% en más bajo si >= 3)");
                    System.out.print("Opción: ");
                    
                    int estOp = leerOpcion();
                    switch (estOp) {
                        case 1: calculadora.setEstrategia(new SinDescuento()); break;
                        case 2: calculadora.setEstrategia(new DescuentoFijo()); break;
                        case 3: calculadora.setEstrategia(new DescPorcentual()); break;
                        case 4: calculadora.setEstrategia(new DescAcumulado()); break;
                        default: 
                            System.out.println("Estrategia no válida, usando Sin Descuento.");
                            calculadora.setEstrategia(new SinDescuento());
                    }

                    double precioFinal = calculadora.calcularPrecioFinal(precioBase, cantidad, precioMasBajo);
                    System.out.printf("Precio Final Calculado: $%.2f\n", precioFinal);

                } catch (InputMismatchException e) {
                    System.out.println("Error: Entrada numérica no válida.");
                    scanner.nextLine(); // Limpiar buffer
                }
            } else if (opcion != 0) {
                System.out.println("Opción no válida.");
            }
        }
    }
    

    // PRUEBA DEL PATRÓN COMMAND (INTERACTIVO)

    private static void probarCommand() {
        System.out.println("--- PRUEBA DE COMMAND (Control Remoto del Televisor) ---");
        Televisor miTV = new Televisor();
        ControlRemoto remote = new ControlRemoto();
        
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- MENÚ CONTROL REMOTO ---");
            System.out.println("1. Encender TV");
            System.out.println("2. Apagar TV");
            System.out.println("3. Subir Volumen");
            System.out.println("4. Cambiar Canal");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            opcion = leerOpcion();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    remote.setComando(new EncenderTV(miTV));
                    remote.presionarBoton();
                    break;
                case 2:
                    remote.setComando(new ApagarTV(miTV));
                    remote.presionarBoton();
                    break;
                case 3:
                    remote.setComando(new SubirVolumen(miTV));
                    remote.presionarBoton();
                    break;
                case 4:
                    System.out.print("Ingrese el número de canal: ");
                    if (scanner.hasNextInt()) {
                        int canal = scanner.nextInt();
                        remote.setComando(new ComandoCanal(miTV, canal));
                        remote.presionarBoton();
                    } else {
                        System.out.println("Entrada no válida.");
                        scanner.next();
                    }
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
