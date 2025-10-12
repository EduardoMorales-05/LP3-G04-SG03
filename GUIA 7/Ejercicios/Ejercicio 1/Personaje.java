package ejercicios;

import java.io.*;
import java.util.*;

public class Personaje {
    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private int alcance;

    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        if (vida <= 0 || ataque <= 0 || defensa <= 0 || alcance <= 0) {
            throw new IllegalArgumentException("Todos los atributos deben ser mayores a 0");
        }
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.alcance = alcance;
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getAlcance() { return alcance; }

    // Setters
    public void setVida(int vida) { 
        if (vida > 0) this.vida = vida; 
    }
    public void setAtaque(int ataque) { 
        if (ataque > 0) this.ataque = ataque; 
    }
    public void setDefensa(int defensa) { 
        if (defensa > 0) this.defensa = defensa; 
    }
    public void setAlcance(int alcance) { 
        if (alcance > 0) this.alcance = alcance; 
    }

    @Override
    public String toString() {
        return String.format("%-12s %-6d %-8d %-10d %-8d", 
            nombre, vida, ataque, defensa, alcance);
    }

    public String toFileString() {
        return nombre + "," + vida + "," + ataque + "," + defensa + "," + alcance;
    }

    public static Personaje fromFileString(String line) {
        String[] parts = line.split(",");
        return new Personaje(parts[0], 
            Integer.parseInt(parts[1]), 
            Integer.parseInt(parts[2]), 
            Integer.parseInt(parts[3]), 
            Integer.parseInt(parts[4]));
    }
}
