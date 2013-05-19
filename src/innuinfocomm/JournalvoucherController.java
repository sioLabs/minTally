package innuinfocomm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class JournalvoucherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField balanceTextBox;

    @FXML
    private ComboBox<?> byAccComboBox;

    @FXML
    private TextField dateTextBox;

    @FXML
    private TextArea descTextArea;

    @FXML
    private Button printBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<?> toAccComboBox;

    @FXML
    private TextField toAmountTextBox;

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
        assert balanceTextBox != null : "fx:id=\"balanceTextBox\" was not injected: check your FXML file 'Journalvoucher.fxml'.";
        assert byAccComboBox != null : "fx:id=\"byAccComboBox\" was not injected: check your FXML file 'Journalvoucher.fxml'.";
        assert dateTextBox != null : "fx:id=\"dateTextBox\" was not injected: check your FXML file 'Journalvoucher.fxml'.";
        assert descTextArea != null : "fx:id=\"descTextArea\" was not injected: check your FXML file 'Journalvoucher.fxml'.";
        assert printBtn != null : "fx:id=\"printBtn\" was not injected: check your FXML file 'Journalvoucher.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'Journalvoucher.fxml'.";
        assert toAccComboBox != null : "fx:id=\"toAccComboBox\" was not injected: check your FXML file 'Journalvoucher.fxml'.";
        assert toAmountTextBox != null : "fx:id=\"toAmountTextBox\" was not injected: check your FXML file 'Journalvoucher.fxml'.";
        assert voucherNoTextBox != null : "fx:id=\"voucherNoTextBox\" was not injected: check your FXML file 'Journalvoucher.fxml'.";


    }

}
