package ec.edu.ups.poo.clases;

import ec.edu.ups.poo.enums.TipoProductoSinImpuesto;

public class ProductoSinImpuesto extends Producto {

    private TipoProductoSinImpuesto tipoExencion;

    public ProductoSinImpuesto(String nombre, String codigo, double precio, TipoProductoSinImpuesto tipoExencion) {
        super(nombre, codigo, precio);
        this.tipoExencion = tipoExencion;
    }

    @Override
    public double aplicarDescuento(double porcentajeDescuento) {
        this.precio *= (1 - porcentajeDescuento / 100); // Modifica directamente el atributo precio
        this.precio = Math.max(this.precio, 0); // Evita valores negativos
        return this.precio;
    }

    public TipoProductoSinImpuesto getTipoExencion() {
        return tipoExencion;
    }

    public void setTipoExencion(TipoProductoSinImpuesto tipoExencion) {
        this.tipoExencion = tipoExencion;
    }

    @Override
    public String toString() {
        return "\nProductoSinImpuesto{" +
                "nombre='" + getNombre() + '\'' +
                ", codigo='" + getCodigo() + '\'' +
                ", precioFinal=" + getPrecio() +
                ", tipoImpuesto=" + tipoExencion +
                '}';
    }
}