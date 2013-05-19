package innuinfocomm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class AddItemController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> itemGroupComboBox;

    @FXML
    private TextField itemNameTextBox;

    @FXML
    private TextField itemOpeningBalTextBox;

    @FXML
    private ComboBox<?> itemRatePerUnitComboBox;

    @FXML
    private TextField itemRateTextBox;

    @FXML
    private TextField itemTotalValueTextBox;

    @FXML
    private ComboBox<?> itemUnitComboBox;

    @FXML
    private TextField itemVatPercTextBox;

    @FXML
    private Button saveItemButton;


    @FXML
    void handleSaveItemButton(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert itemGroupComboBox != null : "fx:id=\"itemGroupComboBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemNameTextBox != null : "fx:id=\"itemNameTextBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemOpeningBalTextBox != null : "fx:id=\"itemOpeningBalTextBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemRatePerUnitComboBox != null : "fx:id=\"itemRatePerUnitComboBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemRateTextBox != null : "fx:id=\"itemRateTextBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemTotalValueTextBox != null : "fx:id=\"itemTotalValueTextBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemUnitComboBox != null : "fx:id=\"itemUnitComboBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemVatPercTextBox != null : "fx:id=\"itemVatPercTextBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert saveItemButton != null : "fx:id=\"saveItemButton\" was not injected: check your FXML file 'AddItem.fxml'.";


    }

}
