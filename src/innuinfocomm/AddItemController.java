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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PopupControl;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import pojos.Items;
import pojos.ItemGroup;
import pojos.Units;
import utils.EntityManagerHelper;


public class AddItemController
    implements Initializable {

      @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addNewCategoryBtn;

    @FXML
    private Button addNewUnitBtn;

    @FXML
    private ComboBox<ItemGroup> itemGroupComboBox;

    @FXML
    private TextField itemNameTextBox;

    @FXML
    private TextField itemOpeningBalTextBox;

    @FXML
    private TextField itemRackNoTextField;

    @FXML
    private TextField itemRateTextBox;

    @FXML
    private ComboBox<Units> itemSecondUnitComboBox;

    @FXML
    private ComboBox<ItemGroup> itemSubGroupComboBox;

    @FXML
    private TextField itemTotalValueTextBox;

    @FXML
    private ComboBox<Units> itemUnitComboBox;

    @FXML
    private TextField itemVatPercTextBox;

    @FXML
    private Button saveItemButton;

    @FXML
    private TextField itemCodeTextBox;
    
   @FXML
    private Label errorLabel;

       @FXML
    private Label successLabel;

    // Handler for Button[fx:id="saveItemButton"] onAction
    public void handleSaveItemButton(ActionEvent event) {
        // handle the event here
         try{
        // handle the event here
        Items item = new Items();
        String itemName = itemNameTextBox.getText();
        item.setItemName(itemName);
        ItemGroup group = itemGroupComboBox.getSelectionModel().getSelectedItem();
           
        
        item.setItemGroup(group);
        Units funit = itemUnitComboBox.getSelectionModel().getSelectedItem();
        item.setItemFirstUnit(funit);
        
        Units sunit = itemSecondUnitComboBox.getSelectionModel().getSelectedItem();
        item.setItemSecondUnit(sunit);
        
        double vat = Double.parseDouble(itemVatPercTextBox.getText());
        item.setItemVatPerc(vat);
        double open = Double.parseDouble(itemOpeningBalTextBox.getText());
        item.setItemOpenStock(open);
        double itemRate = Float.parseFloat(itemRateTextBox.getText());
        item.setItemRate(itemRate);
        item.setItemCode(itemCodeTextBox.getText().trim());
        
        
               
       
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
        successLabel.setVisible(true);
        errorLabel.setVisible(false);
        }catch(Exception e){
            errorLabel.setVisible(true);
            successLabel.setVisible(false);
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
        ArrayList<Units> units = new ArrayList<Units>(q.getResultList());
        
        itemUnitComboBox.getItems().clear();
        itemUnitComboBox.getItems().addAll(units);
        
        itemUnitComboBox.getSelectionModel().clearSelection();
        itemUnitComboBox.setValue(itemUnitComboBox.getItems().get(0));
        itemSecondUnitComboBox.getItems().clear();
        itemSecondUnitComboBox.getItems().addAll(units);
        itemSecondUnitComboBox.getSelectionModel().clearSelection();
        itemSecondUnitComboBox.setValue(itemSecondUnitComboBox.getItems().get(0));
        //itemRatePerUnitComboBox.getItems().clear();
        //itemRatePerUnitComboBox.getItems().addAll(itemUnitComboBox.getItems());
        //itemRatePerUnitComboBox.getSelectionModel().clearSelection();
       
        //itemRatePerUnitComboBox.setValue(itemRatePerUnitComboBox.getItems().get(0));
    
    }
    
    //get all the categories of the items
    
    private void getCategories(){
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        Query q = em.createNamedQuery("ItemGroup.findByItemGroupParent");
        q.setParameter("itemGroupParent", 0);
        ArrayList<ItemGroup> catList = new ArrayList(q.getResultList());        
        
        if(catList.size() < 1){
            itemGroupComboBox.getItems().clear();
            return;
        }
        itemGroupComboBox.getItems().clear();
        itemGroupComboBox.getItems().addAll(catList);
        itemGroupComboBox.getSelectionModel().clearSelection();     
        itemGroupComboBox.setValue(itemGroupComboBox.getItems().get(0));
   }
    
    private void getSubGroup(){
        
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        
        if(itemGroupComboBox.getSelectionModel().getSelectedItem() == null){
            itemSubGroupComboBox.getItems().clear();
            return;
        }
        int parentId = itemGroupComboBox.getSelectionModel().getSelectedItem().getItemGroupId();
        Query q = em.createNamedQuery("ItemGroup.findByItemGroupParent");
        q.setParameter("itemGroupParent", parentId);
        
        ArrayList<ItemGroup> subGroups = new ArrayList<ItemGroup>(q.getResultList());
        if(subGroups.size()<1)
        {
            itemSubGroupComboBox.getItems().clear();
            return;
        }
        
        itemSubGroupComboBox.getItems().clear();
        itemSubGroupComboBox.getItems().addAll(subGroups);
        itemSubGroupComboBox.getSelectionModel().clearSelection();
        itemSubGroupComboBox.setValue(itemSubGroupComboBox.getItems().get(0));
        
    }
    

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert itemGroupComboBox != null : "fx:id=\"itemGroupComboBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemNameTextBox != null : "fx:id=\"itemNameTextBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemOpeningBalTextBox != null : "fx:id=\"itemOpeningBalTextBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemRateTextBox != null : "fx:id=\"itemRateTextBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemTotalValueTextBox != null : "fx:id=\"itemTotalValueTextBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemUnitComboBox != null : "fx:id=\"itemUnitComboBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert itemVatPercTextBox != null : "fx:id=\"itemVatPercTextBox\" was not injected: check your FXML file 'AddItem.fxml'.";
        assert saveItemButton != null : "fx:id=\"saveItemButton\" was not injected: check your FXML file 'AddItem.fxml'.";

        // initialize your logic here: all @FXML variables will have been injected
        getCategories();
        getAllUnits();
        itemGroupComboBox.valueProperty().addListener(new ChangeListener<ItemGroup>(){

            @Override
            public void changed(ObservableValue<? extends ItemGroup> ov, ItemGroup t, ItemGroup t1) {
               getSubGroup();
            }
        
        
        });
        
    }

}
