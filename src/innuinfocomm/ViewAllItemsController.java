package innuinfocomm;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pojos.Items;
import utils.EntityManagerHelper;
import utils.ItemView;



public class ViewAllItemsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TextField searchItemsTextBox;

    @FXML
    private TableView<Items> ItemsTableView;

    @FXML
    private TableColumn<Items, String> itemCodeTableColumn;

    @FXML
    private TableColumn<Items, Double> itemCurrentStockTableColumn;
    

    @FXML
    private TableColumn<Items, String> itemFUnitTableColumn;

    @FXML
    private TableColumn<Items, String> itemGroupTableColumn;

    @FXML
    private TableColumn<Items, String> itemNameTableColumn;

    
    private  ObservableList<Items> data = null;

    private void fillData(){
        
        ArrayList<ItemView> shortView = new ArrayList<ItemView>();
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        Query q = em.createNamedQuery("Items.findAll");
        ArrayList<Items> list = new ArrayList<Items>(q.getResultList());
        
        
        data = FXCollections.observableArrayList(list);
       //ledgerNameColumn.setCellValueFactory(new PropertyValueFactory<LedgerView,String>("name"));
       itemNameTableColumn.setCellValueFactory(new PropertyValueFactory<Items,String>("itemName"));
       itemGroupTableColumn.setCellValueFactory(new PropertyValueFactory<Items,String>("itemGroup"));
       itemFUnitTableColumn.setCellValueFactory(new PropertyValueFactory<Items,String>("itemFirstUnit"));
       itemCodeTableColumn.setCellValueFactory(new PropertyValueFactory<Items,String>("itemCode"));
       itemCurrentStockTableColumn.setCellValueFactory(new PropertyValueFactory<Items,Double>("itemOpenStock"));
       
       ItemsTableView.setItems(data);
    }
    
    public void handleKeyInSearchBox(){
    
        String text = searchItemsTextBox.getText();
        
         if(text == null || text.trim().equals(""))
        {fillData();return;}
         
         text="%"+text+"%";
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        Query q = em.createNamedQuery("Items.findByItemsNameLike");
        q.setParameter("itemName", text);
        ArrayList<Items> shortItems = new ArrayList<Items>(q.getResultList());
           
        
        
        
        
        data = FXCollections.observableArrayList(shortItems);
       
        ItemsTableView.setItems(data);
        ItemsTableView.getSelectionModel().clearSelection();
    
    }
    
    @FXML
    void initialize() {
        assert ItemsTableView != null : "fx:id=\"ItemsTableView\" was not injected: check your FXML file 'ViewAllItems.fxml'.";
        assert itemCodeTableColumn != null : "fx:id=\"itemCodeTableColumn\" was not injected: check your FXML file 'ViewAllItems.fxml'.";
        assert itemCurrentStockTableColumn != null : "fx:id=\"itemCurrentStockTableColumn\" was not injected: check your FXML file 'ViewAllItems.fxml'.";
        assert itemFUnitTableColumn != null : "fx:id=\"itemFUnitTableColumn\" was not injected: check your FXML file 'ViewAllItems.fxml'.";
        assert itemGroupTableColumn != null : "fx:id=\"itemGroupTableColumn\" was not injected: check your FXML file 'ViewAllItems.fxml'.";
        assert itemNameTableColumn != null : "fx:id=\"itemNameTableColumn\" was not injected: check your FXML file 'ViewAllItems.fxml'.";

        fillData();
    }

}
