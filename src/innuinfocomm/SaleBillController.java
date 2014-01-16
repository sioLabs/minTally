package innuinfocomm;


import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentEvent.EventType;
import pojos.ItemGroup;
import pojos.Items;
import pojos.Ledger;
import pojos.LedgerGroup;
import pojos.SaleBill;
import pojos.SalebillItem;
import utils.EntityManagerHelper;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.apache.poi.ss.formula.functions.Columns;
import org.datafx.reader.DataReader;
import org.datafx.reader.JdbcSource;
import org.datafx.reader.converter.JdbcConverter;
import org.eclipse.persistence.jpa.internal.jpql.parser.UpdateItem;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import pojos.ConverterUtil;
import utils.ItemSearchBox;




////////////////////////////////Cellfactories

public class SaleBillController implements Initializable {
    
    //eventBus 
    EventBus eventBus = new EventBus();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private AnchorPane anchorPane;

    SaleBill s = new SaleBill();
    @FXML
    private TextField billNoTextbox;

    @FXML
    private RadioButton challanRadioBtn;

    @FXML
    private RadioButton challanRateRadioBtn;

    @FXML
    private TextField companyTextBox;

    @FXML
    private ComboBox<Ledger> customerComboBox;

    @FXML
    private TextField dateTextBox;

    @FXML
    private TextField discountTextBox;

    @FXML
    private TextField frieghtTextBox;

    @FXML
    private RadioButton invoiceRadioBtn;

    @FXML
    private TableColumn<SalebillItem, String> itemNameTableCol;

    @FXML
    private Button printBtn;



    @FXML
    private TableColumn<SalebillItem, Double> quantityTableCol;

    @FXML
    private TableColumn<SalebillItem, Double> rateTableCol;

    @FXML
    private TableColumn<SalebillItem, String> remarkTableCol;
    
    @FXML
    private TableView<SalebillItem> SaleItemTableView;

    @FXML
    private TextField remarksTextBox;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<String> siteComboBox;

    @FXML
    private TableColumn<SalebillItem, Double> totalTableCol;

    @FXML
    private TextField totalTextBox;

    @FXML
    private TableColumn<SalebillItem, String> unitTableCol;

    @FXML
    private TableColumn<SalebillItem,Double> vatPercTableCol;

    @FXML
    private TableColumn<SalebillItem, Double> vatRsTableCol;

    @FXML
    private TextField vatTextBox;
    
    

    @FXML
    void handlePrintBtn(ActionEvent event) {
    }

    @FXML
    void handleSaveBtn(ActionEvent event) {
        
        
        s.setSaleBillDate(new Date());
        s.setSaleBilRemark(remarksTextBox.getText());
        s.setSaleBillCompany(companyTextBox.getText());
        if(customerComboBox.getSelectionModel().isEmpty())
            s.setSaleBillCustomer(99);
        else
            s.setSaleBillCustomer(customerComboBox.getSelectionModel().getSelectedItem().getLedgerId());
            
//        s.setSaleBillCustomer(customerComboBox.getSelectionModel().getSelectedItem().getLedgerId());
        if(discountTextBox.getText().trim().isEmpty())
            s.setSaleBillDiscount(0.0);
        else
            s.setSaleBillDiscount(Double.parseDouble(discountTextBox.getText()));
        
        //set SaleBillNo
        s.setSaleBillNo(Integer.parseInt(billNoTextbox.getText()));
        
        if(siteComboBox.getSelectionModel().isEmpty())
            s.setSaleBillSite(null);
        else
            s.setSaleBillSite(siteComboBox.getSelectionModel().getSelectedItem());
        
        if(totalTextBox.getText().trim().isEmpty())
            s.setSaleBillTotalAmount(0.0);
        else        
            s.setSaleBillTotalAmount(Double.parseDouble(totalTextBox.getText()));
        
        if(vatTextBox.getText().trim().isEmpty())
            s.setSaleBillTotalvat(0.0);
        else
            s.setSaleBillTotalvat(Double.parseDouble(vatTextBox.getText()));
        
        
        //Now generate the item List
        System.out.println(data.size() + "buton click size");
        int sind = 0;
         for(sind = 0 ; sind<data.size();sind++){
             if(data.get(sind).getSaleBillNo() == null)
             {
                 System.out.println(sind + "break point");
                 break;
             }               
         }
         data.remove(sind,data.size());
         System.out.println(data.size() + "final size");
         
        //SalebillItem[] item = SaleItemTableView.getItems().toArray(new SalebillItem[0]);
            SalebillItem[] item = data.toArray(new SalebillItem[0]);

        //SalebillItem[] item = (SalebillItem[])SaleItemTableView.getItems().toArray();
        
        ArrayList<SalebillItem> items = new ArrayList<SalebillItem>(Arrays.asList(item));
        s.setSalebillItemCollection(items);

       if(frieghtTextBox.getText().trim().length() != 0 )
            s.setSalebillfrieghtCharges(Double.parseDouble(frieghtTextBox.getText()));
       else
            s.setSalebillfrieghtCharges(0.0);
        
        EntityManager em = EntityManagerHelper.getInstance().getEm();
          try{
                em.getTransaction().begin();
                em.persist(s);
                em.getTransaction().commit();
        }catch(Exception ex){
            //errorLabel.setVisible(true);
            ex.printStackTrace();
              System.out.println("error in saving data");
        }
    }
    
    private  ObservableList<SalebillItem> data = FXCollections.observableArrayList();
   // private ArrayList<SalebillItem> saleItemList = new ArrayList<SalebillItem>();

    private ItemSearchBox selectedItemSearchBox;
    private Items selectedItem;
    
    @Subscribe
    public void setSelectedItem(Items userSelectedItem){
        //System.out.println(userSelectedItem.getItemName() + "ullo");
        System.out.println("in setSelection");
        
        selectedItem = userSelectedItem;
        int ind = SaleItemTableView.getSelectionModel().getSelectedIndex();
        System.out.println(ind + "index");
        SalebillItem edit = new ConverterUtil().Items2SalebillItem(selectedItem);
        edit.setSaleBillNo(s);
        data.remove(ind);
        data.add(ind, edit);
        SaleItemTableView.getItems().add(new SalebillItem());
        setTotalTextBox();
    }
    

           

    @FXML
    public void initialize(URL location, ResourceBundle r) {
        eventBus.register(this);
        assert SaleItemTableView != null : "fx:id=\"SaleItemTableView\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert billNoTextbox != null : "fx:id=\"billNoTextbox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert challanRadioBtn != null : "fx:id=\"challanRadioBtn\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert challanRateRadioBtn != null : "fx:id=\"challanRateRadioBtn\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert companyTextBox != null : "fx:id=\"companyTextBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert customerComboBox != null : "fx:id=\"customerComboBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert dateTextBox != null : "fx:id=\"dateTextBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert discountTextBox != null : "fx:id=\"discountTextBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert frieghtTextBox != null : "fx:id=\"frieghtTextBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        //assert groupComboBox != null : "fx:id=\"groupComboBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert invoiceRadioBtn != null : "fx:id=\"invoiceRadioBtn\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert itemNameTableCol != null : "fx:id=\"itemNameTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        //assert itemsComboBox != null : "fx:id=\"itemsCombobox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert printBtn != null : "fx:id=\"printBtn\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert quantityTableCol != null : "fx:id=\"quantityTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert rateTableCol != null : "fx:id=\"rateTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert remarkTableCol != null : "fx:id=\"remarkTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert remarksTextBox != null : "fx:id=\"remarksTextBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert siteComboBox != null : "fx:id=\"siteComboBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        //assert subGroupComboBox != null : "fx:id=\"subGroupComboBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert totalTableCol != null : "fx:id=\"totalTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert totalTextBox != null : "fx:id=\"totalTextBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert unitTableCol != null : "fx:id=\"unitTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert vatPercTableCol != null : "fx:id=\"vatPercTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert vatRsTableCol != null : "fx:id=\"vatRsTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert vatTextBox != null : "fx:id=\"vatTextBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        
        itemNameTableCol.setCellValueFactory(new PropertyValueFactory<SalebillItem,String>("itemName"));
        quantityTableCol.setCellValueFactory(new PropertyValueFactory<SalebillItem,Double>("itemQnty"));
        rateTableCol.setCellValueFactory(new PropertyValueFactory<SalebillItem,Double>("itemRate"));
        unitTableCol.setCellValueFactory(new PropertyValueFactory<SalebillItem,String>("itemUnitName"));
        remarkTableCol.setCellValueFactory(new PropertyValueFactory<SalebillItem,String>("remark"));
        totalTableCol.setCellValueFactory(new PropertyValueFactory<SalebillItem,Double>("total"));
        vatPercTableCol.setCellValueFactory(new PropertyValueFactory<SalebillItem,Double>("itemVatPerc"));
        vatRsTableCol.setCellValueFactory(new PropertyValueFactory<SalebillItem,Double>("itemVatRs"));
        SaleItemTableView.setFocusTraversable(true);
        
        itemNameTableCol.setEditable(true);
        Callback<TableColumn<SalebillItem,String>, TableCell<SalebillItem, String>> itNameCellfactory = 
                    new Callback<TableColumn<SalebillItem, String>, TableCell<SalebillItem, String>>() {
                        
            @Override
            public TableCell<SalebillItem, String> call(TableColumn<SalebillItem, String> p) {
                selectedItemSearchBox =  new ItemSearchBox(eventBus,p.getTableView().getSelectionModel().getSelectedIndex());
                eventBus.register(selectedItemSearchBox);              
                return selectedItemSearchBox;
            }

        };
        itemNameTableCol.setCellFactory(itNameCellfactory);
        //itemNameTableCol.bv o
        itemNameTableCol.setOnEditCommit(new EventHandler<CellEditEvent<SalebillItem, String>>() {

            @Override 
            public void handle(CellEditEvent<SalebillItem, String> t) {
        
                System.out.println("on edit commit itemname :: " + selectedItem.getItemName());
                int ind = t.getTableView().getSelectionModel().getSelectedIndex();
                data.remove(ind);
                SalebillItem edit = new ConverterUtil().Items2SalebillItem(selectedItem);
                data.add(ind, edit);
                t.getTableView().getSelectionModel().clearSelection();
                t.getTableView().getSelectionModel().select(ind);

            }
        });
        
//        
        //////////QUANTITY TABLE CELL
        quantityTableCol.setEditable(true);
        //quantityTableCol.setCellFactory(TextFieldTableCell.);
        Callback<TableColumn<SalebillItem,Double>,TableCell<SalebillItem,Double>> cellFactory = 
                new Callback<TableColumn<SalebillItem,Double>,TableCell<SalebillItem,Double>>(){
                     public TableCell<SalebillItem,Double> call(TableColumn<SalebillItem,Double> p) {
                      return new EditingQntyCell();
                  }
                };
        quantityTableCol.setCellFactory(cellFactory);
        
        quantityTableCol.setOnEditCommit(
               new EventHandler<TableColumn.CellEditEvent<SalebillItem, Double>>(){

                    @Override
                    public void handle(TableColumn.CellEditEvent<SalebillItem, Double> t) {
                       //code to update the  total and the VAT%
                        SalebillItem edit = (SalebillItem) t.getTableView().getItems().get(t.getTablePosition().getRow());
                        edit.setItemQnty(t.getNewValue());
                        
                        setTotalTextBox();
                        t.getTableView().getColumns().get(0).setVisible(false);
                        t.getTableView().getColumns().get(0).setVisible(true);
                    }               
        });
        
     

        initializeSaleBill();

          fillCustomerComboBox();

        customerComboBox.valueProperty().addListener(new ChangeListener<Ledger>(){

            @Override
            public void changed(ObservableValue<? extends Ledger> ov, Ledger t, Ledger t1) {
                //if a new value is chosen and its not first customer
                
                if((t!=null) || (t1!=null)){
                    s.setSaleBillCustomer(t1.getLedgerId());
                }
            }
        
        
        });
       
        itemNameTableCol.setEditable(true);
        data.add(new SalebillItem());
        SaleItemTableView.setEditable(true);
        SaleItemTableView.setItems(data);
        
        
   }
    
    
    
    
    private void initializeTableColumns(){
    
        itemNameTableCol.setEditable(true);
        
    
    }
    

//function to fill the customer combo box. Remember customer is sundry debtors
    private void fillCustomerComboBox(){
        EntityManager em = EntityManagerHelper.getInstance().getEm();
     
        Query q = em.createNamedQuery("Ledger.findAll");
        
        ArrayList<Ledger> custList = new ArrayList<Ledger>(q.getResultList());
        //System.out.println(custList.size());
        customerComboBox.getItems().clear();
        customerComboBox.getItems().addAll(custList);
        customerComboBox.getSelectionModel().clearSelection();
        customerComboBox.getSelectionModel().selectFirst();
        
    }
    

    private void initializeSaleBill() {
        //first thing is get a saleBillNumber;
        
        
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        
        Integer nextValue = (Integer)em.createQuery("select max(s.saleBillNo) from SaleBill s").getSingleResult();
        
        if(nextValue!=null)
        {
            nextValue++;
            billNoTextbox.setText(nextValue+"");
            s.setSaleBillNo(nextValue);
        }
        else
        {
            billNoTextbox.setText(1+"");
            s.setSaleBillNo(1);
        }
        
        
        
        
        s.setSaleBillDate(new Date());          
        
        
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int year = c.get(Calendar.YEAR);
        
        dateTextBox.setText(day+"/"+month+"/"+year);
        dateTextBox.setEditable(false);
        
        fillCustomerComboBox();
        
        
        
    }

    //qnty cell editing tablecell impl
   
    private void setTotalTextBox(){
        
        
        double total = 0.0;
        double vat = 0.0;
        for(int i = 0 ;i <data.size();i++){
            vat += data.get(i).getItemVatRs();
            total += data.get(i).getTotal() + data.get(i).getItemVatRs();
            
        }
        
        
        if(discountTextBox.getText().equals("")){
            totalTextBox.setText(String.format("%.2f", total));
        }
        else{
            double discount = Double.parseDouble(discountTextBox.getText());
            total -= total*discount/100;
            totalTextBox.setText(String.format("%.2f", total));             
         }
        vatTextBox.setText(String.format("%.2f", vat));
    }
    
         
           @FXML
           public void handleViewSaleBillBtn(){
     
            }
           
           
           

}



//Qnty Cell Editor
 class EditingQntyCell extends TableCell<SalebillItem,Double>{
           private TextField textField;
           
           public EditingQntyCell(){}
           
           @Override
           public void startEdit(){
               super.startEdit();
               if(textField == null)
                   createTextField();
               
               setGraphic(textField);
               setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
               textField.selectAll();
           }
           
            @Override
          public void cancelEdit() {
            super.cancelEdit();

            setText(String.valueOf(getItem()));
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
           
           //update Item
              @Override
              public void updateItem(Double item, boolean empty) {
                  super.updateItem(item, empty);

                  if (empty) {
                      setText(null);
                      setGraphic(null);
                  } else {
                      if (isEditing()) {
                          if (textField != null) {
                              textField.setText(getString());
                          }
                          setGraphic(textField);
                          setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                      } else {
                          setText(getString());
                          setContentDisplay(ContentDisplay.TEXT_ONLY);
                      }
                  }
              }
           
           //textfield creation and settings
           private void createTextField(){
                textField = new TextField(getString());
                textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
                textField.setOnKeyPressed(new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent t) {
                     //   System.out.println("inside key handle event");
                       // System.out.println(t.getCode());
                        if (t.getCode() == KeyCode.ENTER) {
                            commitEdit(Double.parseDouble(textField.getText()));
                         //   System.out.println("enter key pressed");
                        } else if (t.getCode() == KeyCode.ESCAPE) {
                            cancelEdit();
                        }
                    }
                });
           
           
          }//end create textfield
         
           private String getString() {
                    return getItem() == null ? "" : getItem().toString();
           }

    }

    
