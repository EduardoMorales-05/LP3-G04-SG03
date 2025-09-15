package exp;

import java.io.IOException;
import java.util.Scanner;

public class LeerEntrada {
    private Scanner sc = new Scanner(System.in);

    public char getChar() throws IOException {
        String entrada = sc.nextLine(); // lee toda la línea
        if (entrada.isEmpty()) {
            return ' '; 
        }
        return entrada.charAt(0); // devolvemos solo el primer caracter
    }

    // Procesa el carácter y lanza la excepción adecuada
    public void procesar() throws VocalException, NumeroException, BlancoException, SalidaException, IOException {
        char c = getChar();

        if ("aeiouAEIOU".indexOf(c) != -1) {
            throw new VocalException("Ingresaste una vocal: " + c);
        } else if (Character.isDigit(c)) {
            throw new NumeroException("Ingresaste un número: " + c);
        } else if (Character.isWhitespace(c)) {
            throw new BlancoException("Ingresaste un espacio en blanco.");
        } else if (c == 'x' || c == 'X') {
            throw new SalidaException("Carácter de salida detectado.");
        } else {
            System.out.println("Carácter aceptado: " + c);
        }
    }
}
