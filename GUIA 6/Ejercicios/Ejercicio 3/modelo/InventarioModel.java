package modelo;

import java.util.ArrayList;
import java.util.List;

public class InventarioModel {
    private List<Item> items;

    public InventarioModel() {
        this.items = new ArrayList<>();
    }

    public void agregarItem(Item item) {
        Item itemExistente = buscarItem(item.getNombre());
        if (itemExistente != null) {
            itemExistente.setCantidad(itemExistente.getCantidad() + item.getCantidad());
        } else {
            items.add(item);
        }
    }

    public void eliminarItem(Item item) {
        items.remove(item);
    }

    public boolean eliminarItemPorNombre(String nombre) {
        Item item = buscarItem(nombre);
        if (item != null) {
            items.remove(item);
            return true;
        }
        return false;
    }

    public boolean eliminarItemPorIndice(int indice) {
        if (indice >= 0 && indice < items.size()) {
            items.remove(indice);
            return true;
        }
        return false;
    }

    public List<Item> obtenerItems() {
        return new ArrayList<>(items);
    }

    public Item buscarItem(String nombre) {
        for (Item item : items) {
            if (item.getNombre().equalsIgnoreCase(nombre)) {
                return item;
            }
        }
        return null;
    }

    public Item buscarPorIndice(int indice) {
        if (indice >= 0 && indice < items.size()) {
            return items.get(indice);
        }
        return null;
    }

    public int getTotalItems() {
        return items.size();
    }

    public int obtenerIndicePorNombre(String nombre) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }
}
