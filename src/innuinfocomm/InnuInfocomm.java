/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package innuinfocomm;

import java.awt.Font;
import javafx.application.Application;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import utils.EntityManagerHelper;

/**
 *
 * @author t0m
 */
public class InnuInfocomm extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        if(!checkDBConnectivity()){
            return;
        }
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }
    
    static boolean checkDBConnectivity(){
        
         try{
            EntityManager em = EntityManagerHelper.getInstance().getEm();
             return true;  
        }catch(Exception e){
            showError();
            return false;
        }
             
        
    }
    
    static void showError(){
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Database Error");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setScene(new Scene(VBoxBuilder.create().
        children(new Text("Database Connectivity Error \nStart WampServer")).
         alignment(Pos.CENTER).padding(new Insets(50)).build()));
         dialogStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }
}