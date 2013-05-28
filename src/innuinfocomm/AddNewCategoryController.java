/**
 * Sample Skeleton for "AddNewCategory.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

package innuinfocomm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import pojos.ItemGroup;
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

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert cancelBtn != null : "fx:id=\"cancelBtn\" was not injected: check your FXML file 'AddNewCategory.fxml'.";
        assert catNameTextBox != null : "fx:id=\"catNameTextBox\" was not injected: check your FXML file 'AddNewCategory.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'AddNewCategory.fxml'.";
        assert successLabel != null :"fx:id=\"successLabel\" was not injected: check your FXML file 'AddNewCategory.fxml'.";
        // initialize your logic here: all @FXML variables will have been injected

    }

}
