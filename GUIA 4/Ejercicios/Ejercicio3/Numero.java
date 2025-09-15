package exp;

public class Numero {
    private double valor;

    public Numero(double valor) {
        setValor(valor); // usamos setValor para validar desde el inicio
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("El valor no puede ser negativo: " + valor);
        }
        this.valor = valor;
    }
}
