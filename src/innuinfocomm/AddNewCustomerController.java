package innuinfocomm;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import pojos.Customer;
import utils.EntityManagerHelper;


public class AddNewCustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    
    @FXML
    private TableView<Customer> customerListTableView;
    
    @FXML
    private TextField searchCustomerTextBox;

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
    private TableColumn<Customer,String> customerCol;
     
     
    @FXML
    private Button deleteBtn;

    Customer selectedCustomer = null;
    boolean customerClicked = false;
    
    ObservableList<Customer> data = FXCollections.observableArrayList();

    boolean dataFromTable = false;
    
    @FXML
    void handleResetBtn(ActionEvent event) {
        nameTextBox.setText(null);
        addTextBox.setText(null);
        licenseTextBox.setText(null);
        phoneTextBox.setText(null);
        mobileTextBox.setText(null);
        delAddTextBox.setText(null);
        balanceTextBox.setText(null);
        addCheckBox.setSelected(false);
        successLabel.setVisible(false);
        errLabel.setVisible(false);
        customerClicked = false;
        selectedCustomer = null;
        deleteBtn.setDisable(true);          
    }

    
    @FXML
    void handleDeleteBtn(ActionEvent event) {
        if(!customerClicked)
            return;
        
        try{
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        Customer c = em.find(Customer.class, selectedCustomer.getId());
        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();
        handleResetBtn(event);
        errLabel.setVisible(false);
        successLabel.setText("Customer Deleted Successfully");
        successLabel.setVisible(true);
        }catch(Exception e){
            successLabel.setVisible(false);
            errLabel.setText("Error in deleting customer.");
            errLabel.setVisible(true);
        }
        
    }
    
    @FXML
    void handleSaveBtn(ActionEvent event) {
        if(verify()){
            
            Customer c ;
            if(dataFromTable){
                c = customerListTableView.getSelectionModel().getSelectedItem();
            }else{
                 c = new Customer();
            }
            
            
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

        deleteBtn.setDisable(true);        
        customerCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("companyName"));
        customerListTableView.setItems(data);
    }
    
    private boolean verify(){
        errLabel.setVisible(true);
        if(nameTextBox.getText().trim().length() < 1)
        {
            errLabel.setText("Name cannot be empty");
            return false;
        }
        if(addTextBox.getText().trim().length() < 1)
        {
            errLabel.setText("Address cannot be empty");
            return false;
        }
        if(mobileTextBox.getText().trim().length() < 1)
        {
            errLabel.setText("Mobile number cannot be empty");
            return false;
        }
        if(licenseTextBox.getText().trim().length() < 1)
        {
            errLabel.setText("License Number cannot be empty");
            return false;
        }
        if(balanceTextBox.getText().trim().length() < 1)
        {
            errLabel.setText("Opening balance cannot be empty");
            return false;
        }
            
        
        return true;
    }
    
      @FXML
    void handleCustomerClicked(MouseEvent event) {
          deleteBtn.setDisable(false);
          customerClicked  = true;
          Customer c = customerListTableView.getSelectionModel().getSelectedItem();
          selectedCustomer = c;
          fillCustomerDetails(c);
          dataFromTable  = true;
          
    }
      
      
    @FXML
    void searchCustomers(KeyEvent event) {
        data.clear();
        String text = searchCustomerTextBox.getText().trim();
        text = "%"+text+"%";
            EntityManager em = EntityManagerHelper.getInstance().getEm();
            Query q  = null;
            if(text.length() ==0 )
                q = em.createNamedQuery("Customer.findAll");
            else
            {q= em.createNamedQuery("Customer.findByCustomersNameLike");
                q.setParameter("companyName", text);
            }
            ArrayList<Customer> list = new ArrayList(q.getResultList());
            System.out.println(list.size()+ " items found");
            data.addAll(list);     
            
        
    }
    
    private void fillCustomerDetails(Customer c){
        
        nameTextBox.setText(c.getCompanyName());
        addTextBox.setText(c.getSiteAddress());
        licenseTextBox.setText(c.getLicenceNo());
        phoneTextBox.setText(c.getPhone());
        mobileTextBox.setText(c.getMobile());
        delAddTextBox.setText(c.getDelAddress());
        balanceTextBox.setText(""+c.getBalance());
        
    }


}
