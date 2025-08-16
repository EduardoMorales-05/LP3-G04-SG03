import java.util.Scanner;

public class NumerosAscendentes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numeros = new int[10]; // Arreglo de 10 elementos
        int anterior = Integer.MIN_VALUE; // Valor inicial mínimo

        System.out.println("Ingrese 10 numeros en orden ascendente:");

        for (int i = 0; i < numeros.length; i++) {
            while (true) {
                System.out.print("Numero " + (i + 1) + ": ");
                int num = scanner.nextInt();

                if (num > anterior) {
                    numeros[i] = num;
                    anterior = num;
                    break; // Sale del bucle while si es válido
                } else {
                    System.out.println(" erroor el numero debe ser mayor que " + anterior + ". intente nuevamente.");
                }
            }
        }

        // Mostrar el arreglo resultante
        System.out.print("\nArreglo final: ");
        for (int n : numeros) {
            System.out.print(n + " ");
        }
    }
}
