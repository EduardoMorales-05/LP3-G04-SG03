import java.util.Scanner;

public class ConversorTiempo {

    public static int convertirASegundos(int horas, int minutos, int segundos) {
        return horas * 3600 + minutos * 60 + segundos;
    }

    public static int pedirTiempoPositivo(Scanner scanner, String unidad) {
        int valor;
        while (true) {
            System.out.print(unidad + ": ");
            valor = scanner.nextInt();
            if (valor >= 0) {
                break; // Sale del bucle si el valor es positivo
            } else {
                System.out.println("¡Error! Ingrese un valor positivo.");
            }
        }
        return valor;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Ingrese el tiempo (solo valores positivos):");
        
        int horas = pedirTiempoPositivo(scanner, "Horas");
        int minutos = pedirTiempoPositivo(scanner, "Minutos");
        int segundos = pedirTiempoPositivo(scanner, "Segundos");
        
        int totalSegundos = convertirASegundos(horas, minutos, segundos);
        System.out.println("El equivalente en segundos es: " + totalSegundos + " segundos");
        
        scanner.close();
    }
}
