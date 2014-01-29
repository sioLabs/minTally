package innuinfocomm;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import pojos.ItemsPharma;
import pojos.ItemsPharmaProperty;
import utils.EntityManagerHelper;


public class SalebillPharmaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<ItemsPharmaProperty, Float> AmountTableColumn;

    @FXML
    private TextField CDamtTextBox;

    @FXML
    private TableColumn<ItemsPharmaProperty, String> batchTableColumn;

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
    private TableColumn<ItemsPharmaProperty, String> descTableColumn;

    @FXML
    private TextField discountTextBox;

    @FXML
    private TableColumn<ItemsPharmaProperty, String> dmTableColumn;

    @FXML
    private Label errorLabel;

    @FXML
    private TableColumn<ItemsPharmaProperty, String> expTableColumn;

    @FXML
    private TextField finalAmtTextBox;

    @FXML
    private TableColumn<ItemsPharmaProperty, String> makeTableColumn;

    @FXML
    private TableColumn<ItemsPharmaProperty, Float> mrpTableColumn;

    @FXML
    private TableColumn<ItemsPharmaProperty, Integer> packTableColumn;

    @FXML
    private ComboBox<String> paymentComboBox;

    @FXML
    private Button printBtn;

    @FXML
    private TableColumn<ItemsPharmaProperty, Float> qntyTableColumn;

    @FXML
    private TableColumn<ItemsPharmaProperty, Float> rateTableColumn;

    @FXML
    private Button resetBtn;

    @FXML
    private TableView<ItemsPharmaProperty> saleItemTableview;

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

    private ObservableList<ItemsPharmaProperty> data = FXCollections.observableArrayList();
    
    
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
        
        mrpTableColumn.setCellValueFactory(new PropertyValueFactory<ItemsPharmaProperty, Float>("mrp"));
        dmTableColumn.setCellValueFactory(new PropertyValueFactory<ItemsPharmaProperty,String>("DM"));
        makeTableColumn.setCellValueFactory(new PropertyValueFactory<ItemsPharmaProperty,String>("make"));
        rateTableColumn.setCellValueFactory(new PropertyValueFactory<ItemsPharmaProperty,Float>("rate"));
        descTableColumn.setCellValueFactory(new PropertyValueFactory<ItemsPharmaProperty,String>("description"));
        
                

       initializeSaleBill();
        
    }
    
        @FXML
    void handlePrintBtn(ActionEvent event) {
            
    }

    @FXML
    void handleResetBtn(ActionEvent event) {
        data.get(0).setDescription("Refresged data"); 
        saleItemTableview.getColumns().get(0).setVisible(false);
        saleItemTableview.getColumns().get(0).setVisible(true);
    }

    @FXML
    void handleSaveBtn(ActionEvent event) {
        System.out.println("Button Clicked");
        data.add(new ItemsPharmaProperty());
        data.get(0).setBatch(new SimpleStringProperty("check 123"));
//        saleItemTableview.getColumns().get(0).setVisible(false);
//        saleItemTableview.getColumns().get(0).setVisible(true);
        
        
       // data.get(0).setDescription("Hello World");
        
    }

    private void initializeSaleBill() {
       saleItemTableview.setItems(data);    
       
       //get the items from the db here
              EntityManager em = EntityManagerHelper.getInstance().getEm();
        Query q = em.createNamedQuery("ItemsPharma.findAll");
        ArrayList<ItemsPharma>  iList = new ArrayList(q.getResultList());
        
        
        for(ItemsPharma i : iList){
            data.add(i.getPropertyObj());
        }
       
       
    }

}
