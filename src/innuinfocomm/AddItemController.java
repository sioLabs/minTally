/**
 * Sample Skeleton for "AddItem.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

package innuinfocomm;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PopupControl;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import pojos.Item;
import pojos.ItemGroup;
import pojos.Units;
import utils.EntityManagerHelper;


public class AddItemController
    implements Initializable {

    @FXML //  fx:id="itemGroupComboBox"
    private ComboBox<String> itemGroupComboBox; // Value injected by FXMLLoader
    
    //array list to get the group number
    ArrayList<ItemGroup> catList = null;

    @FXML //  fx:id="itemNameTextBox"
    private TextField itemNameTextBox; // Value injected by FXMLLoader

    @FXML //  fx:id="itemOpeningBalTextBox"
    private TextField itemOpeningBalTextBox; // Value injected by FXMLLoader

    @FXML //  fx:id="itemRatePerUnitComboBox"
    private ComboBox<String> itemRatePerUnitComboBox; // Value injected by FXMLLoader

    @FXML //  fx:id="itemRateTextBox"
    private TextField itemRateTextBox; // Value injected by FXMLLoader

    @FXML //  fx:id="itemTotalValueTextBox"
    private TextField itemTotalValueTextBox; // Value injected by FXMLLoader

    @FXML //  fx:id="itemUnitComboBox"
    private ComboBox<String> itemUnitComboBox; // Value injected by FXMLLoader

    @FXML //  fx:id="itemVatPercTextBox"
    private TextField itemVatPercTextBox; // Value injected by FXMLLoader

    @FXML //  fx:id="saveItemButton"
    private Button saveItemButton; // Value injected by FXMLLoader


    // Handler for Button[fx:id="saveItemButton"] onAction
    public void handleSaveItemButton(ActionEvent event) {
        // handle the event here
          System.out.println("Check if this wotking or not");
        // handle the event here
        Item item = new Item();
        String itemName = itemNameTextBox.getText();
        item.setItemName(itemName);
        int under = itemGroupComboBox.getSelectionModel().getSelectedIndex();
        //I only have selected Index. How to create  the Object. From the earlier query
       
        ItemGroup i = catList.get(under+1);
        item.setItemGroup(i);
        int unit = itemUnitComboBox.getSelectionModel().getSelectedIndex()+1;
        item.setItemUnit1(unit);
        float vat = Float.parseFloat(itemVatPercTextBox.getText());
        item.setItemVatPerc(vat);
        float open = Float.parseFloat(itemOpeningBalTextBox.getText());
        item.setItemOpenStock(open);
        float itemRate = Float.parseFloat(itemRateTextBox.getText());
        item.setItemRate(itemRate);
        int ratePerUnit = itemRatePerUnitComboBox.getSelectionModel().getSelectedIndex()+1;
        item.setItemRateUnit(ratePerUnit);
        item.setItePresentStock(open);
        float totalValue = open*itemRate;
        item.setItemTotalValue((int)totalValue);
        
        try{
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
        }catch(Exception e){
            System.out.println("Some exception in saving the Item.");
        }
        
    }
    
    //add bew item category on the fly
    public void handleAddNewCategoryBtn() throws IOException{
        
       // PopupControl addNewCategoryPopUp = 
        
        Parent myParent = FXMLLoader.load(getClass().getResource("AddNewCategory.fxml"));
        Stage mystage = new Stage(StageStyle.UTILITY);
        mystage.setScene(new Scene(myParent));
        //Write code here to update the category View
        mystage.setOnCloseRequest(new EventHandler<WindowEvent>(){

            @Override
            public void handle(WindowEvent t) {
               getCategories();
            }
                    
        });
        mystage.show();
    
    }
    
    //add new unit on the fly
    public void handleAddNewUnitBtn() throws IOException{
    
        Parent myParent = FXMLLoader.load(getClass().getResource("AddNewUnit.fxml"));
        Stage mystage = new Stage(StageStyle.UTILITY);
        mystage.setScene(new Scene(myParent));
        //Write code here to update the category View
        mystage.setOnCloseRequest(new EventHandler<WindowEvent>(){

            @Override
            public void handle(WindowEvent t) {
               getAllUnits();
            }
                    
        });
        mystage.show();
    
    }
    
    //get all the units present in the system
    private void getAllUnits(){
    
         EntityManager em = EntityManagerHelper.getInstance().getEm();
        Query q = em.createNamedQuery("Units.findAll");
        List<Units> units = q.getResultList();
        
        itemUnitComboBox.getItems().clear();
        for(Units u:units){
            itemUnitComboBox.getItems().add(u.getId()-1, u.getUnitName());
        }
        itemUnitComboBox.getSelectionModel().clearSelection();
        itemUnitComboBox.setValue(itemUnitComboBox.getItems().get(0));
        itemRatePerUnitComboBox.getItems().clear();
        itemRatePerUnitComboBox.getItems().addAll(itemUnitComboBox.getItems());
        itemRatePerUnitComboBox.getSelectionModel().clearSelection();
       
        itemRatePerUnitComboBox.setValue(itemRatePerUnitComboBox.getItems().get(0));
    
    }
    
    //get all the categories of the items
    
    private void getCategories(){
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        Query q = em.createNamedQuery("ItemGroup.findAll");
        catList = new ArrayList(q.getResultList());        
        itemGroupComboBox.getItems().clear();
        for(ItemGroup ig : catList){
            itemGroupComboBox.getItems().add(ig.getItemGroupId()-1, ig.getItemGroupName());
        }
        
        itemGroupComboBox.getSelectionModel().clearSelection();     
        itemGroupComboBox.setValue(itemGroupComboBox.getItems().get(0));
    }
    

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert itemGroupComboBox != null : "fx:id=\"itemGroupComboBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemNameTextBox != null : "fx:id=\"itemNameTextBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemOpeningBalTextBox != null : "fx:id=\"itemOpeningBalTextBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemRatePerUnitComboBox != null : "fx:id=\"itemRatePerUnitComboBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemRateTextBox != null : "fx:id=\"itemRateTextBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemTotalValueTextBox != null : "fx:id=\"itemTotalValueTextBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemUnitComboBox != null : "fx:id=\"itemUnitComboBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemVatPercTextBox != null : "fx:id=\"itemVatPercTextBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert saveItemButton != null : "fx:id=\"saveItemButton\" was not injected: check your FXML file 'AddItem.fxml'.";

        // initialize your logic here: all @FXML variables will have been injected
        getCategories();
        getAllUnits();
    }

}
