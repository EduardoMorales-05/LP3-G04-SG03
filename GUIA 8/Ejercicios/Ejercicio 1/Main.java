package Practica;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String CLAVE = "1234"; // Clave 
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        Connection con = null;
        try {
            //  CONEXIÓN A LA BASE DE DATOS
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:productos.db");
            
            if (con != null) {
                System.out.println("Base de datos conectada correctamente");
            }

            //  CREAR TABLA CON 4 CAMPOS
            Statement stmtCreate = con.createStatement();
            stmtCreate.execute("CREATE TABLE IF NOT EXISTS productos (" +
                              "id INTEGER PRIMARY KEY, " +
                              "nombre TEXT, " +
                              "precio REAL, " +
                              "cantidad INTEGER)");
            System.out.println("Tabla 'productos' lista para usar");

            //  MENÚ PRINCIPAL
            int opcion;
            do {
                System.out.println("\n=== MENÚ PRINCIPAL ===");
                System.out.println("1. Mostrar todos los productos");
                System.out.println("2. Agregar nuevo producto");
                System.out.println("3. Actualizar producto");
                System.out.println("4. Eliminar producto");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
                
                switch (opcion) {
                    case 1 -> mostrarProductos(con);
                    case 2 -> agregarProducto(con);
                    case 3 -> actualizarProducto(con);
                    case 4 -> eliminarProducto(con);
                    case 5 -> System.out.println("¡Hasta pronto!");
                    default -> System.out.println("Opcion invalida");
                }
            } while (opcion != 5);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // CERRAR CONEXIÓN 
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar conexión: " + e.getMessage());
                }
            }
        }
    }

    // MOSTRAR TODOS LOS PRODUCTOS 
    public static void mostrarProductos(Connection con) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM productos");
            
            System.out.println("\n--- LISTA DE PRODUCTOS ---");
            System.out.println("ID | Nombre | Precio | Cantidad");
            System.out.println("-----------------------------");
            
            boolean hayProductos = false;
            while (rs.next()) {
                hayProductos = true;
                System.out.println(rs.getInt("id") + " | " + 
                                 rs.getString("nombre") + " | $" + 
                                 rs.getDouble("precio") + " | " + 
                                 rs.getInt("cantidad"));
            }
            
            if (!hayProductos) {
                System.out.println("No hay productos registrados");
            }
            
        } catch (Exception e) {
            System.out.println("Error al mostrar productos: " + e.getMessage());
        }
    }

    // AGREGAR PRODUCTO CON TRANSACCIÓN
    public static void agregarProducto(Connection con) {
        try {
            System.out.println("\n--- AGREGAR NUEVO PRODUCTO ---");
            
            // Solicitar datos del producto
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
            
            // Verificar clave 
            if (!verificarClave()) {
                System.out.println("Clave incorrecta. Operacion cancelada.");
                return;
            }
            
            con.setAutoCommit(false); 
            System.out.println("Iniciando transaccion...");
            
            try {
                // Insertar producto 
                PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO productos VALUES (?, ?, ?, ?)");
                pstmt.setInt(1, id);
                pstmt.setString(2, nombre);
                pstmt.setDouble(3, precio);
                pstmt.setInt(4, cantidad);
                
                int filas = pstmt.executeUpdate();
                
                con.commit();
                System.out.println("Producto agregado correctamente (Transaccion confirmada)");
                
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Error: " + e.getMessage() + " - Transaccion cancelada");
            } finally {
                con.setAutoCommit(true);
            }
            
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    // ACTUALIZAR PRODUCTO CON TRANSACCIÓN
    public static void actualizarProducto(Connection con) {
        try {
            System.out.println("\n--- ACTUALIZAR PRODUCTO ---");
            
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
            
            // Verificar clave 
            if (!verificarClave()) {
                System.out.println("Clave incorrecta. Operacion cancelada.");
                return;
            }
            
            // INICIAR TRANSACCIÓN
            con.setAutoCommit(false);
            System.out.println("Iniciando transaccion...");
            
            try {
                // Actualizar producto
                PreparedStatement pstmt = con.prepareStatement(
                    "UPDATE productos SET nombre = ?, precio = ?, cantidad = ? WHERE id = ?");
                pstmt.setString(1, nombre);
                pstmt.setDouble(2, precio);
                pstmt.setInt(3, cantidad);
                pstmt.setInt(4, id);
                
                int filas = pstmt.executeUpdate();
                
                if (filas > 0) {
                    con.commit();
                    System.out.println("Producto actualizado correctamente (Transaccion confirmada)");
                } else {
                    con.rollback();
                    System.out.println("No se encontro el producto con ID: " + id + " - Transaccion cancelada");
                }
                
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Error: " + e.getMessage() + " - Transaccion cancelada");
            } finally {
                con.setAutoCommit(true);
            }
            
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    // ELIMINAR PRODUCTO CON TRANSACCIÓN (Requiere clave)
    public static void eliminarProducto(Connection con) {
        try {
            System.out.println("\n--- ELIMINAR PRODUCTO ---");
            
            // Mostrar productos disponibles
            mostrarProductos(con);
            
            System.out.print("ID del producto a eliminar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            // Verificar clave
            if (!verificarClave()) {
                System.out.println("Clave incorrecta. Operacion cancelada.");
                return;
            }
            
            con.setAutoCommit(false);
            System.out.println("Iniciando transaccion...");
            
            try {
                PreparedStatement pstmt = con.prepareStatement("DELETE FROM productos WHERE id = ?");
                pstmt.setInt(1, id);
                
                int filas = pstmt.executeUpdate();
                
                if (filas > 0) {
                    con.commit();
                    System.out.println("Producto eliminado correctamente (Transaccion confirmada)");
                } else {
                    con.rollback();
                    System.out.println("No se encontro el producto con ID: " + id + " - Transaccion cancelada");
                }
                
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Error: " + e.getMessage() + " - Transaccion cancelada");
            } finally {
                con.setAutoCommit(true);
            }
            
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    // VERIFICAR CLAVE DE SEGURIDAD
    
    public static boolean verificarClave() {
        System.out.print("Ingrese la clave de seguridad para confirmar: ");
        String claveIngresada = scanner.nextLine();
        return claveIngresada.equals(CLAVE);
    }
}
