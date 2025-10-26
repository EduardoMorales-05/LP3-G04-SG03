package Practica;

import java.util.*;
import java.util.stream.Collectors;

public class GestorProductos {
    private List<Producto> productos = new ArrayList<>();
    
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
    
    public void eliminarProducto(int id) {
        productos.removeIf(p -> p.getId() == id);
    }
    
    public List<Producto> obtenerTodos() {
        return new ArrayList<>(productos);
    }
    
    public List<Map<String, Object>> consultaAvanzada(
            List<String> camposMostrar,
            String campoFiltro, String operacion, Object valorFiltro,
            String campoOrden, String direccionOrden,
            Integer limite) {
        
        List<Producto> resultado = new ArrayList<>(productos);
        
        if (campoFiltro != null && operacion != null && valorFiltro != null) {
            resultado = aplicarFiltro(resultado, campoFiltro, operacion, valorFiltro);
        }
        
        if (campoOrden != null && direccionOrden != null) {
            resultado = aplicarOrden(resultado, campoOrden, direccionOrden);
        }
        
        if (limite != null && limite > 0) {
            resultado = resultado.stream().limit(limite).collect(Collectors.toList());
        }
        
        return formatearResultado(resultado, camposMostrar);
    }
    
    private List<Producto> aplicarFiltro(List<Producto> productos, String campo, String operacion, Object valor) {
        return productos.stream().filter(p -> {
            switch (campo.toLowerCase()) {
                case "id":
                    return compararNumerico(p.getId(), operacion, (Integer) valor);
                case "precio":
                    return compararNumerico(p.getPrecio(), operacion, (Double) valor);
                case "cantidad":
                    return compararNumerico(p.getCantidad(), operacion, (Integer) valor);
                case "nombre":
                    return compararTexto(p.getNombre(), operacion, (String) valor);
                default:
                    return true;
            }
        }).collect(Collectors.toList());
    }
    
    private boolean compararNumerico(Number valorActual, String operacion, Number valorComparar) {
        double actual = valorActual.doubleValue();
        double comparar = valorComparar.doubleValue();
        
        switch (operacion) {
            case ">": return actual > comparar;
            case "<": return actual < comparar;
            case "=": return actual == comparar;
            case ">=": return actual >= comparar;
            case "<=": return actual <= comparar;
            default: return true;
        }
    }
    
    private boolean compararTexto(String texto, String operacion, String valor) {
        switch (operacion) {
            case "=": return texto.equalsIgnoreCase(valor);
            case "contiene": return texto.toLowerCase().contains(valor.toLowerCase());
            case "empieza": return texto.toLowerCase().startsWith(valor.toLowerCase());
            default: return true;
        }
    }
    
    private List<Producto> aplicarOrden(List<Producto> productos, String campo, String direccion) {
        Comparator<Producto> comparator = null;
        
        switch (campo.toLowerCase()) {
            case "id":
                comparator = Comparator.comparing(Producto::getId);
                break;
            case "nombre":
                comparator = Comparator.comparing(Producto::getNombre);
                break;
            case "precio":
                comparator = Comparator.comparing(Producto::getPrecio);
                break;
            case "cantidad":
                comparator = Comparator.comparing(Producto::getCantidad);
                break;
            default:
                return productos;
        }
        
        if ("desc".equalsIgnoreCase(direccion)) {
            comparator = comparator.reversed();
        }
        
        return productos.stream().sorted(comparator).collect(Collectors.toList());
    }
    
    private List<Map<String, Object>> formatearResultado(List<Producto> productos, List<String> campos) {
        List<Map<String, Object>> resultado = new ArrayList<>();
        
        for (Producto p : productos) {
            Map<String, Object> fila = new HashMap<>();
            for (String campo : campos) {
                switch (campo.toLowerCase()) {
                    case "id": fila.put("id", p.getId()); break;
                    case "nombre": fila.put("nombre", p.getNombre()); break;
                    case "precio": fila.put("precio", p.getPrecio()); break;
                    case "cantidad": fila.put("cantidad", p.getCantidad()); break;
                }
            }
            resultado.add(fila);
        }
        
        return resultado;
    }
}
