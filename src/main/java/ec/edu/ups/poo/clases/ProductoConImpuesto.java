package ec.edu.ups.poo.clases;

import ec.edu.ups.poo.interfaces.OperacionesCompra;
import ec.edu.ups.poo.enums.TipoProductoConImpuesto;

public class ProductoConImpuesto extends Producto implements OperacionesCompra {
    private TipoProductoConImpuesto tipoImpuesto;
    private static final double impuestoIVA = 0.12;
    private static final double impuestoICE = 0.10;

    public ProductoConImpuesto(String nombre, String codigo, double precio, TipoProductoConImpuesto tipoImpuesto) {
        super(nombre, codigo, precio);
        this.tipoImpuesto = tipoImpuesto;
        this.precio = aplicarImpuesto(precio); // Se asigna el precio con impuesto al atributo
    }

    // Aplica el impuesto correspondiente según el tipo de producto
    private double aplicarImpuesto(double precioBase) {
        double tasa = (tipoImpuesto == TipoProductoConImpuesto.VALOR_AGREGADO_IVA) ? impuestoIVA :
                (tipoImpuesto == TipoProductoConImpuesto.CONSUMO_ESPECIAL_ICE) ? impuestoICE : 0.0;
        return precioBase * (1 + tasa);
    }

    @Override
    public double calcularTotal() {
        return getPrecio(); // Ahora devuelve el precio con impuesto incluido
    }

    @Override
    public double aplicarDescuento(double porcentajeDescuento) {
        this.precio *= (1 - porcentajeDescuento / 100); // Modifica directamente el atributo precio
        this.precio = Math.max(this.precio, 0); // Evita valores negativos
        return this.precio;
    }

    public TipoProductoConImpuesto getTipoImpuesto() {
        return tipoImpuesto;
    }

    @Override
    public String toString() {
        return "\nProductoConImpuesto{" +
                "nombre='" + getNombre() + '\'' +
                ", codigo='" + getCodigo() + '\'' +
                ", tipoImpuesto=" + tipoImpuesto +
                ", precioFinal=" + getPrecio() +
                '}';
    }
}
