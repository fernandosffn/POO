package fes.aragon.modelo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
public class SingletonProvedor {
    private static SingletonProvedor singletonProvedor;

    private ObservableList<Provedor> lista;

    private SingletonProvedor(){
        lista = FXCollections.observableArrayList();
    }

    public static SingletonProvedor getInstance(){
        if(singletonProvedor == null){
            singletonProvedor = new SingletonProvedor();
        }
        return singletonProvedor;
    }

    public ObservableList<Provedor> getLista(){
        return lista;
    }

    public ArrayList<Provedor> getConversion(){
        return new ArrayList<>(lista);
    }
}