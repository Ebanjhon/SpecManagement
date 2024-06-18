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
 * @author eban
 */
@Entity
@Table(name = "subject")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subject.findAll", query = "SELECT s FROM Subject s"),
    @NamedQuery(name = "Subject.findByIdSubject", query = "SELECT s FROM Subject s WHERE s.idSubject = :idSubject"),
    @NamedQuery(name = "Subject.findByNameSubject", query = "SELECT s FROM Subject s WHERE s.nameSubject = :nameSubject")})
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSubject")
    private Integer idSubject;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nameSubject")
    private String nameSubject;
    @OneToMany(mappedBy = "subjectID")
    @JsonIgnore
    private Set<Specification> specificationSet;

    public Subject() {
    }

    public Subject(Integer idSubject) {
        this.idSubject = idSubject;
    }

    public Subject(Integer idSubject, String nameSubject) {
        this.idSubject = idSubject;
        this.nameSubject = nameSubject;
    }

    public Integer getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Integer idSubject) {
        this.idSubject = idSubject;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
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
        hash += (idSubject != null ? idSubject.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        if ((this.idSubject == null && other.idSubject != null) || (this.idSubject != null && !this.idSubject.equals(other.idSubject))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eban.pojo.Subject[ idSubject=" + idSubject + " ]";
    }

}
