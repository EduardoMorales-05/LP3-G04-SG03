package ejercicios;

public class PersonajeMejorado {
    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private int alcance;
    private int nivel;

    public PersonajeMejorado(String nombre, int vida, int ataque, int defensa, int alcance) {
        this(nombre, vida, ataque, defensa, alcance, 1);
    }

    public PersonajeMejorado(String nombre, int vida, int ataque, int defensa, int alcance, int nivel) {
        if (vida <= 0 || ataque <= 0 || defensa <= 0 || alcance <= 0 || nivel <= 0) {
            throw new IllegalArgumentException("Todos los atributos deben ser mayores a 0");
        }
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.alcance = alcance;
        this.nivel = nivel;
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getAlcance() { return alcance; }
    public int getNivel() { return nivel; }

    // Setters individuales
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
    public void setNivel(int nivel) {
        if (nivel > 0) this.nivel = nivel;
    }

    // Subir de nivel - mejora atributos
    public void subirNivel() {
        nivel++;
        // Mejorar atributos al subir de nivel
        vida = (int)(vida * 1.1); // +10%
        ataque = (int)(ataque * 1.1); // +10%
        defensa = (int)(defensa * 1.1); // +10%
        alcance = (int)(alcance * 1.05); // +5%
    }

    @Override
    public String toString() {
        return String.format("%-12s %-6d %-8d %-10d %-8d %-6d", 
            nombre, vida, ataque, defensa, alcance, nivel);
    }

    public String toFileString() {
        return nombre + "," + vida + "," + ataque + "," + defensa + "," + alcance + "," + nivel;
    }

    public static PersonajeMejorado fromFileString(String line) {
        String[] parts = line.split(",");
        return new PersonajeMejorado(
            parts[0], 
            Integer.parseInt(parts[1]), 
            Integer.parseInt(parts[2]), 
            Integer.parseInt(parts[3]), 
            Integer.parseInt(parts[4]),
            Integer.parseInt(parts[5])
        );
    }

    // Métodos para filtrado
    public int getAtributo(String tipo) {
        switch (tipo.toLowerCase()) {
            case "vida": return vida;
            case "ataque": return ataque;
            case "defensa": return defensa;
            case "alcance": return alcance;
            case "nivel": return nivel;
            default: return 0;
        }
    }
}
