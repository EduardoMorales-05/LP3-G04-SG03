import java.util.Random;

public class FrecuenciaDado {
    public static void main(String[] args) {
        int[] frecuencia = new int[6]; // Arreglo para las 6 caras (índices 0-5)
        Random random = new Random();

        // Lanzar el dado 20,000 veces
        for (int i = 0; i < 20000; i++) {
            int cara = random.nextInt(6) + 1; // Genera números del 1 al 6
            frecuencia[cara - 1]++; // Incrementa el contador de la cara correspondiente
        }

        // Mostrar resultados
        System.out.println("Frecuencia de cada cara después de 20,000 lanzamientos:");
        for (int j = 0; j < frecuencia.length; j++) {
            System.out.println("Cara " + (j + 1) + ": " + frecuencia[j] + " veces");
        }
    }
}
