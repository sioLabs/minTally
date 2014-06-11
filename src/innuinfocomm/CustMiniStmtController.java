package innuinfocomm;

import java.sql.Connection;
import java.net.URL;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import pojos.Customer;
import utils.EntityManagerHelper;





public class CustMiniStmtController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Customer> custListView;

    @FXML
    private TextField searchCustTextField;
    
    
    @FXML
    private Button viewStmtBtn;


    @FXML
    void initialize() {
        assert custListView != null : "fx:id=\"custListView\" was not injected: check your FXML file 'CustMiniStmt.fxml'.";
        assert searchCustTextField != null : "fx:id=\"searchCustTextField\" was not injected: check your FXML file 'CustMiniStmt.fxml'.";
        
        start();

    }
    
    public void start(){
        
        searchCustTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

              @Override
              public void handle(KeyEvent t) {
                     if (KeyCode.TAB.equals(t.getCode())) {
                           showCustomers("");
                           return;
                              }
                 showCustomers(searchCustTextField.getText());
                 if(KeyCode.ENTER.equals(t.getCode())){
                     showCustomers(searchCustTextField.getText());
                 }
              }
              });
        
        
      
                
    }
                
      public void showCustomers(String text){
          
          custListView.getItems().clear();
          
           EntityManager em = EntityManagerHelper.getInstance().getEm();
           Query q  = null;
           if(text == null || text.length() == 0)
               q = em.createNamedQuery("Customer.findAll");
           else{
            text = text.trim();
            text = "%"+text+"%";
            q= em.createNamedQuery("Customer.findByCustomersNameLike");
                q.setParameter("companyName", text);
           }
           
            ArrayList<Customer> list = new ArrayList(q.getResultList());
          //  System.out.println(list.size()+ " items found");
            custListView.getItems().addAll(list);
         
          
      }
      
         @FXML
    void handleViewStmtbtn(ActionEvent event) {
        
        if(custListView.getSelectionModel().getSelectedIndex() < 0)
            return                ;
        
            
           Customer c = custListView.getSelectionModel().getSelectedItem();
                
                //now send this to jsp viewer
           
           
                
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/innuinfocomm?user=root&password=");
                    System.out.println("Filling report "  +c.getId());
                    HashMap p = new HashMap();
                    p.put("custId", c.getId());
                    JasperPrint jp = JasperFillManager.fillReport("src/reports/custMiniStmt.jasper", p, con);
                    JasperViewer.viewReport(jp);
                            
                    
                    
                }catch(Exception e){
                    e.printStackTrace();
                }
                
            }
        
    }
                
                
   