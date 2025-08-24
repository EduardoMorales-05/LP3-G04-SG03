public class Motor {
    private int numMotor;
    private int revolucionesPorMin;
    
    public Motor(int numMotor, int revolucionesPorMin) {
        this.numMotor = numMotor;
        this.revolucionesPorMin = revolucionesPorMin;
    }
    
    // Getters y Setters
    public int getNumMotor() {
        return numMotor;
    }
    
    public void setNumMotor(int numMotor) {
        this.numMotor = numMotor;
    }
    
    public int getRevoluciones() {
        return revolucionesPorMin;
    }
    
    public void setRevoluciones(int revolucionesPorMin) {
        this.revolucionesPorMin = revolucionesPorMin;
    }
    
    // Método toString
    @Override
    public String toString() {
        return "Motor [Número: " + numMotor + ", RPM: " + revolucionesPorMin + "]";
    }
}
