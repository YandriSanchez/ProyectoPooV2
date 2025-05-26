package ec.edu.ups.poo.ventanas;

import ec.edu.ups.poo.clases.Proveedor;
import ec.edu.ups.poo.clases.SolicitudCompra;
import ec.edu.ups.poo.clases.ItemCompra;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class VentanaDetallesCompra extends Frame {

    private SolicitudCompra compra;
    private TextArea txtDetallesCompra;
    private Button btnRegresar;

    public VentanaDetallesCompra(SolicitudCompra compra, List<SolicitudCompra> listaCompras, List<Proveedor> listaProveedores) {
        this.compra = compra;
        setTitle("Detalles de Compra");
        setSize(500, 300);
        setLayout(new GridLayout(2, 1)); // Aplicando GridLayout
        setLocationRelativeTo(null);

        // Área de detalles de la compra
        txtDetallesCompra = new TextArea(15, 50);
        txtDetallesCompra.setEditable(false);
        cargarDetallesCompra();
        add(txtDetallesCompra);

        Panel panelInferior = new Panel();
        panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnRegresar = new Button("Regresar");
        panelInferior.add(btnRegresar);
        add(panelInferior);

        btnRegresar.addActionListener(e -> {
            new VentanaGestionCompra(listaCompras, listaProveedores); // Instancia la ventana de gestión de compras
            dispose();
        });

        setVisible(true);
    }

    private void cargarDetallesCompra() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("d 'de' MMMM, 'hora:' HH:mm.ss");

        txtDetallesCompra.setText("Fecha de Emisión: " + formatoFecha.format(compra.getFechaEmision().getTime()) + "\n");
        txtDetallesCompra.append("Código: " + compra.getCodigo() + "\n");
        txtDetallesCompra.append("Proveedor: " + compra.getProveedor().getNombre() + "\n");
        txtDetallesCompra.append("\nProductos:\n");

        for (ItemCompra item : compra.getListadoItemProductos()) {
            txtDetallesCompra.append(item.getProducto().getNombre() + " | Cantidad: " + item.getCantidad() + " | Subtotal: $" + item.getSubtotal() + "\n");
        }

        txtDetallesCompra.append("\nTotal de Compra: $" + compra.calcularTotal() + "\n");
    }
}
