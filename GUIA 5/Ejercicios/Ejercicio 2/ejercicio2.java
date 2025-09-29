class Par<F, S> {
    private F primero;
    private S segundo;

    public Par(F primero, S segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }

    public boolean esIgual(Par<F, S> otro) {
        return this.primero.equals(otro.primero) && this.segundo.equals(otro.segundo);
    }

    @Override
    public String toString() {
        return "(Primero: " + primero + ", Segundo: " + segundo + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        Par<String, Integer> p1 = new Par<>("Edad", 20);
        Par<String, Integer> p2 = new Par<>("Edad", 20);
        Par<String, Integer> p3 = new Par<>("Peso", 70);

        System.out.println(p1);
        System.out.println(p2);
        System.out.println("¿p1 es igual a p2? " + p1.esIgual(p2));
        System.out.println("¿p1 es igual a p3? " + p1.esIgual(p3));
    }
}

