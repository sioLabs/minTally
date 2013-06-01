package innuinfocomm;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import utils.EntityManagerHelper;
import javax.persistence.EntityManager;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pojos.Ledger;
import pojos.LedgerGroup;
import utils.LedgerView;


public class ViewAllLedgerController {

    //object for database handling
    EntityManager em = EntityManagerHelper.getInstance().getEm();
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML //  fx:id="addressTextArea"
    private TextArea addressTextArea; // Value injected by FXMLLoader

    @FXML //  fx:id="cPersonTextBox"
    private TextField cPersonTextBox; // Value injected by FXMLLoader

    @FXML //  fx:id="contactNumberTextBox"
    private TextField contactNumberTextBox; // Value injected by FXMLLoader

    @FXML //  fx:id="cstTinTextBox"
    private TextField cstTinTextBox; // Value injected by FXMLLoader

    @FXML //  fx:id="deleteBtn"
    private Button deleteBtn; // Value injected by FXMLLoader

    @FXML
    private TableColumn<LedgerView,Float> ledgerBalColumn;

    @FXML
    private TableView<LedgerView> ledgerListTableView;

    @FXML
    private TableColumn<LedgerView, String> ledgerNameColumn;

    @FXML
    private TextField searchTextBox;
    
      @FXML //  fx:id="nameTextBox"
    private TextField nameTextBox; // Value injected by FXMLLoader
 
    @FXML //  fx:id="ledgerTypeComboBox"
    private ComboBox<String> ledgerTypeComboBox; // Value injected by FXMLLoader

    @FXML //  fx:id="saveBtn"
    private Button saveBtn; // Value injected by FXMLLoader

    @FXML //  fx:id="vatTinTextBox"
    private TextField vatTinTextBox; // Value injected by FXMLLoader
    
    @FXML //
    private Label successLabel;
    
    @FXML //
    private Label errorLabel;


    private  ObservableList<LedgerView> data = null;
    @FXML
    void initialize() {
         assert addressTextArea != null : "fx:id=\"addressTextArea\" was not injected: check your FXML file 'ViewAllLedger.fxml'.";
        assert cPersonTextBox != null : "fx:id=\"cPersonTextBox\" was not injected: check your FXML file 'ViewAllLedger.fxml'.";
        assert contactNumberTextBox != null : "fx:id=\"contactNumberTextBox\" was not injected: check your FXML file 'ViewAllLedger.fxml'.";
        assert cstTinTextBox != null : "fx:id=\"cstTinTextBox\" was not injected: check your FXML file 'ViewAllLedger.fxml'.";
        assert deleteBtn != null : "fx:id=\"deleteBtn\" was not injected: check your FXML file 'ViewAllLedger.fxml'.";
        assert ledgerBalColumn != null : "fx:id=\"ledgerBalColumn\" was not injected: check your FXML file 'ViewAllLedger.fxml'.";
        assert ledgerListTableView != null : "fx:id=\"ledgerListTableView\" was not injected: check your FXML file 'ViewAllLedger.fxml'.";
        assert ledgerNameColumn != null : "fx:id=\"ledgerNameColumn\" was not injected: check your FXML file 'ViewAllLedger.fxml'.";
        assert ledgerTypeComboBox != null : "fx:id=\"ledgerTypeComboBox\" was not injected: check your FXML file 'ViewAllLedger.fxml'.";
        assert nameTextBox != null : "fx:id=\"nameTextBox\" was not injected: check your FXML file 'ViewAllLedger.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'ViewAllLedger.fxml'.";
        assert searchTextBox != null : "fx:id=\"searchTextBox\" was not injected: check your FXML file 'ViewAllLedger.fxml'.";
        assert vatTinTextBox != null : "fx:id=\"vatTinTextBox\" was not injected: check your FXML file 'ViewAllLedger.fxml'.";
        
        //write code here to check if the dabase is working or not
        
        
       fillData();
       

    }
    
    private void fillData(){
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
        
        ledgerListTableView.setOnMouseClicked(new EventHandler<MouseEvent>(){
              @Override
             public void handle(MouseEvent t) {
                   LedgerView l  = ledgerListTableView.getSelectionModel().getSelectedItem();
                   if(null != l)
                       showLedgerData(l.getId());
             }
       });
    }
    
    
    @FXML
    private void handleSaveDetailsBtn(){
    
        LedgerView lv = ledgerListTableView.getSelectionModel().getSelectedItem();
        
        if(null != lv){
            int id = lv.getId();
            Query q = em.createNamedQuery("Ledger.findByLedgerId");
            q.setParameter("ledgerId", id);
            em.getTransaction().begin();
            Ledger l = (Ledger) q.getSingleResult();
            l.setLedgerName(nameTextBox.getText());
            l.setLedgerPersonName(cPersonTextBox.getText());
            l.setLedgerAddress(addressTextArea.getText());
            l.setLedgerContactNo(contactNumberTextBox.getText()); 
            l.setLedgerVatTin(vatTinTextBox.getText());
            l.setLedgerCstTin(cstTinTextBox.getText());
            l.setLedgerType(ledgerTypeComboBox.getSelectionModel().getSelectedIndex()+1);
            em.persist(l);
            em.getTransaction().commit();
        }
        
        successLabel.setVisible(true);
        
    }
    
     
    @FXML
    private void handleDeleteDetailsBtn(){
    
        LedgerView lv = ledgerListTableView.getSelectionModel().getSelectedItem();
        
        if(null != lv){
            int id = lv.getId();
            Query q = em.createNamedQuery("Ledger.findByLedgerId");
            q.setParameter("ledgerId", id);
            em.getTransaction().begin();
            Ledger l = (Ledger) q.getSingleResult();
            em.remove(l);
            em.getTransaction().commit();
            
            data.remove(ledgerListTableView.getSelectionModel().getSelectedIndex());
            ledgerListTableView.getSelectionModel().clearSelection();
        }
        
        resetDataFom();
       
    }
    
    
    ///helper functions 
    
    private void showLedgerData(int id) {
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        Query q = em.createNamedQuery("Ledger.findByLedgerId");
        q.setParameter("ledgerId", id);
        
        Ledger l= (Ledger) q.getSingleResult();
       
        nameTextBox.setText(l.getLedgerName());
        cPersonTextBox.setText(l.getLedgerPersonName());
        addressTextArea.setText(l.getLedgerAddress());
        contactNumberTextBox.setText(l.getLedgerContactNo());
        vatTinTextBox.setText(l.getLedgerVatTin());
        cstTinTextBox.setText(l.getLedgerCstTin());
        
        
        //Now get all the ledger types
        q = em.createNamedQuery("LedgerGroup.findAll");
        List<LedgerGroup> lgroups =  q.getResultList();
        ledgerTypeComboBox.getItems().clear();
        for(LedgerGroup lg : lgroups){
            ledgerTypeComboBox.getItems().add(lg.getId()-1, lg.getGroupName());           
        }
        
        ledgerTypeComboBox.setValue(lgroups.get(l.getLedgerType()-1).getGroupName());
               
                
   }
    
    
    
    @FXML
    private void handleLedgerSearchKeyPresses(){
        String text = searchTextBox.getText();
        
        if(text == null || text.trim().equals(""))
        {fillData();return;}
        
        text="%"+text+"%";
        Query q = em.createNamedQuery("Ledger.findLedgerNameLike");
        q.setParameter("ledgerName", text);
        List<Ledger> ledgers = q.getResultList();
        
        ArrayList<LedgerView> shortLedger =  new ArrayList<LedgerView>();
        for(Ledger l : ledgers){
            LedgerView lv = new LedgerView();
            lv.setName(l.getLedgerName());
            lv.setBal(l.getLedgerPresentBal());
            lv.setId(l.getLedgerId());
            shortLedger.add(lv);
        }
        
        //data.clear();
        data = FXCollections.observableArrayList(shortLedger);
       
        ledgerListTableView.setItems(data);
        ledgerListTableView.getSelectionModel().clearSelection();
        
        
    }
    
    private void resetDataFom(){
        successLabel.setVisible(false);
        errorLabel.setVisible(false);
        nameTextBox.setText(null);
        cPersonTextBox.setText(null);
        addressTextArea.setText(null);
        contactNumberTextBox.setText(null);
        vatTinTextBox.setText(null);
        cstTinTextBox.setText(null); 
        ledgerTypeComboBox.setValue("Select Type");
        
    }
    

}
