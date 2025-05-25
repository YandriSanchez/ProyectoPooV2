package ec.edu.ups.poo.ventanas;

import ec.edu.ups.poo.clases.Proveedor;
import ec.edu.ups.poo.clases.Producto;
import ec.edu.ups.poo.clases.ItemCompra;
import ec.edu.ups.poo.clases.SolicitudCompra;
import ec.edu.ups.poo.enums.EstadoSolicitud;

import java.awt.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class VentanaRegistroCompra extends Frame {

    private TextField txtIdentificacion, txtNombreProducto, txtCantidad;
    private Button btnBuscar, btnAgregarCantidad, btnRealizarCompra, btnRegresar;
    private Label lblProveedorInfo;
    private TextArea txtListaProductos;
    private Panel panelCantidad, panelContenedor;
    private List<ItemCompra> listaItemCompra;
    private List<Proveedor> listaProveedores;
    private Proveedor proveedorEncontrado;
    private List<SolicitudCompra> listaCompras;
    private List<Producto> productosProveedor;
    private int productoActualIndex;

    public VentanaRegistroCompra(List<SolicitudCompra> listaCompras, List<Proveedor> listaProveedores) {
        setTitle("Registro de Compra");
        setSize(500, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        this.listaCompras = listaCompras;
        this.listaProveedores = listaProveedores;
        this.listaItemCompra = new ArrayList<>();
        this.productoActualIndex = 0;

        // Panel superior con búsqueda de proveedor
        Panel panelSuperior = new Panel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new Label("Ingrese la identificación del proveedor:"));
        txtIdentificacion = new TextField(15);
        panelSuperior.add(txtIdentificacion);
        btnBuscar = new Button("Buscar");
        panelSuperior.add(btnBuscar);
        add(panelSuperior, BorderLayout.NORTH);

        // Panel que contiene los datos del proveedor y los productos
        panelContenedor = new Panel(new BorderLayout());

        lblProveedorInfo = new Label("");
        panelContenedor.add(lblProveedorInfo, BorderLayout.NORTH);

        txtListaProductos = new TextArea(10, 50);
        txtListaProductos.setEditable(false);
        panelContenedor.add(txtListaProductos, BorderLayout.CENTER);

        // Panel dinámico para ingresar cantidad
        panelCantidad = new Panel(new FlowLayout(FlowLayout.LEFT));
        txtNombreProducto = new TextField(20);
        txtNombreProducto.setEditable(false);
        txtCantidad = new TextField(5);
        panelCantidad.add(txtNombreProducto);
        panelCantidad.add(new Label("Cantidad"));
        panelCantidad.add(txtCantidad);
        panelCantidad.setVisible(false); // No aparece hasta encontrar proveedor

        panelContenedor.add(panelCantidad, BorderLayout.SOUTH);

        add(panelContenedor, BorderLayout.CENTER);

        // Panel inferior con botones
        Panel panelInferior = new Panel(new FlowLayout(FlowLayout.CENTER));
        btnAgregarCantidad = new Button("Agregar Cantidad");
        btnRealizarCompra = new Button("Realizar Compra");
        btnRegresar = new Button("Regresar");

        btnAgregarCantidad.setEnabled(false);
        btnRealizarCompra.setEnabled(false);

        panelInferior.add(btnRegresar);
        panelInferior.add(btnAgregarCantidad);
        panelInferior.add(btnRealizarCompra);
        add(panelInferior, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> buscarProveedor());
        btnAgregarCantidad.addActionListener(e -> agregarCantidad());
        btnRealizarCompra.addActionListener(e -> {
            realizarCompra();
            new VentanaGestionCompra(listaCompras, listaProveedores);
            dispose();
        });

        btnRegresar.addActionListener(e -> {
            new VentanaGestionCompra(listaCompras,listaProveedores);
            dispose();
        });

        setVisible(true);
    }

    private void buscarProveedor() {
        proveedorEncontrado = buscarProveedor(txtIdentificacion.getText().trim(), listaProveedores);

        if (proveedorEncontrado == null) {
            lblProveedorInfo.setText("No se encontró el proveedor.");
            txtListaProductos.setText("");
            return;
        }

        productosProveedor = new ArrayList<>(proveedorEncontrado.getListaProductos());
        lblProveedorInfo.setText("Proveedor: " + proveedorEncontrado.getIdentificacion() + " - " +
                proveedorEncontrado.getNombre());

        txtListaProductos.setText("Lista de productos:\n");
        listaItemCompra.clear();

        for (Producto producto : productosProveedor) {
            txtListaProductos.append(producto.getNombre() + " | Precio: $" + producto.getPrecio() +
                    " | Cantidad: 0 | Subtotal: $0\n");
            listaItemCompra.add(new ItemCompra(producto, 0));
        }

        mostrarSiguienteProducto();
        panelCantidad.setVisible(true);
        btnAgregarCantidad.setEnabled(true);

        revalidate();
        repaint();
    }

    private void mostrarSiguienteProducto() {
        if (productoActualIndex < productosProveedor.size()) {
            Producto productoActual = productosProveedor.get(productoActualIndex);
            txtNombreProducto.setText(productoActual.getNombre());
            txtCantidad.setText("");
        } else {
            panelCantidad.setVisible(false);
            btnAgregarCantidad.setVisible(false);
            btnRealizarCompra.setEnabled(true);

            revalidate();
            repaint();
        }
    }

    private void agregarCantidad() {
        if (productoActualIndex >= productosProveedor.size()) {
            return;
        }

        int cantidad = Integer.parseInt(txtCantidad.getText().trim());
        listaItemCompra.get(productoActualIndex).setCantidad(cantidad);

        actualizarListaProductos();
        productoActualIndex++;
        mostrarSiguienteProducto();

        revalidate();
        repaint();
    }

    private void actualizarListaProductos() {
        txtListaProductos.setText("Lista de productos:\n");

        for (ItemCompra item : listaItemCompra) {
            txtListaProductos.append(item.getProducto().getNombre() + " | Precio: $" + item.getProducto().getPrecio() +
                    " | Cantidad: " + item.getCantidad() + " | Subtotal: $" + item.getSubtotal() + "\n");
        }

        revalidate();
        repaint();
    }

    private void realizarCompra() {
        if (proveedorEncontrado == null || listaItemCompra.isEmpty()) {
            System.out.println("No se puede realizar la compra porque no se encontró un proveedor o la lista de productos está vacía.");
            return;
        }

        EstadoSolicitud estado = EstadoSolicitud.SOLICITADA;
        String codigoCompra = String.format("%03d", listaCompras.size() + 1);
        SolicitudCompra nuevaCompra = new SolicitudCompra(estado, new GregorianCalendar(), listaItemCompra, codigoCompra, proveedorEncontrado);

        listaCompras.add(nuevaCompra);
        System.out.println("Compra registrada con éxito: " + nuevaCompra.getCodigo() + " | Proveedor: " + proveedorEncontrado.getNombre());

    }


    private Proveedor buscarProveedor(String cedula, List<Proveedor> listaProveedores) {
        for (Proveedor proveedor : listaProveedores) {
            if (proveedor.getIdentificacion().equals(cedula)) {
                return proveedor;
            }
        }
        return null;
    }
}
