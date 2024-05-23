package fes.aragon.modelo;

import java.io.Serializable;

public class Producto implements Serializable {

    public String nombre;
    public String fechaCaducidad;
    public String cantidad;
    public String precioUnitario;
    public String precioVenta;
    private SerializableImage imagen;

    public Producto() {
    }

    public Producto(String nombre, String fechaCaducidad, String cantidad, String precioUnitario, String precioVenta) {
        this.nombre = nombre;
        this.fechaCaducidad = fechaCaducidad;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.precioVenta = precioVenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(String precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(String precioVenta) {
        this.precioVenta = precioVenta;
    }

    public SerializableImage getImagen() {
        return imagen;
    }

    public void setImagen(SerializableImage imagen) {
        this.imagen = imagen;
    }
}
