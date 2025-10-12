package controlador;

import modelo.Empleado;
import dao.EmpleadoDAO;
import java.util.List;
import java.util.Scanner;

public class EmpleadoController {
    private EmpleadoDAO empleadoDAO;
    private Scanner scanner;

    public EmpleadoController() {
        empleadoDAO = new EmpleadoDAO();
        scanner = new Scanner(System.in);
    }

    // Listar todos los empleados
    public void listarEmpleados() {
        List<Empleado> empleados = empleadoDAO.leerEmpleados();
        
        if (empleados.isEmpty()) {
            System.out.println("\nNo hay empleados registrados.");
            return;
        }

        System.out.println("\n=== LISTA DE EMPLEADOS ===");
        System.out.println("─────────────────────────────────────────────────────────");
        for (Empleado empleado : empleados) {
            System.out.println(empleado);
        }
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("Total de empleados: " + empleados.size());
    }

    // Agregar un nuevo empleado
    public void agregarEmpleado() {
        System.out.println("\n--- AGREGAR NUEVO EMPLEADO ---");
        
        int numero = leerEntero("Número de empleado: ");
        
        // Verificar si el número ya existe
        if (empleadoDAO.existeEmpleado(numero)) {
            System.out.println("❌ Ya existe un empleado con el número: " + numero);
            return;
        }
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        double sueldo = leerDouble("Sueldo: $");
        
        Empleado nuevoEmpleado = new Empleado(numero, nombre, sueldo);
        
        if (empleadoDAO.agregarEmpleado(nuevoEmpleado)) {
            System.out.println("✅ Empleado agregado correctamente.");
        } else {
            System.out.println("❌ Error al agregar empleado.");
        }
    }

    // Buscar empleado por número
    public void buscarEmpleado() {
        System.out.println("\n--- BUSCAR EMPLEADO ---");
        int numero = leerEntero("Número de empleado a buscar: ");
        
        Empleado empleado = empleadoDAO.buscarEmpleado(numero);
        
        if (empleado != null) {
            System.out.println("\n✅ EMPLEADO ENCONTRADO:");
            System.out.println("─────────────────────────────────────────────────────────");
            System.out.println(empleado);
            System.out.println("─────────────────────────────────────────────────────────");
        } else {
            System.out.println("❌ No se encontró ningún empleado con el número: " + numero);
        }
    }

    // Eliminar empleado por número
    public void eliminarEmpleado() {
        System.out.println("\n--- ELIMINAR EMPLEADO ---");
        int numero = leerEntero("Número de empleado a eliminar: ");
        
        Empleado empleado = empleadoDAO.buscarEmpleado(numero);
        if (empleado == null) {
            System.out.println("❌ No se encontró ningún empleado con el número: " + numero);
            return;
        }
        
        System.out.println("Empleado a eliminar:");
        System.out.println(empleado);
        System.out.print("¿Está seguro de eliminar este empleado? (s/n): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("s")) {
            if (empleadoDAO.eliminarEmpleado(numero)) {
                System.out.println("✅ Empleado eliminado correctamente.");
            } else {
                System.out.println("❌ Error al eliminar empleado.");
            }
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    // Métodos auxiliares para lectura de datos
    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor, ingrese un número entero válido.");
            }
        }
    }

    private double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor, ingrese un número válido.");
            }
        }
    }

    public void cerrarScanner() {
        scanner.close();
    }
}
