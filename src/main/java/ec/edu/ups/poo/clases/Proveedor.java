package ec.edu.ups.poo.clases;

import java.util.List;

public class Proveedor extends Persona{

    private boolean impuesto;
    private List<? extends Producto> listaProductos;

    public Proveedor(String nombre, String cedula, String telefono, String correo, String direccion,
                     boolean tieneImpuesto, List<? extends Producto> listaProductos) {
        super(nombre, cedula, telefono, correo, direccion);
        this.impuesto = tieneImpuesto;
        this.listaProductos = listaProductos;
    }

    public boolean isImpuesto() {
        return impuesto;
    }

    public void setImpuesto(boolean impuesto) {
        this.impuesto = impuesto;
    }

    public List<? extends Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<? extends Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @Override
    public String toString() {
        return "\n{nombre='" + getNombre() + '\'' +
                ", identificacion='" + getIdentificacion() + '\'' +
                ", telefono='" + getTelefono() + '\'' +
                ", correo='" + getCorreo() + '\'' +
                ", direccion='" + getDireccion() + '\'' +
                "{listaProductos=" + listaProductos +
                '}';
    }


}
