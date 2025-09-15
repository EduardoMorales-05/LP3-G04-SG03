package exp;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        LeerEntrada lector = new LeerEntrada();
        System.out.println("=== EJERCICIO 1: LECTOR DE CARACTERES ===");
        System.out.println("Ingrese caracteres. Con 'x' o 'X' saldrá del programa.");

        while (true) {
            try {
                lector.procesar();
            } catch (VocalException e) {
                System.out.println("❌ Excepción: " + e.getMessage());
            } catch (NumeroException e) {
                System.out.println("❌ Excepción: " + e.getMessage());
            } catch (BlancoException e) {
                System.out.println("❌ Excepción: " + e.getMessage());
            } catch (SalidaException e) {
                System.out.println("👋 " + e.getMessage() + " Programa finalizado.");
                break;
            } catch (IOException e) {
                System.out.println("❌ Error de entrada: " + e.getMessage());
            }
        }
    }
}
