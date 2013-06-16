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
import javax.persistence.FetchType;
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
@Table(name = "item_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemGroup.findAll", query = "SELECT i FROM ItemGroup i"),
    @NamedQuery(name = "ItemGroup.findByItemGroupId", query = "SELECT i FROM ItemGroup i WHERE i.itemGroupId = :itemGroupId"),
    @NamedQuery(name = "ItemGroup.findByItemGroupName", query = "SELECT i FROM ItemGroup i WHERE i.itemGroupName = :itemGroupName"),
    @NamedQuery(name = "ItemGroup.findByItemGroupParent", query = "SELECT i FROM ItemGroup i WHERE i.itemGroupParent = :itemGroupParent")})
public class ItemGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "item_group_id")
    private Integer itemGroupId;
    @Basic(optional = false)
    @Column(name = "item_group_name")
    private String itemGroupName;
    @Basic(optional = false)
    @Column(name = "item_group_parent")
    private int itemGroupParent;
    @OneToMany(mappedBy = "itemSubGroup", fetch= FetchType.EAGER)
    private Collection<Items> itemsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemGroup", fetch=FetchType.EAGER)
    private Collection<Items> itemsCollection1;

    public ItemGroup() {
    }

    public ItemGroup(Integer itemGroupId) {
        this.itemGroupId = itemGroupId;
    }

    public ItemGroup(Integer itemGroupId, String itemGroupName, int itemGroupParent) {
        this.itemGroupId = itemGroupId;
        this.itemGroupName = itemGroupName;
        this.itemGroupParent = itemGroupParent;
    }

    public Integer getItemGroupId() {
        return itemGroupId;
    }

    public void setItemGroupId(Integer itemGroupId) {
        this.itemGroupId = itemGroupId;
    }

    public String getItemGroupName() {
        return itemGroupName;
    }

    public void setItemGroupName(String itemGroupName) {
        this.itemGroupName = itemGroupName;
    }

    public int getItemGroupParent() {
        return itemGroupParent;
    }

    public void setItemGroupParent(int itemGroupParent) {
        this.itemGroupParent = itemGroupParent;
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
        hash += (itemGroupId != null ? itemGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemGroup)) {
            return false;
        }
        ItemGroup other = (ItemGroup) object;
        if ((this.itemGroupId == null && other.itemGroupId != null) || (this.itemGroupId != null && !this.itemGroupId.equals(other.itemGroupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return itemGroupName;
    }
    
}
