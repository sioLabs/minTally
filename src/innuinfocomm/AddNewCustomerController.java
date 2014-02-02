package innuinfocomm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import pojos.Customer;
import utils.EntityManagerHelper;


public class AddNewCustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox addCheckBox;

    @FXML
    private TextArea addTextBox;

    @FXML
    private TextField balanceTextBox;

    @FXML
    private TextArea delAddTextBox;

    @FXML
    private Label errLabel;

    @FXML
    private TextField licenseTextBox;

    @FXML
    private TextField mobileTextBox;

    @FXML
    private TextField nameTextBox;

    @FXML
    private TextField phoneTextBox;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Label successLabel;


    @FXML
    void handleResetBtn(ActionEvent event) {
    }

    @FXML
    void handleSaveBtn(ActionEvent event) {
        if(verify()){
            
            Customer c = new Customer();
            c.setCompanyName(nameTextBox.getText());
            
            String balS = balanceTextBox.getText();
            if(balS != null || balS.length() > 0){
                float bal = Float.parseFloat(balS);
                c.setBalance(bal);
            }
            
            
            c.setDelAddress(delAddTextBox.getText());
            
            c.setLicenceNo(licenseTextBox.getText());
            
            c.setMobile(mobileTextBox.getText());
            
            c.setPhone(phoneTextBox.getText());
            
            c.setSiteAddress(addTextBox.getText());
            
              EntityManager em = EntityManagerHelper.getInstance().getEm();
        boolean flag = false;
         try{
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
         }catch(Exception e){
            //errLabel.getText().concat("  Error in saving the Item. Please contact the developer");
            errLabel.setVisible(true);
            flag = true;
         }
          
         if(!flag){
             handleResetBtn(event);
             successLabel.setVisible(true);
         }
        }
        
        
    }

    /*
     * check box code to copy the address from the above address in FXMl
     */
    @FXML
    void makeSameDelAdd(ActionEvent event) {
        if(addCheckBox.isSelected()){
            delAddTextBox.setText(addTextBox.getText());
            delAddTextBox.setEditable(false);                   
        } 
        
    }

    @FXML
    void initialize() {
        assert addCheckBox != null : "fx:id=\"addCheckBox\" was not injected: check your FXML file 'AddNewCustomer.fxml'.";
        assert addTextBox != null : "fx:id=\"addTextBox\" was not injected: check your FXML file 'AddNewCustomer.fxml'.";
        assert balanceTextBox != null : "fx:id=\"balanceTextBox\" was not injected: check your FXML file 'AddNewCustomer.fxml'.";
        assert delAddTextBox != null : "fx:id=\"delAddTextBox\" was not injected: check your FXML file 'AddNewCustomer.fxml'.";
        assert errLabel != null : "fx:id=\"errLabel\" was not injected: check your FXML file 'AddNewCustomer.fxml'.";
        assert licenseTextBox != null : "fx:id=\"licenseTextBox\" was not injected: check your FXML file 'AddNewCustomer.fxml'.";
        assert mobileTextBox != null : "fx:id=\"mobileTextBox\" was not injected: check your FXML file 'AddNewCustomer.fxml'.";
        assert nameTextBox != null : "fx:id=\"nameTextBox\" was not injected: check your FXML file 'AddNewCustomer.fxml'.";
        assert phoneTextBox != null : "fx:id=\"phoneTextBox\" was not injected: check your FXML file 'AddNewCustomer.fxml'.";
        assert resetBtn != null : "fx:id=\"resetBtn\" was not injected: check your FXML file 'AddNewCustomer.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'AddNewCustomer.fxml'.";
        assert successLabel != null : "fx:id=\"successLabel\" was not injected: check your FXML file 'AddNewCustomer.fxml'.";

    }
    
    private boolean verify(){
        
        if(nameTextBox.getText().trim().length() < 1)
            return false;
        if(addTextBox.getText().trim().length() < 1)
            return false;
        if(mobileTextBox.getText().trim().length() < 1)
            return false;
        if(licenseTextBox.getText().trim().length() < 1)
            return false;
        if(balanceTextBox.getText().trim().length() < 1)
            return false;
        
        return true;
    }

}
