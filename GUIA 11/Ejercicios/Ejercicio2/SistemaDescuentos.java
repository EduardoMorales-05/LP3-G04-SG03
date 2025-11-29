package lp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

// --- CLASES DEL SISTEMA DE DESCUENTOS (STRATEGY) ---

// Clase Producto
class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return nombre + " ($" + precio + ")";
    }
}

// Interfaz Strategy
interface EstrategiaDescuento {
    double calcularTotal(List<Producto> productos);
}

// Estrategia 1: Sin Descuento
class SinDescuento implements EstrategiaDescuento {
    @Override
    public double calcularTotal(List<Producto> productos) {
        double total = 0;
        for (Producto p : productos) {
            total += p.getPrecio();
        }
        return total;
    }
}

// Estrategia 2: Descuento Fijo (10%)
class DescuentoFijo implements EstrategiaDescuento {
    @Override
    public double calcularTotal(List<Producto> productos) {
        double total = 0;
        for (Producto p : productos) {
            total += p.getPrecio();
        }
        return total * 0.90; // 10% de descuento
    }
}

// Estrategia 3: Descuento Porcentual (30% si hay 2 productos iguales)
class DescuentoPorcentual implements EstrategiaDescuento {
    @Override
    public double calcularTotal(List<Producto> productos) {
        double total = 0;
        boolean hayDuplicados = false;
        Set<String> nombres = new HashSet<>();

        for (Producto p : productos) {
            total += p.getPrecio();
            if (!nombres.add(p.getNombre())) {
                hayDuplicados = true;
            }
        }

        if (hayDuplicados) {
            System.out.println("[Info] Se encontraron productos duplicados. Aplicando 30% de descuento.");
            return total * 0.70;
        } else {
            System.out.println("[Info] No hay productos duplicados. No se aplica descuento.");
            return total;
        }
    }
}

// Estrategia 4: Descuento Porcentual Acumulado (50% en el mas bajo si >= 3 productos)
class DescuentoPorcentualAcumulado implements EstrategiaDescuento {
    @Override
    public double calcularTotal(List<Producto> productos) {
        double total = 0;
        if (productos.isEmpty()) return 0;

        if (productos.size() >= 3) {
            Producto masBarato = Collections.min(productos, Comparator.comparingDouble(Producto::getPrecio));
            for (Producto p : productos) {
                if (p == masBarato) {
                    total += p.getPrecio() * 0.50; // 50% descuento en el mas barato
                    masBarato = null; // Asegurar que solo se aplique una vez si hay varios con mismo precio minimo
                } else {
                    total += p.getPrecio();
                }
            }
            System.out.println("[Info] Mas de 3 productos. Aplicando 50% de descuento al producto mas barato.");
        } else {
            System.out.println("[Info] Menos de 3 productos. No se aplica descuento acumulado.");
            for (Producto p : productos) {
                total += p.getPrecio();
            }
        }
        return total;
    }
}

// Contexto: Calculadora de Precios
class CalculadoraDePrecios {
    private EstrategiaDescuento estrategia;

    public void setEstrategia(EstrategiaDescuento estrategia) {
        this.estrategia = estrategia;
    }

    public double calcular(List<Producto> productos) {
        if (estrategia == null) {
            estrategia = new SinDescuento();
        }
        return estrategia.calcularTotal(productos);
    }
}

// Clase Principal (Main)
public class SistemaDescuentos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Producto> carrito = new ArrayList<>();
        CalculadoraDePrecios calculadora = new CalculadoraDePrecios();
        int opcion = -1;

        System.out.println("==========================================");
        System.out.println("   SISTEMA DE DESCUENTOS (STRATEGY)       ");
        System.out.println("==========================================");

        while (opcion != 0) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Agregar producto al carrito");
            System.out.println("2. Ver carrito");
            System.out.println("3. Calcular precio final");
            System.out.println("4. Limpiar carrito");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir newline
            } else {
                System.out.println("Entrada inválida.");
                scanner.next();
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Precio: ");
                    if (scanner.hasNextDouble()) {
                        double precio = scanner.nextDouble();
                        System.out.print("Cantidad: ");
                        if (scanner.hasNextInt()) {
                            int cantidad = scanner.nextInt();
                            if (cantidad > 0) {
                                for (int i = 0; i < cantidad; i++) {
                                    carrito.add(new Producto(nombre, precio));
                                }
                                System.out.println(cantidad + " producto(s) agregado(s).");
                            } else {
                                System.out.println("Cantidad debe ser mayor a 0.");
                            }
                        } else {
                            System.out.println("Cantidad inválida.");
                            scanner.next();
                        }
                    } else {
                        System.out.println("Precio inválido.");
                        scanner.next();
                    }
                    break;

                case 2:
                    System.out.println("--- Carrito de Compras ---");
                    if (carrito.isEmpty()) {
                        System.out.println("(Vacio)");
                    } else {
                        for (Producto p : carrito) {
                            System.out.println("- " + p);
                        }
                    }
                    break;

                case 3:
                    if (carrito.isEmpty()) {
                        System.out.println("El carrito esta vacio.");
                        break;
                    }
                    System.out.println("Seleccione Estrategia:");
                    System.out.println("1. Sin Descuento");
                    System.out.println("2. Descuento Fijo (10%)");
                    System.out.println("3. Descuento Porcentual (30% si hay 2 iguales)");
                    System.out.println("4. Descuento Acumulado (50% en el mas bajo si >= 3 prod)");
                    System.out.print("Opcion: ");
                    
                    int est = scanner.nextInt();
                    switch (est) {
                        case 1: calculadora.setEstrategia(new SinDescuento()); break;
                        case 2: calculadora.setEstrategia(new DescuentoFijo()); break;
                        case 3: calculadora.setEstrategia(new DescuentoPorcentual()); break;
                        case 4: calculadora.setEstrategia(new DescuentoPorcentualAcumulado()); break;
                        default: 
                            System.out.println("Opcion no valida, usando Sin Descuento.");
                            calculadora.setEstrategia(new SinDescuento());
                    }
                    
                    double total = calculadora.calcular(carrito);
                    System.out.printf("Total a Pagar: $%.2f\n", total);
                    break;

                case 4:
                    carrito.clear();
                    System.out.println("Carrito limpiado.");
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}
