/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nmau4
 */
@Entity
@Table(name = "typeofspecifi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typeofspecifi.findAll", query = "SELECT t FROM Typeofspecifi t"),
    @NamedQuery(name = "Typeofspecifi.findByIdType", query = "SELECT t FROM Typeofspecifi t WHERE t.idType = :idType"),
    @NamedQuery(name = "Typeofspecifi.findByNameType", query = "SELECT t FROM Typeofspecifi t WHERE t.nameType = :nameType")})
public class Typeofspecifi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idType")
    private Integer idType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nameType")
    private String nameType;
    @OneToMany(mappedBy = "typeSpecID")
    @JsonIgnore
    private Set<Specification> specificationSet;

    public Typeofspecifi() {
    }

    public Typeofspecifi(Integer idType) {
        this.idType = idType;
    }

    public Typeofspecifi(Integer idType, String nameType) {
        this.idType = idType;
        this.nameType = nameType;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    @XmlTransient
    public Set<Specification> getSpecificationSet() {
        return specificationSet;
    }

    public void setSpecificationSet(Set<Specification> specificationSet) {
        this.specificationSet = specificationSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idType != null ? idType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typeofspecifi)) {
            return false;
        }
        Typeofspecifi other = (Typeofspecifi) object;
        if ((this.idType == null && other.idType != null) || (this.idType != null && !this.idType.equals(other.idType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eban.pojo.Typeofspecifi[ idType=" + idType + " ]";
    }
    
}
