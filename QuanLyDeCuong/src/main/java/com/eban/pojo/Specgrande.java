/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eban
 */
@Entity
@Table(name = "specgrande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Specgrande.findAll", query = "SELECT s FROM Specgrande s"),
    @NamedQuery(name = "Specgrande.findByIdGrandeSpec", query = "SELECT s FROM Specgrande s WHERE s.idGrandeSpec = :idGrandeSpec"),
    @NamedQuery(name = "Specgrande.findByWeight", query = "SELECT s FROM Specgrande s WHERE s.weight = :weight")})
public class Specgrande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGrandeSpec")
    private Integer idGrandeSpec;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "weight")
    private BigDecimal weight;
    @JoinColumn(name = "grandeID", referencedColumnName = "idGrade")
    @ManyToOne
    private Gradingsheet grandeID;
    @JoinColumn(name = "specifiID", referencedColumnName = "idSpec")
    @ManyToOne
    @JsonIgnore
    private Specification specifiID;

    public Specgrande() {
    }

    public Specgrande(Integer idGrandeSpec) {
        this.idGrandeSpec = idGrandeSpec;
    }

    public Specgrande(Integer idGrandeSpec, BigDecimal weight) {
        this.idGrandeSpec = idGrandeSpec;
        this.weight = weight;
    }

    public Integer getIdGrandeSpec() {
        return idGrandeSpec;
    }

    public void setIdGrandeSpec(Integer idGrandeSpec) {
        this.idGrandeSpec = idGrandeSpec;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Gradingsheet getGrandeID() {
        return grandeID;
    }

    public void setGrandeID(Gradingsheet grandeID) {
        this.grandeID = grandeID;
    }

    public Specification getSpecifiID() {
        return specifiID;
    }

    public void setSpecifiID(Specification specifiID) {
        this.specifiID = specifiID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrandeSpec != null ? idGrandeSpec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Specgrande)) {
            return false;
        }
        Specgrande other = (Specgrande) object;
        if ((this.idGrandeSpec == null && other.idGrandeSpec != null) || (this.idGrandeSpec != null && !this.idGrandeSpec.equals(other.idGrandeSpec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eban.pojo.Specgrande[ idGrandeSpec=" + idGrandeSpec + " ]";
    }
    
}
