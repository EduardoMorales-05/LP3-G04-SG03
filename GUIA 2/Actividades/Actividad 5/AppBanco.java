import java.util.Scanner;

public class AppBanco {
    private static Cuenta[] cuentas;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        inicializarCuentas();
        mostrarMenuPrincipal();
    }
    
    private static void inicializarCuentas() {
        cuentas = new Cuenta[10];
        
        // Crear 5 cuentas de ahorro
        for (int i = 0; i < 5; i++) {
            CuentaAhorro cuenta = new CuentaAhorro();
            cuenta.setTasaInteres(1.5 + (i * 0.5)); // Tasas diferentes: 1.5%, 2.0%, 2.5%, etc.
            cuentas[i] = cuenta;
        }
        
        // Crear 5 cuentas corrientes
        for (int i = 5; i < 10; i++) {
            cuentas[i] = new CuentaCorriente();
        }
        
        // Depositar saldos iniciales
        for (int i = 0; i < 10; i++) {
            cuentas[i].depositar(1000.0 + (i * 500.0));
        }
    }
    
    private static void mostrarMenuPrincipal() {
        boolean salir = false;
        
        while (!salir) {
            System.out.println("\n=== SISTEMA BANCARIO ===");
            System.out.println("D)epositar");
            System.out.println("R)etirar");
            System.out.println("C)onsultar");
            System.out.println("E)stado de Cuentas");
            System.out.println("I)nformación Detallada");
            System.out.println("S)alir");
            System.out.print("Seleccione una opción: ");
            
            String opcion = scanner.next().toUpperCase();
            
            switch (opcion) {
                case "D":
                    realizarOperacion("depositar");
                    break;
                case "R":
                    realizarOperacion("retirar");
                    break;
                case "C":
                    consultarTodasCuentas();
                    break;
                case "E":
                    mostrarEstadoCuentas();
                    break;
                case "I":
                    mostrarInformacionDetallada();
                    break;
                case "S":
                    salir = true;
                    System.out.println("¡Gracias por usar el sistema bancario!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
    
    private static void realizarOperacion(String tipoOperacion) {
        try {
            System.out.print("Ingrese número de cuenta (0-9): ");
            int numeroCuenta = scanner.nextInt();
            
            if (numeroCuenta < 0 || numeroCuenta >= cuentas.length) {
                System.out.println("Número de cuenta no válido.");
                return;
            }
            
            System.out.print("Ingrese monto: S/.");
            double monto = scanner.nextDouble();
            
            if (tipoOperacion.equals("depositar")) {
                cuentas[numeroCuenta].depositar(monto);
            } else {
                cuentas[numeroCuenta].retirar(monto);
            }
            
            System.out.printf("Saldo actual: S/.%.2f%n", cuentas[numeroCuenta].getSaldo());
            
        } catch (Exception e) {
            System.out.println("Error: Entrada no válida.");
            scanner.nextLine(); // Limpiar buffer
        }
    }
    
    private static void consultarTodasCuentas() {
        System.out.println("\n=== CONSULTA GENERAL ===");
        for (int i = 0; i < cuentas.length; i++) {
            System.out.printf("Cuenta %d: ", i);
            cuentas[i].consultar();
        }
        System.out.println(" Todas las cuentas han sido consultadas y actualizadas");
    }
    
    private static void mostrarEstadoCuentas() {
        System.out.println("\n=== ESTADO DE CUENTAS ===");
        System.out.println("No. | Tipo           | Saldo        ");
        System.out.println("----|----------------|--------------");
        
        for (int i = 0; i < cuentas.length; i++) {
            String tipo = (i < 5) ? "Ahorro" : "Corriente";
            System.out.printf("%2d  | %-14s | S/.%9.2f%n", 
                            i, tipo, cuentas[i].getSaldo());
        }
        
        // Mostrar totales
        double totalAhorro = 0, totalCorriente = 0;
        for (int i = 0; i < 5; i++) totalAhorro += cuentas[i].getSaldo();
        for (int i = 5; i < 10; i++) totalCorriente += cuentas[i].getSaldo();
        
        System.out.println("----|----------------|--------------");
        System.out.printf("     | Total Ahorro   | S/.%9.2f%n", totalAhorro);
        System.out.printf("     | Total Corriente| S/.%9.2f%n", totalCorriente);
        System.out.printf("     | TOTAL GENERAL  | S/.%9.2f%n", totalAhorro + totalCorriente);
    }
    
    private static void mostrarInformacionDetallada() {
        System.out.println("\n=== INFORMACIÓN DETALLADA ===");
        for (int i = 0; i < cuentas.length; i++) {
            System.out.printf("Cuenta %d: %s%n", i, cuentas[i].toString());
        }
        
        // Información específica por tipo de cuenta
        System.out.println("\n=== RESUMEN POR TIPO ===");
        System.out.println("Cuentas de Ahorro (0-4):");
        for (int i = 0; i < 5; i++) {
            CuentaAhorro cuenta = (CuentaAhorro) cuentas[i];
            System.out.printf("  Cuenta %d: Tasa %.1f%%%n", i, cuenta.getTasaInteres());
        }
        
        System.out.println("\nCuentas Corriente (5-9):");
        for (int i = 5; i < 10; i++) {
            CuentaCorriente cuenta = (CuentaCorriente) cuentas[i];
            System.out.printf("  Cuenta %d: %d retiros este mes%n", i, cuenta.getRetiros());
        }
    }
}
