/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import java.util.ArrayList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.event.EventType;
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
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import pojos.Items;
import pojos.SalebillItem;

/**
 *
 * @author ashutoshsingh
 */
public class ItemSearchBox extends TableCell<SalebillItem, String> {
    
    private SimpleStringProperty itemName = new SimpleStringProperty();
    private Popup popup;
    private ItemTextField itemTextField;
    private SimpleDoubleProperty itemTextWidth = new SimpleDoubleProperty(100);
    private ChangeListener<Boolean> focusOutListener;
    private ListView<Items> itemsListView = new ListView<Items>();
    private Items selectedItem;
    private EventBus eventBus ;

    public ItemSearchBox(EventBus e) {
        super();
         this.eventBus = e; 
         setAlignment(Pos.CENTER);
         configureItemSearchBox();
    }
    
    public Items getSelectedItem(){
        return selectedItem;
    }
    
    
    private void configureItemSearchBox(){
        popup = new Popup();
        popup.setAutoHide(true);
        popup.setAutoFix(true);
        popup.setHideOnEscape(true);
        
         addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
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
                Items i = itemsListView.getSelectionModel().getSelectedItem();
                    System.out.println("Item Selected is :" + i);
                    selectedItem = i;
                    eventBus.post(selectedItem);
                    commitEdit(selectedItem.getItemName());
                    //updateItem(i.getItemName(), false);
                    
                    hidePopup();
            }
             
         });
         itemsListView.setOnKeyPressed(new EventHandler<KeyEvent>(){

            @Override @Subscribe
            public void handle(KeyEvent t) {
                if(KeyCode.ENTER.equals(t.getCode())){
                    //Enter pressed
                    Items i = itemsListView.getSelectionModel().getSelectedItem();
                    System.out.println("Item Selected is :" + i);
                    selectedItem = i;
                    eventBus.post(selectedItem);
                    commitEdit(selectedItem.getItemName());
                    //updateItem(i.getItemName(), false);
                    hidePopup();
                }
            }
             
         });
         popup.getContent().add(itemsListView);           
         
         //creating the itemname text field
         //TODO empty for now
         itemTextField = new ItemTextField();
         itemTextField.prefWidthProperty().bind(itemTextWidth);
         this.focusOutListener = new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                System.out.println("focus chaneged");
                //if(t1){
                  
                //}
                  
            }
        };
         
         itemTextField.focusedProperty().addListener(focusOutListener);
         
         getChildren().addAll(itemTextField);
         itemTextField.setVisible(true);
                
    }
    
    private void initiatePopUp(String text){
        
           itemsListView.getItems().clear();
        
           //System.out.println("in initiate:" + text);
            
            //write the code to get the items List from the db and then 
            //show it here 
            text = "%"+text+"%";
            EntityManager em = EntityManagerHelper.getInstance().getEm();
            Query q = em.createNamedQuery("Items.findByItemsNameLike");
            q.setParameter("itemName", text);
            ArrayList<Items> list = new ArrayList(q.getResultList());
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
    
    
              @Override
              public void updateItem(String item, boolean empty) {
                  super.updateItem(item, empty);

                  System.out.println("in update item");
                  if (empty) {
                      setText(null);
                      setGraphic(null);
                  } else {
                      if (isEditing()) {
                          if (itemTextField != null) {
                              itemTextField.setText(getString());
                          }
                          setGraphic(itemTextField);
                          setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                      } else {
                          setText(getString());
                          setContentDisplay(ContentDisplay.TEXT_ONLY);
                      }
                  }
              }
               @Override
               public void startEdit(){
                   super.startEdit();
          //         if(itemTextField == null)
            //           createTextField();
                   setGraphic(itemTextField);
                   setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                   itemTextField.selectAll();
               }
               @Override 
               public void cancelEdit(){
                   super.cancelEdit();
                   setText(String.valueOf(getItem()));
                   setContentDisplay(ContentDisplay.TEXT_ONLY);
               }

    private void createTextField() {
        //itemTextField = new TextField(getString());
        //itemTextField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
        
    }

    private String getString() {
         return getItem() == null ? "" : getItem().toString();
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

