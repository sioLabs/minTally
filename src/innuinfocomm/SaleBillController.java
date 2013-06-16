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
        
        itemsComboBox = new ComboBox<>();
        groupComboBox.getItems().clear();
        subGroupComboBox.getItems().clear();        
        itemsComboBox.getItems().clear();
                
        System.out.println("\n in initialize func");
        fillGroupComboBox();
        
        groupComboBox.valueProperty().addListener(new ChangeListener<ItemGroup>(){

            @Override
            public void changed(ObservableValue<? extends ItemGroup> ov, ItemGroup t, ItemGroup t1) {
                //now set the subgroup items here 
                   System.out.println("group combox box value hanged");
                   System.out.println("old value = "+t.getItemGroupName());
                   System.out.println("new value = "+t1.getItemGroupName());
                   fillSubGroupComboBox();
           
            }
        
        
        });
        
        subGroupComboBox.valueProperty().addListener(new ChangeListener<ItemGroup>(){

            @Override
            public void changed(ObservableValue<? extends ItemGroup> ov, ItemGroup t, ItemGroup t1) {
                System.out.println("subgroup value changed to "+t1.getItemGroupName());
                fillItemsComboBox();
            }
        
        
        });
        
        itemsComboBox.valueProperty().addListener(new ChangeListener<Items>(){

            @Override
            public void changed(ObservableValue<? extends Items> ov, Items t, Items t1) {
                System.out.println("Items selection changed" + t1.getItemName());                
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
        groupComboBox.getSelectionModel().selectFirst();
        
    
    }
    
    private void fillSubGroupComboBox(){
        //get the selected value in the group Combo Box
        System.out.println("fill subgroup combo box");
        ItemGroup group = groupComboBox.getSelectionModel().getSelectedItem();
        
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        Query q  = em.createNamedQuery("ItemGroup.findByItemGroupParent");
        q.setParameter("itemGroupParent", group.getItemGroupId());
        em.getTransaction().begin();
        ArrayList<ItemGroup> subGroup = new ArrayList<ItemGroup>(q.getResultList());
        em.getTransaction().commit();
        
        if(subGroup.size()<1){  //this means that no subgroup exist
            System.out.println("inside the nosubgroup condition");
            subGroupComboBox.getItems().clear();
            fillItemsComboBox();
            return ;
        }
        System.out.println("group has subgroups");
        subGroupComboBox.getItems().clear();
        subGroupComboBox.getItems().addAll(subGroup);
        subGroupComboBox.getSelectionModel().clearSelection();
        //subGroupComboBox.setValue(subGroup.get(0));
        subGroupComboBox.getSelectionModel().selectFirst();
    
    }
    
    private void fillItemsComboBox(){
        System.out.println("Inside fill items");
        itemsComboBox.getItems().removeAll(itemsComboBox.getItems());
       
        //check if the number of items in subgorup. 
        if(subGroupComboBox.getItems().size() < 1){
            System.out.println("No sub groups exist");
            //the get the items according to the selected group
            ItemGroup group = groupComboBox.getSelectionModel().getSelectedItem();
            ArrayList<Items> items1 = new ArrayList<Items>(group.getItemsCollection1());
            System.out.println(group.getItemGroupName()+"\n" + group.getItemsCollection().size()+" "+group.getItemsCollection1().size()+"\n"+items1.size());
            itemsComboBox.getItems().clear();
            itemsComboBox.getItems().addAll(items1);
            itemsComboBox.getSelectionModel().clearSelection();
            itemsComboBox.getSelectionModel().selectFirst();
            
        
        }else{
            //get the select subgroup and list items from that 
            //EntityManager em = EntityManagerHelper.getInstance().getEm();
            //em.getTransaction().begin();
            ItemGroup group = subGroupComboBox.getSelectionModel().getSelectedItem();
            
            System.out.println("group has subgroup and subgroup has items");
            System.out.println("subgroup = " +group.getItemGroupName());
            //System.out.println(group.getItemGroupName() + "\n"+group.getItemsCollection().size()+"\n "+group.getItemsCollection1().size());
            ArrayList<Items> items1 = new ArrayList<Items>(group.getItemsCollection());
            System.out.println(items1.size() + "num items ");
            System.out.println(itemsComboBox.getItems().size()+"initial size");
            itemsComboBox.getItems().clear();
            itemsComboBox.getItems().addAll(items1);
            itemsComboBox.getSelectionModel().clearSelection();
            //itemsComboBox.
            System.out.println(itemsComboBox.getItems().size()+"finalSize size");
            itemsComboBox.getSelectionModel().selectFirst();
            //itemsComboBox.getSelectionModel().
            
        
        }
               
    
    }

           
}
