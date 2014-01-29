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
    private int qnty;
    @Basic(optional = false)
    @Column(name = "item_rate")
    private int itemRate;
    @Basic(optional = false)
    @Column(name = "amt")
    private int amt;
    @Basic(optional = false)
    @Column(name = "item_vat_rs")
    private int itemVatRs;
    @JoinColumn(name = "sale_bill_no", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SaleBillPharma saleBillNo;
    @JoinColumn(name = "item_pharma_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemsPharma itemPharmaId;

    public SaleBillPharmaItem() {
    }

    public SaleBillPharmaItem(Integer id) {
        this.id = id;
    }

    public SaleBillPharmaItem(Integer id, int qnty, int itemRate, int amt, int itemVatRs) {
        this.id = id;
        this.qnty = qnty;
        this.itemRate = itemRate;
        this.amt = amt;
        this.itemVatRs = itemVatRs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQnty() {
        return qnty;
    }

    public void setQnty(int qnty) {
        this.qnty = qnty;
    }

    public int getItemRate() {
        return itemRate;
    }

    public void setItemRate(int itemRate) {
        this.itemRate = itemRate;
    }

    public int getAmt() {
        return amt;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }

    public int getItemVatRs() {
        return itemVatRs;
    }

    public void setItemVatRs(int itemVatRs) {
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
        return "pojos.SaleBillPharmaItem[ id=" + id + " ]";
    }
    
}
