package innuinfocomm;

import java.net.URL;
import java.util.ArrayList;
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
import pojos.ItemGroup;
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
    private ComboBox<Items> itemsComboBox;
    
    @FXML 
    private ComboBox<ItemGroup> groupComboBox;
    
    @FXML
    private ComboBox<ItemGroup> subGroupComboBox;
    


   

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
        assert SaleItemTableView != null : "fx:id=\"saleItemTableView\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert itemsComboBox != null : "fx:id=\"itemsComboBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert groupComboBox != null : "fx:id=\"itemsComboBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert subGroupComboBox != null : "fx:id=\"itemsComboBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        
        groupComboBox.getItems().clear();
        subGroupComboBox.getItems().clear();        
        //itemsComboBox.getItems().clear();
                
        System.out.println("\n in initialize func");
        fillGroupComboBox();
        
        groupComboBox.valueProperty().addListener(new ChangeListener<ItemGroup>(){

            
            public void changed(ObservableValue<? extends ItemGroup> ov, ItemGroup t, ItemGroup t1) {
                //now set the subgroup items here 
                  fillSubGroupComboBox();
           
            }
        
        
        });
        
        subGroupComboBox.valueProperty().addListener(new ChangeListener<ItemGroup>(){

            @Override
            public void changed(ObservableValue<? extends ItemGroup> ov, ItemGroup t, ItemGroup t1) {
                fillItemsComboBox();
            }
        
        
        });
        
        
        
   }
    
    
    
    
    
    private void initializeTableColumns(){
    
        itemNameTableCol.setEditable(true);
        
    
    }
    
    private void fillGroupComboBox(){
    
        System.out.println("fillGroupComboBox");
        //get all the item group with parent id as 0;
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        Query q  = em.createNamedQuery("ItemGroup.findByItemGroupParent");
        q.setParameter("itemGroupParent", 0);
        ArrayList<ItemGroup> groups = new ArrayList<ItemGroup>(q.getResultList());
        
        groupComboBox.getItems().clear();
        groupComboBox.getItems().addAll(groups);
        groupComboBox.getSelectionModel().clearSelection();
        groupComboBox.setValue(groups.get(0));
        
    
    }
    
    private void fillSubGroupComboBox(){
        //get the selected value in the group Combo Box
        System.out.println("fill subgrou pcombo box");
        ItemGroup group = groupComboBox.getSelectionModel().getSelectedItem();
        
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        Query q  = em.createNamedQuery("ItemGroup.findByItemGroupParent");
        q.setParameter("itemGroupParent", group.getItemGroupId());
        em.getTransaction().begin();
        ArrayList<ItemGroup> subGroup = new ArrayList<ItemGroup>(q.getResultList());
        em.getTransaction().commit();
        
        if(subGroup.size()<1){  //this means that no subgroup exist
            System.out.println("inside the no subgroup condition");
            subGroupComboBox.getItems().clear();
            fillItemsComboBox();
            return ;
        }
        
        subGroupComboBox.getItems().clear();
        subGroupComboBox.getItems().addAll(subGroup);
        subGroupComboBox.getSelectionModel().clearSelection();
        subGroupComboBox.setValue(subGroup.get(0));
    
    }
    
    private void fillItemsComboBox(){
        //check if the number of items in subgorup. 
        if(subGroupComboBox.getItems().size() < 1){
            //the get the items according to the selected group
            ItemGroup group = groupComboBox.getSelectionModel().getSelectedItem();
            ArrayList<Items> items = new ArrayList<>(group.getItemsCollection());
            itemsComboBox.getItems().clear();
            itemsComboBox.getItems().addAll(items);
            itemsComboBox.getSelectionModel().clearSelection();
            itemsComboBox.setValue(items.get(0));       
            
        
        }else{
            //get the select subgroup and list items from that 
            EntityManager em = EntityManagerHelper.getInstance().getEm();
            em.getTransaction().begin();
            ItemGroup group = subGroupComboBox.getSelectionModel().getSelectedItem();
            ArrayList<Items> items = new ArrayList<>(group.getItemsCollection1());
            em.getTransaction().commit();
            itemsComboBox.getItems().clear();
            itemsComboBox.getItems().addAll(items);
            itemsComboBox.getSelectionModel().clearSelection();
            itemsComboBox.setValue(items.get(0));       
        
        }
               
    
    }

           
}
