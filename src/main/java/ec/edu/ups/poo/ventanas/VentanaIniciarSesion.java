package ec.edu.ups.poo.ventanas;

import ec.edu.ups.poo.App;

import javax.sound.midi.Soundbank;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaIniciarSesion extends Frame {

    private TextField txtUsuario;
    private TextField txtContrasena;
    private Button btnRegistrarse;
    private Button btnIngresar;
    private Panel panelSuperior;
    private Panel panelInferior;
    private Label lblUsuario;
    private Label lblContrasena;
    private App gestorEmpleados = new App();
    private VentanaRegistroEmpleado ventanaRegistroEmpleado;
    private VentanaPrincipal ventanaPrincipal;

    public VentanaIniciarSesion() {
        setTitle("Gestión de Órdenes de Compra");
        setSize(400, 150);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        gestorEmpleados.agregarEmpleadosPorDefecto();
        gestorEmpleados.mostrarTodosEmpleados();

        panelSuperior = new Panel(new FlowLayout(FlowLayout.LEFT));
        panelInferior = new Panel(new FlowLayout());

        lblUsuario = new Label("Usuario:");
        txtUsuario = new TextField(35);
        lblContrasena = new Label("Contraseña:");
        txtContrasena = new TextField(30);
        txtContrasena.setEchoChar('*');

        panelSuperior.add(lblUsuario);
        panelSuperior.add(txtUsuario);
        panelSuperior.add(lblContrasena);
        panelSuperior.add(txtContrasena);

        btnRegistrarse = new Button("Registrarse");
        btnIngresar = new Button("Ingresar");

        panelInferior.add(btnRegistrarse);
        panelInferior.add(btnIngresar);

        add(panelSuperior, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean verificador = gestorEmpleados.verificarCredenciales(txtUsuario.getText().trim(),txtContrasena.getText().trim());
                if(verificador){
                    ventanaPrincipal = new VentanaPrincipal();
                    dispose();
                }else{
                    System.out.println("ERROR, no permitido ingresar");
                }

            }
        });

        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaRegistroEmpleado = new VentanaRegistroEmpleado();
                dispose();
            }
        });

        setVisible(true);
    }
}
