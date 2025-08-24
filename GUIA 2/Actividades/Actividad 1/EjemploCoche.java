public class EjemploCoche {
    public static void main(String[] args) {
        // Crear objetos coche
        Coche cocheDeportivo = new Coche("Ferrari", "488 GTB", 2022, 250000.0);
        Coche cocheTodoTerreno = new Coche("Toyota", "Land Cruiser", 2008, 45000.0);
        
        // Encender los coches
        System.out.println("=== ENCENDIENDO COCHES ===");
        cocheDeportivo.encender();
        cocheTodoTerreno.encender();
        
        // Acelerar y frenar los coches
        System.out.println("\n=== ACELERANDO COCHES ===");
        cocheDeportivo.acelerar(50);
        cocheTodoTerreno.acelerar(30);
        
        System.out.println("\n=== ACELERANDO MÁS ===");
        cocheDeportivo.acelerar(70);
        cocheTodoTerreno.acelerar(40);
        
        System.out.println("\n=== FRENANDO COCHES ===");
        cocheDeportivo.frenar(40);
        cocheTodoTerreno.frenar(20);
        
        System.out.println("\n=== FRENANDO COMPLETAMENTE ===");
        cocheDeportivo.frenar(80); // Frenar completamente
        cocheTodoTerreno.frenar(50); // Frenar completamente
        
        // Apagar los coches
        System.out.println("\n=== APAGANDO COCHES ===");
        cocheDeportivo.apagar();
        cocheTodoTerreno.apagar();
        
        // Verificar funcionamiento de getters y método aplicarDescuento
        System.out.println("\n=== INFORMACIÓN DE LOS COCHES ===");
        System.out.println("Coche Deportivo - Marca: " + cocheDeportivo.getMarca() + 
                          ", Año: " + cocheDeportivo.getAñoFabricacion() + 
                          ", Precio: $" + cocheDeportivo.getPrecio());
        
        System.out.println("Coche Todo Terreno - Marca: " + cocheTodoTerreno.getMarca() + 
                          ", Año: " + cocheTodoTerreno.getAñoFabricacion() + 
                          ", Precio: $" + cocheTodoTerreno.getPrecio());
        
        // Aplicar descuentos
        System.out.println("\n=== APLICANDO DESCUENTOS ===");
        double descuento = 5000.0;
        
        boolean descuentoDeportivo = cocheDeportivo.aplicarDescuento(descuento);
        boolean descuentoTodoTerreno = cocheTodoTerreno.aplicarDescuento(descuento);
        
        System.out.println("Descuento aplicado al Ferrari (2022): " + descuentoDeportivo);
        System.out.println("Descuento aplicado al Toyota (2008): " + descuentoTodoTerreno);
        
        // Mostrar precios actualizados
        System.out.println("\n=== PRECIOS ACTUALIZADOS ===");
        System.out.println("Precio Ferrari: $" + cocheDeportivo.getPrecio());
        System.out.println("Precio Toyota: $" + cocheTodoTerreno.getPrecio());
    }
}
