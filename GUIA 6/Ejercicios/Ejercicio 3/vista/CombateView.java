package vista;

import modelo.Jugador;
import modelo.Enemigo;
import modelo.CombateModel;
import java.util.List;

public class CombateView {
    
    public void mostrarEstadoCombate(Jugador jugador, List<Enemigo> enemigos, Enemigo enemigoActual, int turno) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("TURNO " + turno);
        System.out.println("=".repeat(50));
        
        System.out.println("👤 JUGADOR:");
        System.out.printf("   %s (Nivel %d)\n", jugador.getNombre(), jugador.getNivel());
        System.out.printf("   Salud: %d/%d\n", jugador.getSalud(), jugador.getSaludMaxima());
        System.out.printf("   Arma equipada: %s\n", 
            jugador.getArmaEquipada() != null ? jugador.getArmaEquipada().getNombre() : "Ninguna");
        
        mostrarBarraSalud(jugador.getSalud(), jugador.getSaludMaxima(), "Jugador");
        
        System.out.println("\n👹 ENEMIGOS:");
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo enemigo = enemigos.get(i);
            String indicadorActual = (enemigo == enemigoActual) ? " ⚔️" : "";
            System.out.printf("   %d. %s (Nivel %d) %s\n", 
                i + 1, enemigo.getNombre(), enemigo.getNivel(), indicadorActual);
            System.out.printf("      Salud: %d/%d - Tipo: %s\n", 
                enemigo.getSalud(), enemigo.getSaludMaxima(), enemigo.getTipo());
            mostrarBarraSalud(enemigo.getSalud(), enemigo.getSaludMaxima(), enemigo.getNombre());
        }
        System.out.println();
    }

    private void mostrarBarraSalud(int saludActual, int saludMaxima, String nombre) {
        int porcentaje = (int) ((double) saludActual / saludMaxima * 100);
        int barras = porcentaje / 5;
        
        String barra = "█".repeat(barras) + "░".repeat(20 - barras);
        String color = porcentaje > 50 ? "🟢" : porcentaje > 25 ? "🟡" : "🔴";
        
        System.out.printf("      %s [%s] %d%%\n", color, barra, porcentaje);
    }

    public void mostrarMensajeCombate(String mensaje) {
        System.out.println("💥 " + mensaje);
    }

    public void mostrarAccionEnemigo(String accion) {
        System.out.println("👹 " + accion);
    }

    public void mostrarResultadoCombate(String resultado) {
        System.out.println("\n" + "⭐".repeat(25));
        System.out.println(resultado);
        System.out.println("⭐".repeat(25) + "\n");
    }

    public void mostrarMenuCombate() {
        System.out.println("\n ACCIONES DE COMBATE:");
        System.out.println("1.   Atacar");
        System.out.println("2.  Usar objeto");
        System.out.println("3.  Cambiar arma");
        System.out.println("4.  Ver estado completo");
        System.out.println("5.  Huir del combate");
        System.out.print("Seleccione una acción: ");
    }

    public void mostrarMenuObjetos() {
        System.out.println("\n INVENTARIO DE OBJETOS:");
        System.out.println("1. Usar poción de vida");
        System.out.println("2. Usar poción de mana");
        System.out.println("3. Usar antídoto");
        System.out.println("4. Volver al menú principal");
        System.out.print("Seleccione un objeto: ");
    }

    public void mostrarMenuArmas() {
        System.out.println("\n⚔️  SELECCIONAR ARMA:");
        System.out.println("1. Espada");
        System.out.println("2. Arco");
        System.out.println("3. Daga");
        System.out.println("4. Volver al menú principal");
        System.out.print("Seleccione un arma: ");
    }

    public void mostrarMensajeError(String mensaje) {
        System.out.println(" Error: " + mensaje);
    }

    public void mostrarSeparador() {
        System.out.println("-".repeat(50));
    }
}
