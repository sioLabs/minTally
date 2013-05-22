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
 * @author t0m
 */
public class LedgerView {

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public float getBal() {
        return bal.get();
    }

    public void setBal(float bal) {
        this.bal.set(bal);
    }
    
    public SimpleIntegerProperty id = new SimpleIntegerProperty();
    public SimpleStringProperty name = new SimpleStringProperty();
    public SimpleFloatProperty bal = new SimpleFloatProperty();
   
    
    
}
