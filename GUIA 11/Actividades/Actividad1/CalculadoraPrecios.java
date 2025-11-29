package lp;

// Interfaz Strategy
interface EstrategiaPrecio {
    double calcular(double precioBase, int cantidad, double precioMasBajo);
}

// Estrategias Concretas
class SinDescuento implements EstrategiaPrecio {
    @Override
    public double calcular(double precioBase, int cantidad, double precioMasBajo) {
        return precioBase;
    }
}

class DescuentoFijo implements EstrategiaPrecio {
    @Override
    public double calcular(double precioBase, int cantidad, double precioMasBajo) {
        return precioBase * 0.90; // 10% de descuento
    }
}

class DescPorcentual implements EstrategiaPrecio {
    @Override
    public double calcular(double precioBase, int cantidad, double precioMasBajo) {
        if (cantidad >= 2) {
            return precioBase * 0.70; // 30% de descuento
        }
        return precioBase;
    }
}

class DescAcumulado implements EstrategiaPrecio {
    @Override
    public double calcular(double precioBase, int cantidad, double precioMasBajo) {
        if (cantidad >= 3) {
            // 50% de descuento en el producto más bajo
            return (precioBase - precioMasBajo) + (precioMasBajo * 0.50);
        }
        return precioBase;
    }
}

// Contexto
public class CalculadoraPrecios {
    private EstrategiaPrecio estrategia;

    public void setEstrategia(EstrategiaPrecio estrategia) {
        this.estrategia = estrategia;
    }

    public double calcularPrecioFinal(double precioBase, int cantidad, double precioMasBajo) {
        if (estrategia == null) {
            throw new IllegalStateException("Estrategia no establecida");
        }
        return estrategia.calcular(precioBase, cantidad, precioMasBajo);
    }
}
