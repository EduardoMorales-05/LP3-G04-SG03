public class Coche {
    // Atributos solicitados
    private String marca;
    private int añoFabricacion;
    private double precio;
    
    // Atributos para el funcionamiento del coche
    private boolean encendido;
    private int velocidad;
    
    // Constructores
    public Coche() {
        // Constructor por defecto
        this.marca = "";
        this.añoFabricacion = 0;
        this.precio = 0.0;
        this.encendido = false;
        this.velocidad = 0;
    }
    
    public Coche(String marca, String modelo, int añoFabricacion, double precio) {
        // Constructor con cuatro parámetros
        this.marca = marca;
        this.añoFabricacion = añoFabricacion;
        this.precio = precio;
        this.encendido = false;
        this.velocidad = 0;
    }
    
    // Métodos para el funcionamiento del coche
    public void encender() {
        if (!encendido) {
            encendido = true;
            System.out.println(marca + " encendido");
        } else {
            System.out.println("El " + marca + " ya está encendido");
        }
    }
    
    public void apagar() {
        if (encendido && velocidad == 0) {
            encendido = false;
            System.out.println(marca + " apagado");
        } else if (velocidad > 0) {
            System.out.println("No se puede apagar el " + marca + " en movimiento");
        } else {
            System.out.println("El " + marca + " ya está apagado");
        }
    }
    
    public void acelerar(int kmh) {
        if (encendido) {
            velocidad += kmh;
            System.out.println(marca + " acelerando. Velocidad: " + velocidad + " km/h");
        } else {
            System.out.println("Primero debe encender el " + marca);
        }
    }
    
    public void frenar(int kmh) {
        if (encendido && velocidad > 0) {
            velocidad = Math.max(0, velocidad - kmh);
            System.out.println(marca + " frenando. Velocidad: " + velocidad + " km/h");
        } else if (velocidad == 0) {
            System.out.println("El " + marca + " ya está detenido");
        } else {
            System.out.println("Primero debe encender el " + marca);
        }
    }
    
    // Método aplicarDescuento
    public boolean aplicarDescuento(double descuento) {
        if (añoFabricacion < 2010) {
            this.precio -= descuento;
            return true; // Descuento aplicado
        }
        return false; // No se aplicó descuento
    }
    
    // Getters y Setters
    public String getMarca() {
        return marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public int getAñoFabricacion() {
        return añoFabricacion;
    }
    
    public void setAñoFabricacion(int añoFabricacion) {
        this.añoFabricacion = añoFabricacion;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public boolean isEncendido() {
        return encendido;
    }
    
    public int getVelocidad() {
        return velocidad;
    }
}
