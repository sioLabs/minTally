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

    private Double itemOpenStock;
 
    private String itemRackNo;
    @Column(name = "item_code")
    private String itemCode;
    @Column(name = "item_rate")
    private Double itemRate;
    @Column(name = "item_vat_perc")
    private Double itemVatPerc;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "item_id")
    private Integer itemId;
    @Basic(optional = false)
    @Column(name = "item_name")
    private String itemName;
    @JoinColumn(name = "item_first_unit", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Units itemFirstUnit;
    @JoinColumn(name = "item_sub_group", referencedColumnName = "item_group_id")
    @ManyToOne
    private ItemGroup itemSubGroup;
    @JoinColumn(name = "item_group", referencedColumnName = "item_group_id")
    @ManyToOne(optional = false)
    private ItemGroup itemGroup;
    @JoinColumn(name = "item_second_unit", referencedColumnName = "id")
    @ManyToOne
    private Units itemSecondUnit;

    public Items() {
    }

    public Items(Integer itemId) {
        this.itemId = itemId;
    }

    public Items(Integer itemId, double itemOpenStock, double itemRate, double itemVatPerc, String itemName) {
        this.itemId = itemId;
        this.itemOpenStock = itemOpenStock;
        this.itemRate = itemRate;
        this.itemVatPerc = itemVatPerc;
        this.itemName = itemName;
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

    public Units getItemFirstUnit() {
        return itemFirstUnit;
    }

    public void setItemFirstUnit(Units itemFirstUnit) {
        this.itemFirstUnit = itemFirstUnit;
    }

    public ItemGroup getItemSubGroup() {
        return itemSubGroup;
    }

    public void setItemSubGroup(ItemGroup itemSubGroup) {
        this.itemSubGroup = itemSubGroup;
    }

    public ItemGroup getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(ItemGroup itemGroup) {
        this.itemGroup = itemGroup;
    }

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
        return itemOpenStock;
    }

    public void setItemOpenStock(Double itemOpenStock) {
        this.itemOpenStock = itemOpenStock;
    }

    @Column(name = "item_rack_no")
    public String getItemRackNo() {
        return itemRackNo;
    }

    public void setItemRackNo(String itemRackNo) {
        this.itemRackNo = itemRackNo;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Double getItemRate() {
        return itemRate;
    }

    public void setItemRate(Double itemRate) {
        this.itemRate = itemRate;
    }

    public Double getItemVatPerc() {
        return itemVatPerc;
    }

    public void setItemVatPerc(Double itemVatPerc) {
        this.itemVatPerc = itemVatPerc;
    }
    
}
