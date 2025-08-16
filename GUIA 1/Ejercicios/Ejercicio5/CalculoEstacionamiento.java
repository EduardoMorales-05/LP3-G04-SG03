import java.util.Scanner;

public class CalculoEstacionamiento {

    public static double calcularCargo(int horas) {
        double cargo = 3.00; // Primera hora
        
        if (horas > 1) {
            cargo += 0.50 * (horas - 1); // Horas adicionales
        }
        
        // Aplicar máximo diario
        if (cargo > 12.00) {
            cargo = 12.00;
        }
        
        return cargo;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el número de horas: ");
        int horas = scanner.nextInt();
        
        double total = calcularCargo(horas);
        System.out.println("El cargo total es: S/" + total);
    }
}
