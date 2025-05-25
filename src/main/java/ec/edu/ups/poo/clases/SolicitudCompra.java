package ec.edu.ups.poo.clases;

import ec.edu.ups.poo.enums.EstadoSolicitud;
import ec.edu.ups.poo.interfaces.OperacionesCompra;
import java.util.GregorianCalendar;
import java.util.List;

public class SolicitudCompra implements OperacionesCompra {
    private EstadoSolicitud estado;
    private static List<ItemCompra> listadoItemProductos;
    private GregorianCalendar fechaEmision;
    private String codigo;
    private Proveedor proveedor; // Nuevo atributo

    public SolicitudCompra() {
    }

    public SolicitudCompra(EstadoSolicitud estado, GregorianCalendar fechaEmision, List<ItemCompra> listadoProductos, String codigo, Proveedor proveedor) {
        this.estado = estado;
        this.fechaEmision = fechaEmision;
        this.listadoItemProductos = listadoProductos;
        this.codigo = codigo;
        this.proveedor = proveedor; // Asigna el proveedor al constructor
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public double calcularTotal() {
        double total = 0;
        for (ItemCompra item : listadoItemProductos) {
            total += item.getSubtotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "SolicitudCompra{" +
                "codigo='" + codigo + '\'' +
                ", estado=" + estado +
                ", fechaEmision=" + fechaEmision.getTime() +
                ", proveedor=" + proveedor.getNombre() + // Muestra el nombre del proveedor
                ", listadoProductos=" + listadoItemProductos +
                '}';
    }

    public ItemCompra[] getListadoItemProductos() {
        return listadoItemProductos.toArray(new ItemCompra[listadoItemProductos.size()]);
    }
}
