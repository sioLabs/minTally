package innuinfocomm;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private TextField vatPercTextBox;
     
    @FXML
    private TextField stockTextBox;
    
    
    @FXML
    private ListView<ItemsPharma> itemsListView;
    
    
    @FXML
    private TextField searchItemsTextBox;
    
    @FXML
    private Button updateItemBtn;
    
    @FXML
    private Button deleteItemBtn;
    
    ObservableList<ItemsPharma> data = FXCollections.observableArrayList();

      private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
      private int nextId;
      
      private boolean itemClicked  =false;
    
    @FXML
    void handleAddButtonClick(ActionEvent event) throws ParseException {
        //handle addItemhere
    
        ItemsPharma item = createItemFromForm();
        
        if( null == item){
            return;
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
    
    private ItemsPharma createItemFromForm() throws ParseException{
        if(!verify())
            return null;
        
          ItemsPharma item = new ItemsPharma();
        
           
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
        
        String vatPercS = vatPercTextBox.getText();
        float vatPerc = Float.parseFloat(vatPercS);
        item.setVat(vatPerc);
                
                
         String desc= descTextArea.getText();
         item.setDesc(desc);
                 
         String stockS = stockTextBox.getText();
         if(stockS!=null){
             item.setStockStrips(Integer.parseInt(stockS));
         }
         
         return item;
    }

    private boolean verify(){
        errorLabel.setVisible(true);
        if(makeTextBox.getText().trim().length() < 1){
            errorLabel.setText("Make cannot be empty");
            return false;
        }
        if(batchTextBox.getText().trim().length() < 1){
            errorLabel.setText("Batch Number can not be empty");
            return false;
        }
        
        if(expDateTextBox.getText().trim().length() != 10){
            errorLabel.setText("Invalid Date format. Please Enter date in dd/mm/yyyy format");
            return false;           
        }
        
        if(packTextBox.getText().trim().length()<1){
            errorLabel.setText("Pack Size cannot be empty");
            return false;
        }
        
        if(stockTextBox.getText().trim().length() < 1){
            errorLabel.setText("Stock cannot be empty. Please enter a value");
            return false;
        }
        
        if(descTextArea.getText().trim().length() < 2){
            errorLabel.setText("Please enter the name of the item in the description.");
            return false;
        }
        
        if(mrpTextBox.getText().trim().length() < 1){
            errorLabel.setText("Enter the MRP of the item");
            return false;
        }
        
        if(vatPercTextBox.getText().trim().length() < 1){
            errorLabel.setText("Enter the vat Percentage");
            return false;
        }
        
        
        return true;
    }
    
     @FXML
    void handleResetBtn(ActionEvent event) {
         itemClicked = false;
         setNextId();
         rateTextBox.setText(null);
         packTextBox.setText(null);
         mrpTextBox.setText(null);
         makeTextBox.setText(null);
        
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
          addNewItemButton.setDisable(true);
          deleteItemBtn.setDisable(false);
          updateItemBtn.setDisable(false);
          successLabel.setVisible(false);
          errorLabel.setVisible(false);
          itemClicked = true;
          ItemsPharma item = itemsListView.getSelectionModel().getSelectedItem();
          fillForm(item);
          
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

    private void fillForm(ItemsPharma item) {
        
        itemIdTextBox.setText(item.getId()+"");
        DMTextBox.setText(item.getDM());
        makeTextBox.setText(item.getMake());
        batchTextBox.setText(item.getBatch());
        expDateTextBox.setText(item.getExpDateString()+"");
        packTextBox.setText(item.getPack());
        stockTextBox.setText(item.getStockStrips()+"");
        descTextArea.setText(item.getDesc());
        mrpTextBox.setText(item.getMrp()+"");
        rateTextBox.setText(item.getRateFraction()+"");
    }
    
      @FXML
    void handleDeleteItemBtn(ActionEvent event) {
          if(!itemClicked)
              return;
          
        
        try {
            //now deletre the entity
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        ItemsPharma item = em.find(ItemsPharma.class, Integer.parseInt(itemIdTextBox.getText()));
        em.getTransaction().begin();
        em.remove(item);
        em.getTransaction().commit();
        handleResetBtn(event);
        successLabel.setText("Item Deleted Successfully");
        successLabel.setVisible(true);
        
        } catch (Exception ex) {
            errorLabel.setVisible(true);
            errorLabel.setText("Error in Deleting Item");
            Logger.getLogger(AddNewPharmaItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
         @FXML
    void handleUpdateBtn(ActionEvent event) {
            
             if(!itemClicked)
              return;
             
             if(!verify())
                 return;
          
             errorLabel.setVisible(false);
             ItemsPharma item = null;
        try {
            item =  createItemFromForm();
            item.setId(Integer.parseInt(itemIdTextBox.getText()));
            EntityManager em = EntityManagerHelper.getInstance().getEm();
            em.getTransaction().begin();
            em.merge(item);
            em.getTransaction().commit();
           
            successLabel.setText("Item Updated Successfully");
            successLabel.setVisible(true);
           
        } catch (Exception ex) {
            errorLabel.setVisible(true);
            errorLabel.setText("Error in Deleting Item");
            Logger.getLogger(AddNewPharmaItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
     
        
    }


}

