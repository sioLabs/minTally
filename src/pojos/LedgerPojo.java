/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.util.Date;

/**
 *
 * @author t0m
 */
public class LedgerPojo {
    
    private String address;
    private String email;
    private long contactNo;
    private Date creationDate;
    private String CSTtin;
    private int id;
    private Date modifyDate;
    private String name;
    private float open_bal;
    private int open_bal_type;
    private String person_name;
    private String current_bal;
    private int present_bal_type;
    private int ledger_type;
    private String VATtin;

    public LedgerPojo(String address, String email, long contactNo, Date creationDate, String CSTtin, int id, Date modifyDate, String name, float open_bal, int open_bal_type, String person_name, String current_bal, int present_bal_type, int ledger_type, String VATtin) {
        this.address = address;
        this.email = email;
        this.contactNo = contactNo;
        this.creationDate = creationDate;
        this.CSTtin = CSTtin;
        this.id = id;
        this.modifyDate = modifyDate;
        this.name = name;
        this.open_bal = open_bal;
        this.open_bal_type = open_bal_type;
        this.person_name = person_name;
        this.current_bal = current_bal;
        this.present_bal_type = present_bal_type;
        this.ledger_type = ledger_type;
        this.VATtin = VATtin;
    }

    public LedgerPojo(String address, String email, long contactNo, Date creationDate, String CSTtin, Date modifyDate, String name, float open_bal, int open_bal_type, String person_name, String current_bal, int present_bal_type, int ledger_type, String VATtin) {
        this.address = address;
        this.email = email;
        this.contactNo = contactNo;
        this.creationDate = creationDate;
        this.CSTtin = CSTtin;
        this.modifyDate = modifyDate;
        this.name = name;
        this.open_bal = open_bal;
        this.open_bal_type = open_bal_type;
        this.person_name = person_name;
        this.current_bal = current_bal;
        this.present_bal_type = present_bal_type;
        this.ledger_type = ledger_type;
        this.VATtin = VATtin;
    }

    
    
    
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCSTtin() {
        return CSTtin;
    }

    public void setCSTtin(String CSTtin) {
        this.CSTtin = CSTtin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getOpen_bal() {
        return open_bal;
    }

    public void setOpen_bal(float open_bal) {
        this.open_bal = open_bal;
    }

    public int getOpen_bal_type() {
        return open_bal_type;
    }

    public void setOpen_bal_type(int open_bal_type) {
        this.open_bal_type = open_bal_type;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getCurrent_bal() {
        return current_bal;
    }

    public void setCurrent_bal(String current_bal) {
        this.current_bal = current_bal;
    }

    public int getPresent_bal_type() {
        return present_bal_type;
    }

    public void setPresent_bal_type(int present_bal_type) {
        this.present_bal_type = present_bal_type;
    }

    public int getLedger_type() {
        return ledger_type;
    }

    public void setLedger_type(int ledger_type) {
        this.ledger_type = ledger_type;
    }

    public String getVATtin() {
        return VATtin;
    }

    public void setVATtin(String VATtin) {
        this.VATtin = VATtin;
    }
    
    
    
}
