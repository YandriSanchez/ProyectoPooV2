package ec.edu.ups.poo;

import ec.edu.ups.poo.clases.Empleado;
import ec.edu.ups.poo.ventanas.VentanaIniciarSesion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    private final List<Empleado> listaEmpleados = new ArrayList<>();

    public static void main(String[] args) {
        VentanaIniciarSesion ventanaLogin = new VentanaIniciarSesion();
    }

    //Controla la lista de Empleados

    public void agregarEmpleadosPorDefecto() {
        List<Empleado> empleadosPorDefecto = Arrays.asList(
                new Empleado("Carlos Ramírez", "1101160032", "0987123456", "carlos.ramirez@empresa.ec", "Av. Colón 321", "carlosr", "Carlos2025"),
                new Empleado("María Fernández", "1719690487", "0976543210", "maria.fernandez@empresa.ec", "Calle Bolívar 789", "mariaf", "MariaPass"),
                new Empleado("Juan Pérez", "1724354459", "0998765432", "juan.perez@empresa.ec", "Calle Sucre 567", "juanp", "Juan123"),
                new Empleado("Sofía Gómez", "1709839664", "0965432109", "sofia.gomez@empresa.ec", "Av. Amazonas 100", "sofiag", "SofiaG12"),
                new Empleado("Diego Álvarez", "1712114196", "0954321098", "diego.alvarez@empresa.ec", "Calle Rocafuerte 200", "diegoa", "DiegoPass")
        );
        this.listaEmpleados.addAll(empleadosPorDefecto);
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

    public boolean verificarCredenciales(String usuarioIngresado, String contrasenaIngresada) {
        boolean verificacion = false;
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getUsuario().equals(usuarioIngresado) &&
                    empleado.getContrasena().equals(contrasenaIngresada)) {
                System.out.println("Ingreso Exitoso. Bienvenido, " + empleado.getUsuario());
                return verificacion = true;
            }
        }
        System.out.println("Usuario o contraseña incorrectas.");
        return verificacion;
    }

    public void registrarEmpleado(String nombre, String identificacion, String telefono, String correo,
                                   String direccion, String usuario, String contrasena) {

        if (!nombre.isEmpty() && !identificacion.isEmpty() && !usuario.isEmpty() && !contrasena.isEmpty()) {
            Empleado nuevoEmpleado = new Empleado(nombre, identificacion, telefono, correo, direccion, usuario, contrasena);
            listaEmpleados.add(nuevoEmpleado);
            System.out.println("Empleado registrado exitosamente.");
        } else {
            System.out.println("ERROR, empleado no registrado, todos los campos son obligatorios.");
        }
    }
}
