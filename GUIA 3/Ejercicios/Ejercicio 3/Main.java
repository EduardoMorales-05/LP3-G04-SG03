class Vehiculo {
    public void acelerar() {
        System.out.println("Acelerando...");
    }
}

class Coche extends Vehiculo {
    @Override
    public void acelerar() {
        System.out.println("Acelerando con motor");
    }
}

class Bicicleta extends Vehiculo {
    @Override
    public void acelerar() {
        System.out.println("Acelerando pedaleando");
    }
}

public class Main {
    public static void main(String[] args) {
        Vehiculo[] vehiculos = {
            new Vehiculo(),
            new Coche(),
            new Bicicleta()
        };

        for (Vehiculo v : vehiculos) {
            v.acelerar(); // Todas se comportan correctamente
        }
    }
}
