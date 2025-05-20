package ec.edu.ups.poo.clases;

import ec.edu.ups.poo.enums.EstadoSolicitud;
import ec.edu.ups.poo.interfaces.OperacionesCompra;


import java.util.GregorianCalendar;
import java.util.List;

public class DetalleCompra implements OperacionesCompra {
    private EstadoSolicitud estado;
    private static List<ItemCompra> listadoItemProductos;
    private GregorianCalendar fechaEmision;

    public DetalleCompra(){

    }

    public DetalleCompra(EstadoSolicitud estado, GregorianCalendar fechaEmision, List<ItemCompra> listadoProductos) {
        this.estado = estado;
        this.fechaEmision = fechaEmision;
        this.listadoItemProductos = listadoProductos;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public GregorianCalendar getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(GregorianCalendar fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    @Override
    public double calculcarTotal() {
        double total = 0;
        for (ItemCompra item : listadoItemProductos) {
            total += item.getSubtotal();
        }
        return total;
    }

    public boolean removerItem(String codigoProducto) {
        for (ItemCompra item : listadoItemProductos) {
            if (item.getProducto().getCodigo().equals(codigoProducto)) {
                listadoItemProductos.remove(item);
                System.out.println("Producto removido: " + codigoProducto);
                return true;
            }
        }
        return false;
    }

    public ItemCompra buscarItem(String nombreProducto) {
        for (ItemCompra item : listadoItemProductos) {
            if (item.getProducto().getNombre().equalsIgnoreCase(nombreProducto)) {
                return item;
            }
        }
        return null;
    }

    public void mostrarResumenCompra() {
        System.out.println("Resumen de la Compra:");
        System.out.println("Fecha de emisión: " + fechaEmision.getTime());
        System.out.println("Estado: " + estado);
        System.out.println("Productos:");
        for (ItemCompra item : listadoItemProductos) {
            System.out.println(item);
        }
        System.out.println("Total: " + calculcarTotal());
    }

    @Override
    public String toString() {
        return "DetalleCompra{" +
                "estado=" + estado +
                ", fechaEmision=" + fechaEmision.getTime() +
                ", listadoProductos=" + listadoItemProductos +
                '}';
    }
}
