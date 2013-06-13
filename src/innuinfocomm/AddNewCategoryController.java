/**
 * Sample Skeleton for "AddNewCategory.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

package innuinfocomm;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import pojos.ItemGroup;
import pojos.Items;
import utils.EntityManagerHelper;


public class AddNewCategoryController
    implements Initializable {

    @FXML //  fx:id="cancelBtn"
    private Button cancelBtn; // Value injected by FXMLLoader

    @FXML //  fx:id="catNameTextBox"
    private TextField catNameTextBox; // Value injected by FXMLLoader

    @FXML //  fx:id="saveBtn"
    private Button saveBtn; // Value injected by FXMLLoader
    
    @FXML
    private Label successLabel;
    
    @FXML 
    private ComboBox<ItemGroup> itemGroupComboBox;
    
    @FXML
    private TextField itemSubGroupTextBox;
    
    @FXML
    private RadioButton itemGroupRadioBtn;
    
    @FXML
    private RadioButton itemSubGroupRadioBtn;
    
     @FXML
    private AnchorPane groupUI;
    
    @FXML
    private AnchorPane subGroupUI;


    // Handler for Button[fx:id="cancelBtn"] onAction
    public void handleCancelBtn(ActionEvent event) {
        // handle the event here
        
    }

    // Handler for Button[fx:id="saveBtn"] onAction
    public void handleSaveBtn(ActionEvent event) {
             // handle the event here
         if(null == catNameTextBox.getText() || catNameTextBox.getText().trim().equals(""))
         {  successLabel.setText("Error in adding category");
            successLabel.setStyle("-fx-color:red;");
            successLabel.setVisible(true);
            return;
         }
         
        String catName = catNameTextBox.getText();
        ItemGroup ig = new ItemGroup();
        ig.setItemGroupName(catName);
        ig.setItemGroupParent(0);
        try{
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        em.getTransaction().begin();
        em.persist(ig);
        em.getTransaction().commit();   
        }catch(Exception e){
            //TODO remove sout from here
            System.out.println("Error is saving new category");
        }
        
        successLabel.setStyle("-fx-color:green;");
        successLabel.setVisible(true);
        successLabel.setText("Category Added Successfully");
       
            
    }
    
    @FXML 
    public void handleSaveSubGroupBtn(){
        ItemGroup group = new ItemGroup();
        group.setItemGroupParent(itemGroupComboBox.getSelectionModel().getSelectedItem().getItemGroupId());
        group.setItemGroupName(itemSubGroupTextBox.getText());
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        em.getTransaction().begin();
        em.persist(group);
        em.getTransaction().commit();
        successLabel.setText("Category Saved Successfully");
    }
    
    private void fillGroupComboBox(){
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        Query q = em.createNamedQuery("ItemGroup.findByItemGroupParent");
        q.setParameter("itemGroupParent", 0);
        ArrayList<ItemGroup> groups = new ArrayList<ItemGroup>(q.getResultList());
        itemGroupComboBox.getItems().clear();
        itemGroupComboBox.getItems().addAll(groups);
        itemGroupComboBox.getSelectionModel().clearSelection();
        itemGroupComboBox.getSelectionModel().select(0);
    
    }

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
         assert cancelBtn != null : "fx:id=\"cancelBtn\" was not injected: check your FXML file 'AddNewCategory.fxml'.";
        assert catNameTextBox != null : "fx:id=\"catNameTextBox\" was not injected: check your FXML file 'AddNewCategory.fxml'.";
        assert groupUI != null : "fx:id=\"groupUI\" was not injected: check your FXML file 'AddNewCategory.fxml'.";
        assert itemGroupComboBox != null : "fx:id=\"itemGroupComboBox\" was not injected: check your FXML file 'AddNewCategory.fxml'.";
        assert itemGroupRadioBtn != null : "fx:id=\"itemGroupRadioBtn\" was not injected: check your FXML file 'AddNewCategory.fxml'.";
        assert itemSubGroupRadioBtn != null : "fx:id=\"itemSubGroupRadioBtn\" was not injected: check your FXML file 'AddNewCategory.fxml'.";
        assert itemSubGroupTextBox != null : "fx:id=\"itemSubGroupTextBox\" was not injected: check your FXML file 'AddNewCategory.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'AddNewCategory.fxml'.";
        assert subGroupUI != null : "fx:id=\"subGroupUI\" was not injected: check your FXML file 'AddNewCategory.fxml'.";
        assert successLabel != null : "fx:id=\"successLabel\" was not injected: check your FXML file 'AddNewCategory.fxml'.";
        // initialize your logic here: all @FXML variables will have been injected
        
        itemGroupRadioBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent t) {
                itemSubGroupRadioBtn.setSelected(false);
                subGroupUI.setVisible(false);
                groupUI.setVisible(true);
            }
        
        });
        
        itemSubGroupRadioBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent t) {
                fillGroupComboBox();
                itemGroupRadioBtn.setSelected(false);
                subGroupUI.setVisible(true);
                groupUI.setVisible(false);
            }
        
        
        });
        

    }
    


}
