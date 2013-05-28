/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
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
@Table(name = "item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findByItemId", query = "SELECT i FROM Item i WHERE i.itemId = :itemId"),
    @NamedQuery(name = "Item.findByItemName", query = "SELECT i FROM Item i WHERE i.itemName = :itemName"),
    @NamedQuery(name = "Item.findByItemUnit1", query = "SELECT i FROM Item i WHERE i.itemUnit1 = :itemUnit1"),
    @NamedQuery(name = "Item.findByItemRate", query = "SELECT i FROM Item i WHERE i.itemRate = :itemRate"),
    @NamedQuery(name = "Item.findByItemUnit2", query = "SELECT i FROM Item i WHERE i.itemUnit2 = :itemUnit2"),
    @NamedQuery(name = "Item.findByItemVatPerc", query = "SELECT i FROM Item i WHERE i.itemVatPerc = :itemVatPerc"),
    @NamedQuery(name = "Item.findByItemOpenStock", query = "SELECT i FROM Item i WHERE i.itemOpenStock = :itemOpenStock"),
    @NamedQuery(name = "Item.findByItemTotalValue", query = "SELECT i FROM Item i WHERE i.itemTotalValue = :itemTotalValue")})
public class Item implements Serializable {
    @Basic(optional = false)
    @Column(name = "item_unit_1")
    private int itemUnit1;
    @Basic(optional = false)
    @Column(name = "item_rate_unit")
    private int itemRateUnit;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "item_id")
    private Integer itemId;
    @Basic(optional = false)
    @Column(name = "item_name")
    private String itemName;
    @Basic(optional = false)
    @Column(name = "item_rate")
    private float itemRate;
    @Column(name = "item_unit_2")
    private String itemUnit2;
    @Basic(optional = false)
    @Column(name = "item_vat_perc")
    private float itemVatPerc;
    @Basic(optional = false)
    @Column(name = "item_open_stock")
    private float itemOpenStock;
    @Basic(optional = false)
    @Column(name = "item_total_value")
    private int itemTotalValue;
    @JoinColumn(name = "item_group", referencedColumnName = "item_group_id")
    @ManyToOne(optional = false)
    private ItemGroup itemGroup;

    public Item() {
    }

    public Item(Integer itemId) {
        this.itemId = itemId;
    }

    public Item(Integer itemId, String itemName, int itemUnit1, float itemRate, float itemVatPerc, float itemOpenStock, int itemTotalValue) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemUnit1 = itemUnit1;
        this.itemRate = itemRate;
        this.itemVatPerc = itemVatPerc;
        this.itemOpenStock = itemOpenStock;
        this.itemTotalValue = itemTotalValue;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemUnit1() {
        return itemUnit1;
    }

    public void setItemUnit1(int itemUnit1) {
        this.itemUnit1 = itemUnit1;
    }

    public float getItemRate() {
        return itemRate;
    }

    public void setItemRate(float itemRate) {
        this.itemRate = itemRate;
    }

    public String getItemUnit2() {
        return itemUnit2;
    }

    public void setItemUnit2(String itemUnit2) {
        this.itemUnit2 = itemUnit2;
    }

    public float getItemVatPerc() {
        return itemVatPerc;
    }

    public void setItemVatPerc(float itemVatPerc) {
        this.itemVatPerc = itemVatPerc;
    }

    public float getItemOpenStock() {
        return itemOpenStock;
    }

    public void setItemOpenStock(float itemOpenStock) {
        this.itemOpenStock = itemOpenStock;
    }

    public int getItemTotalValue() {
        return itemTotalValue;
    }

    public void setItemTotalValue(int itemTotalValue) {
        this.itemTotalValue = itemTotalValue;
    }

    public ItemGroup getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(ItemGroup itemGroup) {
        this.itemGroup = itemGroup;
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
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.Item[ itemId=" + itemId + " ]";
    }

 
    public int getItemRateUnit() {
        return itemRateUnit;
    }

    public void setItemRateUnit(int itemRateUnit) {
        this.itemRateUnit = itemRateUnit;
    }
    
}
