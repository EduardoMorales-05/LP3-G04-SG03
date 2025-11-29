package lp;

// --- PATRÓN STRATEGY ---

// Interfaz Estrategia
interface EstrategiaDescuento {
    double calcularTotal(double precioBase, int cantidad);
}

// Estrategias Concretas
class SinDescuento implements EstrategiaDescuento {
    @Override
    public double calcularTotal(double precioBase, int cantidad) {
        return precioBase * cantidad;
    }
}

class DescuentoFlash implements EstrategiaDescuento {
    @Override
    public double calcularTotal(double precioBase, int cantidad) {
        // 10% de descuento fijo
        return (precioBase * cantidad) * 0.90;
    }
}

class DescuentoVIP implements EstrategiaDescuento {
    @Override
    public double calcularTotal(double precioBase, int cantidad) {
        // 20% de descuento para clientes VIP
        return (precioBase * cantidad) * 0.80;
    }
}

class DescuentoLiquidacion implements EstrategiaDescuento {
    @Override
    public double calcularTotal(double precioBase, int cantidad) {
        // 50% de descuento en la segunda unidad (si lleva más de 1)
        // Simplificación: 25% de descuento global si cantidad >= 2
        if (cantidad >= 2) {
            return (precioBase * cantidad) * 0.75;
        }
        return precioBase * cantidad;
    }
}

// Contexto
class TerminalVenta {
    private EstrategiaDescuento estrategia;

    public void setEstrategia(EstrategiaDescuento estrategia) {
        this.estrategia = estrategia;
    }

    public double cobrar(double precioUnitario, int cantidad) {
        if (estrategia == null) {
            estrategia = new SinDescuento();
        }
        return estrategia.calcularTotal(precioUnitario, cantidad);
    }
}

// Clase Módulo (Helper)
public class ModuloVentas {
    private TerminalVenta terminal;

    public ModuloVentas() {
        this.terminal = new TerminalVenta();
    }

    public void realizarVenta(String producto, double precio, int cantidad, String tipoCliente) {
        System.out.println("Procesando venta de: " + producto + " x" + cantidad);

        // Selección de estrategia según tipo de cliente o evento
        switch (tipoCliente.toLowerCase()) {
            case "vip":
                System.out.println("Aplicando tarifa VIP");
                terminal.setEstrategia(new DescuentoVIP());
                break;
            case "flash":
                System.out.println("Aplicando tarifa Flash Sale");
                terminal.setEstrategia(new DescuentoFlash());
                break;
            case "liquidacion":
                System.out.println("Aplicando tarifa Liquidación");
                terminal.setEstrategia(new DescuentoLiquidacion());
                break;
            default:
                System.out.println("Tarifa estándar");
                terminal.setEstrategia(new SinDescuento());
        }

        double total = terminal.cobrar(precio, cantidad);
        System.out.printf("Total a pagar: $%.2f", total);
    }
}
