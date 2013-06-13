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
@Table(name = "items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Items.findAll", query = "SELECT i FROM Items i"),
    @NamedQuery(name = "Items.findByItemId", query = "SELECT i FROM Items i WHERE i.itemId = :itemId"),
    @NamedQuery(name = "Items.findByItemOpenStock", query = "SELECT i FROM Items i WHERE i.itemOpenStock = :itemOpenStock"),
    @NamedQuery(name = "Items.findByItemRackNo", query = "SELECT i FROM Items i WHERE i.itemRackNo = :itemRackNo"),
    @NamedQuery(name = "Items.findByItemCode", query = "SELECT i FROM Items i WHERE i.itemCode = :itemCode"),
    @NamedQuery(name = "Items.findByItemRate", query = "SELECT i FROM Items i WHERE i.itemRate = :itemRate"),
    @NamedQuery(name = "Items.findByItemVatPerc", query = "SELECT i FROM Items i WHERE i.itemVatPerc = :itemVatPerc"),
    @NamedQuery(name = "Items.findByItemName", query = "SELECT i FROM Items i WHERE i.itemName = :itemName")})
    public class Items implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    private DoubleProperty itemOpenStock =  new SimpleDoubleProperty();
 
    private StringProperty itemRackNo = new SimpleStringProperty();
    
    private StringProperty  itemCode = new SimpleStringProperty();
    
    private DoubleProperty itemRate = new SimpleDoubleProperty();
    
    private DoubleProperty itemVatPerc = new SimpleDoubleProperty();
    
    private static final long serialVersionUID = 1L;
    
    
    private IntegerProperty itemId = new SimpleIntegerProperty();
    
    private StringProperty itemName = new SimpleStringProperty();
    
    private Units itemFirstUnit;
    
    private ItemGroup itemSubGroup;
    
    private ItemGroup itemGroup;
    
    private Units itemSecondUnit;

    public Items() {
    }

    public Items(Integer itemId) {
        this.itemId.set(itemId);
    }

    public Items(Integer itemId, double itemOpenStock, double itemRate, double itemVatPerc, String itemName) {
        this.itemId.set(itemId);
        this.itemOpenStock.set(itemOpenStock); 
        this.itemRate.set(itemRate);
        this.itemVatPerc.set(itemVatPerc); 
        this.itemName.set(itemName);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "item_id")
    public Integer getItemId() {
        return itemId.get();
    }

    public void setItemId(Integer itemId) {
        this.itemId.set(itemId);
    }


    @Basic(optional = false)
    @Column(name = "item_name")
    public String getItemName() {
        return itemName.get();
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    @JoinColumn(name = "item_first_unit", referencedColumnName = "id")
    @ManyToOne(optional = false)
    public Units getItemFirstUnit() {
        return itemFirstUnit;
    }

    public void setItemFirstUnit(Units itemFirstUnit) {
        this.itemFirstUnit = itemFirstUnit;
    }

    @JoinColumn(name = "item_sub_group", referencedColumnName = "item_group_id")
    @ManyToOne
    public ItemGroup getItemSubGroup() {
        return itemSubGroup;
    }

    public void setItemSubGroup(ItemGroup itemSubGroup) {
        this.itemSubGroup = itemSubGroup;
    }

    @JoinColumn(name = "item_group", referencedColumnName = "item_group_id")
    @ManyToOne(optional = false)
    public ItemGroup getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(ItemGroup itemGroup) {
        this.itemGroup = itemGroup;
    }

    @JoinColumn(name = "item_second_unit", referencedColumnName = "id")
    @ManyToOne
    public Units getItemSecondUnit() {
        return itemSecondUnit;
    }

    public void setItemSecondUnit(Units itemSecondUnit) {
        this.itemSecondUnit = itemSecondUnit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Items)) {
            return false;
        }
        Items other = (Items) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.Items[ itemId=" + itemId + " ]";
    }

    @Column(name = "item_open_stock")
    public Double getItemOpenStock() {
        return itemOpenStock.get();
    }

    public void setItemOpenStock(Double itemOpenStock) {
        this.itemOpenStock.set(itemOpenStock);
    }

    @Column(name = "item_rack_no")
    public String getItemRackNo() {
        return itemRackNo.get();
    }

    public void setItemRackNo(String itemRackNo) {
        this.itemRackNo.set(itemRackNo);
    }

    @Column(name = "item_code")
    public String getItemCode() {
        return itemCode.get();
    }

    public void setItemCode(String itemCode) {
        this.itemCode.set(itemCode);
    }

    @Column(name = "item_rate")
    public Double getItemRate() {
        return itemRate.get();
    }

    
    public void setItemRate(Double itemRate) {
        this.itemRate.set(itemRate);
    }

    @Column(name = "item_vat_perc")
    public Double getItemVatPerc() {
        return itemVatPerc.get();
    }

    public void setItemVatPerc(Double itemVatPerc) {
        this.itemVatPerc.set(itemVatPerc);
    }
    
}
