
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

/**
 *
 * @author ashutoshsingh
 */
public class ItemSearchBox extends TableCell<ItemsPharma, String> {

    private SimpleStringProperty itemName = new SimpleStringProperty();
    private Popup popup;
    private ItemTextField itemTextField;
    private SimpleDoubleProperty itemTextWidth = new SimpleDoubleProperty(100);
    private ChangeListener<Boolean> focusOutListener;
    private ListView<ItemsPharma> itemsListView = new ListView<ItemsPharma>();
    private ItemsPharma selectedItem;
    private EventBus eventBus ;
    private int rowInd;

    public ItemSearchBox(EventBus e, int rowInd) {
        super();
         this.eventBus = e; 
         this.rowInd = rowInd;
         setAlignment(Pos.CENTER);
         configureItemSearchBox();
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
                              //  if (KeyCode.UP.equals(event.getCode()) || KeyCode.DOWN.equals(event.getCode()) || KeyCode.ENTER.equals(event.getCode())) {
                                        System.out.println(itemTextField.getText() + ": in key pressed");
                                        initiatePopUp(itemTextField.getText());
                                        showPopup();
                                //} else
                                      
                           
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
                    eventBus.post(selectedItem);
                     updateItem(i.toString(), false);
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
                    selectedItem = i;
                    updateItem(i.toString(), false);
                    eventBus.post(selectedItem);                   
                    hidePopup();
                }
            }
             
         });
         popup.getContent().add(itemsListView);           
         
         //creating the itemname text field
         //TODO empty for now
         itemTextField = new ItemTextField();
                 
         itemTextField.prefWidthProperty().bind(itemTextWidth);
                  
//         this.focusOutListener = new ChangeListener<Boolean>() {
//
//            @Override
//            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
//               // System.out.println("focus chaneged");
//                //if(t1){
//                  
//                //}
//                  
//            }
//        };
//         
//         itemTextField.focusedProperty().addListener(focusOutListener);
         
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
              @Override
              public void updateItem(String item, boolean empty) {
                  super.updateItem(item, empty);
                   
                  
                  if (empty) {
                         setText(getString());
                          setContentDisplay(ContentDisplay.TEXT_ONLY);
                  } else {
                          System.out.println(selectedItem.toString() + "update Item");
                          setText(selectedItem.toString());
                          setContentDisplay(ContentDisplay.TEXT_ONLY);
                      
                  }
              }
              
              @Override
               public void startEdit(){
                  super.startEdit();
                  this.requestFocus();
                  setGraphic(itemTextField);
                  setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                  itemTextField.selectAll();                 
               }
//               @Override 
//               public void cancelEdit(){
//                   super.cancelEdit();
//                   //setText(String.valueOf(getItem()));
//                   //setContentDisplay(ContentDisplay.TEXT_ONLY);
//               }
//
//    private void createTextField() {
//  //   itemTextField = new ItemTextField();
//   //    itemTextField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
//        
//    }

    private String getString() {
         return selectedItem == null ? "" : selectedItem.toString();
        
        //return selectedItem.toString();
    }
    
    
     /**
         * Cell Inteface
         * 
         * @author Sai.Dandem
         * 
         * @param <ItemType>
//         */
        public static interface Cell {
                Node getNode();

                void updateItem(String item);

        }
    
  /**
         * Simple Cell Class
         * 
         * @author Sai.Dandem
         * 
         * @param <ItemType>
         */
        public static class ItemTextField extends TextField implements Cell {
                public ItemTextField() {
                        setEditable(true);
                                
                        setPrefHeight(22);
                        setPromptText("Start Typing Item Name");
                }

                public Node getNode() {
                        return this;
                }

                public void updateItem(String item) {
                        setText(item != null ? item.toString() : "");
                }
        }


    
    
}

