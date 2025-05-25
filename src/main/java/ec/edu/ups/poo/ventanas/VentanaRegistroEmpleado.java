package ec.edu.ups.poo.ventanas;

import ec.edu.ups.poo.App;
import ec.edu.ups.poo.clases.Empleado;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

    public VentanaRegistroEmpleado(List<Empleado> listaEmpleados) {

        setTitle("REGISTRANDOSE...");
        setSize(400, 300);
        setLayout(new GridLayout(8, 2));
        setLocationRelativeTo(null);

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

        add(btnRegresar);
        add(btnRegistrar);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtNombre.getText().isEmpty() && !txtIdentificacion.getText().trim().isEmpty() &&
                        !txtUsuario.getText().trim().isEmpty() && !txtContrasena.getText().trim().isEmpty()) {
                    Empleado nuevoEmpleado = new Empleado(txtNombre.getText(), txtIdentificacion.getText().trim(),
                            txtTelefono.getText().trim(), txtCorreo.getText().trim(), txtDireccion.getText(),
                            txtUsuario.getText().trim(), txtContrasena.getText().trim());
                    listaEmpleados.add(nuevoEmpleado);
                    System.out.println("Empleado registrado exitosamente.");
                    dispose();
                } else {
                    System.out.println("ERROR, empleado no registrado, todos los campos son obligatorios.");
                }
            }
        });

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
}
