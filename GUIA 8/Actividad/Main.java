package Practica;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        try {
            // 1. REGISTRAR DRIVER
            Class.forName("org.sqlite.JDBC");

            // 2. ESTABLECER CONEXIÓN
            Connection con = DriverManager.getConnection("jdbc:sqlite:ejemplo.db");

            if (con != null) {
                System.out.println("Se creó y/o abrió la base de datos correctamente");
            }

            // CREAR TABLA SI NO EXISTE
            Statement stmtCreate = con.createStatement();
            stmtCreate.execute("CREATE TABLE IF NOT EXISTS emp (id integer PRIMARY KEY, name text, age integer)");
            System.out.println("Tabla 'emp' creada/verificada");

            // 3. OPERACIONES CRUD BÁSICAS
            System.out.println("\n=== OPERACIONES CRUD BÁSICAS ===");
            
            // INSERTAR REGISTROS
            Statement stmtInsert = con.createStatement();
            stmtInsert.executeUpdate("INSERT OR IGNORE INTO emp VALUES (1, 'Carlos', 11)");
            stmtInsert.executeUpdate("INSERT OR IGNORE INTO emp VALUES (2, 'Bety', 22)");
            stmtInsert.executeUpdate("INSERT OR IGNORE INTO emp VALUES (3, 'Ana', 33)");
            System.out.println("Registros insertados correctamente");

            // RECUPERAR REGISTROS
            System.out.println("\n--- MOSTRAR TODOS LOS REGISTROS ---");
            Statement stmtSelect = con.createStatement();
            ResultSet rs = stmtSelect.executeQuery("SELECT * FROM emp");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
            }

            // ACTUALIZAR REGISTRO
            Statement stmtUpdate = con.createStatement();
            int filasActualizadas = stmtUpdate.executeUpdate("UPDATE emp SET age = 25 WHERE id = 2");
            System.out.println("\nRegistros actualizados: " + filasActualizadas);

            // 4. INTERFAZ PREPAREDSTATEMENT
            System.out.println("\n=== INTERFAZ PREPAREDSTATEMENT ===");
            
            // INSERTAR CON PARÁMETROS
            PreparedStatement pstmtInsert = con.prepareStatement("INSERT INTO emp VALUES (?, ?, ?)");
            pstmtInsert.setInt(1, 4);
            pstmtInsert.setString(2, "David");
            pstmtInsert.setInt(3, 40);
            pstmtInsert.executeUpdate();
            System.out.println("Registro insertado con PreparedStatement");

            // ACTUALIZAR CON PARÁMETROS
            PreparedStatement pstmtUpdate = con.prepareStatement("UPDATE emp SET age = ? WHERE name = ?");
            pstmtUpdate.setInt(1, 35);
            pstmtUpdate.setString(2, "Ana");
            pstmtUpdate.executeUpdate();
            System.out.println("Registro actualizado con PreparedStatement");

            // ELIMINAR CON PARÁMETROS
            PreparedStatement pstmtDelete = con.prepareStatement("DELETE FROM emp WHERE id = ?");
            pstmtDelete.setInt(1, 3);
            pstmtDelete.executeUpdate();
            System.out.println("Registro eliminado con PreparedStatement");

            // 5. MANEJO DE TRANSACCIONES EN JDBC
            System.out.println("\n=== MANEJO DE TRANSACCIONES ===");
            
            // DESACTIVAR AUTO-COMMIT
            con.setAutoCommit(false);
            System.out.println("Auto-commit desactivado");

            Statement stmtTrans = con.createStatement();
            
            try {
                // OPERACIONES DENTRO DE LA TRANSACCIÓN
                stmtTrans.executeUpdate("INSERT INTO emp VALUES (190, 'jose', 77)");
                stmtTrans.executeUpdate("INSERT INTO emp VALUES (191, 'diana', 88)");
                
                // CONFIRMAR TRANSACCIÓN
                con.commit();
                System.out.println("Transacción confirmada (COMMIT) - Registros 190 y 191 insertados");

                // OPERACIÓN FUERA DE TRANSACCIÓN (se auto-confirma)
                con.setAutoCommit(true);
                stmtTrans.executeUpdate("INSERT INTO emp VALUES (200, 'andy', 99)");
                System.out.println("Registro 200 insertado con auto-commit");

                // SIMULAR ROLLBACK EN NUEVA TRANSACCIÓN
                con.setAutoCommit(false);
                stmtTrans.executeUpdate("INSERT INTO emp VALUES (201, 'maria', 44)");
                stmtTrans.executeUpdate("INSERT INTO emp VALUES (202, 'pedro', 55)");
                
                // CANCELAR TRANSACCIÓN
                con.rollback();
                System.out.println("Transacción cancelada (ROLLBACK) - Registros 201 y 202 NO insertados");

            } catch (Exception e) {
                con.rollback();
                System.out.println("Error en transacción - se realizó ROLLBACK: " + e.getMessage());
            } finally {
                // RESTAURAR AUTO-COMMIT
                con.setAutoCommit(true);
            }

            // MOSTRAR RESULTADO FINAL
            System.out.println("\n--- ESTADO FINAL DE LA TABLA ---");
            ResultSet rsFinal = stmtTrans.executeQuery("SELECT * FROM emp ORDER BY id");
            while (rsFinal.next()) {
                System.out.println(rsFinal.getInt(1) + " " + rsFinal.getString(2) + " " + rsFinal.getInt(3));
            }

            // 6. CERRAR CONEXIÓN
            con.close();
            System.out.println("\nConexión cerrada correctamente");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
