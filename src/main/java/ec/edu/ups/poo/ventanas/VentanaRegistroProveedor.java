package ec.edu.ups.poo.ventanas;

import ec.edu.ups.poo.clases.Proveedor;
import ec.edu.ups.poo.clases.Producto;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaRegistroProveedor extends Frame {

    private TextField txtNombre, txtIdentificacion, txtTelefono, txtCorreo, txtDireccion, txtNombreProducto, txtCodigoProducto, txtPrecioProducto;
    private Checkbox chkConImpuesto, chkSinImpuesto, chkAgregarOtro, chkFinalizar;
    private Button btnGuardarProveedor;
    private Panel panelProducto;
    private List<Producto> listaProductos = new ArrayList<>();
    private boolean tieneImpuesto = false;

    public VentanaRegistroProveedor(List<Proveedor> listaProveedores) {
        setTitle("Registro de Proveedor");
        setSize(400, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Panel panelDatos = new Panel(new GridLayout(6, 2, 10, 10));

        panelDatos.add(new Label("Nombre:"));
        txtNombre = new TextField(20);
        panelDatos.add(txtNombre);

        panelDatos.add(new Label("Identificación:"));
        txtIdentificacion = new TextField(20);
        panelDatos.add(txtIdentificacion);

        panelDatos.add(new Label("Teléfono:"));
        txtTelefono = new TextField(20);
        panelDatos.add(txtTelefono);

        panelDatos.add(new Label("Correo:"));
        txtCorreo = new TextField(20);
        panelDatos.add(txtCorreo);

        panelDatos.add(new Label("Dirección:"));
        txtDireccion = new TextField(20);
        panelDatos.add(txtDireccion);

        panelDatos.add(new Label("¿Productos con impuestos?"));
        Panel panelImpuesto = new Panel(new FlowLayout(FlowLayout.LEFT));
        chkConImpuesto = new Checkbox("Sí", false);
        chkSinImpuesto = new Checkbox("No", false);
        panelImpuesto.add(chkConImpuesto);
        panelImpuesto.add(chkSinImpuesto);
        panelDatos.add(panelImpuesto);

        add(panelDatos, BorderLayout.NORTH);

        panelProducto = new Panel(new GridLayout(5, 2, 10, 10));
        panelProducto.add(new Label("Nombre del Producto:"));
        txtNombreProducto = new TextField(20);
        panelProducto.add(txtNombreProducto);

        panelProducto.add(new Label("Código del Producto:"));
        txtCodigoProducto = new TextField(20);
        panelProducto.add(txtCodigoProducto);

        panelProducto.add(new Label("Precio del Producto:"));
        txtPrecioProducto = new TextField(20);
        panelProducto.add(txtPrecioProducto);

        panelProducto.add(new Label("¿Desea agregar otro producto?"));
        Panel panelAgregarOtro = new Panel(new FlowLayout(FlowLayout.LEFT));
        chkAgregarOtro = new Checkbox("Sí", false);
        chkFinalizar = new Checkbox("No", false);
        panelAgregarOtro.add(chkAgregarOtro);
        panelAgregarOtro.add(chkFinalizar);
        panelProducto.add(panelAgregarOtro);

        btnGuardarProveedor = new Button("Guardar Proveedor");
        btnGuardarProveedor.setVisible(false);
        panelProducto.add(btnGuardarProveedor);

        panelProducto.setVisible(false); // Se oculta hasta seleccionar impuestos

        add(panelProducto, BorderLayout.CENTER);

        chkConImpuesto.addItemListener(e -> {
            if (chkConImpuesto.getState()) {
                chkSinImpuesto.setState(false);
                tieneImpuesto = true;
                panelProducto.setVisible(true);
                revalidate();
                repaint();
            }
        });

        chkSinImpuesto.addItemListener(e -> {
            if (chkSinImpuesto.getState()) {
                chkConImpuesto.setState(false);
                tieneImpuesto = false;
                panelProducto.setVisible(true);
                revalidate();
                repaint();
            }
        });

        chkAgregarOtro.addItemListener(e -> {
            if (chkAgregarOtro.getState()) {
                chkFinalizar.setState(false);
                limpiarCamposProducto();
                btnGuardarProveedor.setVisible(false);
                revalidate();
                repaint();
            }
        });

        chkFinalizar.addItemListener(e -> {
            if (chkFinalizar.getState()) {
                chkAgregarOtro.setState(false);
                btnGuardarProveedor.setVisible(true);
                revalidate();
                repaint();
            }
        });

        btnGuardarProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProveedor(listaProveedores);
                new VentanaGestionProveedor(listaProveedores);
                dispose();
            }
        });

        setVisible(true);
    }

    private void limpiarCamposProducto() {
        txtNombreProducto.setText("");
        txtCodigoProducto.setText("");
        txtPrecioProducto.setText("");
    }

    private void guardarProveedor(List<Proveedor> listaProveedores) {
        String nombre = txtNombre.getText().trim();
        String identificacion = txtIdentificacion.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();
        String direccion = txtDireccion.getText().trim();

        Proveedor nuevoProveedor = new Proveedor(nombre, identificacion, telefono, correo, direccion, tieneImpuesto, listaProductos);
        listaProveedores.add(nuevoProveedor);

        System.out.println("Proveedor agregado con éxito: " + nuevoProveedor);
    }
}

