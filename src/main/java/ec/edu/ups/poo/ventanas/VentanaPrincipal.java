package ec.edu.ups.poo.ventanas;

import ec.edu.ups.poo.clases.Empleado;
import ec.edu.ups.poo.clases.Proveedor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaPrincipal extends Frame {

    private Button btnGestionEmpleados;
    private Button btnGestionProveedores;
    private Button btnRealizarSolicitud;
    private Button btnSalir;
    private final List<Empleado> listaEmpleados;
    private final List<Proveedor> listaProveedores;

    public VentanaPrincipal(List<Empleado> listaEmpleados, List<Proveedor> listaProveedores) {
        this.listaEmpleados = listaEmpleados;
        this.listaProveedores = listaProveedores;

        setTitle("Sistema de Gestión");
        setSize(400, 250);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Panel panelCentral = new Panel(new GridLayout(3, 1, 10, 10));

        btnRealizarSolicitud = new Button("Realizar Solicitud de Compra");
        btnGestionEmpleados = new Button("Gestionar Empleados");
        btnGestionProveedores = new Button("Gestionar Proveedores");

        panelCentral.add(btnRealizarSolicitud);
        panelCentral.add(btnGestionEmpleados);
        panelCentral.add(btnGestionProveedores);

        Panel panelInferior = new Panel(new FlowLayout(FlowLayout.LEFT));
        btnSalir = new Button("SALIR");
        panelInferior.add(btnSalir);

        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        btnGestionProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaGestionProveedor(listaProveedores);
            }
        });

        btnRealizarSolicitud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abrir ventana de Solicitud de Compra.");
            }
        });

        btnGestionEmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abrir ventana de Gestión de Empleados.");
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        setVisible(true);
    }
}

