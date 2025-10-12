package ejercicios;

import java.util.Scanner;

public class MainGestor {
    public static void main(String[] args) {
        Gestor gestor = new Gestor();
        Scanner scanner = new Scanner(System.in);
        
        boolean salir = false;
        while (!salir) {
            gestor.mostrarMenu();
            
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                
                if (opcion == 6) {
                    salir = true;
                }
                
                gestor.ejecutarOpcion(opcion);
                
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor, ingrese un número válido.");
            }
        }
        
        gestor.cerrarScanner();
        scanner.close();
    }
}
