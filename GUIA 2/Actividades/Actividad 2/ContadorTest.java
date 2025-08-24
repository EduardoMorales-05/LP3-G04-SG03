public class ContadorTest {
    public static void main(String[] args) {
        Contador c1, c2;
        
        System.out.println("Acumulador inicial: " + Contador.acumulador());
        System.out.println("Contadores creados: " + Contador.getNContadores());
        System.out.println("Último contador: " + Contador.getUltimoContador());
        
        c1 = new Contador(3);
        System.out.println("\nDespués de crear c1(3):");
        System.out.println("Contadores creados: " + Contador.getNContadores());
        System.out.println("Último contador: " + Contador.getUltimoContador());
        
        c2 = new Contador(10);
        System.out.println("\nDespués de crear c2(10):");
        System.out.println("Contadores creados: " + Contador.getNContadores());
        System.out.println("Último contador: " + Contador.getUltimoContador());
        
        c1.inc();
        c1.inc();
        c2.inc();
        
        System.out.println("\nValor de c1: " + c1.getValor());
        System.out.println("Valor de c2: " + c2.getValor());
        System.out.println("Acumulador total: " + Contador.acumulador());
        
        // Crear instancia usando constructor por defecto
        Contador c3 = new Contador();
        System.out.println("\nDespués de crear c3 (constructor por defecto):");
        System.out.println("Valor de c3: " + c3.getValor());
        System.out.println("Acumulador después de c3: " + Contador.acumulador());
        System.out.println("Contadores creados: " + Contador.getNContadores());
        System.out.println("Último contador: " + Contador.getUltimoContador());
    }
}
