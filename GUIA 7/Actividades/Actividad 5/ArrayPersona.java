package defaul;

public class ArrayPersona {
    private Persona[] arreglo;
    private final int max = 128;
    private int tamano = 0;
    
    public ArrayPersona() {
        this.arreglo = new Persona[this.max];
    }
    
    public void buscarPorNombre(String nombre) {
        boolean encontrado = false;
        for (int i = 0; i < this.tamano; i++) {
            if (this.arreglo[i].getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("=== CONTACTO ENCONTRADO ===");
                System.out.println(this.arreglo[i]);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Contacto no encontrado: " + nombre);
        }
    }
    
    public void addArray(Persona persona) {
        if (this.tamano < max) {
            this.arreglo[this.tamano++] = persona;
        } else {
            System.out.println("Agenda llena, no se pueden agregar más contactos");
        }
    }
    
    public int getTamano() {
        return tamano;
    }
}
