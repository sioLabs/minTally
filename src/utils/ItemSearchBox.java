/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import pojos.Items;

/**
 *
 * @author ashutoshsingh
 */
public class ItemSearchBox extends HBox {
    
    private SimpleStringProperty itemName = new SimpleStringProperty();
    private Popup popup;
    private ItemTextField itemTextField;
    private SimpleDoubleProperty itemTextWidth = new SimpleDoubleProperty(100);
    private ChangeListener<Boolean> focusOutListener;
    private ListView<Items> itemsListView;

    public ItemSearchBox() {
        super();
         setAlignment(Pos.CENTER);
         configureItemSearchBox();
    }
    
    
    
    private void configureItemSearchBox(){
        popup = new Popup();
        
        popup.setAutoHide(true);
        popup.setAutoFix(true);
        popup.setHideOnEscape(true);
        
         addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                        public void handle(KeyEvent event) {
                                if (KeyCode.UP.equals(event.getCode()) || KeyCode.DOWN.equals(event.getCode()) || KeyCode.ENTER.equals(event.getCode())) {
                                        initiatePopUp();
                                        showPopup();
                                } else if (KeyCode.TAB.equals(event.getCode())) {
                                        hidePopup();
                                }
                        }
                });
         
         //creating the itemname text field
         //TODO empty for now
         itemTextField = new ItemTextField();
         itemTextField.prefWidthProperty().bind(itemTextWidth);
         this.focusOutListener = new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                System.out.println("changed focus: "+ itemTextField.getText());
            }
        };
         
         itemTextField.focusedProperty().addListener(focusOutListener);
         
         getChildren().addAll(itemTextField);
         itemTextField.setVisible(true);
                
    }
    
    private void initiatePopUp(){
        if(itemsListView == null){
            itemsListView = new ListView<>();
            popup.getContent().add(itemsListView);
            
        }
    }
    
    private void showPopup(){
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
    
    
    
    
    
    
    
    
     /**
         * Cell Inteface
         * 
         * @author Sai.Dandem
         * 
         * @param <ItemType>
         */
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

