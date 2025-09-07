import java.util.Scanner;

class Empleado {
    private String nombre;
    private double salario;
    private String departamento;

    public Empleado(String nombre, double salario, String departamento) {
        this.nombre = nombre;
        this.salario = salario;
        this.departamento = departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalario() {
        return salario;
    }

    public String getDepartamento() {
        return departamento;
    }
}

class CalculadoraPago {
    public double calcularPagoMensual(Empleado empleado) {
        return empleado.getSalario() - (empleado.getSalario() * 0.1);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Ingresar datos del empleado
        System.out.println("SISTEMA DE EMPLEADOS ");
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = scanner.nextLine();
        
        double salario;
        do {
            System.out.print("Ingrese el salario del empleado (no negativo): ");
            salario = scanner.nextDouble();
            if (salario < 0) {
                System.out.println("Error: El salario no puede ser negativo. Intente nuevamente.");
            }
        } while (salario < 0);
        
        scanner.nextLine(); 
        
        System.out.print("Ingrese el departamento del empleado: ");
        String departamento = scanner.nextLine();
        
        // Crear el empleado
        Empleado emp = new Empleado(nombre, salario, departamento);
        
        // Calcular pago mensual
        CalculadoraPago calc = new CalculadoraPago();
        double pago = calc.calcularPagoMensual(emp);
        
        // Mostrar información
        System.out.println("\n=== INFORMACIÓN DEL EMPLEADO ===");
        System.out.println("Nombre: " + emp.getNombre());
        System.out.println("Salario base: $" + emp.getSalario());
        System.out.println("Departamento: " + emp.getDepartamento());
        System.out.println("Pago mensual (después de descuentos): $" + pago);
        
        scanner.close();
    }
}
