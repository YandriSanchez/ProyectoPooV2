package ec.edu.ups.poo.clases;
import ec.edu.ups.poo.enums.TipoProductoSinImpuesto;

public class ProductoSinImpuesto extends Producto {

    private TipoProductoSinImpuesto tipoExencion;

    public ProductoSinImpuesto(String nombre, String codigo, double precio, TipoProductoSinImpuesto tipoExencion) {
        super(nombre, codigo, precio);
        this.tipoExencion = tipoExencion;
    }

    public double calcularPrecioFinal(double precioBase) {
        return precioBase;
    }

    public TipoProductoSinImpuesto getTipoExencion() {
        return tipoExencion;
    }

    public void setTipoExencion(TipoProductoSinImpuesto tipoExencion) {
        this.tipoExencion = tipoExencion;
    }

    public String toString() {
        return "\nProductoSinImpuesto{" +
                "nombre='" + getNombre() + '\'' +
                ", codigo='" + getCodigo() + '\'' +
                ", precioSinImpuesto=" + getPrecio() +
                ", tipoImpuesto=" + tipoExencion +
                '}';
    }
}