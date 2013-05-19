package innuinfocomm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;



public class PaymentVoucherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField accBalTextBox;

    @FXML
    private ComboBox<String> accComboBox;

    @FXML
    private TextField bankBranchTextBox;

    @FXML
    private TextField bankNameTextBox;

    @FXML
    private TextField chequeNoTextBox;

    @FXML
    private TableColumn<?, ?> creditTableColumn;

    @FXML
    private TextField dateTextField;

    @FXML
    private TableColumn<?, ?> debitTableColumn;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TableColumn<?, ?> particularsTableColumn;

    @FXML
    private TableView<?> paymentDetailsTableView;

    @FXML
    private Button printVoucherButton;

    @FXML
    private TextField refNoTextField;

    @FXML
    private Button saveVoucherButton;

    @FXML
    private TextField voucherNoTextField;


    @FXML
    void handlePrintVoucherButton(ActionEvent event) {
    }

    @FXML
    void handleSaveVoucherButton(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert accBalTextBox != null : "fx:id=\"accBalTextBox\" was not injected: check your FXML file 'PaymentVoucher.fxml'.";
        assert accComboBox != null : "fx:id=\"accComboBox\" was not injected: check your FXML file 'PaymentVoucher.fxml'.";
        assert bankBranchTextBox != null : "fx:id=\"bankBranchTextBox\" was not injected: check your FXML file 'PaymentVoucher.fxml'.";
        assert bankNameTextBox != null : "fx:id=\"bankNameTextBox\" was not injected: check your FXML file 'PaymentVoucher.fxml'.";
        assert chequeNoTextBox != null : "fx:id=\"chequeNoTextBox\" was not injected: check your FXML file 'PaymentVoucher.fxml'.";
        assert creditTableColumn != null : "fx:id=\"creditTableColumn\" was not injected: check your FXML file 'PaymentVoucher.fxml'.";
        assert dateTextField != null : "fx:id=\"dateTextField\" was not injected: check your FXML file 'PaymentVoucher.fxml'.";
        assert debitTableColumn != null : "fx:id=\"debitTableColumn\" was not injected: check your FXML file 'PaymentVoucher.fxml'.";
        assert descriptionTextArea != null : "fx:id=\"descriptionTextArea\" was not injected: check your FXML file 'PaymentVoucher.fxml'.";
        assert particularsTableColumn != null : "fx:id=\"particularsTableColumn\" was not injected: check your FXML file 'PaymentVoucher.fxml'.";
        assert paymentDetailsTableView != null : "fx:id=\"paymentDetailsTableView\" was not injected: check your FXML file 'PaymentVoucher.fxml'.";
        assert printVoucherButton != null : "fx:id=\"printVoucherButton\" was not injected: check your FXML file 'PaymentVoucher.fxml'.";
        assert refNoTextField != null : "fx:id=\"refNoTextField\" was not injected: check your FXML file 'PaymentVoucher.fxml'.";
        assert saveVoucherButton != null : "fx:id=\"saveVoucherButton\" was not injected: check your FXML file 'PaymentVoucher.fxml'.";
        assert voucherNoTextField != null : "fx:id=\"voucherNoTextField\" was not injected: check your FXML file 'PaymentVoucher.fxml'.";

        //initializatio code here
        paymentDetailsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

}
