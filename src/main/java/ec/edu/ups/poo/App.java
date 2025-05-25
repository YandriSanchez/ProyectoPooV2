package ec.edu.ups.poo;

import ec.edu.ups.poo.clases.*;
import ec.edu.ups.poo.enums.TipoProductoConImpuesto;
import ec.edu.ups.poo.enums.TipoProductoSinImpuesto;
import ec.edu.ups.poo.ventanas.VentanaIniciarSesion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    private static final List<Empleado> listaEmpleados = new ArrayList<>();
    private static final List<Proveedor> listaProveedores = new ArrayList<>();
    //private List<? extends Producto> listaProductos;

    public static void main(String[] args) {
        agregarEmpleadosPorDefecto();
        proveedoresPorDefecto();
        new VentanaIniciarSesion(listaEmpleados, listaProveedores);

    }

    public static void agregarEmpleadosPorDefecto() {
        List<Empleado> empleadosPorDefecto = Arrays.asList(
                new Empleado("Carlos Ramírez", "1101160032", "0987123456", "carlos.ramirez@empresa.ec", "Av. Colón 321", "carlosr", "Carlos2025"),
                new Empleado("María Fernández", "1719690487", "0976543210", "maria.fernandez@empresa.ec", "Calle Bolívar 789", "mariaf", "MariaPass"),
                new Empleado("Juan Pérez", "1724354459", "0998765432", "juan.perez@empresa.ec", "Calle Sucre 567", "juanp", "Juan123"),
                new Empleado("Sofía Gómez", "1709839664", "0965432109", "sofia.gomez@empresa.ec", "Av. Amazonas 100", "sofiag", "SofiaG12"),
                new Empleado("Diego Álvarez", "1712114196", "0954321098", "diego.alvarez@empresa.ec", "Calle Rocafuerte 200", "diegoa", "DiegoPass")
        );
        listaEmpleados.addAll(empleadosPorDefecto);
    }

    public static List<Proveedor> proveedoresPorDefecto() {
        List<ProductoConImpuesto> listaProductosConImpuestos = new ArrayList<>();
        List<ProductoSinImpuesto> listaProductosSinImpuestos = new ArrayList<>();

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

        return listaProveedores;
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

}
