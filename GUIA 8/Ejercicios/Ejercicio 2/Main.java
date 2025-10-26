package Practica;

import java.sql.*;
import java.util.*;

public class Main {
    private static final String CLAVE = "1234";
    private static Scanner scanner = new Scanner(System.in);
    private static GestorProductos gestor = new GestorProductos();
    
    public static void main(String[] args) {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:productos.db");
            
            Statement stmtCreate = con.createStatement();
            stmtCreate.execute("CREATE TABLE IF NOT EXISTS productos (" +
                              "id INTEGER PRIMARY KEY, " +
                              "nombre TEXT, " +
                              "precio REAL, " +
                              "cantidad INTEGER)");

            cargarDatosDesdeBD(con);
            
            int opcion;
            do {
                System.out.println("\n=== MENÚ PRINCIPAL ===");
                System.out.println("1. Mostrar todos los productos");
                System.out.println("2. Agregar nuevo producto");
                System.out.println("3. Actualizar producto");
                System.out.println("4. Eliminar producto");
                System.out.println("5. Consulta avanzada en memoria");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");
                
                opcion = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcion) {
                    case 1 -> mostrarProductos(con);
                    case 2 -> agregarProducto(con);
                    case 3 -> actualizarProducto(con);
                    case 4 -> eliminarProducto(con);
                    case 5 -> consultaAvanzada();
                    case 6 -> System.out.println("¡Hasta pronto!");
                    default -> System.out.println("Opcion invalida");
                }
            } while (opcion != 6);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (con != null) {
                try { con.close(); } catch (SQLException e) {}
            }
        }
    }

    public static void cargarDatosDesdeBD(Connection con) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM productos");
            
            while (rs.next()) {
                Producto p = new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getInt("cantidad")
                );
                gestor.agregarProducto(p);
            }
            System.out.println("Datos cargados en memoria: " + gestor.obtenerTodos().size() + " productos");
            
        } catch (Exception e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }

    public static void mostrarProductos(Connection con) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM productos");
            
            System.out.println("\n--- LISTA DE PRODUCTOS ---");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + 
                                 rs.getString("nombre") + " | $" + 
                                 rs.getDouble("precio") + " | " + 
                                 rs.getInt("cantidad"));
            }
            
        } catch (Exception e) {
            System.out.println("Error al mostrar productos: " + e.getMessage());
        }
    }

    public static void agregarProducto(Connection con) {
        try {
            System.out.print("ID del producto: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Precio: ");
            double precio = scanner.nextDouble();
            System.out.print("Cantidad: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();
            
            if (!verificarClave()) {
                System.out.println("Clave incorrecta. Operacion cancelada.");
                return;
            }
            
            con.setAutoCommit(false);
            
            try {
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO productos VALUES (?, ?, ?, ?)");
                pstmt.setInt(1, id);
                pstmt.setString(2, nombre);
                pstmt.setDouble(3, precio);
                pstmt.setInt(4, cantidad);
                pstmt.executeUpdate();
                
                gestor.agregarProducto(new Producto(id, nombre, precio, cantidad));
                con.commit();
                System.out.println("Producto agregado correctamente");
                
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Error: " + e.getMessage());
            } finally {
                con.setAutoCommit(true);
            }
            
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    public static void actualizarProducto(Connection con) {
        try {
            mostrarProductos(con);
            System.out.print("ID del producto a actualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nuevo nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Nuevo precio: ");
            double precio = scanner.nextDouble();
            System.out.print("Nueva cantidad: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();
            
            if (!verificarClave()) {
                System.out.println("Clave incorrecta. Operacion cancelada.");
                return;
            }
            
            con.setAutoCommit(false);
            
            try {
                PreparedStatement pstmt = con.prepareStatement("UPDATE productos SET nombre = ?, precio = ?, cantidad = ? WHERE id = ?");
                pstmt.setString(1, nombre);
                pstmt.setDouble(2, precio);
                pstmt.setInt(3, cantidad);
                pstmt.setInt(4, id);
                
                int filas = pstmt.executeUpdate();
                
                if (filas > 0) {
                    con.commit();
                    System.out.println("Producto actualizado correctamente");
                } else {
                    con.rollback();
                    System.out.println("No se encontro el producto con ID: " + id);
                }
                
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Error: " + e.getMessage());
            } finally {
                con.setAutoCommit(true);
            }
            
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    public static void eliminarProducto(Connection con) {
        try {
            mostrarProductos(con);
            System.out.print("ID del producto a eliminar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            if (!verificarClave()) {
                System.out.println("Clave incorrecta. Operacion cancelada.");
                return;
            }
            
            con.setAutoCommit(false);
            
            try {
                PreparedStatement pstmt = con.prepareStatement("DELETE FROM productos WHERE id = ?");
                pstmt.setInt(1, id);
                
                int filas = pstmt.executeUpdate();
                
                if (filas > 0) {
                    gestor.eliminarProducto(id);
                    con.commit();
                    System.out.println("Producto eliminado correctamente");
                } else {
                    con.rollback();
                    System.out.println("No se encontro el producto con ID: " + id);
                }
                
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Error: " + e.getMessage());
            } finally {
                con.setAutoCommit(true);
            }
            
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    public static void consultaAvanzada() {
        System.out.println("\n=== CONSULTA AVANZADA EN MEMORIA ===");
        
        List<String> camposMostrar = obtenerCamposMostrar();
        String campoFiltro = obtenerCampoFiltro();
        String operacion = null;
        Object valorFiltro = null;
        
        if (campoFiltro != null) {
            operacion = obtenerOperacion(campoFiltro);
            valorFiltro = obtenerValorFiltro(campoFiltro, operacion);
        }
        
        String campoOrden = obtenerCampoOrden();
        String direccionOrden = campoOrden != null ? obtenerDireccionOrden() : null;
        Integer limite = obtenerLimite();
        
        List<Map<String, Object>> resultado = gestor.consultaAvanzada(
            camposMostrar, campoFiltro, operacion, valorFiltro,
            campoOrden, direccionOrden, limite
        );
        
        mostrarResultadoConsulta(resultado, camposMostrar);
    }
    
    private static List<String> obtenerCamposMostrar() {
        System.out.print("Campos a mostrar (ej: id,nombre,precio): ");
        String input = scanner.nextLine();
        return Arrays.asList(input.split(","));
    }
    
    private static String obtenerCampoFiltro() {
        System.out.print("¿Filtrar por campo? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Campo (id/nombre/precio/cantidad): ");
            return scanner.nextLine();
        }
        return null;
    }
    
    private static String obtenerOperacion(String campo) {
        if (campo.equals("nombre")) {
            System.out.print("Operación (=/contiene/empieza): ");
        } else {
            System.out.print("Operación (> / < / = / >= / <=): ");
        }
        return scanner.nextLine();
    }
    
    private static Object obtenerValorFiltro(String campo, String operacion) {
        System.out.print("Valor: ");
        if (campo.equals("id") || campo.equals("cantidad")) {
            return scanner.nextInt();
        } else if (campo.equals("precio")) {
            return scanner.nextDouble();
        } else {
            return scanner.nextLine();
        }
    }
    
    private static String obtenerCampoOrden() {
        System.out.print("¿Ordenar por campo? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Campo (id/nombre/precio/cantidad): ");
            return scanner.nextLine();
        }
        return null;
    }
    
    private static String obtenerDireccionOrden() {
        System.out.print("Orden (asc/desc): ");
        return scanner.nextLine();
    }
    
    private static Integer obtenerLimite() {
        System.out.print("¿Limitar resultados? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Cantidad máxima: ");
            return scanner.nextInt();
        }
        return null;
    }
    
    private static void mostrarResultadoConsulta(List<Map<String, Object>> resultado, List<String> campos) {
        System.out.println("\n--- RESULTADOS DE CONSULTA ---");
        for (Map<String, Object> fila : resultado) {
            for (String campo : campos) {
                System.out.print(campo + ": " + fila.get(campo) + " | ");
            }
            System.out.println();
        }
        System.out.println("Total de registros: " + resultado.size());
    }

    public static boolean verificarClave() {
        System.out.print("Ingrese la clave de seguridad: ");
        String claveIngresada = scanner.nextLine();
        return claveIngresada.equals(CLAVE);
    }
}
