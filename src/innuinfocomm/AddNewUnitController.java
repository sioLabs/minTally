/**
 * Sample Skeleton for "AddNewUnit.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

package innuinfocomm;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import pojos.Units;
import utils.EntityManagerHelper;


public class AddNewUnitController
    implements Initializable {

    @FXML //  fx:id="cmpndUnitUI"
    private AnchorPane cmpndUnitUI; 
    
    @FXML //  fx:id="simpleUnit"
    private AnchorPane simpleUnitUI; 
    
    @FXML //  fx:id="compundUnitRadioBtn"
    private RadioButton compundUnitRadioBtn; // Value injected by FXMLLoader

    @FXML //  fx:id="conversionTextBox"
    private TextField conversionTextBox; // Value injected by FXMLLoader

    @FXML //  fx:id="firstUnitTextBox"
    private TextField firstUnitTextBox; // Value injected by FXMLLoader

    @FXML //  fx:id="saveCompUnitBtn"
    private Button saveCompUnitBtn; // Value injected by FXMLLoader

    @FXML //  fx:id="saveSimpleUnitBtn"
    private Button saveSimpleUnitBtn; // Value injected by FXMLLoader

    @FXML //  fx:id="secondUnitComboBox"
    private ComboBox<String> secondUnitComboBox; // Value injected by FXMLLoader

    @FXML //  fx:id="simpleUnitNameTextBox"
    private TextField simpleUnitNameTextBox; // Value injected by FXMLLoader

    @FXML //  fx:id="simpleUnitRadioBtn"
    private RadioButton simpleUnitRadioBtn; // Value injected by FXMLLoader

    @FXML //  fx:id="successLabel"
    private Label successLabel; // Value injected by FXMLLoader


    // Handler for Button[fx:id="saveCompUnitBtn"] onAction
    public void handleSaveCompundUnitBtn(ActionEvent event) {
        // handle the event here
        if((firstUnitTextBox.getText()==null)  || firstUnitTextBox.getText().trim().equals("")){
            
        }else{
            Units unit = new Units();
            unit.setUnitType(2); // 2 for compound unit
            unit.setUnitName(firstUnitTextBox.getText());
            unit.setConv(Float.parseFloat(conversionTextBox.getText()));
            unit.setSecondUnit(secondUnitComboBox.getSelectionModel().getSelectedIndex()+1);           
            
            EntityManager em = EntityManagerHelper.getInstance().getEm();
            em.getTransaction().begin();
            em.persist(unit);
            em.getTransaction().commit();  
                    
        
        }
    }

    // Handler for Button[fx:id="saveSimpleUnitBtn"] onAction
    public void handleSaveSimpleUnitBtn(ActionEvent event) {
        // handle the event here
        if(simpleUnitNameTextBox.getText()!=null){
            Units unit = new Units();
            unit.setUnitName(simpleUnitNameTextBox.getText());
            unit.setConv(1);
            unit.setUnitType(1);
            
            EntityManager em = EntityManagerHelper.getInstance().getEm();
            em.getTransaction().begin();
            em.persist(unit);
            em.getTransaction().commit();
            successLabel.setVisible(true);     
            
        }

    }
    
    private void getAllUnits(){
    
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        Query q = em.createNamedQuery("Units.findAll");
        List<Units> units = q.getResultList();
        
        secondUnitComboBox.getItems().clear();
        for(Units u:units){
            secondUnitComboBox.getItems().add(u.getId()-1, u.getUnitName());
        }
        secondUnitComboBox.getSelectionModel().clearSelection();
    
    }

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert compundUnitRadioBtn != null : "fx:id=\"compundUnitRadioBtn\" was not injected: check your FXML file 'AddNewUnit.fxml'.";
        assert conversionTextBox != null : "fx:id=\"conversionTextBox\" was not injected: check your FXML file 'AddNewUnit.fxml'.";
        assert firstUnitTextBox != null : "fx:id=\"firstUnitTextBox\" was not injected: check your FXML file 'AddNewUnit.fxml'.";
        assert saveCompUnitBtn != null : "fx:id=\"saveCompUnitBtn\" was not injected: check your FXML file 'AddNewUnit.fxml'.";
        assert saveSimpleUnitBtn != null : "fx:id=\"saveSimpleUnitBtn\" was not injected: check your FXML file 'AddNewUnit.fxml'.";
        assert secondUnitComboBox != null : "fx:id=\"secondUnitComboBox\" was not injected: check your FXML file 'AddNewUnit.fxml'.";
        assert simpleUnitNameTextBox != null : "fx:id=\"simpleUnitNameTextBox\" was not injected: check your FXML file 'AddNewUnit.fxml'.";
        assert simpleUnitRadioBtn != null : "fx:id=\"simpleUnitRadioBtn\" was not injected: check your FXML file 'AddNewUnit.fxml'.";
        assert successLabel != null : "fx:id=\"successLabel\" was not injected: check your FXML file 'AddNewUnit.fxml'.";
        assert simpleUnitUI != null : "fx:id=\"simpleUnitUI\" was not injected: check your FXML file 'AddNewUnit.fxml'.";
        assert cmpndUnitUI != null : "fx:id=\"cmpndUnitUI\" was not injected: check your FXML file 'AddNewUnit.fxml'.";

        // initialize your logic here: all @FXML variables will have been injected
        getAllUnits();
        final ToggleGroup group = new ToggleGroup();
        //simpleUnitRadioBtn.setToggleGroup(group);
        //compundUnitRadioBtn.setToggleGroup(group);
        
        simpleUnitRadioBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
                                         

            @Override
            public void handle(MouseEvent t) {
                compundUnitRadioBtn.setSelected(false);
                cmpndUnitUI.setVisible(false);
                simpleUnitUI.setVisible(true);
            }
        });
        
        compundUnitRadioBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent t) {
                simpleUnitRadioBtn.setSelected(false);
                cmpndUnitUI.setVisible(true);
                simpleUnitUI.setVisible(false);
            }
        
        });
        
        
        
        

    }

}
