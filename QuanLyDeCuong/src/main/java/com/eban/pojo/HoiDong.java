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
@Table(name = "HoiDong")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HoiDong.findAll", query = "SELECT h FROM HoiDong h"),
    @NamedQuery(name = "HoiDong.findByIdHoiDong", query = "SELECT h FROM HoiDong h WHERE h.idHoiDong = :idHoiDong")})
public class HoiDong implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHoiDong")
    private Integer idHoiDong;
    @JoinColumn(name = "chuTichID", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User chuTichID;
    @JoinColumn(name = "thuKyID", referencedColumnName = "idUser")
    @ManyToOne
    private User thuKyID;
    @OneToMany(mappedBy = "hoiDongID")
    private Set<Specification> specificationSet;

    public HoiDong() {
    }

    public HoiDong(Integer idHoiDong) {
        this.idHoiDong = idHoiDong;
    }

    public Integer getIdHoiDong() {
        return idHoiDong;
    }

    public void setIdHoiDong(Integer idHoiDong) {
        this.idHoiDong = idHoiDong;
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
        hash += (idHoiDong != null ? idHoiDong.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HoiDong)) {
            return false;
        }
        HoiDong other = (HoiDong) object;
        if ((this.idHoiDong == null && other.idHoiDong != null) || (this.idHoiDong != null && !this.idHoiDong.equals(other.idHoiDong))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eban.pojo.HoiDong[ idHoiDong=" + idHoiDong + " ]";
    }
    
}
