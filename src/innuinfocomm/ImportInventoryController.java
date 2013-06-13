package innuinfocomm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pojos.ItemGroup;
import pojos.Items;
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
    private TextField filePathTextBox;

    @FXML 
    private ProgressIndicator importProgressIndicator;

    private File chosenFile;
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
    
    @FXML
    void handleImportBtn() throws FileNotFoundException, IOException{
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
                
                Items item = new Items();
                row = sheet.getRow(i);
                String name = row.getCell(0).getStringCellValue();
                item.setItemName(name);
                
                //set group
                ItemGroup group = new ItemGroup();
                String gr = row.getCell(1).getStringCellValue();
                group.setItemGroupName(gr);
                group.setItemGroupParent(0);
                if( !groupList.contains(gr)){
                    //save to database
                    group.setItemGroupId(null);
                    em.getTransaction().begin();
                    em.persist(group);
                    em.getTransaction().commit();
                    //em.clear();
                    groupList.add(gr);
                    //group_ind++;
                }else{
                    
                    group.setItemGroupId((int)groupList.indexOf(gr)+1);
                }
                
                item.setItemGroup(group);
                if(group.getItemGroupId() == null)
                    group.setItemGroupId(groupList.size());
                
                
                
                //set subgroup
                
                ItemGroup subgroup = new ItemGroup();
                String subGr = null;
                
                try{
                     subGr =    row.getCell(2).getStringCellValue();
                
                //subgroup exists
                if(subGr.length()!=0){
                    subgroup.setItemGroupName(subGr);
                    //subgroup does not exist
                    if(!groupList.contains(subGr)){
                        //subGroupMap.put(subGr, groupId);
                        //subgroup.setItemGroupId(groupId);
                        subgroup.setItemGroupId(null);
                        subgroup.setItemGroupParent(group.getItemGroupId());
                        subGroupMap.put(subGr,group.getItemGroupId());
                        groupList.add(subGr);
                        em.getTransaction().begin();
                        em.persist(subgroup);
                        em.getTransaction().commit();
                      //  em.clear();
                                        
                    }else{
                       Integer id = groupList.indexOf(subGr)+1;
                       Integer parent  = (int)subGroupMap.get(subGr);
                       subgroup.setItemGroupId(id);
                       subgroup.setItemGroupName(subGr);
                       subgroup.setItemGroupParent(parent);
                       
                    }                    
                }else{
                    subgroup = null;
                }
                }catch(Exception e ){
                    subgroup = null;
                }
                
                
                item.setItemSubGroup(subgroup);                    
                //now the groups are set
                //now set the 
                Units firstUnit = new Units();
                String funit = row.getCell(3).getStringCellValue();
                //TODO code here in case first unit does not exist
                
                //firstUnit.setId(null);
                firstUnit.setConv(1);
                firstUnit.setSecondUnit(0);
                firstUnit.setUnitName(funit);
                firstUnit.setUnitType(1);                
                
                if(unitGroup.contains(funit)){
                    firstUnit.setId(unitGroup.indexOf(funit)+1);
                    //int id = unitGroup.indexOf(funit);                    
                }else{
                    firstUnit.setId(null);
                    em.getTransaction().begin();
                    em.persist(firstUnit);
                    em.getTransaction().commit();
                    //em.clear();
                    unitGroup.add(funit);
    
                }
                
                item.setItemFirstUnit(firstUnit);
                if(firstUnit.getId() == null)
                    firstUnit.setId(unitGroup.size());
                
                
                
                
                //now get the second unit
                Units secondUnit = new Units();
                String sunit  = null;
                try{
                    sunit= row.getCell(4).getStringCellValue();

                    ///if unit exists
                    if(sunit.length() > 0){

                        secondUnit.setUnitName(sunit);
                        secondUnit.setConv(0);
                        secondUnit.setUnitType(2);
                        //secondUnit.setId(null);
                        //if it exists in the group
                        if(unitGroup.contains(sunit)){
                            secondUnit.setId(unitGroup.indexOf(sunit)+1);
                            //secondUnit.setSecondUnit((int)unitMap.get(sunit));
                            secondUnit.setSecondUnit(firstUnit.getId());
                        }else{
                            secondUnit.setId(null);
                            secondUnit.setSecondUnit(firstUnit.getId());
                            em.getTransaction().begin();
                            em.persist(secondUnit);
                            em.getTransaction().commit();    
                            //em.clear();
                            unitGroup.add(sunit);                        
                            unitMap.put(sunit,firstUnit.getId());                                          
                        }
                    }else{
                        secondUnit = null;
                    }
                }catch(Exception e){
                    secondUnit = null;
                }
               
                
                
                
                item.setItemSecondUnit(secondUnit);
                  
                  
                  //code for third unit skip this
                  
                  //open stock
                  Double openStock = null;
                  try{
                  openStock = row.getCell(6).getNumericCellValue();
                  }catch( Exception e){
                      openStock = null;
                  }                  
                  item.setItemOpenStock(openStock);
                  
                  //rackNo                  
                  String rackNo = null;
                  try{
                  rackNo = row.getCell(7).getStringCellValue();
                  if(rackNo.length() > 0)
                    item.setItemRackNo(rackNo);
                  else
                      item.setItemRackNo(null);
                  }catch(Exception e){
                      item.setItemRackNo(null);
                  }
                  
                  //codeValue
                  String itemCode;
                  try{
                          itemCode= row.getCell(8).getStringCellValue();
                  if(itemCode.length() > 0)
                      item.setItemCode(itemCode);
                  else
                      item.setItemCode(null);
                  }catch(Exception e){
                      item.setItemCode(null);
                  }
                  
                  //item Rate
                  Double rate = null;
                  try{
                  rate= row.getCell(9).getNumericCellValue();
                  }catch(Exception e){
                      rate = null;
                  }
                 item.setItemRate(rate);
                 
                 Double vat = null;
                 try{
                     vat= row.getCell(10).getNumericCellValue();
                     
                 }catch(Exception e){
                     vat = null;
                 }
                 item.setItemVatPerc(vat);
                 
                 em.getTransaction().begin();
                 em.persist(item);
                 em.getTransaction().commit();
                 em.clear();
                 
       
                  
                    
          }
                
                
              
                
            }
            ////////////
        
        }
     

    @FXML
    void initialize() {
        assert fileBrowseBtn != null : "fx:id=\"fileBrowseBtn\" was not injected: check your FXML file 'ImportInventory.fxml'.";
        assert filePathTextBox != null : "fx:id=\"filePathTextBox\" was not injected: check your FXML file 'ImportInventory.fxml'.";
        assert importProgressIndicator != null : "fx:id=\"importProgressIndicator\" was not injected: check your FXML file 'ImportInventory.fxml'.";
        importProgressIndicator.setVisible(false);

    }

}
