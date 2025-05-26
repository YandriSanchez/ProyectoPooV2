package ec.edu.ups.poo.ventanas;

import ec.edu.ups.poo.clases.Proveedor;
import ec.edu.ups.poo.clases.SolicitudCompra;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class VentanaGestionCompra extends Frame {

    private TextArea txtListaCompras;
    private TextField txtNumeroBuscar;
    private Button btnAgregar, btnEliminar, btnEditar, btnVerDetalles, btnBuscar, btnAplicarDescuento, btnRegresar;
    private Panel panelBusqueda;
    private List<SolicitudCompra> listaCompras;
    private final List<Proveedor> listaProveedores;

    public VentanaGestionCompra(List<SolicitudCompra> listaCompras, List<Proveedor> listaProveedores) {
        setTitle("Gestión de Compras");
        setSize(450, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        this.listaCompras = listaCompras;
        this.listaProveedores = listaProveedores;

        // Panel superior con botones principales
        Panel panelSuperior = new Panel(new GridLayout(1, 4, 10, 10));
        btnAgregar = new Button("Agregar");
        btnEliminar = new Button("Eliminar");
        btnEditar = new Button("Editar");
        btnVerDetalles = new Button("Ver Detalles");

        panelSuperior.add(btnAgregar);
        panelSuperior.add(btnEliminar);
        panelSuperior.add(btnEditar);
        panelSuperior.add(btnVerDetalles);

        // Panel de búsqueda
        panelBusqueda = new Panel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new Label("Ingrese número de solicitud:"));
        txtNumeroBuscar = new TextField(10);
        panelBusqueda.add(txtNumeroBuscar);
        btnBuscar = new Button("Buscar");
        panelBusqueda.add(btnBuscar);
        panelBusqueda.setVisible(false);

        // Área de texto para lista de compras
        txtListaCompras = new TextArea("", 10, 50, TextArea.SCROLLBARS_VERTICAL_ONLY);
        txtListaCompras.setEditable(false);
        cargarListaCompras();

        // Contenedor central con búsqueda y lista de compras
        Panel panelCentro = new Panel(new BorderLayout());
        panelCentro.add(panelBusqueda, BorderLayout.NORTH);
        panelCentro.add(txtListaCompras, BorderLayout.CENTER);

        // Panel inferior con botones "Regresar" y "Aplicar Descuento"
        Panel panelInferior = new Panel(new FlowLayout(FlowLayout.LEFT));
        btnRegresar = new Button("Regresar");
        btnAplicarDescuento = new Button("Aplicar Descuento");

        panelInferior.add(btnRegresar);
        panelInferior.add(btnAplicarDescuento);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Acciones de los botones
        //btnAgregar.addActionListener(e -> new VentanaRegistroCompra(listaCompras));
        btnAgregar.addActionListener(e -> {
            new VentanaRegistroCompra(listaCompras,listaProveedores);
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

        btnBuscar.addActionListener(e -> buscarSolicitudCompra());

        btnAplicarDescuento.addActionListener(e -> {
            panelBusqueda.setVisible(true);
            btnBuscar.setLabel("Aplicar Descuento");
            revalidate();
            repaint();
        });

        btnRegresar.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void cargarListaCompras() {
        txtListaCompras.setText("");

        SimpleDateFormat formatoFecha = new SimpleDateFormat("d 'de' MMMM, 'Hora:' HH:mm.ss");

        for (SolicitudCompra compra : listaCompras) {
            txtListaCompras.append("Código: " + compra.getCodigo() +
                    " | Proveedor: " + compra.getProveedor().getNombre() +
                    " | Fecha: " + formatoFecha.format(compra.getFechaEmision().getTime()) +
                    " | Total: $" + compra.calcularTotal() +
                    " | Estado: " + compra.getEstado() + "\n");
        }
    }

    private void buscarSolicitudCompra() {
        String codigoIngresado = txtNumeroBuscar.getText().trim();
        SolicitudCompra compraEncontrada = null;

        for (SolicitudCompra compra : listaCompras) {
            if (compra.getCodigo().equals(codigoIngresado)) {
                compraEncontrada = compra;
                break;
            }
        }

        if (compraEncontrada != null) {
            switch (btnBuscar.getLabel()) {
                case "Eliminar":
                    listaCompras.remove(compraEncontrada);
                    cargarListaCompras();
                    System.out.println("Solicitud eliminada.");
                    break;
                case "Editar":
                    new VentanaEditarCompra(compraEncontrada, listaCompras, listaProveedores);
                    break;
                case "Ver Detalles":
                    new VentanaDetallesCompra(compraEncontrada, listaCompras, listaProveedores);
                    break;
                case "Aplicar Descuento":
                    new VentanaAplicarDescuento(compraEncontrada, listaCompras, listaProveedores);
                    System.out.println("Descuento aplicado.");
                    break;
            }
        } else {
            System.out.println("No se encontró ninguna solicitud con ese código.");
        }
    }
}

