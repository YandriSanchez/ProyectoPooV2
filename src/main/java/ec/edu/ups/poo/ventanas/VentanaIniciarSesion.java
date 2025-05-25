package ec.edu.ups.poo.ventanas;

import ec.edu.ups.poo.clases.Empleado;
import ec.edu.ups.poo.clases.Proveedor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaIniciarSesion extends Frame {

    private TextField txtUsuario;
    private TextField txtContrasena;
    private Button btnRegistrarse;
    private Button btnIngresar;
    private Panel panelSuperior;
    private Panel panelInferior;
    private Label lblUsuario;
    private Label lblContrasena;

    private final List<Empleado> listaEmpleados;
    private final List<Proveedor> listaProveedores;

    public VentanaIniciarSesion(List<Empleado> listaEmpleados, List<Proveedor> listaProveedores) {
        setTitle("Gestión de Órdenes de Compra");
        setSize(400, 150);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        this.listaEmpleados = listaEmpleados;
        this.listaProveedores = listaProveedores;
        mostrarTodosEmpleados();

        panelSuperior = new Panel(new FlowLayout(FlowLayout.LEFT));
        panelInferior = new Panel(new FlowLayout());

        lblUsuario = new Label("Usuario:");
        txtUsuario = new TextField(35);
        lblContrasena = new Label("Contraseña:");
        txtContrasena = new TextField(30);
        txtContrasena.setEchoChar('*');

        panelSuperior.add(lblUsuario);
        panelSuperior.add(txtUsuario);
        panelSuperior.add(lblContrasena);
        panelSuperior.add(txtContrasena);

        btnRegistrarse = new Button("Registrarse");
        btnIngresar = new Button("Ingresar");

        panelInferior.add(btnRegistrarse);
        panelInferior.add(btnIngresar);

        add(panelSuperior, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean verificador = verificarCredenciales(txtUsuario.getText().trim(), txtContrasena.getText().trim());
                if (verificador) {
                    new VentanaPrincipal(listaEmpleados, listaProveedores);
                    dispose();
                }
            }
        });

        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaRegistroEmpleado(listaEmpleados);
            }
        });

        setVisible(true);
    }

    public boolean verificarCredenciales(String usuarioIngresado, String contrasenaIngresada) {
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getUsuario().equals(usuarioIngresado) &&
                    empleado.getContrasena().equals(contrasenaIngresada)) {
                System.out.println("Ingreso Exitoso. Bienvenido, " + empleado.getUsuario());
                return true;
            }
        }
        System.out.println("Usuario o contraseña incorrectas.");
        return false;
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
}

