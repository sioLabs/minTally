package innuinfocomm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;


public class MainWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TitledPane VoucherMgmt;

    @FXML
    private Button addLedgerButton;

    
    @FXML
    private Button AddItemBtn;
    
    @FXML
    private Button addNewLedgerType;

    @FXML
    private TitledPane ledgerMgmt;

    @FXML
    private AnchorPane leftMenuHolder;

    @FXML
    private AnchorPane mainContentHolder;

    @FXML
    private TabPane mainContentTabPane;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private MenuBar topMenuBar;

    @FXML
    private Button viewAllLedgerButton;
    
    @FXML
    private Button createNewSaleBillBtn;

    @FXML
    private TitledPane x1;
    
       @FXML
    private Button viewAllItemsBtn;


    @FXML
    void handleAddLedgerButton(ActionEvent event) throws IOException {
        
        Parent rootNew = FXMLLoader.load(getClass().getResource("AddLedger.fxml"));
        Tab tab = new Tab("Add a New Ledger");
        tab.setContent(rootNew);
        mainContentTabPane.getTabs().add(tab);
        
        
    }

    @FXML
    void handleAddNewLedgerType(ActionEvent event) {
    }

    @FXML
    void handleViewAllLedgerButton(ActionEvent event) throws IOException {
        Parent rootNew = FXMLLoader.load(getClass().getResource("ViewAllLedger.fxml"));
        Tab tab = new Tab("View All Ledger");
        tab.setContent(rootNew);
        mainContentTabPane.getTabs().add(tab);
        
    }
    
    @FXML 
    void handleAddItemBtn() throws IOException{
        Parent rootNew = FXMLLoader.load(getClass().getResource("AddItem.fxml"));
        Tab tab = new Tab("Add an Item");
        tab.setContent(rootNew);
        mainContentTabPane.getTabs().add(tab);
    
    }
    
    @FXML
    void handleViewAllItemsBtn() throws IOException{
    
        Parent rootNew = FXMLLoader.load(getClass().getResource("ViewAllItems.fxml"));
        Tab tab = new Tab("View All Items");
        tab.setContent(rootNew);
        mainContentTabPane.getTabs().add(tab);
    }
    
    @FXML
    void handleCreateNewSaleBillBtn() throws IOException{
        Parent rootNew = FXMLLoader.load(getClass().getResource("SaleBill.fxml"));
        Tab tab = new Tab("Create New Sale Bill");
        tab.setContent(rootNew);
        mainContentTabPane.getTabs().add(tab);
    
    }

    @FXML
    void initialize() {
        assert VoucherMgmt != null : "fx:id=\"VoucherMgmt\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert addLedgerButton != null : "fx:id=\"addLedgerButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert addNewLedgerType != null : "fx:id=\"addNewLedgerType\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert ledgerMgmt != null : "fx:id=\"ledgerMgmt\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert leftMenuHolder != null : "fx:id=\"leftMenuHolder\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert mainContentHolder != null : "fx:id=\"mainContentHolder\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert mainContentTabPane != null : "fx:id=\"mainContentTabPane\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert topMenuBar != null : "fx:id=\"topMenuBar\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert viewAllLedgerButton != null : "fx:id=\"viewAllLedgerButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert AddItemBtn != null : "fx:id=\"viewAllLedgerButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert x1 != null : "fx:id=\"x1\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert createNewSaleBillBtn != null : "fx:id=\"createNewSaleBillBtn\" was not injected: check your FXML file 'MainWindow.fxml'.";


    }

}
