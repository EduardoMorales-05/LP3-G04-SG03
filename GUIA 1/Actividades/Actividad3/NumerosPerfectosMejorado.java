import java.util.Scanner;

public class NumerosPerfectosMejorado {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un número límite (n > 1): ");
        long n = scanner.nextLong();

        if (n <= 1) {
            System.out.println("error Ingrese un número mayor que 1.");
            return;
        }

        System.out.println("\n=== Numeros perfectos menores que " + n + "=");
        imprimirNumerosPerfectos(n);
    }

    // Función optimizada para verificar si un número es perfecto
    public static boolean esPerfecto(long num) {
        if (num <= 1) return false; // 0 y 1 no son perfectos

        long sumaDivisores = 1; // 1 es divisor universal (excepto para num=1)
        for (long i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                sumaDivisores += i;
                long complemento = num / i;
                if (complemento != i) {
                    sumaDivisores += complemento;
                }
            }
        }
        return sumaDivisores == num;
    }

    // Función para imprimir números perfectos
    public static void imprimirNumerosPerfectos(long limite) {
        int contador = 0;
        for (long num = 2; num < limite; num++) {
            if (esPerfecto(num)) {
                System.out.println("- " + num + " es un número perfecto.");
                contador++;
            }
        }
        if (contador == 0) {
            System.out.println("No se encontraron números perfectos menores que " + limite);
        } else {
            System.out.println("Total encontrados: " + contador);
        }
    }
}
