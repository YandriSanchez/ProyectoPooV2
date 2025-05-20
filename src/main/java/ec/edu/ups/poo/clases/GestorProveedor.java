package ec.edu.ups.poo.clases;

import java.util.*;
import ec.edu.ups.poo.enums.TipoProductoSinImpuesto;
import ec.edu.ups.poo.enums.TipoProductoConImpuesto;

public class GestorProveedor {

    private static List<Proveedor> listaProveedores = new ArrayList<>();
    private static List<ProductoConImpuesto> listaProductosConImpuestos;
    private static List<ProductoSinImpuesto> listaProductosSinImpuestos;
    private List<? extends Producto> listaProductos;
    Scanner scanner = new Scanner(System.in);

    public void agregarProveedor() {
        GestorProducto gestorProducto = new GestorProducto();
        System.out.println("Ingrese los datos del nuevo proveedor:");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        String identificacion = GestorMenu.solicitarCedulaValida();

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("Correo: ");
        String correo = scanner.nextLine();

        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        System.out.print("¿El proveedor tiene productos con impuestos? (true/false): ");
        boolean tieneImpuesto = scanner.nextBoolean();

        if(tieneImpuesto){
            listaProductosConImpuestos = new ArrayList<>();
            listaProductosConImpuestos.add(gestorProducto.solicitarProductoConImpuesto());
        } else {
            listaProductosSinImpuestos = new ArrayList<>();
            listaProductosSinImpuestos.add(gestorProducto.solicitarProductoSinImpuesto());
        }
        System.out.println("Producto agregado correctamente ");
        listaProductos = listaProductosSinImpuestos;
        Proveedor nuevoProveedor = new Proveedor(nombre, identificacion, telefono, correo, direccion, tieneImpuesto,
                listaProductos);
        listaProveedores.add(nuevoProveedor);
        System.out.println("Proveedor agregado correctamente.");
    }


    public void eliminarProveedor(String identificacion) {
        for (Proveedor proveedor : listaProveedores) {
            if (proveedor.getIdentificacion().equals(identificacion)) {
                listaProveedores.remove(proveedor);
                System.out.println("Proveedor eliminado.");
                return;
            }
        }
        System.out.println("Proveedor no encontrado.");
    }

    public Proveedor buscarProveedor(String identificacion) {
        for (Proveedor proveedor : listaProveedores) {
            if (proveedor.getIdentificacion().equals(identificacion)) {
                return proveedor;
            }
        }
        System.out.println("Proveedor no encontrado.");
        return null;
    }

    public void mostrarTodosProveedores() {
        if (listaProveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
        } else {
            for (Proveedor proveedor : listaProveedores) {
                System.out.println("");
                System.out.println(proveedor.toString());
            }
        }
    }

    public void agregarProducto(String identificacion) {
        GestorProducto gestorProducto = new GestorProducto();
        Proveedor proveedorEncontrado = buscarProveedor(identificacion);
        if(proveedorEncontrado.isImpuesto()){
            System.out.println("Ingrese los productos con impuestos:");
            listaProductosConImpuestos.add(gestorProducto.solicitarProductoConImpuesto());
            listaProductos = listaProductosSinImpuestos;
            proveedorEncontrado.setListaProductos(listaProductos);
        } else {
            System.out.println("Ingrese los productos sin impuestos:");
            listaProductosSinImpuestos.add(gestorProducto.solicitarProductoSinImpuesto());
            listaProductos = listaProductosSinImpuestos;
            proveedorEncontrado.setListaProductos(listaProductos);
        }
        System.out.println("Producto agregado correctamente ");
    }

    public void proveedoresPorDefecto() {
        listaProductosConImpuestos = new ArrayList<>();
        listaProductosSinImpuestos = new ArrayList<>();

        listaProductosConImpuestos.add(new ProductoConImpuesto("Smartphone Samsung", "SAM001", 899.99, TipoProductoConImpuesto.VALOR_AGREGADO_IVA));
        listaProductosConImpuestos.add(new ProductoConImpuesto("Laptop Dell XPS", "DEL002", 1350.50, TipoProductoConImpuesto.VALOR_AGREGADO_IVA));
        listaProductosConImpuestos.add(new ProductoConImpuesto("Teclado mecánico Corsair", "COR006", 110.00, TipoProductoConImpuesto.VALOR_AGREGADO_IVA));
        listaProductosConImpuestos.add(new ProductoConImpuesto("Mouse inalámbrico Logitech", "LOG007", 49.99, TipoProductoConImpuesto.VALOR_AGREGADO_IVA));

        Proveedor proveedor1 = new Proveedor("ElectroTech S.A.", "0150614121", "0987654321", "ventas@electrotech.ec", "Av. 6 de Diciembre 123", true, listaProductosConImpuestos);
        listaProveedores.add(proveedor1);

        listaProductosConImpuestos.clear();
        listaProductosConImpuestos.add(new ProductoConImpuesto("Neumático Pirelli 225/55R17", "NEU005", 250.00, TipoProductoConImpuesto.CONSUMO_ESPECIAL_ICE));
        listaProductosConImpuestos.add(new ProductoConImpuesto("Radio multimedia Pioneer", "RAD006", 410.00, TipoProductoConImpuesto.VALOR_AGREGADO_IVA));
        listaProductosConImpuestos.add(new ProductoConImpuesto("Cámara de reversa", "CAM007", 185.99, TipoProductoConImpuesto.VALOR_AGREGADO_IVA));

        Proveedor proveedor2 = new Proveedor("AutoPartes Ecuador", "0703094458", "0965432109", "info@autopartes.ec", "Av. Amazonas 789", true, listaProductosConImpuestos);
        listaProveedores.add(proveedor2);

        listaProductosSinImpuestos.clear();
        listaProductosSinImpuestos.add(new ProductoSinImpuesto("Frutas Frescas", "FRU104", 5.00, TipoProductoSinImpuesto.ALIMENTO_BASICO));
        listaProductosSinImpuestos.add(new ProductoSinImpuesto("Aceite de oliva", "ACE106", 7.00, TipoProductoSinImpuesto.ALIMENTO_BASICO));
        listaProductosSinImpuestos.add(new ProductoSinImpuesto("Harina de trigo", "HAR107", 2.00, TipoProductoSinImpuesto.ALIMENTO_BASICO));

        Proveedor proveedor3 = new Proveedor("SuperFoods", "1720882685", "0987654321", "contacto@superfoods.ec", "Calle Sucre 456", false, listaProductosSinImpuestos);
        listaProveedores.add(proveedor3);

        listaProductosSinImpuestos.clear();
        listaProductosSinImpuestos.add(new ProductoSinImpuesto("Paracetamol 500mg", "PAR001", 3.50, TipoProductoSinImpuesto.MEDICAMENTO));
        listaProductosSinImpuestos.add(new ProductoSinImpuesto("Ibuprofeno 400mg", "IBU002", 4.75, TipoProductoSinImpuesto.MEDICAMENTO));
        listaProductosSinImpuestos.add(new ProductoSinImpuesto("Jarabe para la tos", "JAR003", 6.90, TipoProductoSinImpuesto.MEDICAMENTO));

        Proveedor proveedor4 = new Proveedor("Farmacia Vida", "0706338340", "0976543210", "farmacia@vida.ec", "Calle Sucre 321", false, listaProductosSinImpuestos);
        listaProveedores.add(proveedor4);
    }
}