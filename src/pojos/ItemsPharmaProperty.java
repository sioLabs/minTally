/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.text.ParseException;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author ashutoshsingh
 */
public class ItemsPharmaProperty {

    SimpleIntegerProperty id = new SimpleIntegerProperty();
    SimpleStringProperty DM = new SimpleStringProperty();
    SimpleStringProperty make = new SimpleStringProperty();
    SimpleStringProperty batch = new SimpleStringProperty();
    SimpleStringProperty date = new SimpleStringProperty();
    SimpleStringProperty description = new SimpleStringProperty();
    SimpleStringProperty pack = new SimpleStringProperty();
    SimpleFloatProperty mrp = new SimpleFloatProperty();
    SimpleFloatProperty rate = new SimpleFloatProperty();
    SimpleFloatProperty vat = new SimpleFloatProperty();
            

    public ItemsPharmaProperty(){
        
    }
    public ItemsPharmaProperty( int id, String dm, String make, String batch, String date, String desc, String pack, float mrp, float rate, float vat) {
        this.id.set(id);
        this.DM.set(dm);
        this.make.set(make);
        this.batch.set(batch);
        this.date.set(date);
        this.description.set(desc);
        this.pack.set(pack);
        this.mrp.set(mrp);
        this.rate.set(rate);
        this.vat.set(vat);
    }


    
  public ItemsPharma getModelObj() throws ParseException{
     
      return new ItemsPharma(id.get(), DM.get(), make.get(), batch.get(), date.get(), description.get(), pack.get(),mrp.get(), rate.get(), vat.get());
      
      
   
  }

    public SimpleIntegerProperty getId() {
        return id;
    }

    public void setId(SimpleIntegerProperty id) {
        this.id = id;
    }

    public SimpleStringProperty getDM() {
        return DM;
    }

    public void setDM(SimpleStringProperty DM) {
        this.DM = DM;
    }

    public SimpleStringProperty getMake() {
        return make;
    }

    public void setMake(SimpleStringProperty make) {
        this.make = make;
    }

    public SimpleStringProperty getBatch() {
        return batch;
    }

    public void setBatch(SimpleStringProperty batch) {
        this.batch = batch;
    }

    public SimpleStringProperty getDate() {
        return date;
    }

    public void setDate(SimpleStringProperty date) {
        this.date = date;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getPack() {
        return pack.get();
    }

    public void setPack(String pack) {
        this.pack.set(pack);
    }

    public SimpleFloatProperty getMrp() {
        return mrp;
    }

    public void setMrp(SimpleFloatProperty mrp) {
        this.mrp = mrp;
    }

    public SimpleFloatProperty getRate() {
        return rate;
    }


    public void setRate(SimpleFloatProperty rate) {
        this.rate = rate;
    }



   

}
