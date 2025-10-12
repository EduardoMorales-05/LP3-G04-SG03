package dao;

import modelo.Empleado;
import java.io.*;
import java.util.*;

public class EmpleadoDAO {
    private static final String ARCHIVO = "empleados.dat";
    private List<Empleado> empleados;

    public EmpleadoDAO() {
        empleados = new ArrayList<>();
        leerEmpleados();
    }

    // Leer todos los empleados desde el archivo
    public List<Empleado> leerEmpleados() {
        empleados.clear();
        File archivo = new File(ARCHIVO);
        
        if (!archivo.exists()) {
            return empleados;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            while (true) {
                try {
                    Empleado empleado = (Empleado) ois.readObject();
                    empleados.add(empleado);
                } catch (EOFException e) {
                    break; // Fin del archivo
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer empleados: " + e.getMessage());
        }
        
        return empleados;
    }

    // Guardar todos los empleados en el archivo
    private void guardarEmpleados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            for (Empleado empleado : empleados) {
                oos.writeObject(empleado);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar empleados: " + e.getMessage());
        }
    }

    // Agregar un nuevo empleado
    public boolean agregarEmpleado(Empleado empleado) {
        // Verificar si el número ya existe
        for (Empleado emp : empleados) {
            if (emp.getNumero() == empleado.getNumero()) {
                return false;
            }
        }
        empleados.add(empleado);
        guardarEmpleados();
        return true;
    }

    // Buscar empleado por número
    public Empleado buscarEmpleado(int numero) {
        for (Empleado empleado : empleados) {
            if (empleado.getNumero() == numero) {
                return empleado;
            }
        }
        return null;
    }

    // Eliminar empleado por número
    public boolean eliminarEmpleado(int numero) {
        Empleado empleado = buscarEmpleado(numero);
        if (empleado != null) {
            empleados.remove(empleado);
            guardarEmpleados();
            return true;
        }
        return false;
    }

    // Obtener todos los empleados
    public List<Empleado> obtenerTodosEmpleados() {
        return new ArrayList<>(empleados);
    }

    // Verificar si existe un empleado
    public boolean existeEmpleado(int numero) {
        return buscarEmpleado(numero) != null;
    }
}
