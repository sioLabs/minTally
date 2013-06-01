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
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import pojos.Item;
import utils.EntityManagerHelper;
import utils.ItemView;
import utils.LedgerView;


public class ViewAllItemsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ItemView> ItemsTableView;

    @FXML
    private TableColumn<ItemView, Integer> itemCodeTableColumn;

    @FXML
    private TableColumn<ItemView, Float> itemCurrentStockTableColumn;

    @FXML
    private TableColumn<ItemView, Integer> itemFUnitTableColumn;

    @FXML
    private TableColumn<ItemView, String> itemGroupTableColumn;

    @FXML
    private TableColumn<ItemView, String> itemNameTableColumn;

    
    private  ObservableList<ItemView> data = null;

    private void fillData(){
        
        ArrayList<ItemView> shortView = new ArrayList<ItemView>();
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        Query q = em.createNamedQuery("Item.findAll");
        List<Item> list = q.getResultList();
        for(Item i:list){
        
            ItemView iV = new ItemView();
            iV.setItemId(i.getItemId());
            iV.setItemName(i.getItemName());
            iV.setUnit(i.getItemUnit1());
            iV.setItemStock(i.getItePresentStock());
            iV.setItemGroup(i.getItemGroup().getItemGroupName());
            shortView.add(iV);
        }
        
        data = FXCollections.observableArrayList(shortView);
       //ledgerNameColumn.setCellValueFactory(new PropertyValueFactory<LedgerView,String>("name"));
       itemNameTableColumn.setCellValueFactory(new PropertyValueFactory<ItemView,String>("itemName"));
       itemGroupTableColumn.setCellValueFactory(new PropertyValueFactory<ItemView,String>("itemGroup"));
       itemFUnitTableColumn.setCellValueFactory(new PropertyValueFactory<ItemView,Integer>("unit"));
       itemCodeTableColumn.setCellValueFactory(new PropertyValueFactory<ItemView,Integer>("itemId"));
       itemCurrentStockTableColumn.setCellValueFactory(new PropertyValueFactory<ItemView,Float>("itemStock"));
       
       ItemsTableView.setItems(data);
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
