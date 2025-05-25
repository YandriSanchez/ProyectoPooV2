package ec.edu.ups.poo.ventanas;

import ec.edu.ups.poo.App;
import ec.edu.ups.poo.clases.Empleado;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

    private List<Empleado> listaEmpleados;
    private VentanaRegistroEmpleado ventanaRegistroEmpleado;
    private VentanaPrincipal ventanaPrincipal;

    public VentanaIniciarSesion(List<Empleado> listaEmpleados) {
        setTitle("Gestión de Órdenes de Compra");
        setSize(400, 150);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        this.listaEmpleados = listaEmpleados;

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
                boolean verificador = verificarCredenciales(txtUsuario.getText().trim(),txtContrasena.getText().trim());
                if(verificador){
                    ventanaPrincipal = new VentanaPrincipal();
                    dispose();
                }
            }
        });

        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaRegistroEmpleado = new VentanaRegistroEmpleado(listaEmpleados);
                dispose();
            }
        });

        setVisible(true);
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
}
