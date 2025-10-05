package modelo;

import java.util.List;

public class Jugador {
    private String nombre;
    private int salud;
    private int saludMaxima;
    private int nivel;
    private InventarioModel inventario;
    private Item armaEquipada;

    public Jugador(String nombre, int nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.saludMaxima = 100 + (nivel * 20);
        this.salud = saludMaxima;
        this.inventario = new InventarioModel();
        this.armaEquipada = null;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
    public int getSaludMaxima() { return saludMaxima; }
    public int getNivel() { return nivel; }
    public InventarioModel getInventario() { return inventario; }
    public Item getArmaEquipada() { return armaEquipada; }

    public void setArmaEquipada(Item arma) {
        if (arma != null && arma.getTipo().equals("Arma")) {
            this.armaEquipada = arma;
        }
    }

    // Métodos de combate
    public int atacar() {
        int dañoBase = 10 + (nivel * 2);
        if (armaEquipada != null) {
            dañoBase += 15; // Bonus por arma equipada
        }
        return dañoBase;
    }

    public void recibirDaño(int daño) {
        this.salud -= daño;
        if (this.salud < 0) {
            this.salud = 0;
        }
    }

    public void usarItem(String nombreItem) {
        Item item = inventario.buscarItem(nombreItem);
        if (item != null && item.getTipo().equals("Poción")) {
            item.usarItem();
            int curacion = 30;
            this.salud += curacion;
            if (this.salud > this.saludMaxima) {
                this.salud = this.saludMaxima;
            }
            System.out.println(nombre + " usa " + nombreItem + " y recupera " + curacion + " de salud.");
            
            if (item.getCantidad() == 0) {
                inventario.eliminarItemPorNombre(nombreItem);
            }
        }
    }

    public boolean estaVivo() {
        return salud > 0;
    }

    public void curarCompletamente() {
        this.salud = this.saludMaxima;
    }

    public void subirNivel() {
        this.nivel++;
        this.saludMaxima = 100 + (nivel * 20);
        this.salud = saludMaxima;
    }
}
