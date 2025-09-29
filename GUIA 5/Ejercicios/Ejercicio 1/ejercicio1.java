class Par<F, S> {
    private F primero;
    private S segundo;

    public Par(F primero, S segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }

    public F getPrimero() { return primero; }
    public S getSegundo() { return segundo; }

    public void setPrimero(F primero) { this.primero = primero; }
    public void setSegundo(S segundo) { this.segundo = segundo; }

    @Override
    public String toString() {
        return "(Primero: " + primero + ", Segundo: " + segundo + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        Par<String, Integer> p1 = new Par<>("Edad", 20);
        Par<Double, Boolean> p2 = new Par<>(15.5, true);

        System.out.println(p1);
        System.out.println(p2);
    }
}
