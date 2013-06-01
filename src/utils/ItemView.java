/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author ashutoshsingh
 */
public class ItemView {

    public int getItemId() {
        return itemId.get();
    }

    public void setItemId(int itemId) {
        this.itemId.set(itemId);
    }

    public String getItemName() {
        return itemName.get();
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    public String getItemGroup() {
        return itemGroup.get();
    }

    public void setItemGroup(String itemGroup1) {
        this.itemGroup.set(itemGroup1);
    }

    public Integer getUnit() {
        return unit.get();
    }

    public void setUnit(Integer unit) {
        this.unit.set(unit);
    }


    
    private SimpleIntegerProperty itemId = new SimpleIntegerProperty();;
    private SimpleStringProperty itemName = new SimpleStringProperty();;
    private SimpleStringProperty itemGroup = new SimpleStringProperty();;
    private SimpleIntegerProperty unit = new SimpleIntegerProperty();;
    private SimpleFloatProperty itemStock = new SimpleFloatProperty();

    public Float getItemStock() {
        return itemStock.get();
    }

    public void setItemStock(float itemStock) {
        this.itemStock.set(itemStock);
    }
}
