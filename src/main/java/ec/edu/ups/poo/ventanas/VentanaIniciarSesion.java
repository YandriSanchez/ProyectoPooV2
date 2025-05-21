package ec.edu.ups.poo.ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class VentanaIniciarSesion extends Frame {

    private TextField txtUsuario;
    private TextField txtContraseña;
    private Button btnCargarDatos;
    private Button btnRegistrarse;
    private Button btnIngresar;
    private Panel panelIzquierdo;
    private Panel panelDerecho;
    private Panel panelSuperior;
    private Panel panelInferior;
    private Label lblUsuario;
    private Label lblContraseña;
    private Image imagen;
    private HashMap<String, String> usuarios; // Almacena usuarios y contraseñas

    public VentanaIniciarSesion() {
        setTitle("Gestión de Órdenes de Compra");
        setSize(500, 250);
        setLayout(new BorderLayout());

        // Cargar imagen para el panel izquierdo
        imagen = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Usuario\\Documents\\UPS-POO\\ProyectoPooV2\\logo2.jpg");

        // Creación del panel izquierdo (Imagen)
        panelIzquierdo = new Panel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                if (imagen != null) {
                    g.drawImage(imagen, 10, 10, getWidth() - 20, getHeight() - 20, this);
                }
            }
        };
        panelIzquierdo.setLayout(new BorderLayout());

        // Botón para cargar usuarios
        btnCargarDatos = new Button("Cargar Datos");
        panelIzquierdo.add(btnCargarDatos, BorderLayout.SOUTH);

        // Creación del panel derecho y sus subpaneles
        panelDerecho = new Panel(new BorderLayout());
        panelSuperior = new Panel(new FlowLayout(FlowLayout.LEFT));
        panelInferior = new Panel(new FlowLayout());

        // Configuración del panel superior (Usuario y Contraseña)
        lblUsuario = new Label("Usuario:");
        txtUsuario = new TextField(15);
        lblContraseña = new Label("Contraseña:");
        txtContraseña = new TextField(15);
        txtContraseña.setEchoChar('*');

        panelSuperior.add(lblUsuario);
        panelSuperior.add(txtUsuario);
        panelSuperior.add(lblContraseña);
        panelSuperior.add(txtContraseña);

        // Configuración del panel inferior (Botones)
        btnRegistrarse = new Button("Registrarse");
        btnIngresar = new Button("Ingresar");

        // Inicialmente deshabilitados
        btnRegistrarse.setEnabled(false);
        btnIngresar.setEnabled(false);

        panelInferior.add(btnRegistrarse);
        panelInferior.add(btnIngresar);

        // Agregar subpaneles al panel derecho
        panelDerecho.add(panelSuperior, BorderLayout.CENTER);
        panelDerecho.add(panelInferior, BorderLayout.SOUTH);

        // Agregar los paneles principales a la ventana
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);

        // Lista de usuarios y contraseñas
        usuarios = new HashMap<>();

        // Manejo de eventos con clases anónimas
        btnCargarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarUsuarios(); // Carga la lista de usuarios
                btnRegistrarse.setEnabled(true);
                btnIngresar.setEnabled(true);
                System.out.println("Usuarios cargados. Ahora puedes ingresar.");
            }
        });

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarCredenciales();
            }
        });

        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        // Configuración final
        setVisible(true);
    }

    // Método para cargar usuarios en la lista
    private void cargarUsuarios() {
        usuarios.put("admin", "1234");
        usuarios.put("usuario1", "abcd");
        System.out.println("Usuarios precargados en la lista.");
    }

    // Método para verificar credenciales
    private void verificarCredenciales() {
        String usuario = txtUsuario.getText();
        String contraseña = txtContraseña.getText();

        if (usuarios.containsKey(usuario) && usuarios.get(usuario).equals(contraseña)) {
            System.out.println("Acceso permitido. Bienvenido, " + usuario);
        } else {
            System.out.println("Credenciales incorrectas.");
        }
    }

    // Método para registrar nuevos usuarios
    private void registrarUsuario() {
        String usuario = txtUsuario.getText();
        String contraseña = txtContraseña.getText();

        if (!usuario.isEmpty() && !contraseña.isEmpty()) {
            usuarios.put(usuario, contraseña);
            System.out.println("Usuario registrado correctamente.");
        } else {
            System.out.println("Usuario y contraseña no pueden estar vacíos.");
        }
    }

    public static void main(String[] args) {
        new VentanaIniciarSesion();
    }
}