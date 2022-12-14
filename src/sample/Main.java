package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class Main extends Application {


    TableView<Product> table;
    TextField idInput, nameInput, priceInput, qtyInput;
    Label label;

    ObservableList<Product> products = FXCollections.observableArrayList();


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Product list");

        // Id Column
        TableColumn<Product, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setMinWidth(200);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Name Column
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Price Column
        TableColumn<Product, BigDecimal> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(200);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Qty Column
        TableColumn<Product, Integer> qtyColumn = new TableColumn<>("Qty");
        qtyColumn.setMinWidth(200);
        qtyColumn.setCellValueFactory(new PropertyValueFactory<>("qty"));

        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(idColumn, nameColumn, priceColumn, qtyColumn);

        table.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );

        // Id input
        idInput = new TextField();
        idInput.setPromptText("Id");
        idInput.setMinWidth(100);

        // Name input
        nameInput = new TextField();
        nameInput.setPromptText("Name");
        nameInput.setMinWidth(100);

        // Price input
        priceInput = new TextField();
        priceInput.setPromptText("Price");
        priceInput.setMinWidth(100);

        // Qty input
        qtyInput = new TextField();
        qtyInput.setPromptText("Qty");
        qtyInput.setMinWidth(100);


        idInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    idInput.setText(newValue.replaceAll("[^\\d]", ""));
                    label.setText("There is a problem. Id is not number");
                } else {
                    label.setText("");
                }
            }
        });

        priceInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*") && !newValue.contains(".")) {
                    priceInput.setText(newValue.replaceAll("[^\\d]", ""));
                    label.setText("There is a problem. Price is not number");
                } else {
                    label.setText("");
                }
            }
        });

        qtyInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    qtyInput.setText(newValue.replaceAll("[^\\d]", ""));
                    label.setText("There is a problem. Quantity is not number");
                } else {
                    label.setText("");
                }
            }
        });


        // Buttons
        Button addButton = new Button("ADD/UPDATE");
        addButton.setOnAction(e -> addOrUpdateButtonClick());

        Button deleteButton = new Button("DELETE");
        deleteButton.setOnAction(e -> deleteButtonClick());

        label = new Label();

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(idInput, nameInput, priceInput, qtyInput, addButton, deleteButton);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(table, hBox, label);


        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Add or update product AddButton clicked
    private void addOrUpdateButtonClick() {

        try {
            int id = Integer.parseInt(idInput.getText());
            int size = table.getItems().filtered(p -> p.getId() == id)
                    .size();

            if (size > 0) {
                Product product = new Product(id, nameInput.getText(),
                        new BigDecimal(priceInput.getText()), Integer.valueOf(qtyInput.getText()));
                products.remove(id - 1);
                products.add(id - 1, product);
                label.setText("Product Update is successfuly");
            } else {
                Product product = new Product(nameInput.getText(),
                        new BigDecimal(priceInput.getText()), Integer.valueOf(qtyInput.getText()));
                products.add(product);
                label.setText("Product Add is successfuly");
            }
            label.setStyle("-fx-font-size:14px; -fx-text-fill: green;");
            idInput.clear();
            nameInput.clear();
            priceInput.clear();
            qtyInput.clear();
        } catch (Exception e){
            label.setStyle("-fx-font-size:14px; -fx-text-fill: red;");
            label.setText("There is a problem. Please check inputs");
            e.printStackTrace();
        }


    }

    // delete product DeleteButton clicked
    private void deleteButtonClick() {
        ObservableList<Product> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();
        allProducts.removeAll(productSelected);

        idInput.clear();
        nameInput.clear();
        priceInput.clear();
        qtyInput.clear();
    }


    public static void main(String[] args) {
        launch(args);
    }


    public ObservableList<Product> getProduct() {
        products.add(new Product("Apple", BigDecimal.valueOf(5264.29), 4));
        products.add(new Product("Acer", BigDecimal.valueOf(2469.12), 5));
        products.add(new Product("Asus", BigDecimal.valueOf(2569.22), 8));
        products.add(new Product("Dell", BigDecimal.valueOf(1895.61), 7));
        products.add(new Product("HP", BigDecimal.valueOf(1296.16), 7));
        products.add(new Product("Lenovo", BigDecimal.valueOf(1225.25), 12));
        return products;
    }


}
