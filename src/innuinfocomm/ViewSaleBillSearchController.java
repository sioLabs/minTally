/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package innuinfocomm;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author ashutoshsingh
 */
public class ViewSaleBillSearchController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private GridPane dateGridPane;
    
    private DatePicker datePicker;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //initialize a grid pane
        assert dateGridPane != null : "fx-id = dateGridPAne was not injected" ;
        
        datePicker = new DatePicker(Locale.US);
        datePicker.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        datePicker.getCalendarView().todayButtonTextProperty().set("Today");
        datePicker.getCalendarView().setShowWeeks(false);
        datePicker.getStylesheets().add("/DatePicker.css");
        
        
        dateGridPane.add(datePicker, 0, 0);
        datePicker.selectedDateProperty().addListener(new InvalidationListener() {
            

            @Override
            public void invalidated(Observable o) {
                System.out.println(datePicker.selectedDateProperty().get()); 
               
            }
        });
        
    }    
}
