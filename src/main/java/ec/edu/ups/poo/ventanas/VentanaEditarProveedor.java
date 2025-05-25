package ec.edu.ups.poo.ventanas;

import ec.edu.ups.poo.clases.Proveedor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaEditarProveedor extends Frame {

    private TextField txtNombre, txtIdentificacion, txtTelefono, txtCorreo, txtDireccion;
    private Button btnGuardarProveedor, btnRegresarProveedor;
    private Proveedor proveedorEncontrado;
    private List<Proveedor> listaProveedores;

    public VentanaEditarProveedor(Proveedor proveedorEncontrado, List<Proveedor> listaProveedores) {
        setTitle("Editar Proveedor");
        setSize(400, 300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        this.proveedorEncontrado = proveedorEncontrado;
        this.listaProveedores = listaProveedores;

        Panel panelDatos = new Panel(new GridLayout(6, 2, 10, 10));

        panelDatos.add(new Label("Nombre:"));
        txtNombre = new TextField(proveedorEncontrado.getNombre(), 20);
        panelDatos.add(txtNombre);

        panelDatos.add(new Label("Identificación:"));
        txtIdentificacion = new TextField(proveedorEncontrado.getIdentificacion(), 20);
        txtIdentificacion.setEditable(false); // Bloquea edición de identificación
        panelDatos.add(txtIdentificacion);

        panelDatos.add(new Label("Teléfono:"));
        txtTelefono = new TextField(proveedorEncontrado.getTelefono(), 20);
        panelDatos.add(txtTelefono);

        panelDatos.add(new Label("Correo:"));
        txtCorreo = new TextField(proveedorEncontrado.getCorreo(), 20);
        panelDatos.add(txtCorreo);

        panelDatos.add(new Label("Dirección:"));
        txtDireccion = new TextField(proveedorEncontrado.getDireccion(), 20);
        panelDatos.add(txtDireccion);

        add(panelDatos, BorderLayout.CENTER);

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER));
        btnGuardarProveedor = new Button("Guardar Cambios");
        btnRegresarProveedor = new Button("Regresar");

        panelBotones.add(btnGuardarProveedor);
        panelBotones.add(btnRegresarProveedor);
        add(panelBotones, BorderLayout.SOUTH);

        btnGuardarProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarEdicionProveedor();
                new VentanaGestionProveedor(listaProveedores);
                dispose();
            }
        });

        btnRegresarProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void guardarEdicionProveedor() {
        proveedorEncontrado.setNombre(txtNombre.getText().trim());
        proveedorEncontrado.setTelefono(txtTelefono.getText().trim());
        proveedorEncontrado.setCorreo(txtCorreo.getText().trim());
        proveedorEncontrado.setDireccion(txtDireccion.getText().trim());

        for (int i = 0; i < listaProveedores.size(); i++) {
            if (listaProveedores.get(i).getIdentificacion().equals(proveedorEncontrado.getIdentificacion())) {
                listaProveedores.set(i, proveedorEncontrado);
                break;
            }
        }

        System.out.println("Proveedor actualizado con éxito: " + proveedorEncontrado);
    }
}
