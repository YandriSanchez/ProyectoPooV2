package ec.edu.ups.poo.clases;

import java.util.*;
import ec.edu.ups.poo.enums.TipoProductoSinImpuesto;
import ec.edu.ups.poo.enums.TipoProductoConImpuesto;

public class GestorProveedor {

    Scanner scanner = new Scanner(System.in);

    public void agregarProveedor() {
        GestorProducto gestorProducto = new GestorProducto();
        System.out.println("Ingrese los datos del nuevo proveedor:");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        String identificacion = scanner.nextLine();

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


}