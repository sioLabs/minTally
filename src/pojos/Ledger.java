/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author t0m
 */
@Entity
@Table(name = "ledger_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ledger.findLedgerNameLike", query = "SELECT l FROM Ledger l WHERE l.ledgerName LIKE  :ledgerName"),
    @NamedQuery(name = "Ledger.findAll", query = "SELECT l FROM Ledger l"),
    @NamedQuery(name = "Ledger.findByLedgerId", query = "SELECT l FROM Ledger l WHERE l.ledgerId = :ledgerId"),
    @NamedQuery(name = "Ledger.findByLedgerName", query = "SELECT l FROM Ledger l WHERE l.ledgerName = :ledgerName"),
    @NamedQuery(name = "Ledger.findByLedgerPersonName", query = "SELECT l FROM Ledger l WHERE l.ledgerPersonName = :ledgerPersonName"),
    @NamedQuery(name = "Ledger.findByLedgerType", query = "SELECT l FROM Ledger l WHERE l.ledgerType = :ledgerType"),
    @NamedQuery(name = "Ledger.findByLedgerOpenBal", query = "SELECT l FROM Ledger l WHERE l.ledgerOpenBal = :ledgerOpenBal"),
    @NamedQuery(name = "Ledger.findByLedgerOpenBalType", query = "SELECT l FROM Ledger l WHERE l.ledgerOpenBalType = :ledgerOpenBalType"),
    @NamedQuery(name = "Ledger.findByLedgerPresentBal", query = "SELECT l FROM Ledger l WHERE l.ledgerPresentBal = :ledgerPresentBal"),
    @NamedQuery(name = "Ledger.findByLedgerPresentBalType", query = "SELECT l FROM Ledger l WHERE l.ledgerPresentBalType = :ledgerPresentBalType"),
    @NamedQuery(name = "Ledger.findByLedgerAddress", query = "SELECT l FROM Ledger l WHERE l.ledgerAddress = :ledgerAddress"),
    @NamedQuery(name = "Ledger.findByLedgerContactNo", query = "SELECT l FROM Ledger l WHERE l.ledgerContactNo = :ledgerContactNo"),
    @NamedQuery(name = "Ledger.findByLedgerContactEmail", query = "SELECT l FROM Ledger l WHERE l.ledgerContactEmail = :ledgerContactEmail"),
    @NamedQuery(name = "Ledger.findByLedgerVatTin", query = "SELECT l FROM Ledger l WHERE l.ledgerVatTin = :ledgerVatTin"),
    @NamedQuery(name = "Ledger.findByLedgerCstTin", query = "SELECT l FROM Ledger l WHERE l.ledgerCstTin = :ledgerCstTin"),
    @NamedQuery(name = "Ledger.findByLedgerCreateDate", query = "SELECT l FROM Ledger l WHERE l.ledgerCreateDate = :ledgerCreateDate"),
    @NamedQuery(name = "Ledger.findByLedgerModificationDate", query = "SELECT l FROM Ledger l WHERE l.ledgerModificationDate = :ledgerModificationDate")})
public class Ledger implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ledger_id")
    private Integer ledgerId;
    @Basic(optional = false)
    @Column(name = "ledger_name")
    private String ledgerName;
    @Column(name = "ledger_person_name")
    private String ledgerPersonName;
    @Basic(optional = false)
    @Column(name = "ledger_type")
    private int ledgerType;
    @Basic(optional = false)
    @Column(name = "ledger_open_bal")
    private float ledgerOpenBal;
    @Basic(optional = false)
    @Column(name = "ledger_open_bal_type")
    private boolean ledgerOpenBalType;
    @Basic(optional = false)
    @Column(name = "ledger_present_bal")
    private float ledgerPresentBal;
    @Basic(optional = false)
    @Column(name = "ledger_present_bal_type")
    private boolean ledgerPresentBalType;
    @Column(name = "ledger_address")
    private String ledgerAddress;
    @Column(name = "ledger_contact_no")
    private String ledgerContactNo;
    @Column(name = "ledger_contact_email")
    private String ledgerContactEmail;
    @Column(name = "ledger_vat_tin")
    private String ledgerVatTin;
    @Column(name = "ledger_cst_tin")
    private String ledgerCstTin;
    @Basic(optional = false)
    @Column(name = "ledger_create_date")
    @Temporal(TemporalType.DATE)
    private Date ledgerCreateDate;
    @Basic(optional = false)
    @Column(name = "ledger_modification_date")
    @Temporal(TemporalType.DATE)
    private Date ledgerModificationDate;

    public Ledger() {
    }

    public Ledger(Integer ledgerId) {
        this.ledgerId = ledgerId;
    }

    public Ledger(Integer ledgerId, String ledgerName, int ledgerType, long ledgerOpenBal, boolean ledgerOpenBalType, long ledgerPresentBal, boolean ledgerPresentBalType, Date ledgerCreateDate, Date ledgerModificationDate) {
        this.ledgerId = ledgerId;
        this.ledgerName = ledgerName;
        this.ledgerType = ledgerType;
        this.ledgerOpenBal = ledgerOpenBal;
        this.ledgerOpenBalType = ledgerOpenBalType;
        this.ledgerPresentBal = ledgerPresentBal;
        this.ledgerPresentBalType = ledgerPresentBalType;
        this.ledgerCreateDate = ledgerCreateDate;
        this.ledgerModificationDate = ledgerModificationDate;
    }

    public Integer getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(Integer ledgerId) {
        this.ledgerId = ledgerId;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public String getLedgerPersonName() {
        return ledgerPersonName;
    }

    public void setLedgerPersonName(String ledgerPersonName) {
        this.ledgerPersonName = ledgerPersonName;
    }

    public int getLedgerType() {
        return ledgerType;
    }

    public void setLedgerType(int ledgerType) {
        this.ledgerType = ledgerType;
    }

    public float getLedgerOpenBal() {
        return ledgerOpenBal;
    }

    public void setLedgerOpenBal(float ledgerOpenBal) {
        this.ledgerOpenBal = ledgerOpenBal;
    }

    public boolean getLedgerOpenBalType() {
        return ledgerOpenBalType;
    }

    public void setLedgerOpenBalType(boolean ledgerOpenBalType) {
        this.ledgerOpenBalType = ledgerOpenBalType;
    }

    public float getLedgerPresentBal() {
        return ledgerPresentBal;
    }

    public void setLedgerPresentBal(float ledgerPresentBal) {
        this.ledgerPresentBal = ledgerPresentBal;
    }

    public boolean getLedgerPresentBalType() {
        return ledgerPresentBalType;
    }

    public void setLedgerPresentBalType(boolean ledgerPresentBalType) {
        this.ledgerPresentBalType = ledgerPresentBalType;
    }

    public String getLedgerAddress() {
        return ledgerAddress;
    }

    public void setLedgerAddress(String ledgerAddress) {
        this.ledgerAddress = ledgerAddress;
    }

    public String getLedgerContactNo() {
        return ledgerContactNo;
    }

    public void setLedgerContactNo(String ledgerContactNo) {
        this.ledgerContactNo = ledgerContactNo;
    }

    public String getLedgerContactEmail() {
        return ledgerContactEmail;
    }

    public void setLedgerContactEmail(String ledgerContactEmail) {
        this.ledgerContactEmail = ledgerContactEmail;
    }

    public String getLedgerVatTin() {
        return ledgerVatTin;
    }

    public void setLedgerVatTin(String ledgerVatTin) {
        this.ledgerVatTin = ledgerVatTin;
    }

    public String getLedgerCstTin() {
        return ledgerCstTin;
    }

    public void setLedgerCstTin(String ledgerCstTin) {
        this.ledgerCstTin = ledgerCstTin;
    }

    public Date getLedgerCreateDate() {
        return ledgerCreateDate;
    }

    public void setLedgerCreateDate(Date ledgerCreateDate) {
        this.ledgerCreateDate = ledgerCreateDate;
    }

    public Date getLedgerModificationDate() {
        return ledgerModificationDate;
    }

    public void setLedgerModificationDate(Date ledgerModificationDate) {
        this.ledgerModificationDate = ledgerModificationDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ledgerId != null ? ledgerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ledger)) {
            return false;
        }
        Ledger other = (Ledger) object;
        if ((this.ledgerId == null && other.ledgerId != null) || (this.ledgerId != null && !this.ledgerId.equals(other.ledgerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ledgerName;
    }
    
}
