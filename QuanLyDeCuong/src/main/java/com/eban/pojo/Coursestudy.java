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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eban
 */
@Entity
@Table(name = "coursestudy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coursestudy.findAll", query = "SELECT c FROM Coursestudy c"),
    @NamedQuery(name = "Coursestudy.findByIdCourse", query = "SELECT c FROM Coursestudy c WHERE c.idCourse = :idCourse"),
    @NamedQuery(name = "Coursestudy.findByNameCourse", query = "SELECT c FROM Coursestudy c WHERE c.nameCourse = :nameCourse")})
public class Coursestudy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCourse")
    private Integer idCourse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nameCourse")
    private String nameCourse;
    @JoinColumn(name = "specID", referencedColumnName = "idSpec")
    @ManyToOne
    private Specification specID;
    @JoinColumn(name = "teacherID", referencedColumnName = "idUser")
    @ManyToOne
    private User teacherID;

    public Coursestudy() {
    }

    public Coursestudy(Integer idCourse) {
        this.idCourse = idCourse;
    }

    public Coursestudy(Integer idCourse, String nameCourse) {
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
    }

    public Integer getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Integer idCourse) {
        this.idCourse = idCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public Specification getSpecID() {
        return specID;
    }

    public void setSpecID(Specification specID) {
        this.specID = specID;
    }

    public User getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(User teacherID) {
        this.teacherID = teacherID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCourse != null ? idCourse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coursestudy)) {
            return false;
        }
        Coursestudy other = (Coursestudy) object;
        if ((this.idCourse == null && other.idCourse != null) || (this.idCourse != null && !this.idCourse.equals(other.idCourse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eban.pojo.Coursestudy[ idCourse=" + idCourse + " ]";
    }
    
}
