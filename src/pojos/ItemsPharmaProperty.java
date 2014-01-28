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

    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty DM = new SimpleStringProperty();
    StringProperty make = new SimpleStringProperty();
    StringProperty batch = new SimpleStringProperty();
    StringProperty date = new SimpleStringProperty();
    StringProperty description = new SimpleStringProperty();
    IntegerProperty pack = new SimpleIntegerProperty();
    FloatProperty mrp = new SimpleFloatProperty();
    FloatProperty rate = new SimpleFloatProperty();
    FloatProperty vat = new SimpleFloatProperty();
            

    public ItemsPharmaProperty(){
        
    }
    public ItemsPharmaProperty( int id, String dm, String make, String batch, String date, String desc, int pack, float mrp, float rate, float vat) {
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

    public IntegerProperty getId() {
        return id;
    }

    public void setId(IntegerProperty id) {
        this.id = id;
    }

    public StringProperty getDM() {
        return DM;
    }

    public void setDM(StringProperty DM) {
        this.DM = DM;
    }

    public StringProperty getMake() {
        return make;
    }

    public void setMake(StringProperty make) {
        this.make = make;
    }

    public StringProperty getBatch() {
        return batch;
    }

    public void setBatch(StringProperty batch) {
        this.batch = batch;
    }

    public StringProperty getDate() {
        return date;
    }

    public void setDate(StringProperty date) {
        this.date = date;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public IntegerProperty getPack() {
        return pack;
    }

    public void setPack(IntegerProperty pack) {
        this.pack = pack;
    }

    public FloatProperty getMrp() {
        return mrp;
    }

    public void setMrp(FloatProperty mrp) {
        this.mrp = mrp;
    }

    public FloatProperty getRate() {
        return rate;
    }


    public void setRate(FloatProperty rate) {
        this.rate = rate;
    }



   

}
