public class CuentaAhorro extends Cuenta {
    private double tasaInteres;
    private double minSaldo;
    
    public CuentaAhorro() {
        super();
        this.tasaInteres = 1.5; // 1.5% por defecto
        this.minSaldo = Double.MAX_VALUE;
    }
    
    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }
    
    public double getTasaInteres() {
        return tasaInteres;
    }
    
    @Override
    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            actualizarSaldoMinimo();
            System.out.printf("✓ Depósito exitoso: S/.%.2f%n", monto);
        } else {
            System.out.println("✗ Error: El monto debe ser positivo");
        }
    }
    
    @Override
    public void retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            actualizarSaldoMinimo();
            System.out.printf("✓ Retiro exitoso: S/.%.2f%n", monto);
        } else if (monto > saldo) {
            System.out.printf("✗ Fondos insuficientes. Saldo actual: S/.%.2f%n", saldo);
        } else {
            System.out.println("✗ Error: El monto debe ser positivo");
        }
    }
    
    @Override
    public void consultar() {
        double interes = minSaldo * tasaInteres / 100;
        if (interes > 0) {
            saldo += interes;
            System.out.printf("✓ Interés capitalizado: S/.%.2f (%.1f%% sobre S/.%.2f)%n", 
                            interes, tasaInteres, minSaldo);
        }
        minSaldo = saldo; // Reiniciar para el próximo mes
        System.out.printf("✓ Consulta realizada. %s%n", getInfoBasica());
    }
    
    private void actualizarSaldoMinimo() {
        if (saldo < minSaldo) {
            minSaldo = saldo;
        }
    }
    
    @Override
    public String toString() {
        return String.format("Cuenta Ahorro [%s, Tasa: %.1f%%, Saldo Mínimo: S/.%.2f]", 
                           getInfoBasica(), tasaInteres, minSaldo);
    }
}
