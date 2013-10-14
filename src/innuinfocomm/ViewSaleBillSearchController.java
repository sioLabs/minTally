/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package innuinfocomm;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import pojos.SaleBill;
import utils.EntityManagerHelper;

/**
 * FXML Controller class
 *
 * @author ashutoshsingh
 */
public class ViewSaleBillSearchController implements Initializable {

    /**
     * Initializes the controller class.
     */
     //object for database handling
    EntityManager em = EntityManagerHelper.getInstance().getEm();
    
    
    @FXML
    private GridPane dateGridPane;
    
    private DatePicker datePicker;
    
     @FXML
    private TableColumn<SaleBill, Double> amtTableCol;

    @FXML
    private TableColumn<SaleBill, Integer> billnoTableCol;

    @FXML
    private TableColumn<SaleBill, String> customerTalbleCol;
    private ObservableList<SaleBill> data;
     @FXML
    private TableView<SaleBill> SaleBillTableView;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //initialize a grid pane
          assert SaleBillTableView != null : "fx:id=\"SaleBillTableView\" was not injected: check your FXML file 'ViewSaleBillSearch.fxml'.";
        assert amtTableCol != null : "fx:id=\"amtTableCol\" was not injected: check your FXML file 'ViewSaleBillSearch.fxml'.";
        assert billnoTableCol != null : "fx:id=\"billnoTableCol\" was not injected: check your FXML file 'ViewSaleBillSearch.fxml'.";
        assert customerTalbleCol != null : "fx:id=\"customerTalbleCol\" was not injected: check your FXML file 'ViewSaleBillSearch.fxml'.";
        assert dateGridPane != null : "fx:id=\"dateGridPane\" was not injected: check your FXML file 'ViewSaleBillSearch.fxml'.";
        customerTalbleCol.setCellValueFactory(new PropertyValueFactory<SaleBill,String>("custName"));
        billnoTableCol.setCellValueFactory(new PropertyValueFactory<SaleBill, Integer>("saleBillNo"));
        amtTableCol.setCellValueFactory(new PropertyValueFactory<SaleBill,Double>("saleBillTotalAmount"));

        
        datePicker = new DatePicker(Locale.US);
        datePicker.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        datePicker.getCalendarView().todayButtonTextProperty().set("Today");
        datePicker.getCalendarView().setShowWeeks(false);
        datePicker.getStylesheets().add("/DatePicker.css");
        
        
        dateGridPane.add(datePicker, 0, 0);
        datePicker.selectedDateProperty().addListener(new InvalidationListener() {
            

            @Override
            public void invalidated(Observable o) {
                Date d = datePicker.selectedDateProperty().get() ;
                getSaleBillByDate(d);
                 
                                
               
            }

        });
        
    }    
    
            //function to get the all the salebill by Date    
            private void getSaleBillByDate(Date d) {
                Query query = em.createNamedQuery("SaleBill.findBySaleBillDate");
                query.setParameter("saleBillDate", d);
                ArrayList<SaleBill> saleBills =  new ArrayList<SaleBill>(query.getResultList());
                if(saleBills != null)
                    fillSaleBillTable(saleBills);
                else
                    return;                       
                
            }

    private void fillSaleBillTable(ArrayList<SaleBill> saleBills) {
         data = FXCollections.observableArrayList(saleBills);
         SaleBillTableView.setItems(data);
         SaleBillTableView.getSelectionModel().clearSelection();        
    }
}
