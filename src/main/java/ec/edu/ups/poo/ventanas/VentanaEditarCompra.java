package ec.edu.ups.poo.ventanas;

import ec.edu.ups.poo.clases.Proveedor;
import ec.edu.ups.poo.clases.SolicitudCompra;
import ec.edu.ups.poo.clases.ItemCompra;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class VentanaEditarCompra extends Frame {

    private SolicitudCompra compra;
    private List<SolicitudCompra> listaCompras;
    private TextField txtCodigoCompra, txtProveedor, txtTotal, txtBuscarProducto, txtNuevaCantidad;
    private TextArea txtListaProductos;
    private Button btnActualizar, btnRegresar, btnGuardar;
    private Panel panelEdicion;

    public VentanaEditarCompra(SolicitudCompra compra, List<SolicitudCompra> listaCompras, List<Proveedor> listaProveedores) {
        this.compra = compra;
        this.listaCompras = listaCompras;
        setTitle("Editar Compra");
        setSize(500, 500);
        setLayout(new GridBagLayout()); // Cambio a GridBagLayout
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Panel superior con código de compra y proveedor
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new Label("Código de Compra:"), gbc);

        gbc.gridx = 1;
        txtCodigoCompra = new TextField(compra.getCodigo());
        txtCodigoCompra.setEditable(false);
        add(txtCodigoCompra, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new Label("Proveedor:"), gbc);

        gbc.gridx = 1;
        txtProveedor = new TextField(compra.getProveedor().getNombre());
        txtProveedor.setEditable(false);
        add(txtProveedor, gbc);

        // Panel con total de compra
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new Label("Total:"), gbc);

        gbc.gridx = 1;
        txtTotal = new TextField(String.valueOf(compra.calcularTotal()), 10);
        txtTotal.setEditable(false);
        add(txtTotal, gbc);

        // Lista de productos
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        txtListaProductos = new TextArea(10, 50);
        txtListaProductos.setEditable(false);
        cargarListaProductos();
        add(txtListaProductos, gbc);

        // Panel de edición correctamente ubicado
        panelEdicion = new Panel(new GridBagLayout());
        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel.insets = new Insets(5, 5, 5, 5);

        gbcPanel.gridx = 0;
        gbcPanel.gridy = 0;
        panelEdicion.add(new Label("Buscar Producto:"), gbcPanel);

        gbcPanel.gridx = 1;
        txtBuscarProducto = new TextField(15);
        panelEdicion.add(txtBuscarProducto, gbcPanel);

        gbcPanel.gridx = 0;
        gbcPanel.gridy = 1;
        panelEdicion.add(new Label("Nueva Cantidad:"), gbcPanel);

        gbcPanel.gridx = 1;
        txtNuevaCantidad = new TextField(5);
        panelEdicion.add(txtNuevaCantidad, gbcPanel);

        gbcPanel.gridx = 2;
        btnActualizar = new Button("Actualizar");
        panelEdicion.add(btnActualizar, gbcPanel);

        // Añadir el panel de edición correctamente a la ventana
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(panelEdicion, gbc);

        // Panel de botones
        Panel panelInferior = new Panel(new FlowLayout(FlowLayout.CENTER));
        btnRegresar = new Button("Regresar");
        btnGuardar = new Button("Guardar Cambios");

        panelInferior.add(btnRegresar);
        panelInferior.add(btnGuardar);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(panelInferior, gbc);

        btnActualizar.addActionListener(e -> actualizarCantidad());
        btnGuardar.addActionListener(e -> {
            guardarCambios();
            new VentanaGestionCompra(listaCompras,listaProveedores);
            dispose();
        });
        btnRegresar.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void cargarListaProductos() {
        txtListaProductos.setText("Lista de productos:\n");
        for (ItemCompra item : compra.getListadoItemProductos()) {
            txtListaProductos.append(item.getProducto().getNombre() + " | Cantidad: " + item.getCantidad() + " | Subtotal: $" + item.getSubtotal() + "\n");
        }
    }

    private void actualizarCantidad() {
        String nombreProducto = txtBuscarProducto.getText().trim();
        int nuevaCantidad = Integer.parseInt(txtNuevaCantidad.getText().trim());

        for (ItemCompra item : compra.getListadoItemProductos()) {
            if (item.getProducto().getNombre().equalsIgnoreCase(nombreProducto)) {
                item.setCantidad(nuevaCantidad);
                cargarListaProductos();
                txtTotal.setText(String.valueOf(compra.calcularTotal()));
                txtBuscarProducto.setText("");
                txtNuevaCantidad.setText("");

                revalidate();
                repaint();
                break;
            }
        }
    }

    private void guardarCambios() {
        for (int i = 0; i < listaCompras.size(); i++) {
            if (listaCompras.get(i).getCodigo().equals(compra.getCodigo())) {
                listaCompras.set(i, compra);
                break;
            }
        }

        System.out.println("Cambios guardados correctamente en la compra " + compra.getCodigo());
    }
}
