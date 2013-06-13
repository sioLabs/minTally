package innuinfocomm;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import pojos.Items;
import utils.EntityManagerHelper;
import utils.SaleBillItem;


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
    private ComboBox<String> customerComboBox;

    @FXML
    private TextField dateTextBox;

    @FXML
    private TextField discountTextBox;

    @FXML
    private TextField frieghtTextBox;

    @FXML
    private RadioButton invoiceRadioBtn;

    @FXML
    private TableColumn<SaleBillItem, String> itemNameTableCol;

    @FXML
    private Button printBtn;

    @FXML
    private TableColumn<SaleBillItem, Float> quantity2TableCol;

    @FXML
    private TableColumn<SaleBillItem, Float> quantityTableCol;

    @FXML
    private TableColumn<SaleBillItem, Float> rateTableCol;

    @FXML
    private TableColumn<SaleBillItem, String> remarkTableCol;
    
    @FXML
    private TableView<Items> SaleItemTableView;

    @FXML
    private TextField remarksTextBox;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<String> siteComboBox;

    @FXML
    private TableColumn<SaleBillItem, Float> totalTableCol;

    @FXML
    private Label totalTextBox;

    @FXML
    private TableColumn<SaleBillItem, String> unit2TableCol;

    @FXML
    private TableColumn<SaleBillItem, String> unitTableCol;

    @FXML
    private TableColumn<SaleBillItem, Float> vatPercTableCol;

    @FXML
    private TableColumn<SaleBillItem, Float> vatRsTableCol;

    @FXML
    private TextField vatTextBox;
    
    @FXML
    private ComboBox<Items> itemComboBox;


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
        assert itemComboBox != null : "fx:id=\"itemComboBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert SaleItemTableView != null : "fx:id=\"saleItemTableView\" was not injected: check your FXML file 'SaleBill.fxml'.";
        
        fillComboBox();
        /*itemComboBox.valueProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> ov, String t,  String t1) {
            
            }

        
        });*/
        
        
   }
    
    @FXML
    public void handleComboBox(){
        
        String text = itemComboBox.getEditor().getText();
        if(text.equals("") || text == null){
            fillComboBox();
            return;
        }
        text="%"+text+"%";
        
       
        //code here to get all the items in the database
        //and how do we do that
        //1. GEt all items 
        //2. Put them in the combobox
     
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        Query q = em.createNamedQuery("Item.findItemNameLike");
        q.setParameter("itemName", text);
        List<Items> list = q.getResultList();
        itemComboBox.getItems().clear();
        itemComboBox.getItems().addAll(list);
        itemComboBox.getSelectionModel().clearSelection();
        itemComboBox.show();
    }
    
    
    
    private void initializeTableColumns(){
    
        itemNameTableCol.setEditable(true);
        
    
    }
    
    

    private void fillComboBox() {
        
//        EntityManager em = EntityManagerHelper.getInstance().getEm();
  //      Query q = em.createNamedQuery("Item.findAll");
    //    List<Items> list = q.getResultList();
        
        
        
        //data = FXCollections.observableList(list);
        //itemComboBox.getItems().clear();
        //itemComboBox.getItems().addAll(list);
        //itemComboBox.setItems(data);
        //itemComboBox.getSelectionModel().clearSelection();
        
    }

       
}
