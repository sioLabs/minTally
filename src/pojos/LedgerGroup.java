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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author t0m
 */
@Entity
@Table(name = "ledger_group_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LedgerGroup.findAll", query = "SELECT l FROM LedgerGroup l"),
    @NamedQuery(name = "LedgerGroup.findById", query = "SELECT l FROM LedgerGroup l WHERE l.id = :id"),
    @NamedQuery(name = "LedgerGroup.findByGroupName", query = "SELECT l FROM LedgerGroup l WHERE l.groupName = :groupName")})
public class LedgerGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "group_name")
    private String groupName;

    public LedgerGroup() {
    }

    public LedgerGroup(Integer id) {
        this.id = id;
    }

    public LedgerGroup(Integer id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
        if (!(object instanceof LedgerGroup)) {
            return false;
        }
        LedgerGroup other = (LedgerGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.LedgerGroup[ id=" + id + " ]";
    }
    
}
