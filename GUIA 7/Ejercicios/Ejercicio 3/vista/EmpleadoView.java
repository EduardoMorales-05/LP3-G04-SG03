package vista;

import controlador.EmpleadoController;
import java.util.Scanner;

public class EmpleadoView {
    private EmpleadoController controller;
    private Scanner scanner;

    public EmpleadoView() {
        controller = new EmpleadoController();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\n🏢 SISTEMA DE GESTIÓN DE EMPLEADOS 🏢");
        System.out.println("========================================");
        System.out.println("1. Listar todos los empleados");
        System.out.println("2. Agregar un nuevo empleado");
        System.out.println("3. Buscar empleado por número");
        System.out.println("4. Eliminar empleado por número");
        System.out.println("5. Salir del programa");
        System.out.println("========================================");
        System.out.print("Seleccione una opción: ");
    }

    public void iniciar() {
        System.out.println("¡Bienvenido al Sistema de Gestión de Empleados!");
        
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1:
                        controller.listarEmpleados();
                        break;
                    case 2:
                        controller.agregarEmpleado();
                        break;
                    case 3:
                        controller.buscarEmpleado();
                        break;
                    case 4:
                        controller.eliminarEmpleado();
                        break;
                    case 5:
                        salir = true;
                        System.out.println("\n¡Gracias por usar el sistema! ¡Hasta pronto! 👋");
                        break;
                    default:
                        System.out.println("❌ Opción no válida. Por favor, seleccione 1-5.");
                }
                
                // Pausa para que el usuario pueda ver los resultados
                if (!salir) {
                    System.out.print("\nPresione Enter para continuar...");
                    scanner.nextLine();
                }
                
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor, ingrese un número válido (1-5).");
            }
        }
        
        controller.cerrarScanner();
        scanner.close();
    }
}
