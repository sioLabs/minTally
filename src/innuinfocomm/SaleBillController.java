package innuinfocomm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;


public class SaleBillController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField billNoTextbox;

    @FXML
    private RadioButton challanRadioBtn;

    @FXML
    private RadioButton challanRateRadioBtn;

    @FXML
    private TextField companyTextBox;

    @FXML
    private ComboBox<?> customerComboBox;

    @FXML
    private TextField dateTextBox;

    @FXML
    private TextField discountTextBox;

    @FXML
    private TextField frieghtTextBox;

    @FXML
    private RadioButton invoiceRadioBtn;

    @FXML
    private TableColumn<?, ?> itemNameTableCol;

    @FXML
    private Button printBtn;

    @FXML
    private TableColumn<?, ?> quantity2TableCol;

    @FXML
    private TableColumn<?, ?> quantityTableCol;

    @FXML
    private TableColumn<?, ?> rateTableCol;

    @FXML
    private TableColumn<?, ?> remarkTableCol;

    @FXML
    private TextField remarksTextBox;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<?> siteComboBox;

    @FXML
    private TableColumn<?, ?> totalTableCol;

    @FXML
    private Label totalTextBox;

    @FXML
    private TableColumn<?, ?> unit2TableCol;

    @FXML
    private TableColumn<?, ?> unitTableCol;

    @FXML
    private TableColumn<?, ?> vatPercTableCol;

    @FXML
    private TableColumn<?, ?> vatRsTableCol;

    @FXML
    private TextField vatTextBox;


    @FXML
    void handlePrintBtn(ActionEvent event) {
    }

    @FXML
    void handleSaveBtn(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert billNoTextbox != null : "fx:id=\"billNoTextbox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert challanRadioBtn != null : "fx:id=\"challanRadioBtn\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert challanRateRadioBtn != null : "fx:id=\"challanRateRadioBtn\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert companyTextBox != null : "fx:id=\"companyTextBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert customerComboBox != null : "fx:id=\"customerComboBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert dateTextBox != null : "fx:id=\"dateTextBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert discountTextBox != null : "fx:id=\"discountTextBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert frieghtTextBox != null : "fx:id=\"frieghtTextBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert invoiceRadioBtn != null : "fx:id=\"invoiceRadioBtn\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert itemNameTableCol != null : "fx:id=\"itemNameTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert printBtn != null : "fx:id=\"printBtn\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert quantity2TableCol != null : "fx:id=\"quantity2TableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert quantityTableCol != null : "fx:id=\"quantityTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert rateTableCol != null : "fx:id=\"rateTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert remarkTableCol != null : "fx:id=\"remarkTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert remarksTextBox != null : "fx:id=\"remarksTextBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert siteComboBox != null : "fx:id=\"siteComboBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert totalTableCol != null : "fx:id=\"totalTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert totalTextBox != null : "fx:id=\"totalTextBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert unit2TableCol != null : "fx:id=\"unit2TableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert unitTableCol != null : "fx:id=\"unitTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert vatPercTableCol != null : "fx:id=\"vatPercTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert vatRsTableCol != null : "fx:id=\"vatRsTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert vatTextBox != null : "fx:id=\"vatTextBox\" was not injected: check your FXML file 'SaleBill.fxml'.";


    }

}
