package ec.edu.ups.poo.clases;
import ec.edu.ups.poo.interfaces.OperacionesCompra;
import ec.edu.ups.poo.enums.TipoProductoConImpuesto;

public class ProductoConImpuesto extends Producto implements OperacionesCompra {
    private TipoProductoConImpuesto tipoImpuesto;
    private double precioConImpuesto;

    public ProductoConImpuesto(String nombre, String codigo, double precio, TipoProductoConImpuesto tipoImpuesto) {
        super(nombre, codigo, precio);
        this.tipoImpuesto = tipoImpuesto;
        this.precioConImpuesto = calcularPrecioFinal();
    }


    private double calcularPrecioFinal() {
        double tasa = (tipoImpuesto == TipoProductoConImpuesto.VALOR_AGREGADO_IVA) ? impuestoIVA :
                (tipoImpuesto == TipoProductoConImpuesto.CONSUMO_ESPECIAL_ICE) ? impuestoICE : 0.0;
        return getPrecio() * (1 + tasa);
    }

    @Override
    public double calculcarTotal() {
        return precioConImpuesto; // Ya tiene aplicado el impuesto
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
                ", precioConImpuesto=" + precioConImpuesto +
                '}';
    }
}