interface Forma {
    void dibujar();
}

class Circulo implements Forma {
    @Override
    public void dibujar() {
        System.out.println("Dibujando un círculo");
    }
}

class Rectangulo implements Forma {
    @Override
    public void dibujar() {
        System.out.println("Dibujando un rectángulo");
    }
}

class Triangulo implements Forma {
    @Override
    public void dibujar() {
        System.out.println("Dibujando un triángulo");
    }
}

public class Main {
    public static void main(String[] args) {
        Forma[] formas = {
            new Circulo(),
            new Rectangulo(),
            new Triangulo() // Nueva forma sin modificar las existentes
        };

        for (Forma forma : formas) {
            forma.dibujar();
        }
    }
}
