/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "units")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Units.findAll", query = "SELECT u FROM Units u"),
    @NamedQuery(name = "Units.findById", query = "SELECT u FROM Units u WHERE u.id = :id"),
    @NamedQuery(name = "Units.findByUnitType", query = "SELECT u FROM Units u WHERE u.unitType = :unitType"),
    @NamedQuery(name = "Units.findByUnitName", query = "SELECT u FROM Units u WHERE u.unitName = :unitName"),
    @NamedQuery(name = "Units.findByConv", query = "SELECT u FROM Units u WHERE u.conv = :conv"),
    @NamedQuery(name = "Units.findBySecondUnit", query = "SELECT u FROM Units u WHERE u.secondUnit = :secondUnit")})
public class Units implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "unit_type")
    private int unitType;
    @Basic(optional = false)
    @Column(name = "unit_name")
    private String unitName;
    @Basic(optional = false)
    @Column(name = "conv")
    private float conv;
    @Column(name = "second_unit")
    private Integer secondUnit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemFirstUnit")
    private Collection<Items> itemsCollection;
    @OneToMany(mappedBy = "itemSecondUnit")
    private Collection<Items> itemsCollection1;

    public Units() {
    }

    public Units(Integer id) {
        this.id = id;
    }

    public Units(Integer id, int unitType, String unitName, float conv) {
        this.id = id;
        this.unitType = unitType;
        this.unitName = unitName;
        this.conv = conv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUnitType() {
        return unitType;
    }

    public void setUnitType(int unitType) {
        this.unitType = unitType;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public float getConv() {
        return conv;
    }

    public void setConv(float conv) {
        this.conv = conv;
    }

    public Integer getSecondUnit() {
        return secondUnit;
    }

    public void setSecondUnit(Integer secondUnit) {
        this.secondUnit = secondUnit;
    }

    @XmlTransient
    public Collection<Items> getItemsCollection() {
        return itemsCollection;
    }

    public void setItemsCollection(Collection<Items> itemsCollection) {
        this.itemsCollection = itemsCollection;
    }

    @XmlTransient
    public Collection<Items> getItemsCollection1() {
        return itemsCollection1;
    }

    public void setItemsCollection1(Collection<Items> itemsCollection1) {
        this.itemsCollection1 = itemsCollection1;
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
        if (!(object instanceof Units)) {
            return false;
        }
        Units other = (Units) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return unitName;
    }
    
}
