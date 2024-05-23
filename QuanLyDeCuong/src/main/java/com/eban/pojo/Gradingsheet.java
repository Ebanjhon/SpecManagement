/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.pojo;

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
 * @author Admin
 */
@Entity
@Table(name = "gradingsheet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gradingsheet.findAll", query = "SELECT g FROM Gradingsheet g"),
    @NamedQuery(name = "Gradingsheet.findByIdGrade", query = "SELECT g FROM Gradingsheet g WHERE g.idGrade = :idGrade"),
    @NamedQuery(name = "Gradingsheet.findByNameColumn", query = "SELECT g FROM Gradingsheet g WHERE g.nameColumn = :nameColumn")})
public class Gradingsheet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGrade")
    private Integer idGrade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nameColumn")
    private String nameColumn;
    @OneToMany(mappedBy = "grandeID")
    private Set<Specgrande> specgrandeSet;

    public Gradingsheet() {
    }

    public Gradingsheet(Integer idGrade) {
        this.idGrade = idGrade;
    }

    public Gradingsheet(Integer idGrade, String nameColumn) {
        this.idGrade = idGrade;
        this.nameColumn = nameColumn;
    }

    public Integer getIdGrade() {
        return idGrade;
    }

    public void setIdGrade(Integer idGrade) {
        this.idGrade = idGrade;
    }

    public String getNameColumn() {
        return nameColumn;
    }

    public void setNameColumn(String nameColumn) {
        this.nameColumn = nameColumn;
    }

    @XmlTransient
    public Set<Specgrande> getSpecgrandeSet() {
        return specgrandeSet;
    }

    public void setSpecgrandeSet(Set<Specgrande> specgrandeSet) {
        this.specgrandeSet = specgrandeSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrade != null ? idGrade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gradingsheet)) {
            return false;
        }
        Gradingsheet other = (Gradingsheet) object;
        if ((this.idGrade == null && other.idGrade != null) || (this.idGrade != null && !this.idGrade.equals(other.idGrade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eban.pojo.Gradingsheet[ idGrade=" + idGrade + " ]";
    }
    
}
