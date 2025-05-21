package ec.edu.ups.poo.ventanas;

import ec.edu.ups.poo.clases.GestorProveedor;
import ec.edu.ups.poo.clases.Proveedor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaGestionProveedor extends Frame {

    private GestorProveedor gestorProveedor;
    private TextArea txtListaProveedores;
    private TextField txtCedulaBuscar;
    private Button btnAgregar;
    private Button btnEliminar;
    private Button btnEditar;
    private Button btnVerDetalles;
    private Button btnBuscar;
    private Button btnRegresar;
    private Panel panelBusqueda;

    public VentanaGestionProveedor(GestorProveedor gestorProveedor) {
        this.gestorProveedor = gestorProveedor;

        setTitle("Gestión de Proveedores");
        setSize(400, 350);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Panel panelSuperior = new Panel(new GridLayout(1, 4, 10, 10));
        btnAgregar = new Button("Agregar");
        btnEliminar = new Button("Eliminar");
        btnEditar = new Button("Editar");
        btnVerDetalles = new Button("Ver Detalles");
        btnRegresar = new Button("Regresar");

        panelSuperior.add(btnAgregar);
        panelSuperior.add(btnEliminar);
        panelSuperior.add(btnEditar);
        panelSuperior.add(btnVerDetalles);

        panelBusqueda = new Panel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new Label("Ingrese cédula:"));
        txtCedulaBuscar = new TextField(15);
        panelBusqueda.add(txtCedulaBuscar);
        btnBuscar = new Button("...........");
        panelBusqueda.add(btnBuscar);
        panelBusqueda.setVisible(false);

        txtListaProveedores = new TextArea("", 10, 50, TextArea.SCROLLBARS_VERTICAL_ONLY);
        txtListaProveedores.setEditable(false);
        cargarListaProveedores();

        Panel panelCentro = new Panel(new BorderLayout());
        panelCentro.add(panelBusqueda, BorderLayout.NORTH); // Panel de búsqueda encima
        panelCentro.add(txtListaProveedores, BorderLayout.CENTER); // Lista debajo

        Panel panelInferior = new Panel(new FlowLayout(FlowLayout.LEFT));
        panelInferior.add(btnRegresar);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelBusqueda();
                btnBuscar.setLabel("Eliminar");
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelBusqueda();
                btnBuscar.setLabel("Editar");
            }
        });

        btnVerDetalles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnBuscar.setLabel("Ver Detalles");
                btnBuscar.setPreferredSize(btnBuscar.getPreferredSize());
                mostrarPanelBusqueda();
            }
        });

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abrir ventana de Agregar Proveedor...");
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarAccionBusqueda();
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

    private void cargarListaProveedores() {
        txtListaProveedores.setText("");

        for (Proveedor proveedor : gestorProveedor.listaProveedores) {
            txtListaProveedores.append(proveedor.getIdentificacion() + " - " + proveedor.getNombre()
                    + " - Impuesto: " + (proveedor.isImpuesto() ? "Sí" : "No") + "\n");
        }
    }

    private void mostrarPanelBusqueda() {
        panelBusqueda.setVisible(true);
        revalidate();
        repaint();
    }

    private void procesarAccionBusqueda() {
        String cedula = txtCedulaBuscar.getText().trim();
        Proveedor proveedor = gestorProveedor.buscarProveedor(cedula);

        if (proveedor != null) {
            switch (btnBuscar.getLabel()) {
                case "Eliminar":
                    gestorProveedor.eliminarProveedor(cedula);
                    cargarListaProveedores();
                    break;
                case "Editar":
                    System.out.println("Abrir ventana de edición de proveedor...");
                    break;
                case "Ver Detalles":
                    System.out.println("Abrir ventana de detalles del proveedor...");
                    break;
            }
        } else {
            System.out.println("No se encontró un proveedor con esa cédula.");
        }
    }

}