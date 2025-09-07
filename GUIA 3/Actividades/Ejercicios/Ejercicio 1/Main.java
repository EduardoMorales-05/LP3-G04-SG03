class Empleado {
    private String nombre;
    private double salario;
    private String departamento;

    public Empleado(String nombre, double salario, String departamento) {
        this.nombre = nombre;
        this.salario = salario;
        this.departamento = departamento;
    }

    public double getSalario() {
        return salario;
    }
}

class CalculadoraPago {
    public double calcularPagoMensual(Empleado empleado) {
        return empleado.getSalario() - (empleado.getSalario() * 0.1);
    }
}

public class Main {
    public static void main(String[] args) {
        Empleado emp = new Empleado("Jose", 3000, "Ventas");
        CalculadoraPago calc = new CalculadoraPago();
        double pago = calc.calcularPagoMensual(emp);
        System.out.println("Pago mensual: " + pago);
    }
}
