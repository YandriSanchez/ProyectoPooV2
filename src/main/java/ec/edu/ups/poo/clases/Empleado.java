package ec.edu.ups.poo.clases;

public class Empleado extends Persona {

    private String usuario;
    private String contrasena;

    public Empleado(String nombre, String identificacion, String telefono, String correo, String direccion,
                    String usuario, String contrasena) {
        super(nombre, identificacion, telefono, correo, direccion);
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "usuario='" + usuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
}