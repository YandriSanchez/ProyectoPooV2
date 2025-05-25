package ec.edu.ups.poo.ventanas;

import ec.edu.ups.poo.clases.Empleado;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaDetallesEmpleado extends Frame {

    private TextArea txtEmpleadoDetalles;
    private Button btnRegresar;
    private Empleado empleadoEncontrado;

    public VentanaDetallesEmpleado(Empleado empleadoEncontrado) {
        setTitle("Detalles del Empleado");
        setSize(400, 250);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        this.empleadoEncontrado = empleadoEncontrado;

        txtEmpleadoDetalles = new TextArea("", 10, 50, TextArea.SCROLLBARS_VERTICAL_ONLY);
        txtEmpleadoDetalles.setEditable(false);

        cargarDetallesEmpleado();

        add(txtEmpleadoDetalles, BorderLayout.CENTER);

        Panel panelInferior = new Panel(new FlowLayout(FlowLayout.CENTER));
        btnRegresar = new Button("Regresar");
        panelInferior.add(btnRegresar);
        add(panelInferior, BorderLayout.SOUTH);

        btnRegresar.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void cargarDetallesEmpleado() {
        txtEmpleadoDetalles.setText("Información del Empleado:\n");
        txtEmpleadoDetalles.append("Nombre: " + empleadoEncontrado.getNombre() + "\n");
        txtEmpleadoDetalles.append("Identificación: " + empleadoEncontrado.getIdentificacion() + "\n");
        txtEmpleadoDetalles.append("Teléfono: " + empleadoEncontrado.getTelefono() + "\n");
        txtEmpleadoDetalles.append("Correo: " + empleadoEncontrado.getCorreo() + "\n");
        txtEmpleadoDetalles.append("Dirección: " + empleadoEncontrado.getDireccion() + "\n");
    }
}