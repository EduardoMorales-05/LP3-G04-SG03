class InvalidSubscriptException extends RuntimeException {
    public InvalidSubscriptException(String mensaje) {
        super(mensaje);
    }
}

public class Main {
    public static <E> void imprimirArreglo(E[] arregloEntrada) {
        for (E elemento : arregloEntrada) {
            System.out.printf("%s ", elemento);
        }
        System.out.println();
    }

    public static <E> int imprimirArreglo(E[] arregloEntrada, int subInferior, int subSuperior) {
        if (subInferior < 0 || subSuperior > arregloEntrada.length || subInferior >= subSuperior) {
            throw new InvalidSubscriptException("Índices inválidos");
        }

        int count = 0;
        for (int i = subInferior; i < subSuperior; i++) {
            System.out.printf("%s ", arregloEntrada[i]);
            count++;
        }
        System.out.println();
        return count;
    }

    public static void main(String[] args) {
        Integer[] enteros = {1, 2, 3, 4, 5, 6};
        Double[] doubles = {1.1, 2.2, 3.3, 4.4, 5.5};
        Character[] chars = {'H', 'O', 'L', 'A'};

        imprimirArreglo(enteros);
        imprimirArreglo(doubles, 1, 4);

        try {
            imprimirArreglo(chars, 2, 1);
        } catch (InvalidSubscriptException e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }
    }
}
