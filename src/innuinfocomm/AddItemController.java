/**
 * Sample Skeleton for "AddItem.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import pojos.Item;
import utils.EntityManagerHelper;


public class AddItemController
    implements Initializable {

      @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML //  fx:id="itemGroupComboBox"
    private ComboBox<?> itemGroupComboBox; // Value injected by FXMLLoader

    @FXML //  fx:id="itemNameTextBox"
    private TextField itemNameTextBox; // Value injected by FXMLLoader

    @FXML //  fx:id="itemOpeningBalTextBox"
    private TextField itemOpeningBalTextBox; // Value injected by FXMLLoader

    @FXML //  fx:id="itemRatePerUnitComboBox"
    private ComboBox<?> itemRatePerUnitComboBox; // Value injected by FXMLLoader

    @FXML //  fx:id="itemRateTextBox"
    private TextField itemRateTextBox; // Value injected by FXMLLoader

    @FXML //  fx:id="itemTotalValueTextBox"
    private TextField itemTotalValueTextBox; // Value injected by FXMLLoader

    @FXML //  fx:id="itemUnitComboBox"
    private ComboBox<?> itemUnitComboBox; // Value injected by FXMLLoader

    @FXML //  fx:id="itemVatPercTextBox"
    private TextField itemVatPercTextBox; // Value injected by FXMLLoader

    @FXML //  fx:id="saveItemButton"
    private Button saveItemButton; // Value injected by FXMLLoader


    // Handler for Button[fx:id="saveItemButton"] onAction
    public void handleSaveItemButton(ActionEvent event) {
        
        System.out.println("Check if this wotking or not");
        // handle the event here
        Item item = new Item();
        String itemName = itemNameTextBox.getText();
        int under = itemGroupComboBox.getSelectionModel().getSelectedIndex()+1;
        int unit = itemUnitComboBox.getSelectionModel().getSelectedIndex()+1;
        float vat = Float.parseFloat(itemVatPercTextBox.getText());
        float open = Float.parseFloat(itemOpeningBalTextBox.getText());
        float itemRate = Float.parseFloat(itemRateTextBox.getText());
        int ratePerUnit = itemRatePerUnitComboBox.getSelectionModel().getSelectedIndex()+1;
        float totalValue = open*itemRate;
        
        try{
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
        }catch(Exception e){
            System.out.println("Some exception in saving the Item.");
        }
        
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
        
    }

}
