package modelo;

import java.util.Random;

public class Enemigo {
    private String nombre;
    private int salud;
    private int saludMaxima;
    private int nivel;
    private String tipo;
    private Random random;

    public Enemigo(String nombre, int nivel, String tipo) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = tipo;
        this.saludMaxima = 50 + (nivel * 15);
        this.salud = saludMaxima;
        this.random = new Random();
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
    public int getSaludMaxima() { return saludMaxima; }
    public int getNivel() { return nivel; }
    public String getTipo() { return tipo; }

    // Métodos de combate
    public int atacar() {
        int dañoBase = 8 + (nivel * 2);
        
        switch (tipo) {
            case "Jefe":
                dañoBase += 10;
                break;
            case "Élite":
                dañoBase += 5;
                break;
        }
        
        int variacion = (int)(dañoBase * 0.2);
        return dañoBase - variacion + random.nextInt(variacion * 2 + 1);
    }

    public void recibirDaño(int daño) {
        this.salud -= daño;
        if (this.salud < 0) {
            this.salud = 0;
        }
    }

    public boolean estaVivo() {
        return salud > 0;
    }

    public String realizarAccionAleatoria() {
        int accion = random.nextInt(3);
        switch (accion) {
            case 0: return "se prepara para atacar";
            case 1: return "gruñe amenazadoramente";
            case 2: return "busca una apertura";
            default: return "observa atentamente";
        }
    }
}
