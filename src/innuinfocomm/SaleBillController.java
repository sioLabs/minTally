package innuinfocomm;

import java.awt.Event;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import org.datafx.reader.DataReader;
import org.datafx.reader.JdbcSource;
import org.datafx.reader.converter.JdbcConverter;
import utils.ItemSearchBox;


public class SaleBillController implements Initializable {

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
    private ComboBox<Items> itemsComboBox;
    
    @FXML 
    private ComboBox<ItemGroup> groupComboBox;
    
    @FXML
    private ComboBox<ItemGroup> subGroupComboBox;
    
    /*
     * popused to contain a listview
     */
    //private  Popup itemsListContainer = new Popup();
    

    @FXML
    void handlePrintBtn(ActionEvent event) {
    }

    @FXML
    void handleSaveBtn(ActionEvent event) {
        //save the item to db here
       // SaleBill s = new SaleBill();
        //s.setSaleBillNo(Integer.parseInt(billNoTextbox.getText()));
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
        SalebillItem[] item = SaleItemTableView.getItems().toArray(new SalebillItem[0]);
        //SalebillItem[] item = (SalebillItem[])SaleItemTableView.getItems().toArray();
        
        ArrayList<SalebillItem> items = new ArrayList<SalebillItem>(Arrays.asList(item));
        s.setSalebillItemCollection(items);
        
        s.setSalebillfrieghtCharges(Double.parseDouble(frieghtTextBox.getText()));
        
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


    @FXML
    public void initialize(URL location, ResourceBundle r) {
        assert SaleItemTableView != null : "fx:id=\"SaleItemTableView\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert billNoTextbox != null : "fx:id=\"billNoTextbox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert challanRadioBtn != null : "fx:id=\"challanRadioBtn\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert challanRateRadioBtn != null : "fx:id=\"challanRateRadioBtn\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert companyTextBox != null : "fx:id=\"companyTextBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert customerComboBox != null : "fx:id=\"customerComboBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert dateTextBox != null : "fx:id=\"dateTextBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert discountTextBox != null : "fx:id=\"discountTextBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert frieghtTextBox != null : "fx:id=\"frieghtTextBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert groupComboBox != null : "fx:id=\"groupComboBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert invoiceRadioBtn != null : "fx:id=\"invoiceRadioBtn\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert itemNameTableCol != null : "fx:id=\"itemNameTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert itemsComboBox != null : "fx:id=\"itemsCombobox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert printBtn != null : "fx:id=\"printBtn\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert quantityTableCol != null : "fx:id=\"quantityTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert rateTableCol != null : "fx:id=\"rateTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert remarkTableCol != null : "fx:id=\"remarkTableCol\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert remarksTextBox != null : "fx:id=\"remarksTextBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert siteComboBox != null : "fx:id=\"siteComboBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
        assert subGroupComboBox != null : "fx:id=\"subGroupComboBox\" was not injected: check your FXML file 'SaleBill.fxml'.";
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
        
        SaleItemTableView.setEditable(true);
        
        itemNameTableCol.setEditable(true);
        Callback<TableColumn<SalebillItem,String>, TableCell<SalebillItem, String>> itNameCellfactory = 
                    new Callback<TableColumn<SalebillItem, String>, TableCell<SalebillItem, String>>() {
                        
            @Override
            public TableCell<SalebillItem, String> call(TableColumn<SalebillItem, String> p) {
                return new ItemSearchBox();
            }

        };
        itemNameTableCol.setCellFactory(itNameCellfactory);
        itemNameTableCol.setOnEditCommit(new EventHandler<CellEditEvent<SalebillItem, String>>() {

            @Override
            public void handle(CellEditEvent<SalebillItem, String> t) {
                System.out.println("on edit commit itemname" + t.getNewValue());
                SalebillItem edit = t.getTableView().getSelectionModel().getSelectedItem();
                edit.setItemName(t.getNewValue());
                t.getTableView().setVisible(false);
                t.getTableView().setVisible(true);
                //edit = new 
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
                        System.out.println("on edit commit called " + t.getNewValue());
                        SalebillItem edit = (SalebillItem) t.getTableView().getItems().get(t.getTablePosition().getRow());
                        edit.setItemQnty(t.getNewValue());
                        
                        setTotalTextBox();
                        t.getTableView().getColumns().get(0).setVisible(false);
                        t.getTableView().getColumns().get(0).setVisible(true);
                        //t.getTableView().getItems()
                        //t.getTableView().getSelectionModel().clearSelection();
                        //t.getTableView().getSelectionModel().clearSelection();
                    }               
        });
        
        /////////UNIT TABLE CELL
//        unitTableCol.setCellFactory(new Callback<TableCoumn<SalebillItem,String>,TableCell<SalebillItem,String>>(){
//
//            
//            @Override
//            public TableCell<SalebillItem, String> call(TableColumn<SalebillItem,String> p ) {
//                
//            }
//
//        
//        
//        });
        

        initializeSaleBill();
        //itemsComboBox = new ComboBox<>();
        groupComboBox.getItems().clear();
        subGroupComboBox.getItems().clear();        
        itemsComboBox.getItems().clear();
        //customerComboBox.getItems().clear();
          fillCustomerComboBox();
        //System.out.println("\n in initialize func");
        fillGroupComboBox();
        
        groupComboBox.valueProperty().addListener(new ChangeListener<ItemGroup>(){

            @Override
            public void changed(ObservableValue<? extends ItemGroup> ov, ItemGroup t, ItemGroup t1) {
                //now set the subgroup items here 
//                   System.out.println("group combox box value hanged");
//                   System.out.println("old value = "+t.getItemGroupName());
//                   System.out.println("new value = "+t1.getItemGroupName());
                   fillSubGroupComboBox();
           
            }
        
        
        });
        
        subGroupComboBox.valueProperty().addListener(new ChangeListener<ItemGroup>(){

            @Override
            public void changed(ObservableValue<? extends ItemGroup> ov, ItemGroup t, ItemGroup t1) {
                if((t1!=null))
                        {
                            System.out.println("subgroup value changed to "+t1.getItemGroupName());
                            fillItemsComboBox();
                        }
            }
        
        
        });
        
        itemsComboBox.valueProperty().addListener(new ChangeListener<Items>(){

            @Override
            public void changed(ObservableValue<? extends Items> ov, Items t, Items t1) {
                if((t!=null) &&(t1!=null)){
                    SalebillItem item = new SalebillItem();
                    item.setItemId(t1.getItemId());
                    item.setItemName(t1.getItemName());
                    item.setItemUnitName(t1.getItemFirstUnit().getUnitName());
                    item.setItemVatPerc(t1.getItemVatPerc());
                    item.setItemRate(t1.getItemRate());
                    item.setItemQnty(1.0);
                    
                    item.setRemark("");
                    //item.setItemVatRs(0.0);
                    item.setSaleBillNo(s);
                    System.out.println(item.getTotal());
                    //item.setTotal(0.0);
                    //saleItemList.add(item);
                    data.add(item);
                    //data = FXCollections.observableArrayList(saleItemList);
                    SaleItemTableView.setItems(data);
                    SaleItemTableView.getSelectionModel().clearSelection();
                    setTotalTextBox();
                }
            }
        
            
        });
        
        customerComboBox.valueProperty().addListener(new ChangeListener<Ledger>(){

            @Override
            public void changed(ObservableValue<? extends Ledger> ov, Ledger t, Ledger t1) {
                //if a new value is chosen and its not first customer
                
                if((t!=null) || (t1!=null)){
                    s.setSaleBillCustomer(t1.getLedgerId());
                }
            }
        
        
        });
       
        
        
        
   }
    
    
    
    
    
    private void initializeTableColumns(){
    
        itemNameTableCol.setEditable(true);
        
    
    }
    

    private void fillGroupComboBox(){
    
   //     System.out.println("fillGroupComboBox");
        //get all the item group with parent id as 0;
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        Query q  = em.createNamedQuery("ItemGroup.findByItemGroupParent");
        q.setParameter("itemGroupParent", 0);
        ArrayList<ItemGroup> groups = new ArrayList<ItemGroup>(q.getResultList());        
        //System.out.println(groups.get(0)+"\n"+groups.get(1)+"\n"+groups.get(2));
        groupComboBox.getItems().clear();
        groupComboBox.getItems().addAll(groups);
        groupComboBox.getSelectionModel().clearSelection();
        groupComboBox.getSelectionModel().selectFirst();
        
    
    }
    
    private void fillSubGroupComboBox(){
        //get the selected value in the group Combo Box
   //     System.out.println("fill subgroup combo box");
        ItemGroup group = groupComboBox.getSelectionModel().getSelectedItem();
        
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        Query q  = em.createNamedQuery("ItemGroup.findByItemGroupParent");
        q.setParameter("itemGroupParent", group.getItemGroupId());
        // em.getTransaction().begin();
        ArrayList<ItemGroup> subGroup = new ArrayList<ItemGroup>(q.getResultList());
        //em.getTransaction().commit();
        
        if(subGroup.size()<1){  //this means that no subgroup exist
       //     System.out.println("inside the nosubgroup condition");
            subGroupComboBox.getItems().clear();
            fillItemsComboBox();
            return ;
        }
      //  System.out.println("group has subgroups" + subGroup.size());
        subGroupComboBox.getItems().clear();
        subGroupComboBox.getItems().addAll(subGroup);
        subGroupComboBox.getSelectionModel().clearSelection();
        subGroupComboBox.getSelectionModel().selectFirst();
        fillItemsComboBox();
    
    }
    
    private void fillItemsComboBox(){
   //     System.out.println("Inside fill items");
        //itemsComboBox.getItems().removeAll(itemsComboBox.getItems());
       
        //check if the number of items in subgorup. 
        if(subGroupComboBox.getItems().size() < 1){
 //           System.out.println("No sub groups exist");
            //the get the items according to the selected group
            ItemGroup group = groupComboBox.getSelectionModel().getSelectedItem();
            ArrayList<Items> items1 = new ArrayList<Items>(group.getItemsCollection1());
   //         System.out.println(group.getItemGroupName()+"\n" + group.getItemsCollection().size()+" "+group.getItemsCollection1().size()+"\n"+items1.size());
            itemsComboBox.getItems().clear();
            itemsComboBox.getItems().addAll(items1);
            itemsComboBox.getSelectionModel().clearSelection();
            itemsComboBox.getSelectionModel().selectFirst();
            
        
        }else{
            //get the select subgroup and list items from that 
            //EntityManager em = EntityManagerHelper.getInstance().getEm();
            //em.getTransaction().begin();
            ItemGroup group = subGroupComboBox.getSelectionModel().getSelectedItem();
            
    //        System.out.println("group has subgroup and subgroup has items");
    //        System.out.println("subgroup = " +group.getItemGroupName());
            //System.out.println(group.getItemGroupName() + "\n"+group.getItemsCollection().size()+"\n "+group.getItemsCollection1().size());
            ArrayList<Items> items1 = new ArrayList<Items>(group.getItemsCollection());
      //      System.out.println(items1.size() + "num items ");
            itemsComboBox.getItems().clear();
            itemsComboBox.getItems().addAll(items1);
            itemsComboBox.getSelectionModel().clearSelection();                    
            itemsComboBox.getSelectionModel().selectFirst();      
            
        
        }
        
        
    
        
    }
    
    //function to fill the customer combo box. Remember customer is sundry debtors
    private void fillCustomerComboBox(){
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        //Query q = em.createNamedQuery("Ledger.findByLedgerType");
        Query q = em.createNamedQuery("Ledger.findAll");
        //Query q1 = em.createNamedQuery("LedgerGroup.findByGroupName");
        //q1.setParameter("groupName", "Sundry Debtors");
        //LedgerGroup l  = (LedgerGroup)q1.getSingleResult();
        //q.setParameter("ledgerType", l.getId());
        //System.out.println(l.getGroupName()+ " "+ l.getId());
        
        //customerComboBox.setOnKeyReleased(new KeyHandler());
        
        ArrayList<Ledger> custList = new ArrayList<Ledger>(q.getResultList());
        //System.out.println(custList.size());
        customerComboBox.getItems().clear();
        customerComboBox.getItems().addAll(custList);
        customerComboBox.getSelectionModel().clearSelection();
        customerComboBox.getSelectionModel().selectFirst();
        //customerComboBox.setEditable(true);
        //customerComboBox.fireEvent();
            
    }
    
//  Code for the key handler class
//    private class KeyHandler implements EventHandler<KeyEvent>{
//
//        private SingleSelectionModel<Ledger> sm;
//        private String s;
//        
//        public KeyHandler(){
//            sm = customerComboBox.getSelectionModel();
//            s = "";
//        }
//        @Override
//        public void handle(KeyEvent event) {
//            
//            if( event.getCode() == KeyCode.BACK_SPACE && s.length()>0)
//                s = s.substring( 0, s.length() - 1 );
//            else s += event.getText();
//
//            if( s.length() == 0 ) {
//             //   fillCustomerComboBox();
//                sm.selectFirst();
//                return;
//            }
//            System.out.println( s );
//            ArrayList<Ledger> searchList = new ArrayList<Ledger>();
//            for( Ledger item: customerComboBox.getItems() ) {
//                if( item.getLedgerName().contains(s ) ) 
//                    searchList.add(item);              
//                     //sm.select( item );
//                    //customerComboBox.
//            }
//            
//            
//                
//            if(searchList.size() != 0 ){
//            customerComboBox.getItems().clear();
//            customerComboBox.getItems().addAll(searchList);
//            }
//            
//            if(searchList.size() == 1){
//                customerComboBox.getSelectionModel().selectFirst();
//            }
//            
//            //customerComboBox.fireEvent();
//            //customerComboBox.setSelectionModel(sm);
//            
//        }
//        
//    }
//    

    private void initializeSaleBill() {
        //first thing is get a saleBillNumber;
        
        
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        
        Integer nextValue = (Integer)em.createQuery("select max(s.saleBillNo) from SaleBill s").getSingleResult();
        
        if(nextValue!=null)
        {
            nextValue++;
            billNoTextbox.setText(nextValue+"");
        }
        else
            billNoTextbox.setText(1+"");
        
        
        
        
        s.setSaleBillDate(new Date());          
        
        
        s.setSaleBillDate(new Date());
        
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int year = c.get(Calendar.YEAR);
        
        dateTextBox.setText(day+"/"+month+"/"+year);
        dateTextBox.setEditable(false);
        
        fillCustomerComboBox();
        
//        ItemSearchBox mySearchBox = new ItemSearchBox();
  //      anchorPane.getChildren().add(mySearchBox);
        
        
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
           
           
           
           ////////////////EditingItemnamecell////////////
             class EditingItemNameCell extends TableCell<SalebillItem, String>{
               private TextField textField;
               private ListView<Items>  itemsList;
               
               EntityManager em = EntityManagerHelper.getInstance().getEm();
             
              
               public EditingItemNameCell() {}
               
               @Override
               public void updateItem(String name, boolean empty){
                   if(empty){
                       setText(null);
                       setGraphic(null);
                   }else{
                       if(textField != null){
                           String query = getString();
                       }
                   }
               }
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
               public void cancelEdit(){
                   super.cancelEdit();
                   setText(String.valueOf(getItem()));
                   setContentDisplay(ContentDisplay.TEXT_ONLY);
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
                        itemsList = new ListView<Items>();
                         
                        EntityManager em = EntityManagerHelper.getInstance().getEm();
                        Query q  = em.createNamedQuery("Items.findByItemName");
                        q.setParameter("itemName",textField.getText());
                        ArrayList<Items> itList = new ArrayList<Items>(q.getResultList());
                        itemsList.getItems().clear();
                        itemsList.getItems().addAll(itList);
                        itemsList.getSelectionModel().clearSelection();
                        itemsList.getSelectionModel().selectFirst();
                        
//                        itemsListContainer.setAutoFix(true);
//                        itemsListContainer.setHideOnEscape(true);
//                        itemsListContainer.getContent().addAll(itemsList);
//                        
//                       Parent parent = getParent();
//                       
                       
                       
                       
                        
                        
                        
                        if (t.getCode() == KeyCode.ENTER) {
                            commitEdit(textField.getText());
                         //   System.out.println("enter key pressed");
                        } else if (t.getCode() == KeyCode.ESCAPE) {
                            cancelEdit();
                        }
                    }
                });
           }
               
                 private String getString() {
                    return getItem() == null ? "" : getItem().toString();
            }
       }
           
     

           ////////////////////////////////////
           
}


////////////////////////////////Cellfactories

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

    
