package ec.edu.ups.poo.ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends Frame {

    private Button btnGestionEmpleados;
    private Button btnGestionProveedores;
    private Button btnRealizarSolicitud;
    private Button btnRegresar;

    public VentanaPrincipal() {
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
        btnRegresar = new Button("Regresar");
        panelInferior.add(btnRegresar);

        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

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

        btnGestionProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abrir ventana de Gestión de Proveedores.");
            }
        });

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                VentanaIniciarSesion ventana = new VentanaIniciarSesion();
            }
        });

        setVisible(true);
    }
}
