package exp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculadora calc = new Calculadora();

        System.out.println("=== CALCULADORA BÁSICA ===");
        try {
            System.out.print("Ingrese el primer número: ");
            double a = sc.nextDouble();

            System.out.print("Ingrese el segundo número: ");
            double b = sc.nextDouble();

            System.out.println("Suma: " + calc.sumar(a, b));
            System.out.println("Resta: " + calc.restar(a, b));
            System.out.println("Multiplicación: " + calc.multiplicar(a, b));

            // División con excepción personalizada
            double resultado = calc.dividir(a, b);
            System.out.println("División: " + resultado);

        } catch (DivisionPorCeroException e) {
            System.out.println(" Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(" Argumento inválido: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println(" Error aritmético: " + e.getMessage());
        } finally {
            sc.close();
            System.out.println(" Programa finalizado.");
        }
    }
}
