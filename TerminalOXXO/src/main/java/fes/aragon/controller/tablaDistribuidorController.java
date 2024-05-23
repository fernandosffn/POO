package fes.aragon.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import fes.aragon.modelo.Distribuidor;
import fes.aragon.modelo.Provedor;
import fes.aragon.modelo.SingletonDistribuidor;
import fes.aragon.modelo.SingletonProvedor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class tablaDistribuidorController implements Initializable {

    @FXML
    private TableColumn<Provedor, String> clmCorreo;

    @FXML
    private TableColumn<Provedor, String> clmDireccion;

    @FXML
    private TableColumn<Provedor, String> clmNombre;

    @FXML
    private TableColumn<Provedor, String> clmOperaciones;

    @FXML
    private TableColumn<Provedor, String> clmTelefono;

    @FXML
    private FontAwesomeIconView iconAbrirDistribuidor;

    @FXML
    private FontAwesomeIconView iconGuardarDistribuidor;

    @FXML
    private FontAwesomeIconView iconNuevoDistribuidor;

    @FXML
    private TableView<Provedor> tblDistribuidor;

    @FXML
    void eventoAbrirDistribuidor(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        //       fileChooser.getExtensionFilters().addAll(
        //               new FileChooser.ExtensionFilter("FES", "*.fes"));
        File selectedFile = fileChooser.showOpenDialog(this.iconAbrirDistribuidor.getScene().
                getWindow());
        if (selectedFile != null) {
            try {
                FileInputStream fo = new FileInputStream(selectedFile);
                ObjectInputStream entrada = new ObjectInputStream(fo);
                ArrayList<Provedor> datos = (ArrayList<Provedor>) entrada.readObject();
                SingletonProvedor.getInstance().getLista().clear();
                for (Provedor us:datos) {
                    System.out.println(us.getImagen());
                    SingletonProvedor.getInstance().getLista().add(us);
                }
            }catch (IOException | ClassNotFoundException e) { //+FileNotFound
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void eventoGuardarDistribuidor(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        //       fileChooser.getExtensionFilters().addAll(
        //               new FileChooser.ExtensionFilter("FES", "*.fes"));
        File selectedFile = fileChooser.showSaveDialog(this.iconAbrirDistribuidor.getScene().
                getWindow());
        if (selectedFile != null) {
            try {
                FileOutputStream fo = new FileOutputStream(selectedFile);
                ObjectOutputStream salida = new ObjectOutputStream(fo);
                ArrayList<Provedor> datos = SingletonProvedor.getInstance().getConversion();
                for (Provedor us : datos) {
                    System.out.println(us.getDistribuidor().getImagen());
                }
                salida.writeObject(datos);
                salida.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void eventoNuevoDistribuidor(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/fes/aragon/xml/registro_de_distribuidor.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.clmNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.clmCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        this.clmTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        this.clmDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tblDistribuidor.setItems(SingletonProvedor.getInstance().getLista());
        Callback<TableColumn<Provedor, String>, TableCell<Provedor, String>>
                celda = (TableColumn<Provedor, String> parametros) -> {
            final TableCell<Provedor, String> cel = new TableCell<>() {
                @Override
                protected void updateItem(String s, boolean b) {
                    super.updateItem(s, b);
                    if (b) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        FontAwesomeIconView borrarIcono = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        borrarIcono.setGlyphStyle("-fx-cursor:hand;" + "gylph-size:28px;" + "-fx-fill:#ff1744");

                        FontAwesomeIconView modificarIcono = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
                        modificarIcono.setGlyphStyle("-fx-cursor:hand;" + "gylph-size:28px;" + "-fx-fill:#ff1744");
                        borrarIcono.setOnMouseClicked((MouseEvent evento) -> {
                            int indice = tblDistribuidor.getSelectionModel().
                                    getSelectedIndex();
                            SingletonDistribuidor.getInstance().getLista().
                                    remove(indice);
                        });
                        modificarIcono.setOnMouseClicked((MouseEvent evento) -> {
                            modificarDistribuidor(tblDistribuidor.getSelectionModel().
                                    getSelectedIndex());
                        });
                        HBox hBox = new HBox(modificarIcono, borrarIcono);
                        hBox.setStyle("-fx-alignment:center");
                        HBox.setMargin(modificarIcono, new Insets(2, 2, 0, 3));
                        HBox.setMargin(borrarIcono, new Insets(2, 2, 0, 3));
                        setGraphic(hBox);
                        setText(null);
                    }
                }
            };
            return cel;
        };
        this.clmOperaciones.setCellFactory(celda);
    }
    public void modificarDistribuidor(int indice){
        try {
            FXMLLoader modificar = new FXMLLoader(getClass().
                    getResource("/fes/aragon/xml/registro_de_distribuidor.fxml"));
            Parent parent = (Parent) modificar.load();
            ((registroDistribuidorController) modificar.getController()).indiceDistribuidor(indice);
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
