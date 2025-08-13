import java.util.Scanner;

public class Palindromo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce un número entero: "); 
        int numero = scanner.nextInt(); 
        
        int original = numero; // Guardamos el número original para compararlo después
        int inverso = 0; // Variable para almacenar el número invertido
        
        // Invertimos el número
        while (numero != 0) {
            inverso = inverso * 10 + (numero % 10); // Añadimos el último dígito al número invertido
            numero /= 10; // Eliminamos el último dígito del número original
        }
        
        // Comparamos el número original con el número invertido
        if (original == inverso) {
            System.out.println(original + " es un palíndromo.");
        } else {
            System.out.println(original + " no es un palíndromo.");
        }
    }
}

