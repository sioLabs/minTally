package innuinfocomm;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import pojos.ConverterUtil;
import pojos.ItemsPharma;
import pojos.ItemsPharma;
import pojos.SaleBill;
import pojos.SalebillItem;
import utils.EntityManagerHelper;
import utils.ItemSearchBox;


public class SalebillPharmaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn AmountTableColumn = new TableColumn();

    @FXML
    private TextField CDamtTextBox;

    @FXML
    private TableColumn<ItemsPharma, String> batchTableColumn;

    @FXML
    private TextField billNoTextbox;

    @FXML
    private TextField custLicTextBox;

    @FXML
    private TextField customerTextBox;

    @FXML
    private TextField dateTextBox;

    @FXML
    private TextArea deliveryTextArea;

    @FXML
    private TableColumn<ItemsPharma, String> descTableColumn;

    @FXML
    private TextField discountTextBox;

    @FXML
    private TableColumn<ItemsPharma, String> dmTableColumn;

    @FXML
    private Label errorLabel;

    @FXML
    private TableColumn<ItemsPharma, String> expTableColumn;

    @FXML
    private TextField finalAmtTextBox;

    @FXML
    private TableColumn<ItemsPharma, String> makeTableColumn;

    @FXML
    private TableColumn<ItemsPharma, Float> mrpTableColumn;

    @FXML
    private TableColumn<ItemsPharma, String> packTableColumn;

    @FXML
    private ComboBox<String> paymentComboBox;

    @FXML
    private Button printBtn;

    @FXML
    private TableColumn qntyTableColumn = new TableColumn();

    @FXML
    private TableColumn<ItemsPharma, Float> rateTableColumn;

    @FXML
    private Button resetBtn;

    @FXML
    private TableView<ItemsPharma> saleItemTableview;

    @FXML
    private Button saveBtn;

    @FXML
    private Label successLabel;

    @FXML
    private TextField totalTextBox;

    @FXML
     private Label vat125AmtLabel;

    @FXML
    private Label vat5AmtLabel;

    @FXML
    private TextField vatTextBox;
    
    private int currentSelectedIndex = -1;
    
    private float vat5Amt = 0.0f;
    private float vat125Amt = 0.0f;
    private float totalAmt = 0.0f;
    
    private ObservableList<ItemsPharma> data = FXCollections.observableArrayList();
     private ItemSearchBox selectedItemSearchBox;
     EventBus eventBus = new EventBus();
    private ItemsPharma selectedItem;
    @FXML
    void initialize() {
       eventBus.register(this);
        assert AmountTableColumn != null : "fx:id=\"AmountTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert CDamtTextBox != null : "fx:id=\"CDamtTextBox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert batchTableColumn != null : "fx:id=\"batchTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert billNoTextbox != null : "fx:id=\"billNoTextbox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert custLicTextBox != null : "fx:id=\"custLicTextBox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert customerTextBox != null : "fx:id=\"customerTextBox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert dateTextBox != null : "fx:id=\"dateTextBox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert deliveryTextArea != null : "fx:id=\"deliveryTextArea\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert descTableColumn != null : "fx:id=\"descTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert discountTextBox != null : "fx:id=\"discountTextBox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert dmTableColumn != null : "fx:id=\"dmTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert errorLabel != null : "fx:id=\"errorLabel\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert expTableColumn != null : "fx:id=\"expTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert finalAmtTextBox != null : "fx:id=\"finalAmtTextBox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert makeTableColumn != null : "fx:id=\"makeTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert mrpTableColumn != null : "fx:id=\"mrpTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert packTableColumn != null : "fx:id=\"packTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert paymentComboBox != null : "fx:id=\"paymentComboBox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert printBtn != null : "fx:id=\"printBtn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert qntyTableColumn != null : "fx:id=\"qntyTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert rateTableColumn != null : "fx:id=\"rateTableColumn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert resetBtn != null : "fx:id=\"resetBtn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert saleItemTableview != null : "fx:id=\"saleItemTableview\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert successLabel != null : "fx:id=\"successLabel\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert totalTextBox != null : "fx:id=\"totalTextBox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert vat125AmtLabel != null : "fx:id=\"vat125AmtLabel\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert vat5AmtLabel != null : "fx:id=\"vat5AmtLabel\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        assert vatTextBox != null : "fx:id=\"vatTextBox\" was not injected: check your FXML file 'SalebillPharma.fxml'.";
        
        //selection listener for the tableview
       saleItemTableview.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {

            @Override
            public void onChanged(ListChangeListener.Change<? extends Integer> change) {
                if(change.getList().size() > 0)
                {
                                currentSelectedIndex = change.getList().get(0);
                    
                }
            }
        });
        

        mrpTableColumn.setCellValueFactory(new PropertyValueFactory<ItemsPharma, Float>("mrp"));
        dmTableColumn.setCellValueFactory(new PropertyValueFactory<ItemsPharma,String>("DM"));
        makeTableColumn.setCellValueFactory(new PropertyValueFactory<ItemsPharma,String>("make"));
        packTableColumn.setCellValueFactory(new PropertyValueFactory<ItemsPharma, String>("pack"));
        rateTableColumn.setCellValueFactory(new PropertyValueFactory<ItemsPharma,Float>("rateFraction"));
        descTableColumn.setCellValueFactory(new PropertyValueFactory<ItemsPharma,String>("description"));
        
        descTableColumn.setEditable(true);
        //textbox for the description
          Callback<TableColumn<ItemsPharma,String>, TableCell<ItemsPharma, String>> itNameCellfactory = 
                    new Callback<TableColumn<ItemsPharma, String>, TableCell<ItemsPharma, String>>() {

            @Override
            public TableCell<ItemsPharma, String> call(TableColumn<ItemsPharma, String> p) {
                
                currentSelectedIndex = p.getTableView().getSelectionModel().getSelectedIndex();
                selectedItemSearchBox =  new ItemSearchBox(eventBus,currentSelectedIndex);
                System.out.println(p.getTableView().getSelectionModel().getSelectedIndex());
                eventBus.register(selectedItemSearchBox);              
                return selectedItemSearchBox;
            }
        };
          descTableColumn.setCellFactory(itNameCellfactory);
        
        //make qnty col editable
          
           Callback<TableColumn<SalebillItem,Double>,TableCell<SalebillItem,Double>> cellFactory = 
                new Callback<TableColumn<SalebillItem,Double>,TableCell<SalebillItem,Double>>(){
                     public TableCell<SalebillItem,Double> call(TableColumn<SalebillItem,Double> p) {
                         EditableCell ec = new EditableCell(eventBus);
                         eventBus.register(ec);
                        return  ec;
                  }
                };
          
          qntyTableColumn.setCellFactory(cellFactory);

       initializeSaleBill();
        
    }
    

 
    @Subscribe
    public void updateAmount(String s){
        //ge tht equntity and rate at that column.
        ItemsPharma i  = saleItemTableview.getItems().get(currentSelectedIndex);
        float rate = i.getRateFraction();
        int qty = Integer.parseInt(s);
        
        float amount = rate*qty;
        float vatPerc = i.getVat();
        float vatAmt = 0.0f;
        if(Math.abs(vatPerc -5) < 0.0001){
            vatAmt = amount*0.05f;
            //TODO remove previous amount
            vat5Amt += vatAmt;
            vat5AmtLabel.setText(vat5AmtLabel.getText().concat(": "+vat5Amt));
        }else{
            vatAmt = amount * 0.125f;
            //TODO initialize vat 125 sale amt.
            vat125Amt += vatAmt;
            vat125AmtLabel.setText(vat125AmtLabel.getText().concat(": "+vat125Amt));
        }
        
          amount += vatAmt;
         
          
        
        System.out.println(amount+"\n"+vat5Amt+"\n"+ vat125Amt);
        
        
    }
    
 
    
    
    @Subscribe
    public void setSelectedItem(ItemsPharma userSelectedItem){
        System.out.println(currentSelectedIndex);
        selectedItem = userSelectedItem;         
        //int ind = saleItemTableview.getSelectionModel().getSelectedIndex();
        System.out.println(currentSelectedIndex + "index in controller");
        data.remove(currentSelectedIndex); 
        data.add(currentSelectedIndex, selectedItem);
        System.out.println(selectedItem.getDesc() + "in con");
         saleItemTableview.getColumns().get(0).setVisible(false);
         saleItemTableview.getColumns().get(0).setVisible(true);     
        
    }
        
    
    
        @FXML
    void handlePrintBtn(ActionEvent event) {
            
    }

    @FXML
    void handleResetBtn(ActionEvent event) {

     
    }

    @FXML
    void handleSaveBtn(ActionEvent event) {
        System.out.println("Button Clicked");
     //   data.add(new ItemsPharma());      
     
        
    }

    private void initializeSaleBill() {
       saleItemTableview.setItems(data);    
       ItemsPharma i = new ItemsPharma();
       i.setDesc("Check this out");
       data.add(i);
       
//       //get the items from the db here
//        EntityManager em = EntityManagerHelper.getInstance().getEm();
//        Query q = em.createNamedQuery("ItemsPharma.findAll");
//        ArrayList<ItemsPharma>  iList = new ArrayList(q.getResultList());
       
    }

}


//Editing Cell
//Qnty Cell Editor
 class EditableCell extends TableCell{
           private TextField textField;
           private EventBus appBus;
           
           public EditableCell(EventBus appBus){
               this.appBus = appBus;
           }
           
           public EditableCell(){}
           
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
            
            
              public void updateItem(Integer item, boolean empty) {
                  super.updateItem(item, empty);

                  if (empty) {
                      setText(null);
                      setGraphic(null);
                  } else {
                          setText(getString());
                          setContentDisplay(ContentDisplay.TEXT_ONLY);
                          appBus.post(getString());
                      }
                  
              }
           
           //textfield creation and settings
           private void createTextField(){
                textField = new TextField(getString());
                textField.setAlignment(Pos.CENTER);
                textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
                textField.setOnKeyPressed(new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent t) {
                        
                     //   System.out.println("inside key handle event");
                       // System.out.println(t.getCode());
                        if (t.getCode() == KeyCode.ENTER) {
                           updateItem(Integer.parseInt(textField.getText()), false);
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
