/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ashutoshsingh
 */
@Entity
@Table(name = "customer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findById", query = "SELECT c FROM Customer c WHERE c.id = :id"),
    @NamedQuery(name = "Customer.findByCompanyName", query = "SELECT c FROM Customer c WHERE c.companyName = :companyName"),
    @NamedQuery(name = "Customer.findBySiteAddress", query = "SELECT c FROM Customer c WHERE c.siteAddress = :siteAddress"),
    @NamedQuery(name = "Customer.findByPhone", query = "SELECT c FROM Customer c WHERE c.phone = :phone"),
    @NamedQuery(name = "Customer.findByMobile", query = "SELECT c FROM Customer c WHERE c.mobile = :mobile"),
    @NamedQuery(name = "Customer.findByBalance", query = "SELECT c FROM Customer c WHERE c.balance = :balance"),
    @NamedQuery(name = "Customer.findByLicenceNo", query = "SELECT c FROM Customer c WHERE c.licenceNo = :licenceNo"),
    @NamedQuery(name = "Customer.findByTinNo", query = "SELECT c FROM Customer c WHERE c.tinNo = :tinNo"),
    @NamedQuery(name = "Customer.findByCstNo", query = "SELECT c FROM Customer c WHERE c.cstNo = :cstNo")})
public class Customer implements Serializable {
    @Column(name = "del_address")
    private String delAddress;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "site_address")
    private String siteAddress;
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @Column(name = "mobile")
    private String mobile;
    @Basic(optional = false)
    @Column(name = "balance")
    private float balance;
    @Column(name = "licence_no")
    private String licenceNo;
    @Column(name = "tin_no")
    private String tinNo;
    @Column(name = "cst_no")
    private String cstNo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private List<SaleBillPharma> saleBillPharmaList;

    public Customer() {
    }

    public Customer(Integer id) {
        this.id = id;
    }

    public Customer(Integer id, String companyName, String mobile, float balance) {
        this.id = id;
        this.companyName = companyName;
        this.mobile = mobile;
        this.balance = balance;
    }

    public Customer(String delAddress, String companyName, String siteAddress, String phone, String mobile, float balance, String licenceNo) {
        this.delAddress = delAddress;
        this.companyName = companyName;
        this.siteAddress = siteAddress;
        this.phone = phone;
        this.mobile = mobile;
        this.balance = balance;
        this.licenceNo = licenceNo;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public String getTinNo() {
        return tinNo;
    }

    public void setTinNo(String tinNo) {
        this.tinNo = tinNo;
    }

    public String getCstNo() {
        return cstNo;
    }

    public void setCstNo(String cstNo) {
        this.cstNo = cstNo;
    }

    @XmlTransient
    public List<SaleBillPharma> getSaleBillPharmaList() {
        return saleBillPharmaList;
    }

    public void setSaleBillPharmaList(List<SaleBillPharma> saleBillPharmaList) {
        this.saleBillPharmaList = saleBillPharmaList;
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
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return companyName;
    }

    public String getDelAddress() {
        return delAddress;
    }

    public void setDelAddress(String delAddress) {
        this.delAddress = delAddress;
    }
    
}
