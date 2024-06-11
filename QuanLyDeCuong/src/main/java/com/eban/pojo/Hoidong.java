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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eban
 */
@Entity
@Table(name = "hoidong")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hoidong.findAll", query = "SELECT h FROM Hoidong h"),
    @NamedQuery(name = "Hoidong.findByIdHoiDong", query = "SELECT h FROM Hoidong h WHERE h.idHoiDong = :idHoiDong")})
public class Hoidong implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHoiDong")
    private Integer idHoiDong;
    @OneToMany(mappedBy = "hoiDongID")
    private Set<Specification> specificationSet;
    @JoinColumn(name = "chuTichID", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User chuTichID;
    @JoinColumn(name = "thuKyID", referencedColumnName = "idUser")
    @ManyToOne
    private User thuKyID;

    public Hoidong() {
    }

    public Hoidong(Integer idHoiDong) {
        this.idHoiDong = idHoiDong;
    }

    public Integer getIdHoiDong() {
        return idHoiDong;
    }

    public void setIdHoiDong(Integer idHoiDong) {
        this.idHoiDong = idHoiDong;
    }

    @XmlTransient
    public Set<Specification> getSpecificationSet() {
        return specificationSet;
    }

    public void setSpecificationSet(Set<Specification> specificationSet) {
        this.specificationSet = specificationSet;
    }

    public User getChuTichID() {
        return chuTichID;
    }

    public void setChuTichID(User chuTichID) {
        this.chuTichID = chuTichID;
    }

    public User getThuKyID() {
        return thuKyID;
    }

    public void setThuKyID(User thuKyID) {
        this.thuKyID = thuKyID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHoiDong != null ? idHoiDong.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hoidong)) {
            return false;
        }
        Hoidong other = (Hoidong) object;
        if ((this.idHoiDong == null && other.idHoiDong != null) || (this.idHoiDong != null && !this.idHoiDong.equals(other.idHoiDong))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eban.pojo.Hoidong[ idHoiDong=" + idHoiDong + " ]";
    }
    
}
