package fes.aragon.modelo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
public class SingletonProducto {
    private static SingletonProducto singletonProducto;

    private ObservableList<Producto> lista;

    private SingletonProducto(){
        lista = FXCollections.observableArrayList();
    }

    public static SingletonProducto getInstance(){
        if(singletonProducto == null){
            singletonProducto = new SingletonProducto();
        }
        return singletonProducto;
    }

    public ObservableList<Producto> getLista(){
        return lista;
    }

    public ArrayList<Producto> getConversion(){
        return new ArrayList<>(lista);
    }
}
