public class Automovil {
    private String placa;
    private int numPuertas;
    private String marca;
    private String modelo;
    private Motor motor;  // Relación de agregación
    
    public Automovil(String placa, int numPuertas, String marca, String modelo) {
        this.placa = placa;
        this.numPuertas = numPuertas;
        this.marca = marca;
        this.modelo = modelo;
    }
    
    // Getters y Setters
    public String getPlaca() {
        return placa;
    }
    
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
    public int getNumPuertas() {
        return numPuertas;
    }
    
    public void setNumPuertas(int numPuertas) {
        this.numPuertas = numPuertas;
    }
    
    public String getMarca() {
        return marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public String getModelo() {
        return modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public Motor getMotor() {
        return motor;
    }
    
    public void setMotor(Motor motor) {
        this.motor = motor;
    }
    
    // Método toString
    @Override
    public String toString() {
        String info = "Automóvil [Placa: " + placa + 
                     ", Puertas: " + numPuertas + 
                     ", Marca: " + marca + 
                     ", Modelo: " + modelo;
        
        if (motor != null) {
            info += ", " + motor.toString();
        } else {
            info += ", Sin motor asignado";
        }
        
        info += "]";
        return info;
    }
}
