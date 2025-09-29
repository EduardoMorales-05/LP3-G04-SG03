class Pila<E> {
    private final int tamanio;
    int superior;
    E[] elementos;

    @SuppressWarnings("unchecked")
    public Pila(int s) {
        tamanio = s > 0 ? s : 10;
        superior = -1;
        elementos = (E[]) new Object[tamanio];
    }

    public void push(E valor) {
        if (superior == tamanio - 1) throw new RuntimeException("La pila está llena");
        elementos[++superior] = valor;
    }

    public boolean esIgual(Pila<E> otraPila) {
        if (this.superior != otraPila.superior) return false;
        for (int i = 0; i <= superior; i++) {
            if (!this.elementos[i].equals(otraPila.elementos[i])) return false;
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        Pila<Integer> pila1 = new Pila<>(5);
        Pila<Integer> pila2 = new Pila<>(5);

        pila1.push(10);
        pila1.push(20);

        pila2.push(10);
        pila2.push(20);

        System.out.println("¿Son iguales? " + pila1.esIgual(pila2));
    }
}
