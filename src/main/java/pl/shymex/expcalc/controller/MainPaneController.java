package pl.shymex.expcalc.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pl.shymex.expcalc.logic.Calculation;
import pl.shymex.expcalc.logic.Person;
import pl.shymex.expcalc.logic.Product;

import static javafx.collections.FXCollections.*;

public class MainPaneController {

    @FXML
    private TextField priceField;

    @FXML
    private TextField nameField;

    @FXML
    private ChoiceBox<Person> choosePersonBox;

    @FXML
    private CheckBox wCheckbox;

    @FXML
    private CheckBox sCheckbox;

    @FXML
    private CheckBox mCheckbox;

    @FXML
    private ListView<Product> productList;

    @FXML
    private TextArea productDetails;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button calculateButton;


    public void initialize() {

        Person S = new Person("Szymon", "S");
        Person W = new Person("Wiktoria", "W");
        Person M = new Person("Marcel", "M");

        choosePersonBox.getItems().addAll(W, S, M);

        addButton.setOnAction(actionEvent -> {
            String consumers = "";
            if(wCheckbox.isSelected()) {
                consumers = consumers + "W" + "";
            }
            if(sCheckbox.isSelected()) {
                consumers = consumers + "S";
            }
            if(mCheckbox.isSelected()) {
                consumers = consumers + "M";
            }
            Product product = new Product(nameField.getText(), datePicker.getValue(),
                    Float.parseFloat(priceField.getText().replace(',', '.')),
                    choosePersonBox.getValue().getName(), consumers);
            nameField.setText("");
            priceField.setText("");

            productList.getItems().add(product);
        });
        productList.addEventFilter(MouseEvent.MOUSE_CLICKED, actionEvent -> {
            Product toShow = productList.getSelectionModel().getSelectedItem();
            productDetails.setText("Name: " + toShow.getName() + "\n" + "Price: "
                    + Math.round(toShow.getPrice() * 100.0)/100.0 + "PLN"
                    + "\n" + "Bought by: " + toShow.getBuyer()
                    + "\n" + "Date: " + toShow.getDate()
                    + "\n" + "Consumers: " + toShow.getConsumer());
        });

        removeButton.setOnAction(actionEvent -> {
            Product toRemove = productList.getSelectionModel().getSelectedItem();
            productList.getItems().remove(toRemove);
            W.removeProduct(toRemove);
            S.removeProduct(toRemove);
            M.removeProduct(toRemove);
        });

        calculateButton.setOnAction(actionEvent -> {
            Calculation calc = new Calculation(W, S, M);
            calc.assignDebtors();
            calc.optimizeDebts();
            String summary = "";
            for (Person person : W.getDebtors().keySet()) {
                if (W.getDebtors().get(person) > 0) {
                    summary += W.getName() + " owes " + person.getName() + " " + W.getDebtors().get(person) + "PLN\n";
                }
            }
            for (Person person : S.getDebtors().keySet()) {
                if (S.getDebtors().get(person) > 0) {
                    summary += S.getName() + " owes " + person.getName() + " " + S.getDebtors().get(person) + "PLN\n";
                }
            }
            for (Person person : M.getDebtors().keySet()) {
                if (M.getDebtors().get(person) > 0) {
                    summary += M.getName() + " owes " + person.getName() + " " + M.getDebtors().get(person) + "PLN\n";
                }
            }

            productDetails.setText(summary);
        });

    }


}
