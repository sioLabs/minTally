/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ashutoshsingh
 */
public class TableSaleBill {

 
    
    private SimpleIntegerProperty billNo = new SimpleIntegerProperty();
    private SimpleStringProperty customerName = new SimpleStringProperty();
    private SimpleDoubleProperty billAmt = new SimpleDoubleProperty();
    
    private int custID ;
    
    public SimpleIntegerProperty getBillNo() {
        return billNo;
    }

    public void setBillNo(SimpleIntegerProperty billNo) {
        this.billNo = billNo;
    }

    public SimpleStringProperty getCustomerName() {
        return customerName;
    }

    public void setCustomerName(SimpleStringProperty customerName) {
        this.customerName = customerName;
    }

    public SimpleDoubleProperty getBillAmt() {
        return billAmt;
    }

    public void setBillAmt(SimpleDoubleProperty billAmt) {
        this.billAmt = billAmt;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }
    
    
    
}
