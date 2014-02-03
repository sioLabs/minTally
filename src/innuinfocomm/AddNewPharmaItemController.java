package innuinfocomm;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import pojos.Customer;
import pojos.ItemsPharma;
import utils.EntityManagerHelper;


public class AddNewPharmaItemController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField DMTextBox;

    @FXML
    private Button addNewItemButton;

    @FXML
    private TextField batchTextBox;

    @FXML
    private TextArea descTextArea;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField expDateTextBox;

    @FXML
    private TextField itemIdTextBox;

    @FXML
    private TextField makeTextBox;

    @FXML
    private TextField mrpTextBox;

    @FXML
    private TextField packTextBox;

    @FXML
    private TextField rateTextBox;
    
     @FXML
    private Button resetBtn;
     
     @FXML
     private Label successLabel;
     
    @FXML
    private TextField stockTextBox;
    
    
    @FXML
    private ListView<ItemsPharma> itemsListView;
    
    
    @FXML
    private TextField searchItemsTextBox;
    
    ObservableList<ItemsPharma> data = FXCollections.observableArrayList();

      private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
      private int nextId;

    @FXML
    void handleAddButtonClick(ActionEvent event) throws ParseException {
        //handle addItemhere
        ItemsPharma item = new ItemsPharma();
        
        //get the Item id.from the database
        
        
        
        String batch = batchTextBox.getText();
        item.setBatch(batch);
        
        String dm = DMTextBox.getText();
        item.setDM(dm);
        
        String make = makeTextBox.getText();
        item.setMake(make);
        
        String expDString = expDateTextBox.getText();
        Date eDate = dateFormatter.parse(expDString);
        item.setExpDate(eDate);
        
        
        String packS = packTextBox.getText();
        //int pack = Integer.parseInt(packS);
        item.setPack(packS);
        
        String mrpS = mrpTextBox.getText();
        float mrp = Float.parseFloat(mrpS);
        item.setMrp(mrp);
        
        String rateS = rateTextBox.getText();
        float rate = Float.parseFloat(rateS)*mrp;
        item.setRateFraction(rate);
                
         String desc= descTextArea.getText();
         item.setDesc(desc);
                 
         String stockS = stockTextBox.getText();
         if(stockS!=null){
             item.setStockStrips(Integer.parseInt(stockS));
         }
         
         EntityManager em = EntityManagerHelper.getInstance().getEm();
        boolean flag = false;
         try{
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
         }catch(Exception e){
            errorLabel.getText().concat("  Error in saving the Item. Please contact the developer");
            errorLabel.setVisible(true);
            flag = true;
         }
                
         if(!flag)
         {    handleResetBtn(event);
             successLabel.setVisible(true);
         }
        
    }

    
     @FXML
    void handleResetBtn(ActionEvent event) {
         setNextId();
         rateTextBox.setText(null);
         packTextBox.setText(null);
         mrpTextBox.setText(null);
         makeTextBox.setText(null);
         itemIdTextBox.setText(null);
         expDateTextBox.setText(null);
         errorLabel.setVisible(false);
         successLabel.setVisible(false);
         descTextArea.setText(null);
         batchTextBox.setText(null);
         DMTextBox.setText(null);
         stockTextBox.setText(null);
         
         
         
    }
    
    @FXML
    void initialize() {
        assert DMTextBox != null : "fx:id=\"DMTextBox\" was not injected: check your FXML file 'AddNewPharmaItem.fxml'.";
        assert addNewItemButton != null : "fx:id=\"addNewItemButton\" was not injected: check your FXML file 'AddNewPharmaItem.fxml'.";
        assert batchTextBox != null : "fx:id=\"batchTextBox\" was not injected: check your FXML file 'AddNewPharmaItem.fxml'.";
        assert descTextArea != null : "fx:id=\"descTextArea\" was not injected: check your FXML file 'AddNewPharmaItem.fxml'.";
        assert errorLabel != null : "fx:id=\"errorLabel\" was not injected: check your FXML file 'AddNewPharmaItem.fxml'.";
        assert expDateTextBox != null : "fx:id=\"expDateTextBox\" was not injected: check your FXML file 'AddNewPharmaItem.fxml'.";
        assert itemIdTextBox != null : "fx:id=\"itemIdTextBox\" was not injected: check your FXML file 'AddNewPharmaItem.fxml'.";
        assert makeTextBox != null : "fx:id=\"makeTextBox\" was not injected: check your FXML file 'AddNewPharmaItem.fxml'.";
        assert mrpTextBox != null : "fx:id=\"mrpTextBox\" was not injected: check your FXML file 'AddNewPharmaItem.fxml'.";
        assert packTextBox != null : "fx:id=\"packTextBox\" was not injected: check your FXML file 'AddNewPharmaItem.fxml'.";
        assert rateTextBox != null : "fx:id=\"rateTextBox\" was not injected: check your FXML file 'AddNewPharmaItem.fxml'.";
        assert stockTextBox != null : "fx:id=\"rateTextBox\" was not injected: check your FXML file 'AddNewPharmaItem.fxml'.";
        setNextId();
        
        itemsListView.setItems(data);
    }
    
    private void setNextId(){
                EntityManager em = EntityManagerHelper.getInstance().getEm();
        Query q = em.createNamedQuery("ItemsPharma.findNextId");
      
        
        if(q.getSingleResult() == null )
            nextId = 1;
        else
        {
            nextId = Integer.parseInt(""+q.getSingleResult());
            nextId++;
        }
        
        itemIdTextBox.setText(""+nextId);

}

    
      @FXML
    void handleItemsClicked(MouseEvent event) {
    }
      
          @FXML
    void searchItems(KeyEvent event) {
                    data.clear();
        String text = searchItemsTextBox.getText().trim();
        text = "%"+text+"%";
            EntityManager em = EntityManagerHelper.getInstance().getEm();
            Query q  = null;
            if(text.length() ==0 )
                q = em.createNamedQuery("ItemsPharma.findAll");
            else
            {q= em.createNamedQuery("Items.findByItemsPharmaNameLike");
                q.setParameter("description", text);
            }
            ArrayList<ItemsPharma> list = new ArrayList(q.getResultList());
            System.out.println(list.size()+ " items found");
            data.addAll(list);     
              
    }

}

