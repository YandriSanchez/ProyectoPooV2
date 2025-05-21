package ec.edu.ups.poo.clases;
import ec.edu.ups.poo.interfaces.OperacionesCompra;
import ec.edu.ups.poo.enums.TipoProductoConImpuesto;

public class ProductoConImpuesto extends Producto implements OperacionesCompra {
    private TipoProductoConImpuesto tipoImpuesto;

    public ProductoConImpuesto(String nombre, String codigo, double precio, TipoProductoConImpuesto tipoImpuesto) {
        super(nombre, codigo, precio);
        this.tipoImpuesto = tipoImpuesto;
    }

    // Método para calcular el precio final sin necesidad de un atributo
    @Override
    public double calcularTotal() {
        double tasa = (tipoImpuesto == TipoProductoConImpuesto.VALOR_AGREGADO_IVA) ? impuestoIVA :
                (tipoImpuesto == TipoProductoConImpuesto.CONSUMO_ESPECIAL_ICE) ? impuestoICE : 0.0;
        return getPrecio() * (1 + tasa);
    }

    public TipoProductoConImpuesto getTipoImpuesto() {
        return tipoImpuesto;
    }

    @Override
    public String toString() {
        return "\nProductoConImpuesto{" +
                "nombre='" + getNombre() + '\'' +
                ", codigo='" + getCodigo() + '\'' +
                ", precioSinImpuesto=" + getPrecio() +
                ", tipoImpuesto=" + tipoImpuesto +
                ", precioConImpuesto=" + calcularTotal() + // Se calcula directamente sin atributo
                '}';
    }

    @Override
    public double aplicarDescuento(double porcentajeDescuento) {
        double precioConDescuento = calcularTotal() * (1 - porcentajeDescuento / 100);
        return Math.max(precioConDescuento, 0); // Evita valores negativos
    }
}
