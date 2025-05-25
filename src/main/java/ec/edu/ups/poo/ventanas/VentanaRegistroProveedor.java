package ec.edu.ups.poo.ventanas;

import ec.edu.ups.poo.clases.Proveedor;
import ec.edu.ups.poo.clases.Producto;
import ec.edu.ups.poo.clases.ProductoConImpuesto;
import ec.edu.ups.poo.clases.ProductoSinImpuesto;
import ec.edu.ups.poo.enums.TipoProductoConImpuesto;
import ec.edu.ups.poo.enums.TipoProductoSinImpuesto;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaRegistroProveedor extends Frame {

    private TextField txtNombre, txtIdentificacion, txtTelefono, txtCorreo, txtDireccion, txtNombreProducto, txtCodigoProducto, txtPrecioProducto;
    private Checkbox chkConImpuesto, chkSinImpuesto, chkValorAgregado, chkConsumoEspecial, chkAlimentoBasico, chkMedicamento, chkMaterialEducativo, chkAgregarOtro, chkFinalizar;
    private Button btnGuardarProveedor;
    private Panel panelProducto, panelTipoProducto;
    private List<? extends Producto> listaProductos;
    private boolean tieneImpuesto = false;

    public VentanaRegistroProveedor(List<Proveedor> listaProveedores) {
        setTitle("Registro de Proveedor");
        setSize(450, 550);
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

        panelTipoProducto = new Panel(new FlowLayout(FlowLayout.LEFT));
        panelTipoProducto.setVisible(false);
        add(panelTipoProducto, BorderLayout.CENTER);

        chkValorAgregado = new Checkbox("IVA", false);
        chkConsumoEspecial = new Checkbox("ICE", false);
        chkAlimentoBasico = new Checkbox("Alimento Básico", false);
        chkMedicamento = new Checkbox("Medicamento", false);
        chkMaterialEducativo = new Checkbox("Material Educativo", false);

        panelProducto = new Panel(new GridLayout(6, 2, 10, 10));
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

        panelProducto.setVisible(false);
        add(panelProducto, BorderLayout.SOUTH);

        chkConImpuesto.addItemListener(e -> {
            if (chkConImpuesto.getState()) {
                chkSinImpuesto.setState(false);
                tieneImpuesto = true;
                listaProductos = new ArrayList<ProductoConImpuesto>();

                panelTipoProducto.removeAll();
                panelTipoProducto.add(new Label("Seleccione tipo de impuesto:"));
                panelTipoProducto.add(chkValorAgregado);
                panelTipoProducto.add(chkConsumoEspecial);
                panelTipoProducto.setVisible(true);
                panelProducto.setVisible(true);
                revalidate();
                repaint();
            }
        });

        chkSinImpuesto.addItemListener(e -> {
            if (chkSinImpuesto.getState()) {
                chkConImpuesto.setState(false);
                tieneImpuesto = false;
                listaProductos = new ArrayList<ProductoSinImpuesto>();

                panelTipoProducto.removeAll();
                panelTipoProducto.add(new Label("Seleccione tipo de exención:"));
                panelTipoProducto.add(chkAlimentoBasico);
                panelTipoProducto.add(chkMedicamento);
                panelTipoProducto.add(chkMaterialEducativo);
                panelTipoProducto.setVisible(true);
                panelProducto.setVisible(true);
                revalidate();
                repaint();
            }
        });

        chkAgregarOtro.addItemListener(e -> {
            if (chkAgregarOtro.getState()) {
                chkFinalizar.setState(false);
                guardarProducto();
                limpiarCamposProducto();
                revalidate();
                repaint();
            }
        });

        chkFinalizar.addItemListener(e -> {
            if (chkFinalizar.getState()) {
                chkAgregarOtro.setState(false);
                guardarProducto();
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

        chkValorAgregado.setState(false);
        chkConsumoEspecial.setState(false);
        chkAlimentoBasico.setState(false);
        chkMedicamento.setState(false);
        chkMaterialEducativo.setState(false);
    }

    private void guardarProducto() {
        if (tieneImpuesto) {
            TipoProductoConImpuesto tipoImpuestoSeleccionado = chkValorAgregado.getState() ? TipoProductoConImpuesto.VALOR_AGREGADO_IVA : TipoProductoConImpuesto.CONSUMO_ESPECIAL_ICE;

            ((List<ProductoConImpuesto>) listaProductos).add(new ProductoConImpuesto(
                    txtNombreProducto.getText().trim(),
                    txtCodigoProducto.getText().trim(),
                    Double.parseDouble(txtPrecioProducto.getText().trim()),
                    tipoImpuestoSeleccionado
            ));
        } else {
            TipoProductoSinImpuesto tipoExencionSeleccionada = chkAlimentoBasico.getState() ? TipoProductoSinImpuesto.ALIMENTO_BASICO :
                    chkMedicamento.getState() ? TipoProductoSinImpuesto.MEDICAMENTO : TipoProductoSinImpuesto.MATERIAL_EDUCATIVO;

            ((List<ProductoSinImpuesto>) listaProductos).add(new ProductoSinImpuesto(
                    txtNombreProducto.getText().trim(),
                    txtCodigoProducto.getText().trim(),
                    Double.parseDouble(txtPrecioProducto.getText().trim()),
                    tipoExencionSeleccionada
            ));
        }

        System.out.println("Producto agregado con éxito.");
    }

    private void guardarProveedor(List<Proveedor> listaProveedores) {
        Proveedor nuevoProveedor = new Proveedor(txtNombre.getText().trim(), txtIdentificacion.getText().trim(),
                txtTelefono.getText().trim(), txtCorreo.getText().trim(), txtDireccion.getText().trim(),
                tieneImpuesto, listaProductos);

        listaProveedores.add(nuevoProveedor);
        System.out.println("Proveedor agregado con éxito: " + nuevoProveedor);
    }
}



