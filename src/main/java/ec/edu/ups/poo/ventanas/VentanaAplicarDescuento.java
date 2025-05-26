package ec.edu.ups.poo.ventanas;

import ec.edu.ups.poo.clases.Proveedor;
import ec.edu.ups.poo.clases.SolicitudCompra;
import ec.edu.ups.poo.clases.ItemCompra;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;

public class VentanaAplicarDescuento extends Frame {

    private SolicitudCompra compra;
    private List<SolicitudCompra> listaCompras;
    private TextArea txtListaProductos, txtListaDescuentos;
    private TextField txtPorcentajeDescuento;
    private Button btnAplicarDescuento, btnGuardarDescuento, btnRegresar;
    private DecimalFormat formatoMoneda = new DecimalFormat("#,##0.00");

    public VentanaAplicarDescuento(SolicitudCompra compra, List<SolicitudCompra> listaCompras, List<Proveedor> listaProveedores) {
        this.compra = compra;
        this.listaCompras = listaCompras;
        setTitle("Aplicar Descuento");
        setSize(500, 500);
        setLayout(new GridLayout(4, 1)); // Se ajusta GridLayout para incluir "Guardar Descuento"
        setLocationRelativeTo(null);

        // Lista de productos con precio actual
        txtListaProductos = new TextArea(10, 50);
        txtListaProductos.setEditable(false);
        cargarListaProductos();
        add(txtListaProductos);

        // Panel para ingresar el porcentaje de descuento
        Panel panelDescuento = new Panel(new FlowLayout(FlowLayout.CENTER));
        panelDescuento.add(new Label("Porcentaje de Descuento (%):"));
        txtPorcentajeDescuento = new TextField(5);
        panelDescuento.add(txtPorcentajeDescuento);
        btnAplicarDescuento = new Button("Aplicar Descuento");
        panelDescuento.add(btnAplicarDescuento);
        add(panelDescuento);

        // Lista de precios después del descuento
        txtListaDescuentos = new TextArea(10, 50);
        txtListaDescuentos.setEditable(false);
        add(txtListaDescuentos);

        // Panel de botones inferiores
        Panel panelInferior = new Panel(new FlowLayout(FlowLayout.CENTER));
        btnGuardarDescuento = new Button("Guardar Descuento"); // Nuevo botón
        btnRegresar = new Button("Regresar");
        panelInferior.add(btnGuardarDescuento);
        panelInferior.add(btnRegresar);
        add(panelInferior);

        btnAplicarDescuento.addActionListener(e -> aplicarDescuento());
        btnGuardarDescuento.addActionListener(e -> guardarDescuento()); // Evento de guardar
        btnRegresar.addActionListener(e -> {
            new VentanaGestionCompra(this.listaCompras, listaProveedores);
            dispose();
        });

        setVisible(true);
    }

    private void cargarListaProductos() {
        txtListaProductos.setText("Lista de productos:\n");
        for (ItemCompra item : compra.getListadoItemProductos()) {
            txtListaProductos.append(item.getProducto().getNombre() +
                    " | Precio Actual: $" + formatoMoneda.format(item.getProducto().getPrecio()) + "\n");
        }
    }

    private void aplicarDescuento() {
        double porcentajeDescuento = Double.parseDouble(txtPorcentajeDescuento.getText().trim());
        txtListaDescuentos.setText("Precios ajustados con descuento:\n");

        for (ItemCompra item : compra.getListadoItemProductos()) {
            item.getProducto().aplicarDescuento(porcentajeDescuento); // Modifica el precio directamente
            double subtotalFinal = item.getProducto().getPrecio() * item.getCantidad();

            txtListaDescuentos.append(item.getProducto().getNombre() +
                    " | Nuevo Precio: $" + formatoMoneda.format(item.getProducto().getPrecio()) + "\n");
        }
    }

    private void guardarDescuento() {
        // Buscar la compra dentro de listaCompras y actualizar los precios
        for (SolicitudCompra solicitud : listaCompras) {
            if (solicitud.getCodigo().equals(compra.getCodigo())) {
                // Actualizar la lista de productos con los nuevos precios
                for (ItemCompra itemCompra : solicitud.getListadoItemProductos()) {
                    for (ItemCompra itemModificado : compra.getListadoItemProductos()) {
                        if (itemCompra.getProducto().getCodigo().equals(itemModificado.getProducto().getCodigo())) {
                            itemCompra.getProducto().setPrecio(itemModificado.getProducto().getPrecio()); // Guarda el nuevo precio
                        }
                    }
                }
                break;
            }
        }

        System.out.println("Descuento guardado correctamente en la solicitud de compra.");
    }

}
