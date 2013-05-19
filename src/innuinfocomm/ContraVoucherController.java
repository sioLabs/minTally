package innuinfocomm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ContraVoucherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField bankNameTextBox;

    @FXML
    private TextField branchTextBox;

    @FXML
    private TextField byAccBalTextBox;

    @FXML
    private ComboBox<?> byAccComboBox;

    @FXML
    private TextField chequeNoTextBox;

    @FXML
    private TextField dateTextBox;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Button printBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField toAccAmountTextbox;

    @FXML
    private ComboBox<?> toAccComboBox;

    @FXML
    private TextField voucherNoTextBox;


    @FXML
    void handlePrintBtn(ActionEvent event) {
    }

    @FXML
    void handleSaveBtn(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert bankNameTextBox != null : "fx:id=\"bankNameTextBox\" was not injected: check your FXML file 'ContraVoucher.fxml'.";
        assert branchTextBox != null : "fx:id=\"branchTextBox\" was not injected: check your FXML file 'ContraVoucher.fxml'.";
        assert byAccBalTextBox != null : "fx:id=\"byAccBalTextBox\" was not injected: check your FXML file 'ContraVoucher.fxml'.";
        assert byAccComboBox != null : "fx:id=\"byAccComboBox\" was not injected: check your FXML file 'ContraVoucher.fxml'.";
        assert chequeNoTextBox != null : "fx:id=\"chequeNoTextBox\" was not injected: check your FXML file 'ContraVoucher.fxml'.";
        assert dateTextBox != null : "fx:id=\"dateTextBox\" was not injected: check your FXML file 'ContraVoucher.fxml'.";
        assert descriptionTextArea != null : "fx:id=\"descriptionTextArea\" was not injected: check your FXML file 'ContraVoucher.fxml'.";
        assert printBtn != null : "fx:id=\"printBtn\" was not injected: check your FXML file 'ContraVoucher.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'ContraVoucher.fxml'.";
        assert toAccAmountTextbox != null : "fx:id=\"toAccAmountTextbox\" was not injected: check your FXML file 'ContraVoucher.fxml'.";
        assert toAccComboBox != null : "fx:id=\"toAccComboBox\" was not injected: check your FXML file 'ContraVoucher.fxml'.";
        assert voucherNoTextBox != null : "fx:id=\"voucherNoTextBox\" was not injected: check your FXML file 'ContraVoucher.fxml'.";


    }

}
