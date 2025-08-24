public abstract class Cuenta {
    protected double saldo;
    
    public Cuenta() {
        this.saldo = 0.0;
    }
    
    // Métodos abstractos que deben ser implementados por las subclases
    public abstract void depositar(double monto);
    public abstract void retirar(double monto);
    public abstract void consultar();
    
    // Método concreto común a todas las cuentas
    public double getSaldo() {
        return saldo;
    }
    
    // Método para mostrar información básica
    public String getInfoBasica() {
        return String.format("Saldo: S/.%.2f", saldo);
    }
}
