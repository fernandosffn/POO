package fes.aragon.modelo;

import java.io.Serializable;
import fes.aragon.modelo.Producto;
import fes.aragon.modelo.Distribuidor;

public class Provedor {
    private Producto producto;
    private Distribuidor distribuidor;
    private SerializableImage imagen;


    public Provedor() {
        this.producto = new Producto();
        this.distribuidor = new Distribuidor();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Distribuidor getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
    }
    public SerializableImage getImagen() {
        return imagen;
    }

    public void setImagen(SerializableImage imagen) {
        this.imagen = imagen;
    }
}
