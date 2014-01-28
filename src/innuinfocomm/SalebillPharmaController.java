package innuinfocomm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class SalebillPharmaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> AmountTableColumn;

    @FXML
    private TextField CDamtTextBox;

    @FXML
    private TableColumn<?, ?> batchTableColumn;

    @FXML
    private TextField billNoTextbox;

    @FXML
    private TextField custLicTextBox;

    @FXML
    private TextField customerTextBox;

    @FXML
    private TextField dateTextBox;

    @FXML
    private TextArea deliveryTextArea;

    @FXML
    private TableColumn<?, ?> descTableColumn;

    @FXML
    private TextField discountTextBox;

    @FXML
    private TableColumn<?, ?> dmTableColumn;

    @FXML
    private Label errorLabel;

    @FXML
    private TableColumn<?, ?> expTableColumn;

    @FXML
    private TextField finalAmtTextBox;

    @FXML
    private TableColumn<?, ?> makeTableColumn;

    @FXML
    private TableColumn<?, ?> mrpTableColumn;

    @FXML
    private TableColumn<?, ?> packTableColumn;

    @FXML
    private ComboBox<?> paymentComboBox;

    @FXML
    private Button printBtn;

    @FXML
    private TableColumn<?, ?> qntyTableColumn;

    @FXML
    private TableColumn<?, ?> rateTableColumn;

    @FXML
    private Button resetBtn;

    @FXML
    private TableView<?> saleItemTableview;

    @FXML
    private Button saveBtn;

    @FXML
    private Label successLabel;

    @FXML
    private TextField totalTextBox;

    @FXML
    private Label vat125AmtLabel;

    @FXML
    private Label vat5AmtLabel;

    @FXML
    private TextField vatTextBox;


    @FXML
    void initialize() {
        assert AmountTableColumn != null : "fx:id=\"AmountTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert CDamtTextBox != null : "fx:id=\"CDamtTextBox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert batchTableColumn != null : "fx:id=\"batchTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert billNoTextbox != null : "fx:id=\"billNoTextbox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert custLicTextBox != null : "fx:id=\"custLicTextBox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert customerTextBox != null : "fx:id=\"customerTextBox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert dateTextBox != null : "fx:id=\"dateTextBox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert deliveryTextArea != null : "fx:id=\"deliveryTextArea\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert descTableColumn != null : "fx:id=\"descTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert discountTextBox != null : "fx:id=\"discountTextBox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert dmTableColumn != null : "fx:id=\"dmTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert errorLabel != null : "fx:id=\"errorLabel\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert expTableColumn != null : "fx:id=\"expTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert finalAmtTextBox != null : "fx:id=\"finalAmtTextBox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert makeTableColumn != null : "fx:id=\"makeTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert mrpTableColumn != null : "fx:id=\"mrpTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert packTableColumn != null : "fx:id=\"packTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert paymentComboBox != null : "fx:id=\"paymentComboBox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert printBtn != null : "fx:id=\"printBtn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert qntyTableColumn != null : "fx:id=\"qntyTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert rateTableColumn != null : "fx:id=\"rateTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert resetBtn != null : "fx:id=\"resetBtn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert saleItemTableview != null : "fx:id=\"saleItemTableview\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert successLabel != null : "fx:id=\"successLabel\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert totalTextBox != null : "fx:id=\"totalTextBox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert vat125AmtLabel != null : "fx:id=\"vat125AmtLabel\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert vat5AmtLabel != null : "fx:id=\"vat5AmtLabel\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert vatTextBox != null : "fx:id=\"vatTextBox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";


    }

}
