package innuinfocomm;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import pojos.Ledger;
import utils.LedgerView;


public class ViewAllLedgerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<LedgerView,Float> ledgerBalColumn;

    @FXML
    private TableView<LedgerView> ledgerListTableView;

    @FXML
    private TableColumn<LedgerView, String> ledgerNameColumn;

    @FXML
    private TextField searchTextBox;


    private  ObservableList<LedgerView> data = null;
    @FXML
    void initialize() {
        assert ledgerBalColumn != null : "fx:id=\"ledgerBalColumn\" was not injected: check your FXML file 'ViewAllLedger.fxml'.";
        assert ledgerListTableView != null : "fx:id=\"ledgerListTableView\" was not injected: check your FXML file 'ViewAllLedger.fxml'.";
        assert ledgerNameColumn != null : "fx:id=\"ledgerNameColumn\" was not injected: check your FXML file 'ViewAllLedger.fxml'.";
        assert searchTextBox != null : "fx:id=\"searchTextBox\" was not injected: check your FXML file 'ViewAllLedger.fxml'.";
        
        //write code here to check if the dabase is working or not
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InnuInfocommPU");
        EntityManager em  = emf.createEntityManager();
        
        Query query = em.createNamedQuery("Ledger.findAll");
        List<Ledger> ledgers = query.getResultList();
        ArrayList<LedgerView> shortLedger =  new ArrayList<LedgerView>();
        for(Ledger l : ledgers){
            LedgerView lv = new LedgerView();
            lv.setName(l.getLedgerName());
            lv.setBal(l.getLedgerPresentBal());
            lv.setId(l.getLedgerId());
            shortLedger.add(lv);
        }
        
        
        data = FXCollections.observableArrayList(shortLedger);
        ledgerNameColumn.setCellValueFactory(new PropertyValueFactory<LedgerView,String>("name"));
        ledgerBalColumn.setCellValueFactory(new PropertyValueFactory<LedgerView,Float>("bal"));
        ledgerListTableView.setItems(data);
        
        em.close();
        emf.close();

    }
    
    

}
