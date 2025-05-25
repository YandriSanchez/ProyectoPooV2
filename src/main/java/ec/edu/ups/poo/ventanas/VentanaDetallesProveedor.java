package ec.edu.ups.poo.ventanas;

import ec.edu.ups.poo.clases.Proveedor;
import ec.edu.ups.poo.clases.Producto;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaDetallesProveedor extends Frame {

    private TextArea txtProveedorDetalles, txtListaProductos;
    private Button btnRegresar;
    private Proveedor proveedorEncontrado;

    public VentanaDetallesProveedor(Proveedor proveedorEncontrado) {
        setTitle("Detalles del Proveedor");
        setSize(500, 350);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        this.proveedorEncontrado = proveedorEncontrado;

        Panel panelCentral = new Panel(new GridLayout(1, 2, 10, 10)); // Dos columnas

        txtProveedorDetalles = new TextArea("", 10, 25, TextArea.SCROLLBARS_VERTICAL_ONLY);
        txtProveedorDetalles.setEditable(false);
        txtListaProductos = new TextArea("", 10, 25, TextArea.SCROLLBARS_VERTICAL_ONLY);
        txtListaProductos.setEditable(false);

        cargarDetallesProveedor();
        cargarListaProductos();

        panelCentral.add(txtProveedorDetalles);
        panelCentral.add(txtListaProductos);
        add(panelCentral, BorderLayout.CENTER);

        Panel panelInferior = new Panel(new FlowLayout(FlowLayout.LEFT));
        btnRegresar = new Button("Regresar");
        panelInferior.add(btnRegresar);
        add(panelInferior, BorderLayout.SOUTH);

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void cargarDetallesProveedor() {
        txtProveedorDetalles.setText("Información del Proveedor:\n");
        txtProveedorDetalles.append("Nombre: " + proveedorEncontrado.getNombre() + "\n");
        txtProveedorDetalles.append("Identificación: " + proveedorEncontrado.getIdentificacion() + "\n");
        txtProveedorDetalles.append("Teléfono: " + proveedorEncontrado.getTelefono() + "\n");
        txtProveedorDetalles.append("Correo: " + proveedorEncontrado.getCorreo() + "\n");
        txtProveedorDetalles.append("Dirección: " + proveedorEncontrado.getDireccion() + "\n");
        txtProveedorDetalles.append("¿Aplica Impuesto?: " + (proveedorEncontrado.isImpuesto() ? "Sí" : "No") + "\n");
    }

    private void cargarListaProductos() {
        List<? extends Producto> listaProductos = proveedorEncontrado.getListaProductos();

        txtListaProductos.setText("Lista de Productos:\n");

        if (listaProductos.isEmpty()) {
            txtListaProductos.append("No hay productos registrados.\n");
        } else {
            for (Producto producto : listaProductos) {
                txtListaProductos.append("🔹 " + producto.getNombre() + " | Código: " + producto.getCodigo() + " | Precio: $" + producto.getPrecio() + "\n");
            }
        }
    }
}
