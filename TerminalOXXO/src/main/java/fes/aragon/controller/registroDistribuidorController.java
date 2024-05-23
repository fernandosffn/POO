package fes.aragon.controller;

import fes.aragon.modelo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class registroDistribuidorController {
    private Integer indice;
    @FXML
    private Button btnAbrirImagen;


    @FXML
    private Button btnGuardar;

    @FXML
    private ImageView imgImagen;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;
    private File selectedFile;
    @FXML
    void abrirImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("imagen", "*.png"),
                new FileChooser.ExtensionFilter("imagen JPG", "*.jpg")
        );
        this.selectedFile = fileChooser.showOpenDialog(
                this.btnGuardar.getScene().getWindow());
        if (selectedFile != null) {
            try {
                FileInputStream fo = new FileInputStream(selectedFile);
                Image imagen = new Image(fo);
                this.imgImagen.setImage(imagen);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void guardarDistribuidor(ActionEvent event) {
        Provedor provedor = new Provedor();
        provedor.getDistribuidor().setNombre(txtNombre.getText());
        provedor.getDistribuidor().setCorreo(txtCorreo.getText());
        provedor.getDistribuidor().setTelefono(txtTelefono.getText());
        provedor.getDistribuidor().setDireccion(txtDireccion.getText());
        if (selectedFile != null) {
            try {
                FileInputStream fo = new FileInputStream(selectedFile);
                Image imagen = new Image(fo);
                SerializableImage img = new SerializableImage();
                img.setImage(imagen);
                provedor.getDistribuidor().setImagen(img);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if (indice == null) {
            System.out.println(provedor);
            SingletonProvedor.getInstance().getLista().add(provedor);
        }else{
            SingletonProvedor.getInstance().getLista().
                    set(indice, provedor);
            Stage stage = (Stage) this.btnGuardar.getScene().getWindow();
            stage.close();
        }

        txtNombre.clear();
        txtCorreo.clear();
        txtTelefono.clear();
        txtDireccion.clear();
        imgImagen.setImage(null);
    }
    public void indiceDistribuidor(int indice){
        this.indice = indice;
        Provedor provedor = SingletonProvedor.   // ¡¡¡  !!!
                getInstance().getLista().get(indice);
        txtNombre.setText(provedor.getDistribuidor().getNombre());
        txtCorreo.setText(provedor.getDistribuidor().getCorreo());
        txtTelefono.setText(provedor.getDistribuidor().getTelefono());
        txtDireccion.setText(provedor.getDistribuidor().getDireccion());
        System.out.println(provedor.getDistribuidor().getImagen());
        imgImagen.setImage(provedor.getDistribuidor().getImagen().getImage()); //Usuarios -> Imagen / SerializableImage ->Image
    }


}
