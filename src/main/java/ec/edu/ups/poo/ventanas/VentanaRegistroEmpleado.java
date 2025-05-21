package ec.edu.ups.poo.ventanas;

import ec.edu.ups.poo.App;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistroEmpleado extends Frame {

    private TextField txtNombre;
    private TextField txtIdentificacion;
    private TextField txtTelefono;
    private TextField txtCorreo;
    private TextField txtDireccion;
    private TextField txtUsuario;
    private TextField txtContrasena;
    private Button btnRegistrar;
    private Button btnRegresar;
    private App gestorEmpleados = new App();

    public VentanaRegistroEmpleado() {

        setTitle("REGISTRANDOSE...");
        setSize(400, 300);
        setLayout(new GridLayout(8, 2));
        setLocationRelativeTo(null);

        // Crear Labels y TextFields
        add(new Label("Nombre:"));
        txtNombre = new TextField();
        add(txtNombre);

        add(new Label("Identificación:"));
        txtIdentificacion = new TextField();
        add(txtIdentificacion);

        add(new Label("Teléfono:"));
        txtTelefono = new TextField();
        add(txtTelefono);

        add(new Label("Correo:"));
        txtCorreo = new TextField();
        add(txtCorreo);

        add(new Label("Dirección:"));
        txtDireccion = new TextField();
        add(txtDireccion);

        add(new Label("Usuario:"));
        txtUsuario = new TextField();
        add(txtUsuario);

        add(new Label("Contraseña:"));
        txtContrasena = new TextField();
        txtContrasena.setEchoChar('*'); // Ocultar caracteres
        add(txtContrasena);

        // Botones
        btnRegistrar = new Button("Registrar");
        btnRegresar = new Button("Regresar");

        add(btnRegistrar);
        add(btnRegresar);

        // Evento para registrar empleado
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestorEmpleados.registrarEmpleado(txtNombre.getText(), txtIdentificacion.getText().trim(),
                        txtTelefono.getText().trim(), txtCorreo.getText().trim(), txtDireccion.getText(),
                        txtUsuario.getText().trim(), txtContrasena.getText().trim());
                dispose();
            }
        });

        // Evento para regresar
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                VentanaIniciarSesion ventana = new VentanaIniciarSesion();
            }
        });

        setVisible(true);
    }
}
