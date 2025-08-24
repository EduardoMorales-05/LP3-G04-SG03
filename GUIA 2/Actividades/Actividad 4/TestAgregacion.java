public class TestAgregacion {
    public static void main(String[] args) {
        // Crear motores
        Motor motor1 = new Motor(12345, 3000);
        Motor motor2 = new Motor(67890, 3500);
        Motor motor3 = new Motor(11111, 2800);
        
        // Crear automóviles
        Automovil auto1 = new Automovil("ABC123", 4, "Toyota", "Corolla");
        Automovil auto2 = new Automovil("XYZ789", 2, "Honda", "Civic");
        Automovil auto3 = new Automovil("DEF456", 5, "Nissan", "Sentra");
        Automovil auto4 = new Automovil("GHI789", 4, "Ford", "Focus");
        
        // Asignar motores a los automóviles (Relación de agregación)
        auto1.setMotor(motor1);
        auto2.setMotor(motor2);
        auto3.setMotor(motor3);
        // auto4 queda sin motor asignado
        
        // Mostrar información de los automóviles
        System.out.println("=== INFORMACIÓN DE AUTOMÓVILES ===");
        System.out.println(auto1.toString());
        System.out.println(auto2.toString());
        System.out.println(auto3.toString());
        System.out.println(auto4.toString());
        
        System.out.println("\n=== MODIFICANDO ATRIBUTOS ===");
        
        // Modificar algunos atributos
        auto1.setNumPuertas(5);
        motor1.setRevoluciones(3200);
        
        auto4.setMotor(new Motor(99999, 4000)); // Asignar motor a auto4
        auto4.setPlaca("JKL012");
        
        System.out.println("\n=== INFORMACIÓN ACTUALIZADA ===");
        System.out.println(auto1.toString());
        System.out.println(auto4.toString());
        
        System.out.println("\n=== INFORMACIÓN DE MOTORES ===");
        System.out.println("Motor 1: " + motor1.toString());
        System.out.println("Motor 2: " + motor2.toString());
        System.out.println("Motor 3: " + motor3.toString());
        
        // Demostrar que los motores pueden existir independientemente
        System.out.println("\n=== MOTORES INDEPENDIENTES ===");
        System.out.println("El motor " + motor2.getNumMotor() + " existe independientemente de los automóviles");
        System.out.println("El motor " + motor3.getNumMotor() + " puede ser reasignado a otro automóvil");
    }
}
