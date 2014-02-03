package innuinfocomm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pojos.ItemGroup;
import pojos.Items;
import pojos.ItemsPharma;
import pojos.Units;
import utils.EntityManagerHelper;


public class ImportInventoryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button fileBrowseBtn;
    
    @FXML
    private Button importFileBtn;
    
    @FXML
    private ProgressBar progressBar;

    @FXML
    private TextField filePathTextBox;

    @FXML 
    private ProgressIndicator importProgressIndicator;

    private File chosenFile;
    
    private SimpleDateFormat dateformtter = new SimpleDateFormat("dd-MMM-yy");
    
    @FXML
    void handleBrowseBtn(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Inventory Excel Sheet");
        File defaultDirectory = new File("c:/");
        chooser.setInitialDirectory(defaultDirectory);
        chosenFile = chooser.showOpenDialog(((Node)event.getTarget()).getScene().getWindow());
        if(chosenFile != null)
            filePathTextBox.setText(chosenFile.getAbsolutePath());
        
         
    }
    
    void importToDatabase() throws FileNotFoundException, IOException{
        if(null != chosenFile){
            InputStream input = new FileInputStream(chosenFile.getAbsolutePath());
           
            Workbook wb = new XSSFWorkbook(input);
            XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(0);
            //HSSFSheet sheet = wb.getSheetAt(0);
            Row row;
            
            
            
            ArrayList<String> groupList = new ArrayList<String>();
            ArrayList<String> unitGroup = new ArrayList<String>();
            HashMap unitMap = new HashMap();
            HashMap subGroupMap = new HashMap();
            
            EntityManager em = EntityManagerHelper.getInstance().getEm();
           
            int size = sheet.getLastRowNum();
            ///////////change here
            for(int i=1; i<=sheet.getLastRowNum(); i++){
                
                ItemsPharma item = new ItemsPharma();
                row = sheet.getRow(i);
                String name = row.getCell(1).getStringCellValue();
                item.setDesc(name);
                
                //set group
                
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                String pack = row.getCell(2).getStringCellValue();
                item.setPack(pack);
                
                
                int stock =  (int) row.getCell(3).getNumericCellValue();
                item.setStockStrips(stock);
                
                
                Float mrp = (float)(row.getCell(4).getNumericCellValue());
                item.setMrp(mrp);
                                
                float rate = (float)(row.getCell(5).getNumericCellValue());
                item.setRateFraction(rate);
                
                 int expDate = (int)row.getCell(6).getNumericCellValue();
                 
                 Date d = ExcelDateParse(expDate);
                item.setExpDate(d);
                  
                  
                  
                  String batch = row.getCell(7).getStringCellValue().trim();
                  item.setBatch(batch);
                  
                  String make = row.getCell(8).getStringCellValue().trim();
                  item.setMake(make);
                  
                 em.getTransaction().begin();
                 em.persist(item);
                 em.getTransaction().commit();
                 em.clear();
                 
       
                  progressBar.setProgress(i/size);
                    
          }
                
                
              
        }
        
        //TODO show error here no file chosen.
    }
    
    public static Date ExcelDateParse(int ExcelDate){
    Date result = null;
    try{
        GregorianCalendar gc = new GregorianCalendar(1900, Calendar.JANUARY, 1);
        gc.add(Calendar.DATE, ExcelDate -2);
        result = gc.getTime();
    } catch(RuntimeException e1) {}
    return result;
} 
    
    @FXML
    void handleImportBtn() throws FileNotFoundException, IOException{
        
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    try {
                        importToDatabase();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ImportInventoryController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ImportInventoryController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            })
                    ;
        
            }
            ////////////
        
        
     

    @FXML
    void initialize() {
        assert fileBrowseBtn != null : "fx:id=\"fileBrowseBtn\" was not injected: check your FXML file 'ImportInventory.fxml'.";
        assert filePathTextBox != null : "fx:id=\"filePathTextBox\" was not injected: check your FXML file 'ImportInventory.fxml'.";
        assert importProgressIndicator != null : "fx:id=\"importProgressIndicator\" was not injected: check your FXML file 'ImportInventory.fxml'.";
        

    }

}
