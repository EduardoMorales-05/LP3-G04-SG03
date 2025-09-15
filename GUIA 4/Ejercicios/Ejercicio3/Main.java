package exp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Numero numero = null; // aún no creado
        int opcion;

        do {
            System.out.println("\n=== MENÚ GESTIÓN DE NÚMEROS ===");
            System.out.println("1. Ingresar un número");
            System.out.println("2. Cambiar número");
            System.out.println("3. Mostrar número");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese un número inicial: ");
                    double valorInicial = sc.nextDouble();
                    try {
                        numero = new Numero(valorInicial);
                        System.out.println(" Número creado: " + numero.getValor());
                    } catch (IllegalArgumentException e) {
                        System.out.println(" Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    if (numero == null) {
                        System.out.println("⚠ Primero debe crear un número.");
                    } else {
                        System.out.print("Ingrese un nuevo número: ");
                        double nuevoValor = sc.nextDouble();
                        try {
                            numero.setValor(nuevoValor);
                            System.out.println(" Número actualizado: " + numero.getValor());
                        } catch (IllegalArgumentException e) {
                            System.out.println(" Error: " + e.getMessage());
                        }
                    }
                    break;

                case 3:
                    if (numero == null) {
                        System.out.println(" No hay número creado aún.");
                    } else {
                        System.out.println(" Número actual: " + numero.getValor());
                    }
                    break;

                case 4:
                    System.out.println(" Saliendo del programa...");
                    break;

                default:
                    System.out.println(" Opción inválida, intente otra vez.");
            }
        } while (opcion != 4);

        sc.close();
    }
}
