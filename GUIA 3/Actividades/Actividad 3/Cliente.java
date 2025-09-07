public class Cliente {
    private String nombre;
    private String contacto;

    public Cliente(String nombre, String contacto) {
        this.nombre = nombre;
        this.contacto = contacto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContacto() {
        return contacto;
    }

    @Override
    public String toString() {
        return nombre + " (" + contacto + ")";
    }
}
