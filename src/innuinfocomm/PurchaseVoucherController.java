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


public class PurchaseVoucherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField biiNoTextBox;

    @FXML
    private TextField cstNoTextBox;

    @FXML
    private TextField dateTextBox;

    @FXML
    private TextField finalTotalTextBox;

    @FXML
    private TextField finalVatTextBox;

    @FXML
    private TableColumn<?, ?> itemNameColumn;

    @FXML
    private Button printBtn;

    @FXML
    private TableColumn<?, ?> quantity2Column;

    @FXML
    private TableColumn<?, ?> quantityColumn;

    @FXML
    private TableColumn<?, ?> rateColumn;

    @FXML
    private TextField refNoTextBox;

    @FXML
    private TextArea remarksTextArea;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<?> supplierNameComboBox;

    @FXML
    private TableColumn<?, ?> totalColumn;

    @FXML
    private TableColumn<?, ?> unit2Column;

    @FXML
    private TableColumn<?, ?> unitColumn;

    @FXML
    private TableColumn<?, ?> vatPercColumn;

    @FXML
    private TableColumn<?, ?> vatRsColumn;


    @FXML
    void handlePrintBtn(ActionEvent event) {
    }

    @FXML
    void handleSaveBtn(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert biiNoTextBox != null : "fx:id=\"biiNoTextBox\" was not injected: check your FXML file 'PurchaseVoucher.fxml'.";
        assert cstNoTextBox != null : "fx:id=\"cstNoTextBox\" was not injected: check your FXML file 'PurchaseVoucher.fxml'.";
        assert dateTextBox != null : "fx:id=\"dateTextBox\" was not injected: check your FXML file 'PurchaseVoucher.fxml'.";
        assert finalTotalTextBox != null : "fx:id=\"finalTotalTextBox\" was not injected: check your FXML file 'PurchaseVoucher.fxml'.";
        assert finalVatTextBox != null : "fx:id=\"finalVatTextBox\" was not injected: check your FXML file 'PurchaseVoucher.fxml'.";
        assert itemNameColumn != null : "fx:id=\"itemNameColumn\" was not injected: check your FXML file 'PurchaseVoucher.fxml'.";
        assert printBtn != null : "fx:id=\"printBtn\" was not injected: check your FXML file 'PurchaseVoucher.fxml'.";
        assert quantity2Column != null : "fx:id=\"quantity2Column\" was not injected: check your FXML file 'PurchaseVoucher.fxml'.";
        assert quantityColumn != null : "fx:id=\"quantityColumn\" was not injected: check your FXML file 'PurchaseVoucher.fxml'.";
        assert rateColumn != null : "fx:id=\"rateColumn\" was not injected: check your FXML file 'PurchaseVoucher.fxml'.";
        assert refNoTextBox != null : "fx:id=\"refNoTextBox\" was not injected: check your FXML file 'PurchaseVoucher.fxml'.";
        assert remarksTextArea != null : "fx:id=\"remarksTextArea\" was not injected: check your FXML file 'PurchaseVoucher.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'PurchaseVoucher.fxml'.";
        assert supplierNameComboBox != null : "fx:id=\"supplierNameComboBox\" was not injected: check your FXML file 'PurchaseVoucher.fxml'.";
        assert totalColumn != null : "fx:id=\"totalColumn\" was not injected: check your FXML file 'PurchaseVoucher.fxml'.";
        assert unit2Column != null : "fx:id=\"unit2Column\" was not injected: check your FXML file 'PurchaseVoucher.fxml'.";
        assert unitColumn != null : "fx:id=\"unitColumn\" was not injected: check your FXML file 'PurchaseVoucher.fxml'.";
        assert vatPercColumn != null : "fx:id=\"vatPercColumn\" was not injected: check your FXML file 'PurchaseVoucher.fxml'.";
        assert vatRsColumn != null : "fx:id=\"vatRsColumn\" was not injected: check your FXML file 'PurchaseVoucher.fxml'.";


    }

}
