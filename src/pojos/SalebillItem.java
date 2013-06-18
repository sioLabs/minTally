/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ashutoshsingh
 */
@Entity
@Table(name = "salebill_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalebillItem.findAll", query = "SELECT s FROM SalebillItem s"),
    @NamedQuery(name = "SalebillItem.findById", query = "SELECT s FROM SalebillItem s WHERE s.id = :id"),
    @NamedQuery(name = "SalebillItem.findByItemId", query = "SELECT s FROM SalebillItem s WHERE s.itemId = :itemId"),
    @NamedQuery(name = "SalebillItem.findByItemName", query = "SELECT s FROM SalebillItem s WHERE s.itemName = :itemName"),
    @NamedQuery(name = "SalebillItem.findByItemQnty", query = "SELECT s FROM SalebillItem s WHERE s.itemQnty = :itemQnty"),
    @NamedQuery(name = "SalebillItem.findByItemUnit", query = "SELECT s FROM SalebillItem s WHERE s.itemUnit = :itemUnit"),
    @NamedQuery(name = "SalebillItem.findByItemRate", query = "SELECT s FROM SalebillItem s WHERE s.itemRate = :itemRate"),
    @NamedQuery(name = "SalebillItem.findByTotal", query = "SELECT s FROM SalebillItem s WHERE s.total = :total"),
    @NamedQuery(name = "SalebillItem.findByRemark", query = "SELECT s FROM SalebillItem s WHERE s.remark = :remark"),
    @NamedQuery(name = "SalebillItem.findByItemVatRs", query = "SELECT s FROM SalebillItem s WHERE s.itemVatRs = :itemVatRs")})
public class SalebillItem implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    private int itemId;

    private StringProperty itemName = new SimpleStringProperty();
    
    private DoubleProperty itemQnty = new SimpleDoubleProperty();
    
    private IntegerProperty itemUnit = new SimpleIntegerProperty();
    
    private StringProperty itemUnitName = new SimpleStringProperty();
    
    
    private DoubleProperty itemRate =  new SimpleDoubleProperty();
    
    
    private DoubleProperty total = new SimpleDoubleProperty();
    
    
    private StringProperty remark = new SimpleStringProperty();
    
    
    private DoubleProperty itemVatRs = new SimpleDoubleProperty();
    
    private DoubleProperty itemVatPerc = new SimpleDoubleProperty();

    public String getItemUnitName() {
        return itemUnitName.getValue();
    }

    public void setItemUnitName(String itemUnitName) {
        this.itemUnitName.set(itemUnitName);
    }

    public Double getItemVatPerc() {
        return itemVatPerc.getValue();
    }

    public void setItemVatPerc(Double itemVatPerc) {
        this.itemVatPerc.setValue(itemVatPerc);
    }
    
    
    private SaleBill saleBillNo;

    public SalebillItem() {
    }

    public SalebillItem(Integer id) {
        this.id = id;
    }

    public SalebillItem(Integer id, int itemId, String itemName, double itemQnty, int itemUnit, double itemRate, double total, int itemVatRs) {
        this.id = id;
        this.itemId = itemId;
        this.itemName.setValue(itemName);
        //this.itemQnty = itemQnty;
        ///this.itemUnit = itemUnit;
        //this.itemRate = itemRate;
        //this.total = total;
        //this.itemVatRs = itemVatRs;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "item_id")
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic(optional = false)
    @Column(name = "item_name")
    public String getItemName() {
        return itemName.getValueSafe();
    }

    public void setItemName(String itemName) {
        this.itemName.setValue(itemName);
    }

    
    @Basic(optional = false)
    @Column(name = "item_qnty")
    public Double getItemQnty() {
        return itemQnty.getValue();
    }

    public void setItemQnty(Double itemQnty) {
        this.itemQnty.setValue(itemQnty);
    }

    @Basic(optional = false)
    @Column(name = "item_unit")
    public Integer getItemUnit() {
        return itemUnit.getValue();
    }

    public void setItemUnit(Integer itemUnit) {
        this.itemUnit.setValue(itemUnit);
    }

    @Basic(optional = false)
    @Column(name = "item_rate")
    public Double getItemRate() {
        return itemRate.getValue();
    }

    public void setItemRate(Double itemRate) {
        this.itemRate.setValue(itemRate);
    }

    @Basic(optional = false)
    @Column(name = "total") 
    public Double getTotal() {
        return total.getValue();
    }

    public void setTotal(Double total) {
        this.total.setValue(total);
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark.getValue();
    }

    public void setRemark(String remark) {
        this.remark.setValue(remark);
    }

    @Basic(optional = false)
    @Column(name = "item_vat_rs")
    public Double getItemVatRs() {
        return itemVatRs.getValue();
    }

    public void setItemVatRs(Double itemVatRs) {
        this.itemVatRs.setValue(itemVatRs);
    }

    @JoinColumn(name = "sale_bill_no", referencedColumnName = "sale_bill_no")
    @ManyToOne(optional = false)
    public SaleBill getSaleBillNo() {
        return saleBillNo;
    }

    public void setSaleBillNo(SaleBill saleBillNo) {
        this.saleBillNo = saleBillNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalebillItem)) {
            return false;
        }
        SalebillItem other = (SalebillItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.SalebillItem[ id=" + id + " ]";
    }
    
}
