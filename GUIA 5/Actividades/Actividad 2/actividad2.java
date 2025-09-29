class ExcepcionPilaLlena extends RuntimeException {
    public ExcepcionPilaLlena(String mensaje) { super(mensaje); }
}

class ExcepcionPilaVacia extends RuntimeException {
    public ExcepcionPilaVacia(String mensaje) { super(mensaje); }
}

class Pila<E> {
    private final int tamanio;
    private int superior;
    private E[] elementos;

    @SuppressWarnings("unchecked")
    public Pila(int s) {
        tamanio = s > 0 ? s : 10;
        superior = -1;
        elementos = (E[]) new Object[tamanio];
    }

    public void push(E valor) {
        if (superior == tamanio - 1) throw new ExcepcionPilaLlena("La pila está llena");
        elementos[++superior] = valor;
    }

    public E pop() {
        if (superior == -1) throw new ExcepcionPilaVacia("La pila está vacía");
        return elementos[superior--];
    }

    public boolean contains(E elemento) {
        for (int i = superior; i >= 0; i--) {
            if (elementos[i].equals(elemento)) return true;
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        Pila<Integer> pila = new Pila<>(5);
        pila.push(10);
        pila.push(20);
        pila.push(30);

        System.out.println("¿Contiene 20? " + pila.contains(20));
        System.out.println("¿Contiene 50? " + pila.contains(50));
    }
}
