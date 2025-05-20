package ec.edu.ups.poo.clases;

import ec.edu.ups.poo.interfaces.OperacionesCompra;

public class ItemCompra implements OperacionesCompra {
    private Producto producto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public ItemCompra(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio();
        this.subtotal = calculcarTotal();
    }

    @Override
    public double calculcarTotal() {
        return precioUnitario * cantidad; // Aplica el impuesto
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = calculcarTotal();
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.subtotal = calculcarTotal();
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return "ItemCompra{" +
                "producto=" + producto +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", subtotal=" + subtotal +
                '}';
    }
}