package ec.edu.ups.poo.clases;

import ec.edu.ups.poo.enums.TipoProductoConImpuesto;
import ec.edu.ups.poo.enums.TipoProductoSinImpuesto;

import java.util.List;
import java.util.Scanner;

public class GestorProducto {
    Scanner scanner = new Scanner(System.in);

    private List<? extends Producto> listaProductos;
    public ProductoConImpuesto solicitarProductoConImpuesto() {

        System.out.println("Ingrese los datos del producto:");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = scanner.nextDouble();

        System.out.print("Ingrese el tipo de impuesto (CONSUMO_ESPECIAL_ICE, VALOR_AGREGADO_IVA): ");
        TipoProductoConImpuesto tipoImpuesto = TipoProductoConImpuesto.valueOf(scanner.next().toUpperCase());
        return new ProductoConImpuesto(nombre, codigo, precio, tipoImpuesto);
    }
    public ProductoSinImpuesto solicitarProductoSinImpuesto() {

        System.out.println("Ingrese los datos del producto:");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Ingrese el tipo de impuesto (ALIMENTO_BASICO,MEDICAMENTO,MATERIAL_EDUCATIVO): ");
        TipoProductoSinImpuesto tipoImpuesto2 = TipoProductoSinImpuesto.valueOf(scanner.next().toUpperCase());
        return new ProductoSinImpuesto(nombre, codigo, precio, tipoImpuesto2 );
    }

}