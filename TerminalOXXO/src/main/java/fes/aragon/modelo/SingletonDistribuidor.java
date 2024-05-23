package fes.aragon.modelo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
public class SingletonDistribuidor {
    private static SingletonDistribuidor singletonDistribuidor;

    private ObservableList<Distribuidor> lista;

    private SingletonDistribuidor(){
        lista = FXCollections.observableArrayList();
    }

    public static SingletonDistribuidor getInstance(){
        if(singletonDistribuidor == null){
            singletonDistribuidor = new SingletonDistribuidor();
        }
        return singletonDistribuidor;
    }

    public ObservableList<Distribuidor> getLista(){
        return lista;
    }

    public ArrayList<Distribuidor> getConversion(){
        return new ArrayList<>(lista);
    }
}
