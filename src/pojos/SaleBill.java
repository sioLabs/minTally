/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import utils.EntityManagerHelper;

/**
 *
 * @author ashutoshsingh
 */
@Entity
@Table(name = "sale_bill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SaleBill.findAll", query = "SELECT s FROM SaleBill s"),
    @NamedQuery(name = "SaleBill.findBySaleBillNo", query = "SELECT s FROM SaleBill s WHERE s.saleBillNo = :saleBillNo"),
    @NamedQuery(name = "SaleBill.findBySaleBillDate", query = "SELECT s FROM SaleBill s WHERE s.saleBillDate = :saleBillDate"),
    @NamedQuery(name = "SaleBill.findBySaleBillCustomer", query = "SELECT s FROM SaleBill s WHERE s.saleBillCustomer = :saleBillCustomer"),
    @NamedQuery(name = "SaleBill.findBySaleBillSite", query = "SELECT s FROM SaleBill s WHERE s.saleBillSite = :saleBillSite"),
    @NamedQuery(name = "SaleBill.findBySaleBilRemark", query = "SELECT s FROM SaleBill s WHERE s.saleBilRemark = :saleBilRemark"),
    @NamedQuery(name = "SaleBill.findBySaleBillCompany", query = "SELECT s FROM SaleBill s WHERE s.saleBillCompany = :saleBillCompany"),
    @NamedQuery(name = "SaleBill.findBySaleBillTotalvat", query = "SELECT s FROM SaleBill s WHERE s.saleBillTotalvat = :saleBillTotalvat"),
    @NamedQuery(name = "SaleBill.findBySalebillfrieghtCharges", query = "SELECT s FROM SaleBill s WHERE s.salebillfrieghtCharges = :salebillfrieghtCharges"),
    @NamedQuery(name = "SaleBill.findBySaleBillDiscount", query = "SELECT s FROM SaleBill s WHERE s.saleBillDiscount = :saleBillDiscount"),
    @NamedQuery(name = "SaleBill.findBySaleBillTotalAmount", query = "SELECT s FROM SaleBill s WHERE s.saleBillTotalAmount = :saleBillTotalAmount")})
public class SaleBill implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sale_bill_no")
    private Integer saleBillNo;
    @Basic(optional = false)
    @Column(name = "sale_bill_date")
    @Temporal(TemporalType.DATE)
    private Date saleBillDate;
    @Basic(optional = false)
    @Column(name = "sale_bill_customer")
    private int saleBillCustomer;
    @Basic(optional = true)
    @Column(name = "sale_bill_site")
    private String saleBillSite;
    @Basic(optional = true)
    @Column(name = "sale_bil_remark")
    private String saleBilRemark;
    @Basic(optional = true)
    @Column(name = "sale_bill_company")
    private String saleBillCompany;
    @Basic(optional = false)
    @Column(name = "sale_bill_totalvat")
    private double saleBillTotalvat;
    @Basic(optional = false)
    @Column(name = "sale_bill_frieghtCharges")
    private double salebillfrieghtCharges;
    @Basic(optional = false)
    @Column(name = "sale_bill_discount")
    private double saleBillDiscount;
    @Basic(optional = false)
    @Column(name = "sale_bill_total_amount")
    private double saleBillTotalAmount;
    @OneToMany( mappedBy = "saleBillNo",cascade = CascadeType.PERSIST)
    private Collection<SalebillItem> salebillItemCollection;
    
    @Transient
    private String custName;
    
    public String getCustName(){
        return this.custName;
    }
    
    public void setCustName(String name){
        
        //EntityManger em = 
        this.custName = name;
    }

    public SaleBill() {
    }

    public SaleBill(Integer saleBillNo) {
        this.saleBillNo = saleBillNo;
    }

    public SaleBill(Integer saleBillNo, Date saleBillDate, int saleBillCustomer, String saleBillSite, String saleBilRemark, String saleBillCompany, double saleBillTotalvat, double salebillfrieghtCharges, double saleBillDiscount, double saleBillTotalAmount) {
        this.saleBillNo = saleBillNo;
        this.saleBillDate = saleBillDate;
        //this.saleBillCustomer = saleBillCustomer;
        this.setSaleBillCustomer(saleBillCustomer);
        this.saleBillSite = saleBillSite;
        this.saleBilRemark = saleBilRemark;
        this.saleBillCompany = saleBillCompany;
        this.saleBillTotalvat = saleBillTotalvat;
        this.salebillfrieghtCharges = salebillfrieghtCharges;
        this.saleBillDiscount = saleBillDiscount;
        this.saleBillTotalAmount = saleBillTotalAmount;
    }

    public Integer getSaleBillNo() {
        return saleBillNo;
    }

    public void setSaleBillNo(Integer saleBillNo) {
        this.saleBillNo = saleBillNo;
    }

    public Date getSaleBillDate() {
        return saleBillDate;
    }

    public void setSaleBillDate(Date saleBillDate) {
        
        this.saleBillDate = saleBillDate;
    }

    public int getSaleBillCustomer() {
        return saleBillCustomer;
    }

    public void setSaleBillCustomer(int saleBillCustomer) {
        this.saleBillCustomer = saleBillCustomer;
          EntityManager em = EntityManagerHelper.getInstance().getEm();
          Query q = em.createNamedQuery("Ledger.findByLedgerId");
          q.setParameter(":LedgerID", saleBillCustomer);
          Ledger l = (Ledger)q.getSingleResult();
          setCustName(l.getLedgerPersonName()); 
    }

    public String getSaleBillSite() {
        return saleBillSite;
    }

    public void setSaleBillSite(String saleBillSite) {
        this.saleBillSite = saleBillSite;
    }

    public String getSaleBilRemark() {
        return saleBilRemark;
    }

    public void setSaleBilRemark(String saleBilRemark) {
        this.saleBilRemark = saleBilRemark;
    }

    public String getSaleBillCompany() {
              
        return saleBillCompany;
    }

    public void setSaleBillCompany(String saleBillCompany) {
        this.saleBillCompany = saleBillCompany;
    }

    public double getSaleBillTotalvat() {
        return saleBillTotalvat;
    }

    public void setSaleBillTotalvat(double saleBillTotalvat) {
        this.saleBillTotalvat = saleBillTotalvat;
    }

    public double getSalebillfrieghtCharges() {
        return salebillfrieghtCharges;
    }

    public void setSalebillfrieghtCharges(double salebillfrieghtCharges) {
        this.salebillfrieghtCharges = salebillfrieghtCharges;
    }

    public double getSaleBillDiscount() {
        return saleBillDiscount;
    }

    public void setSaleBillDiscount(double saleBillDiscount) {
        this.saleBillDiscount = saleBillDiscount;
    }

    public double getSaleBillTotalAmount() {
        return saleBillTotalAmount;
    }

    public void setSaleBillTotalAmount(double saleBillTotalAmount) {
        this.saleBillTotalAmount = saleBillTotalAmount;
    }

    @XmlTransient
    public Collection<SalebillItem> getSalebillItemCollection() {
        return salebillItemCollection;
    }

    public void setSalebillItemCollection(Collection<SalebillItem> salebillItemCollection) {
        this.salebillItemCollection = salebillItemCollection;
    }
    


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (saleBillNo != null ? saleBillNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SaleBill)) {
            return false;
        }
        SaleBill other = (SaleBill) object;
        if ((this.saleBillNo == null && other.saleBillNo != null) || (this.saleBillNo != null && !this.saleBillNo.equals(other.saleBillNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.SaleBill[ saleBillNo=" + saleBillNo + " ]";
    }
    
}
