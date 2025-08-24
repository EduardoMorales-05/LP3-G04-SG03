import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Clase abstracta Persona
abstract class Persona {
    protected int id;
    protected String nombre;
    protected String apellido;
    protected String email;
    protected String password;

    public Persona(int id, String nombre, String apellido, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }

    public boolean autenticar(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getEmail() { return email; }
}

// Clase Estudiante
class Estudiante extends Persona {
    private List<Curso> cursosInscritos;

    public Estudiante(int id, String nombre, String apellido, String email, String password) {
        super(id, nombre, apellido, email, password);
        this.cursosInscritos = new ArrayList<>();
    }

    public void inscribirCurso(Curso curso) {
        if (!cursosInscritos.contains(curso)) {
            cursosInscritos.add(curso);
            curso.agregarEstudiante(this);
            System.out.println("Inscripción exitosa en: " + curso.getNombre());
        } else {
            System.out.println("Ya estás inscrito en este curso");
        }
    }

    public void mostrarCursos() {
        System.out.println("\n--- CURSOS INSCRITOS ---");
        if (cursosInscritos.isEmpty()) {
            System.out.println("No estás inscrito en ningún curso");
        } else {
            for (int i = 0; i < cursosInscritos.size(); i++) {
                Curso curso = cursosInscritos.get(i);
                System.out.println((i + 1) + ". " + curso.getNombre() + 
                                 " - Profesor: " + curso.getProfesor().getNombreCompleto());
            }
        }
    }

    public List<Curso> getCursosInscritos() {
        return cursosInscritos;
    }
}

// Clase Profesor
class Profesor extends Persona {
    private List<Curso> cursosAsignados;

    public Profesor(int id, String nombre, String apellido, String email, String password) {
        super(id, nombre, apellido, email, password);
        this.cursosAsignados = new ArrayList<>();
    }

    public void asignarCurso(Curso curso) {
        cursosAsignados.add(curso);
        curso.asignarProfesor(this);
    }

    public void mostrarCursos() {
        System.out.println("\n--- CURSOS ASIGNADOS ---");
        if (cursosAsignados.isEmpty()) {
            System.out.println("No tienes cursos asignados");
        } else {
            for (int i = 0; i < cursosAsignados.size(); i++) {
                Curso curso = cursosAsignados.get(i);
                System.out.println((i + 1) + ". " + curso.getNombre() + 
                                 " - Estudiantes: " + curso.getCantidadEstudiantes());
            }
        }
    }

    public List<Curso> getCursosAsignados() {
        return cursosAsignados;
    }
}

// Clase Curso
class Curso {
    private static int contadorId = 1;
    
    private int id;
    private String nombre;
    private String categoria;
    private Profesor profesor;
    private List<Estudiante> estudiantes;

    public Curso(String nombre, String categoria) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.categoria = categoria;
        this.estudiantes = new ArrayList<>();
    }

    public void agregarEstudiante(Estudiante estudiante) {
        if (!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
        }
    }

    public void asignarProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public int getCantidadEstudiantes() {
        return estudiantes.size();
    }

    public void mostrarEstudiantes() {
        System.out.println("\n--- ESTUDIANTES INSCRITOS EN " + nombre.toUpperCase() + " ---");
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes inscritos");
        } else {
            for (Estudiante estudiante : estudiantes) {
                System.out.println("- " + estudiante.getNombreCompleto());
            }
        }
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public Profesor getProfesor() { return profesor; }
}

// Sistema de Gestión
class SistemaGestion {
    private List<Estudiante> estudiantes;
    private List<Profesor> profesores;
    private List<Curso> cursos;
    private Scanner scanner;

    public SistemaGestion() {
        this.estudiantes = new ArrayList<>();
        this.profesores = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        inicializarDatos();
    }

    private void inicializarDatos() {
        // Crear profesores
        Profesor prof1 = new Profesor(1, "Carlos", "Mendoza", "cmendoza@email.com", "pass123");
        Profesor prof2 = new Profesor(2, "Ana", "Garcia", "agarcia@email.com", "pass123");
        Profesor prof3 = new Profesor(3, "Luis", "Torres", "ltorres@email.com", "pass123");
        Profesor prof4 = new Profesor(4, "Maria", "Lopez", "mlopez@email.com", "pass123");

        profesores.add(prof1);
        profesores.add(prof2);
        profesores.add(prof3);
        profesores.add(prof4);

        // Crear cursos
        Curso curso1 = new Curso("Programación I", "Programación");
        Curso curso2 = new Curso("Base de Datos", "Bases de Datos");
        Curso curso3 = new Curso("Matemáticas Discretas", "Matemáticas");
        Curso curso4 = new Curso("Estructuras de Datos", "Programación");

        cursos.add(curso1);
        cursos.add(curso2);
        cursos.add(curso3);
        cursos.add(curso4);

        // Asignar profesores a cursos
        prof1.asignarCurso(curso1);
        prof2.asignarCurso(curso2);
        prof3.asignarCurso(curso3);
        prof4.asignarCurso(curso4);

        // Crear estudiantes
        Estudiante est1 = new Estudiante(1, "Juan", "Perez", "jperez@email.com", "pass123");
        Estudiante est2 = new Estudiante(2, "Laura", "Sanchez", "lsanchez@email.com", "pass123");
        Estudiante est3 = new Estudiante(3, "Pedro", "Ramirez", "pramirez@email.com", "pass123");

        estudiantes.add(est1);
        estudiantes.add(est2);
        estudiantes.add(est3);

        // Inscribir estudiantes en cursos
        est1.inscribirCurso(curso1);
        est1.inscribirCurso(curso2);
        est2.inscribirCurso(curso1);
        est2.inscribirCurso(curso3);
        est3.inscribirCurso(curso4);
    }

    public void iniciar() {
        while (true) {
            System.out.println("\n=== SISTEMA DE GESTIÓN UNIVERSITARIA ===");
            System.out.println("1. Iniciar sesión como Estudiante");
            System.out.println("2. Iniciar sesión como Profesor");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    iniciarSesionEstudiante();
                    break;
                case 2:
                    iniciarSesionProfesor();
                    break;
                case 3:
                    System.out.println("¡Hasta pronto!");
                    return;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void iniciarSesionEstudiante() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        Estudiante estudiante = null;
        for (Estudiante est : estudiantes) {
            if (est.autenticar(email, password)) {
                estudiante = est;
                break;
            }
        }

        if (estudiante != null) {
            menuEstudiante(estudiante);
        } else {
            System.out.println("Credenciales incorrectas");
        }
    }

    private void iniciarSesionProfesor() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        Profesor profesor = null;
        for (Profesor prof : profesores) {
            if (prof.autenticar(email, password)) {
                profesor = prof;
                break;
            }
        }

        if (profesor != null) {
            menuProfesor(profesor);
        } else {
            System.out.println("Credenciales incorrectas");
        }
    }

    private void menuEstudiante(Estudiante estudiante) {
        while (true) {
            System.out.println("\n=== MENÚ ESTUDIANTE ===");
            System.out.println("Bienvenido: " + estudiante.getNombreCompleto());
            System.out.println("1. Ver cursos disponibles");
            System.out.println("2. Inscribirse en curso");
            System.out.println("3. Ver mis cursos");
            System.out.println("4. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    mostrarCursosDisponibles();
                    break;
                case 2:
                    inscribirEnCurso(estudiante);
                    break;
                case 3:
                    estudiante.mostrarCursos();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void menuProfesor(Profesor profesor) {
        while (true) {
            System.out.println("\n=== MENÚ PROFESOR ===");
            System.out.println("Bienvenido: " + profesor.getNombreCompleto());
            System.out.println("1. Ver mis cursos");
            System.out.println("2. Ver estudiantes por curso");
            System.out.println("3. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    profesor.mostrarCursos();
                    break;
                case 2:
                    verEstudiantesPorCurso(profesor);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void mostrarCursosDisponibles() {
        System.out.println("\n--- CURSOS DISPONIBLES ---");
        for (int i = 0; i < cursos.size(); i++) {
            Curso curso = cursos.get(i);
            System.out.println((i + 1) + ". " + curso.getNombre() + 
                             " - Categoría: " + curso.getCategoria() +
                             " - Profesor: " + curso.getProfesor().getNombreCompleto() +
                             " - Estudiantes: " + curso.getCantidadEstudiantes());
        }
    }

    private void inscribirEnCurso(Estudiante estudiante) {
        mostrarCursosDisponibles();
        System.out.print("Seleccione el número del curso: ");
        int numeroCurso = scanner.nextInt();
        scanner.nextLine();

        if (numeroCurso > 0 && numeroCurso <= cursos.size()) {
            Curso cursoSeleccionado = cursos.get(numeroCurso - 1);
            estudiante.inscribirCurso(cursoSeleccionado);
        } else {
            System.out.println("Número de curso inválido");
        }
    }

    private void verEstudiantesPorCurso(Profesor profesor) {
        profesor.mostrarCursos();
        System.out.print("Seleccione el número del curso: ");
        int numeroCurso = scanner.nextInt();
        scanner.nextLine();

        List<Curso> cursosProfesor = profesor.getCursosAsignados();
        if (numeroCurso > 0 && numeroCurso <= cursosProfesor.size()) {
            Curso cursoSeleccionado = cursosProfesor.get(numeroCurso - 1);
            cursoSeleccionado.mostrarEstudiantes();
        } else {
            System.out.println("Número de curso inválido");
        }
    }
}

// Clase principal
public class Main {
    public static void main(String[] args) {
        SistemaGestion sistema = new SistemaGestion();
        sistema.iniciar();
    }
}
