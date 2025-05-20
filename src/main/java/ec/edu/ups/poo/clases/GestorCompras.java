package ec.edu.ups.poo.clases;

import ec.edu.ups.poo.enums.EstadoSolicitud;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class GestorCompras{
    private final List<DetalleCompra> listaCompras = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void registrarSolicitudCompra() {
        GestorProveedor gestorProveedor = new GestorProveedor();
        System.out.println("Ingrese la cédula de identidad del proveedor: ");
        String identificacion = scanner.nextLine();

        List<? extends Producto> listaProductos = gestorProveedor.buscarProveedor(identificacion).getListaProductos();

        if (listaProductos == null || listaProductos.isEmpty()) {
            System.out.println("No se encontraron productos para este proveedor.");
            return;
        }

        List<ItemCompra> listaItemCompra = new ArrayList<>();
        for (Producto producto : listaProductos) {
            System.out.println("Ingrese la cantidad para el producto: " + producto.getNombre());
            int cantidad = scanner.nextInt();
            scanner.nextLine();

            if (producto instanceof ProductoConImpuesto) {
                ItemCompra item = new ItemCompra(producto, cantidad);
                listaItemCompra.add(item);
            } else if (producto instanceof ProductoSinImpuesto) {
                ItemCompra item = new ItemCompra(producto, cantidad);
                listaItemCompra.add(item);
            } else {
                System.out.println("Tipo de producto desconocido. No se agregará a la compra.");
            }
        }

        System.out.println("Ingrese el estado de la solicitud (SOLICITADA, EN_REVISION, APROBADA, RECHAZADA): ");
        EstadoSolicitud estado = EstadoSolicitud.valueOf(scanner.next().toUpperCase());

        DetalleCompra nuevaCompra = new DetalleCompra(estado, new GregorianCalendar(), listaItemCompra);
        listaCompras.add(nuevaCompra);

        System.out.println("Solicitud de compra registrada exitosamente.");
    }

    public void listarSolicitudesCompra() {
        if (listaCompras.isEmpty()) {
            System.out.println("No hay solicitudes registradas.");
        } else {
            for (DetalleCompra compra : listaCompras) {
                System.out.println(compra.toString());
                compra.mostrarResumenCompra();
            }
        }
    }

    public DetalleCompra buscarSolicitudPorNumero(int numero) {
        if (numero >= 0 && numero < listaCompras.size()) {
            return listaCompras.get(numero);
        }
        System.out.println("Solicitud no encontrada.");
        return null;
    }

    public void gestionarEstadoSolicitud(int numero, EstadoSolicitud nuevoEstado) {
        DetalleCompra compra = buscarSolicitudPorNumero(numero);
        if (compra != null) {
            compra.setEstado(nuevoEstado);
            System.out.println("Estado actualizado a: " + nuevoEstado);
        }
    }

    public void calcularTotalSolicitud(int numero) {
        DetalleCompra compra = buscarSolicitudPorNumero(numero);
        if (compra != null) {
            System.out.println("Total de la compra: " + compra.calculcarTotal());
        }
    }

    public void eliminarProductoPorCodigo() {
        DetalleCompra detalleCompra = new DetalleCompra();
        System.out.print("Ingrese el nombre del producto a eliminar: ");
        String codigoProducto = scanner.nextLine();

        ItemCompra itemEncontrado = detalleCompra.buscarItem(codigoProducto);

        if (itemEncontrado != null) {
            boolean eliminado = detalleCompra.removerItem(itemEncontrado.getProducto().getCodigo());
            if (eliminado) {
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el producto.");
            }
        } else {
            System.out.println("No se encontró ningún producto con el código ingresado.");
        }
    }

    public int NumeroBuscar(){
        System.out.println("Ingrese el numero dependiendo del orden de Compras: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }

    public EstadoSolicitud nuevoEstado(){
        System.out.println("Ingrese el nuevo estado de la compra: "+
                "\nSOLICITADA"+
                "\nEN_REVISION"+
                "\nAPROBADA"+
                "\nRECHAZADA");
        return EstadoSolicitud.valueOf(scanner.next().toUpperCase());
    }

}