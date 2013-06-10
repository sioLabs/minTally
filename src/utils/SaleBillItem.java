/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author ashutoshsingh
 */
public class SaleBillItem {

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public float getItemQty() {
        return itemQty;
    }

    public void setItemQty(float itemQty) {
        this.itemQty = itemQty;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    public float getItemQty2() {
        return itemQty2;
    }

    public void setItemQty2(float itemQty2) {
        this.itemQty2 = itemQty2;
    }

    public String getItemUnit2() {
        return itemUnit2;
    }

    public void setItemUnit2(String itemUnit2) {
        this.itemUnit2 = itemUnit2;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public float getVatPerc() {
        return vatPerc;
    }

    public void setVatPerc(float vatPerc) {
        this.vatPerc = vatPerc;
    }

    public float getVatRs() {
        return vatRs;
    }

    public void setVatRs(float vatRs) {
        this.vatRs = vatRs;
    }
 
    private String itemName;
    private int itemId;
    private float itemQty;
    private String itemUnit;
    private float itemQty2;
    private String itemUnit2;
    private float total;
    private String remark;
    private float vatPerc;
    private float vatRs;
    
    
    
    
}
