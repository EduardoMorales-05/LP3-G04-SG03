import java.util.Scanner;

public class MenorDeTresNumeros {
    
    public static double encontrarMenor(double a, double b, double c) {
        return Math.min(Math.min(a, b), c);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] numeros = new double[3];
        
        System.out.println("Ingrese tres números decimales:");

        for (int i = 0; i < 3; i++) {
            while (true) {
                System.out.print("Número " + (i + 1) + ": ");
                try {
                    numeros[i] = scanner.nextDouble();
                    break;  // Sale del bucle si la entrada es válida
                } catch (Exception e) {
                    System.out.println("¡Error! Debe ingresar un número válido.");
                    scanner.next();
                }
            }
        }

        double resultado = encontrarMenor(numeros[0], numeros[1], numeros[2]);
        System.out.println("\nEl menor de los tres números es: " + resultado);
        
        scanner.close();
    }
}
