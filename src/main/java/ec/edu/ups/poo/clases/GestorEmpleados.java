package ec.edu.ups.poo.clases;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GestorEmpleados {

    private final List<Empleado> listaEmpleados = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void agregarEmpleado() {
        System.out.println("Ingrese los datos del nuevo empleado:");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        String identificacion = GestorMenu.solicitarCedulaValida();

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("Correo: ");
        String correo = scanner.nextLine();

        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        Empleado nuevoEmpleado = new Empleado(nombre, identificacion, telefono, correo, direccion, usuario, contrasena);
        listaEmpleados.add(nuevoEmpleado);
        System.out.println("Empleado agregado correctamente.");
    }

    public Empleado buscarEmpleado(String identificacion) {
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getIdentificacion().equals(identificacion)) {
                return empleado;
            }
        }
        System.out.println("Empleado no encontrado.");
        return null;
    }

    public void mostrarTodosEmpleados() {
        if (listaEmpleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (Empleado empleado : listaEmpleados) {
                System.out.println(empleado.toString());
            }
        }
    }

    public void agregarEmpleadosPorDefecto() {
        List<Empleado> empleadosPorDefecto = Arrays.asList(
                new Empleado("Carlos Ramírez", "1101160032", "0987123456", "carlos.ramirez@empresa.ec", "Av. Colón 321", "carlosr", "Carlos2025"),
                new Empleado("María Fernández", "1719690487", "0976543210", "maria.fernandez@empresa.ec", "Calle Bolívar 789", "mariaf", "MariaPass"),
                new Empleado("Juan Pérez", "1724354459", "0998765432", "juan.perez@empresa.ec", "Calle Sucre 567", "juanp", "Juan123"),
                new Empleado("Sofía Gómez", "1709839664", "0965432109", "sofia.gomez@empresa.ec", "Av. Amazonas 100", "sofiag", "SofiaG12"),
                new Empleado("Diego Álvarez", "1712114196", "0954321098", "diego.alvarez@empresa.ec", "Calle Rocafuerte 200", "diegoa", "DiegoPass")
        );

        listaEmpleados.addAll(empleadosPorDefecto);
    }

}