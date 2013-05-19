package innuinfocomm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class RecieptVoucherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> accountDescColumn;

    @FXML
    private TextField balanceTextField;

    @FXML
    private ComboBox<?> bankAcComboBox;

    @FXML
    private TextField bankNameTextBox;

    @FXML
    private TextField branchNameTextBox;

    @FXML
    private TextField chequeNoTextBox;

    @FXML
    private TableColumn<?, ?> crColumn;

    @FXML
    private TextField dateTextBox;

    @FXML
    private TextArea descTextArea;

    @FXML
    private TableColumn<?, ?> drColumn;

    @FXML
    private Button printBtn;

    @FXML
    private TextField refNoTextBox;

    @FXML
    private Button saveBtn;

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
        assert accountDescColumn != null : "fx:id=\"accountDescColumn\" was not injected: check your FXML file 'RecieptVoucher.fxml'.";
        assert balanceTextField != null : "fx:id=\"balanceTextField\" was not injected: check your FXML file 'RecieptVoucher.fxml'.";
        assert bankAcComboBox != null : "fx:id=\"bankAcComboBox\" was not injected: check your FXML file 'RecieptVoucher.fxml'.";
        assert bankNameTextBox != null : "fx:id=\"bankNameTextBox\" was not injected: check your FXML file 'RecieptVoucher.fxml'.";
        assert branchNameTextBox != null : "fx:id=\"branchNameTextBox\" was not injected: check your FXML file 'RecieptVoucher.fxml'.";
        assert chequeNoTextBox != null : "fx:id=\"chequeNoTextBox\" was not injected: check your FXML file 'RecieptVoucher.fxml'.";
        assert crColumn != null : "fx:id=\"crColumn\" was not injected: check your FXML file 'RecieptVoucher.fxml'.";
        assert dateTextBox != null : "fx:id=\"dateTextBox\" was not injected: check your FXML file 'RecieptVoucher.fxml'.";
        assert descTextArea != null : "fx:id=\"descTextArea\" was not injected: check your FXML file 'RecieptVoucher.fxml'.";
        assert drColumn != null : "fx:id=\"drColumn\" was not injected: check your FXML file 'RecieptVoucher.fxml'.";
        assert printBtn != null : "fx:id=\"printBtn\" was not injected: check your FXML file 'RecieptVoucher.fxml'.";
        assert refNoTextBox != null : "fx:id=\"refNoTextBox\" was not injected: check your FXML file 'RecieptVoucher.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'RecieptVoucher.fxml'.";
        assert voucherNoTextBox != null : "fx:id=\"voucherNoTextBox\" was not injected: check your FXML file 'RecieptVoucher.fxml'.";


    }

}
