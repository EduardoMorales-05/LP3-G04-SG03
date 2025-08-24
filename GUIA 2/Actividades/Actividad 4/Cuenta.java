public class Cuenta {
    private int numero;
    private double saldo;
    
    public Cuenta(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }
    
    public Cuenta(int numero) {
        this(numero, 0);                                         
    }
    
    // Getters y Setters
    public int getNumCuenta() {
        return numero;
    }
    
    public void setNumCuenta(int numero) {
        this.numero = numero;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    // Método toString
    @Override
    public String toString() {
        return "Cuenta [Número: " + numero + ", Saldo: S/." + saldo + "]";
    }
}
