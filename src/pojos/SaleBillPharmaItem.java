/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ashutoshsingh
 */
@Entity
@Table(name = "sale_bill_pharma_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SaleBillPharmaItem.findAll", query = "SELECT s FROM SaleBillPharmaItem s"),
    @NamedQuery(name = "SaleBillPharmaItem.findById", query = "SELECT s FROM SaleBillPharmaItem s WHERE s.id = :id"),
    @NamedQuery(name = "SaleBillPharmaItem.findByQnty", query = "SELECT s FROM SaleBillPharmaItem s WHERE s.qnty = :qnty"),
    @NamedQuery(name = "SaleBillPharmaItem.findByItemRate", query = "SELECT s FROM SaleBillPharmaItem s WHERE s.itemRate = :itemRate"),
    @NamedQuery(name = "SaleBillPharmaItem.findByAmt", query = "SELECT s FROM SaleBillPharmaItem s WHERE s.amt = :amt"),
    @NamedQuery(name = "SaleBillPharmaItem.findByItemVatRs", query = "SELECT s FROM SaleBillPharmaItem s WHERE s.itemVatRs = :itemVatRs")})
public class SaleBillPharmaItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "qnty")
    private String qnty;
    @Basic(optional = false)
    @Column(name = "item_rate")
    private float  itemRate;
    @Basic(optional = false)
    @Column(name = "amt")
    private float  amt;
    @Basic(optional = false)
    @Column(name = "item_vat_rs")
    private float  itemVatRs;
    @JoinColumn(name = "sale_bill_no", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SaleBillPharma saleBillNo;
    @JoinColumn(name = "item_pharma_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemsPharma itemPharmaId;

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public float getMrp() {
        return mrp;
    }

    public void setMrp(float mrp) {
        this.mrp = mrp;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getDM() {
        return DM;
    }

    public void setDM(String DM) {
        this.DM = DM;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }
    
    @Transient
    String ItemName;
    
    @Transient
     float mrp;
    
    @Transient 
    String make;
    
    @Transient 
    String batch;
    
    @Transient 
    String DM;
    
    @Transient 
    String expDate;
    
    @Transient
    String pack;
    
     @Transient
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
     
     @Transient 
     float itemVatPerc;

    public SaleBillPharmaItem() {
    }

    public SaleBillPharmaItem(Integer id) {
        this.id = id;
    }

    public SaleBillPharmaItem(Integer id, String qnty, int itemRate, int amt, int itemVatRs) {
        this.id = id;
        this.qnty = qnty;
        this.itemRate = itemRate;
        this.amt = amt;
        this.itemVatRs = itemVatRs;
    }
    
    public SaleBillPharmaItem(ItemsPharma item){
        this.itemPharmaId = item;
        this.ItemName = item.getDesc();
        this.DM = item.getDM();
        this.amt = 0.0f;
        this.batch = item.getBatch();
        this.expDate = dateFormatter.format(item.getExpDate());                
        this.itemVatRs = 0.0f;
        this.make = item.getMake();
        this.mrp = item.getMrp();
        this.qnty = ""+1;
        this.saleBillNo = null; 
        this.pack = item.getPack();
        this.itemRate = item.getRateFraction();
        this.itemVatPerc = item.getVat();
        
        
    }

    public float getItemVatPerc() {
        return itemVatPerc;
    }

    public void setItemVatPerc(float itemVatPerc) {
        this.itemVatPerc = itemVatPerc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQnty() {
        return qnty;
    }
    
    

    public void setQnty(String qnty) {
        this.qnty = qnty;
        this.amt = Integer.parseInt(qnty)*this.getItemRate();
        this.setItemVatRs(amt*getItemVatPerc());
    }

    public float getItemRate() {
        return itemRate;
    }

    public void setItemRate(float itemRate) {
        this.itemRate = itemRate;
    }

    public float getAmt() {
        return amt;
    }

    public void setAmt(float amt) {
        this.amt = amt;
    }

    public float getItemVatRs() {
        return itemVatRs;
    }

    public void setItemVatRs(float itemVatRs) {
        this.itemVatRs = itemVatRs;
    }

    public SaleBillPharma getSaleBillNo() {
        return saleBillNo;
    }

    public void setSaleBillNo(SaleBillPharma saleBillNo) {
        this.saleBillNo = saleBillNo;
    }

    public ItemsPharma getItemPharmaId() {
        return itemPharmaId;
    }

    public void setItemPharmaId(ItemsPharma itemPharmaId) {
        this.itemPharmaId = itemPharmaId;
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
        if (!(object instanceof SaleBillPharmaItem)) {
            return false;
        }
        SaleBillPharmaItem other = (SaleBillPharmaItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.ItemName + " - " +this.getBatch();
    }
    
}
