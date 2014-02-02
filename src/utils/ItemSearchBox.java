
package utils;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import pojos.ItemsPharma;
import pojos.SaleBillPharmaItem;

/**
 *
 * @author ashutoshsingh
 */
public class ItemSearchBox extends TableCell {

    
    private Popup popup;
    private TextField itemTextField;
    private ListView<ItemsPharma> itemsListView = new ListView<ItemsPharma>();
    private ItemsPharma selectedItem;
    private EventBus eventBus ;
    

    public ItemSearchBox(EventBus e, String itemName) {
        super();
         this.eventBus = e; 
    
        
         setAlignment(Pos.CENTER);
         configureItemSearchBox();
    
    }
    public ItemSearchBox()
    {
        
    }
    
    public ItemsPharma getSelectedItem(){
        return selectedItem;
    }
    
    
    private void configureItemSearchBox(){
        popup = new Popup();
        popup.setAutoHide(true);
        popup.setAutoFix(true);
        popup.setHideOnEscape(true);

         addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                              if (KeyCode.TAB.equals(event.getCode())) {
                                       hidePopup();
                                        return;
                              }
                            initiatePopUp(itemTextField.getText());
                            showPopup();
                            if(KeyCode.ENTER.equals(event.getCode())){
                                codeOnEnter();
                            }
                                      
                           
                        }
                });
         
         //actions listener for the listBox
         itemsListView.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent t) {
                System.out.println("Clicked on "+itemsListView.getSelectionModel().getSelectedItem());
                ItemsPharma i = itemsListView.getSelectionModel().getSelectedItem();
                
                    System.out.println("Item Selected is :" + i);
                    selectedItem = i;
                    commitEdit(selectedItem.toString());
                    eventBus.post(selectedItem);
             
                    hidePopup();
            }
             
         });
         itemsListView.setOnKeyPressed(new EventHandler<KeyEvent>(){

            @Override 
            public void handle(KeyEvent t) {
                if(KeyCode.ENTER.equals(t.getCode())){
                    //Enter pressed
                    ItemsPharma i = itemsListView.getSelectionModel().getSelectedItem();
                    System.out.println("keyb Item Selected is :" + i);
                    //selectedItem = i;
                    setText(i.toString());
                    //updateItem(i.toString(), false);
                    commitEdit(selectedItem.toString());
                    //eventBus.post(selectedItem);                   
                    hidePopup();
                }
            }
             
         });
         popup.getContent().add(itemsListView);           
         
         //creating the itemname text field
         //TODO empty for now
         itemTextField = new TextField();
                 


         
         getChildren().addAll(itemTextField);
         
         itemTextField.setVisible(true);
                
    }
    
    private void initiatePopUp(String text){
        
           itemsListView.getItems().clear();
         
            //write the code to get the items List from the db and then 
            //show it here 
            text = text.trim();
            text = "%"+text+"%";
            EntityManager em = EntityManagerHelper.getInstance().getEm();
            Query q  = null;
            if(text.length() ==0 )
                q = em.createNamedQuery("Items.findAll");
            else
            {q= em.createNamedQuery("Items.findByItemsPharmaNameLike");
                q.setParameter("description", text);
            }
            ArrayList<ItemsPharma> list = new ArrayList(q.getResultList());
            System.out.println(list.size()+ " items found");
            itemsListView.getItems().addAll(list);
        //}
        //
        
    }
    
    private void showPopup(){
        System.out.println("in show popup");
        Parent parent = getParent();
        Bounds childBounds = getBoundsInParent();
        Bounds parentBounds = parent.localToScene(parent.getBoundsInLocal());
        double layoutX = childBounds.getMinX() + parentBounds.getMinX() + parent.getScene().getX() + parent.getScene().getWindow().getX();
        double layoutY = childBounds.getMaxY() + parentBounds.getMinY() + parent.getScene().getY() + parent.getScene().getWindow().getY();
        popup.show(this, layoutX, layoutY);        
    }
    
    private void hidePopup(){
        popup.hide();
    }
    
    
               //all the view is created by the update item. I have to show the selected Item now who calls the updateItem
               //an event in popup will call the updateITem.
             //@Override
              public  void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);

                  if (empty) {
                      setText(null);
                      setGraphic(null);
                  } else {
                          setText(getString());
                          setContentDisplay(ContentDisplay.TEXT_ONLY);
                          eventBus.post(getString());
                      }
                  
              }
              
              @Override
               public void startEdit(){
                           super.startEdit();
               if(itemTextField == null)
                   createTextField();
               
               setGraphic(itemTextField);
               setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
               itemTextField.selectAll();
               }

          @Override
          public void cancelEdit() {
            super.cancelEdit();

            setText(String.valueOf(getItem()));
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
              
    private void createTextField() {
           itemTextField = new TextField();
           itemTextField.setAlignment(Pos.CENTER);
           itemTextField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
           itemTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

               @Override
               public void handle(KeyEvent t) {
                  if(t.getCode() == KeyCode.ESCAPE){
                      cancelEdit();
                  }
                  else{
                                          codeOnEnter();
                  }
               }
           });
           
         
       
   }
    
  public void codeOnEnter(){
                commitEdit(selectedItem.getDesc());
           }
           private String getString() {
                    return getItem() == null ? "" : getItem().toString();
           }
}  
    
     /**
         * Cell Inteface
         * 
         * @author Sai.Dandem
         * 
         * @param <ItemType>
//         */
//        public static interface Cell {
//                Node getNode();
//
//                void updateItem(String item);
//
//        }
//    
//  /**
//         * Simple Cell Class
//         * 
//         * @author Sai.Dandem
//         * 
//         * @param <ItemType>
//         */
//        public static class ItemTextField extends TextField implements Cell {
//                public ItemTextField() {
//                        setEditable(true);
//                                
//                        setPrefHeight(22);
//                        setPromptText("Start Typing Item Name");
//                }
//
//                public Node getNode() {
//                        return this;
//                }
//
//                public void updateItem(String item) {
//                        setText(item != null ? item.toString() : "");
//                }
//        }


    
    


