public class CuentaCorriente extends Cuenta {
    private int retiros;
    private static final int LIBRE_RETIROS = 3;
    private static final double TARIFA_TRANSACCION = 3.0;
    
    public CuentaCorriente() {
        super();
        this.retiros = 0;
    }
    
    public int getRetiros() {
        return retiros;
    }
    
    @Override
    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            System.out.printf("✓ Depósito exitoso: S/.%.2f%n", monto);
        } else {
            System.out.println("✗ Error: El monto debe ser positivo");
        }
    }
    
    @Override
    public void retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            retiros++;
            
            // Aplicar tarifa si excede los retiros gratuitos
            if (retiros > LIBRE_RETIROS) {
                saldo -= TARIFA_TRANSACCION;
                System.out.printf("✓ Retiro exitoso: S/.%.2f (Tarifa aplicada: S/.%.2f)%n", 
                                monto, TARIFA_TRANSACCION);
            } else {
                System.out.printf("✓ Retiro exitoso: S/.%.2f (Retiros gratuitos: %d/%d)%n", 
                                monto, retiros, LIBRE_RETIROS);
            }
        } else if (monto > saldo) {
            System.out.printf("✗ Fondos insuficientes. Saldo actual: S/.%.2f%n", saldo);
        } else {
            System.out.println("✗ Error: El monto debe ser positivo");
        }
    }
    
    @Override
    public void consultar() {
        System.out.printf("✓ Consulta realizada. %s%n", getInfoBasica());
        System.out.printf("   Retiros este mes: %d (%d gratuitos restantes)%n", 
                         retiros, Math.max(0, LIBRE_RETIROS - retiros));
        retiros = 0; // Reiniciar contador para el próximo mes
        System.out.println("   Contador de retiros reiniciado");
    }
    
    @Override
    public String toString() {
        return String.format("Cuenta Corriente [%s, Retiros: %d/%d, Tarifa: S/.%.2f]", 
                           getInfoBasica(), retiros, LIBRE_RETIROS, TARIFA_TRANSACCION);
    }
}
