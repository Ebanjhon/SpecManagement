/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.pojo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nmau4
 */
@Entity
@Table(name = "oderdc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oderdc.findAll", query = "SELECT o FROM Oderdc o"),
    @NamedQuery(name = "Oderdc.findByIdOrder", query = "SELECT o FROM Oderdc o WHERE o.idOrder = :idOrder")})
public class Oderdc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrder")
    private Integer idOrder;
    @JoinColumn(name = "specID", referencedColumnName = "idSpec")
    @ManyToOne
    private Specification specID;
    @JoinColumn(name = "userID", referencedColumnName = "idUser")
    @ManyToOne
    private User userID;

    public Oderdc() {
    }

    public Oderdc(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Specification getSpecID() {
        return specID;
    }

    public void setSpecID(Specification specID) {
        this.specID = specID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrder != null ? idOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oderdc)) {
            return false;
        }
        Oderdc other = (Oderdc) object;
        if ((this.idOrder == null && other.idOrder != null) || (this.idOrder != null && !this.idOrder.equals(other.idOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eban.pojo.Oderdc[ idOrder=" + idOrder + " ]";
    }
    
}
