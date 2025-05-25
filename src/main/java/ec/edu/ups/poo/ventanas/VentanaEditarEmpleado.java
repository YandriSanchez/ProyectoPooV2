package ec.edu.ups.poo.ventanas;

import ec.edu.ups.poo.clases.Empleado;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaEditarEmpleado extends Frame {

    private TextField txtNombre, txtIdentificacion, txtTelefono, txtCorreo, txtDireccion;
    private Button btnGuardarEmpleado, btnRegresarEmpleado;
    private Empleado empleadoEncontrado;
    private List<Empleado> listaEmpleados;

    public VentanaEditarEmpleado(Empleado empleadoEncontrado, List<Empleado> listaEmpleados) {
        setTitle("Editar Empleado");
        setSize(400, 300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        this.empleadoEncontrado = empleadoEncontrado;
        this.listaEmpleados = listaEmpleados;

        Panel panelDatos = new Panel(new GridLayout(5, 2, 10, 10));

        panelDatos.add(new Label("Nombre:"));
        txtNombre = new TextField(empleadoEncontrado.getNombre(), 20);
        panelDatos.add(txtNombre);

        panelDatos.add(new Label("Identificación:"));
        txtIdentificacion = new TextField(empleadoEncontrado.getIdentificacion(), 20);
        txtIdentificacion.setEditable(false);
        panelDatos.add(txtIdentificacion);

        panelDatos.add(new Label("Teléfono:"));
        txtTelefono = new TextField(empleadoEncontrado.getTelefono(), 20);
        panelDatos.add(txtTelefono);

        panelDatos.add(new Label("Correo:"));
        txtCorreo = new TextField(empleadoEncontrado.getCorreo(), 20);
        panelDatos.add(txtCorreo);

        panelDatos.add(new Label("Dirección:"));
        txtDireccion = new TextField(empleadoEncontrado.getDireccion(), 20);
        panelDatos.add(txtDireccion);

        add(panelDatos, BorderLayout.CENTER);

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER));
        btnGuardarEmpleado = new Button("Guardar Cambios");
        btnRegresarEmpleado = new Button("Regresar");

        panelBotones.add(btnGuardarEmpleado);
        panelBotones.add(btnRegresarEmpleado);
        add(panelBotones, BorderLayout.SOUTH);

        btnGuardarEmpleado.addActionListener(e -> {
            guardarEdicionEmpleado();
            new VentanaGestionEmpleado(listaEmpleados);
            dispose();
        });

        btnRegresarEmpleado.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void guardarEdicionEmpleado() {
        empleadoEncontrado.setNombre(txtNombre.getText().trim());
        empleadoEncontrado.setTelefono(txtTelefono.getText().trim());
        empleadoEncontrado.setCorreo(txtCorreo.getText().trim());
        empleadoEncontrado.setDireccion(txtDireccion.getText().trim());

        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (listaEmpleados.get(i).getIdentificacion().equals(empleadoEncontrado.getIdentificacion())) {
                listaEmpleados.set(i, empleadoEncontrado);
                break;
            }
        }

        System.out.println("Empleado actualizado con éxito: " + empleadoEncontrado);
    }
}