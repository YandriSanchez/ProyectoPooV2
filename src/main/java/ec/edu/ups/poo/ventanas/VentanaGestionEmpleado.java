package ec.edu.ups.poo.ventanas;

import ec.edu.ups.poo.clases.Empleado;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaGestionEmpleado extends Frame {

    private TextArea txtListaEmpleados;
    private TextField txtCedulaBuscar;
    private Button btnAgregar, btnEliminar, btnEditar, btnVerDetalles, btnBuscar, btnRegresar;
    private Panel panelBusqueda;
    private List<Empleado> listaEmpleados;

    public VentanaGestionEmpleado(List<Empleado> listaEmpleados) {
        setTitle("Gestión de Empleados");
        setSize(400, 350);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        this.listaEmpleados = listaEmpleados;

        Panel panelSuperior = new Panel(new GridLayout(1, 4, 10, 10));
        btnAgregar = new Button("Agregar");
        btnEliminar = new Button("Eliminar");
        btnEditar = new Button("Editar");
        btnVerDetalles = new Button("Ver Detalles");

        panelSuperior.add(btnAgregar);
        panelSuperior.add(btnEliminar);
        panelSuperior.add(btnEditar);
        panelSuperior.add(btnVerDetalles);

        panelBusqueda = new Panel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new Label("Ingrese cédula:"));
        txtCedulaBuscar = new TextField(15);
        panelBusqueda.add(txtCedulaBuscar);
        btnBuscar = new Button("Buscar");
        panelBusqueda.add(btnBuscar);
        panelBusqueda.setVisible(false);

        txtListaEmpleados = new TextArea("", 10, 50, TextArea.SCROLLBARS_VERTICAL_ONLY);
        txtListaEmpleados.setEditable(false);
        cargarListaEmpleados();

        Panel panelCentro = new Panel(new BorderLayout());
        panelCentro.add(panelBusqueda, BorderLayout.NORTH);
        panelCentro.add(txtListaEmpleados, BorderLayout.CENTER);

        Panel panelInferior = new Panel(new FlowLayout(FlowLayout.LEFT));
        btnRegresar = new Button("Regresar");
        panelInferior.add(btnRegresar);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaRegistroEmpleado(listaEmpleados);
                dispose();
            }
        });

        btnEliminar.addActionListener(e -> {
            panelBusqueda.setVisible(true);
            btnBuscar.setLabel("Eliminar");
            revalidate();
            repaint();
        });

        btnEditar.addActionListener(e -> {
            panelBusqueda.setVisible(true);
            btnBuscar.setLabel("Editar");
            revalidate();
            repaint();
        });

        btnVerDetalles.addActionListener(e -> {
            panelBusqueda.setVisible(true);
            btnBuscar.setLabel("Ver Detalles");
            revalidate();
            repaint();
        });

        btnBuscar.addActionListener(e -> buscarEmpleado());

        btnRegresar.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void cargarListaEmpleados() {
        txtListaEmpleados.setText("");

        for (Empleado empleado : listaEmpleados) {
            txtListaEmpleados.append(empleado.getIdentificacion() + " - " + empleado.getNombre() + "\n");
        }
    }

    private void buscarEmpleado() {
        String cedula = txtCedulaBuscar.getText().trim();
        Empleado empleadoEncontrado = null;

        for (Empleado empleado : listaEmpleados) {
            if (empleado.getIdentificacion().equals(cedula)) {
                empleadoEncontrado = empleado;
                break;
            }
        }

        if (empleadoEncontrado != null) {
            switch (btnBuscar.getLabel()) {
                case "Eliminar":
                    listaEmpleados.remove(empleadoEncontrado);
                    cargarListaEmpleados();
                    System.out.println("Empleado eliminado: " + empleadoEncontrado.getNombre());
                    break;
                case "Editar":
                    new VentanaEditarEmpleado(empleadoEncontrado, listaEmpleados);
                    break;
                case "Ver Detalles":
                    new VentanaDetallesEmpleado(empleadoEncontrado);
                    break;
            }
        } else {
            System.out.println("No se encontró un empleado con esa cédula.");
        }
    }
}