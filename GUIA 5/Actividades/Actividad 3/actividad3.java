class IgualGenerico {
    public static <T> boolean esIgualA(T a, T b) {
        if (a == null || b == null) return false;
        return a.equals(b);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(IgualGenerico.esIgualA(5, 5));        // true
        System.out.println(IgualGenerico.esIgualA("Hola", "Hola")); // true
        System.out.println(IgualGenerico.esIgualA(null, "algo"));   // false
        System.out.println(IgualGenerico.esIgualA(10, 20));         // false
    }
}
