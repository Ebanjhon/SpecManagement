/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author eban
 */
@Entity
@Table(name = "specification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Specification.findAll", query = "SELECT s FROM Specification s"),
    @NamedQuery(name = "Specification.findByIdSpec", query = "SELECT s FROM Specification s WHERE s.idSpec = :idSpec"),
    @NamedQuery(name = "Specification.findByNameSpec", query = "SELECT s FROM Specification s WHERE s.nameSpec = :nameSpec"),
    @NamedQuery(name = "Specification.findByCredit", query = "SELECT s FROM Specification s WHERE s.credit = :credit"),
    @NamedQuery(name = "Specification.findByDateCreate", query = "SELECT s FROM Specification s WHERE s.dateCreate = :dateCreate"),
    @NamedQuery(name = "Specification.findByFileSpec", query = "SELECT s FROM Specification s WHERE s.fileSpec = :fileSpec"),
    @NamedQuery(name = "Specification.findByStatus", query = "SELECT s FROM Specification s WHERE s.status = :status")})
public class Specification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSpec")
    private Integer idSpec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nameSpec")
    private String nameSpec;
    @Column(name = "credit")
    private Integer credit;
    @Lob
    @Size(max = 65535)
    @Column(name = "content")
    private String content;
    @Column(name = "dateCreate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    @Size(max = 255)
    @Column(name = "fileSpec")
    private String fileSpec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "status")
    private String status;
    @JsonIgnore
    @OneToMany(mappedBy = "specifiID")
    private Set<Specgrande> specgrandeSet;
    @JsonIgnore
    @OneToMany(mappedBy = "specID")
    private Set<Coursestudy> coursestudySet;
    @JoinColumn(name = "hoiDongID", referencedColumnName = "idHoiDong")
    @ManyToOne
    @JsonIgnore
    private Hoidong hoiDongID;
    @JoinColumn(name = "subjectID", referencedColumnName = "idSubject")
    @ManyToOne
    @JsonIgnore
    private Subject subjectID;
    @JoinColumn(name = "typeSpecID", referencedColumnName = "idType")
    @ManyToOne
    @JsonIgnore
    private Typeofspecifi typeSpecID;
    @JoinColumn(name = "authorID", referencedColumnName = "idUser")
    @ManyToOne
    @JsonIgnore
    private User authorID;
    @OneToMany(mappedBy = "specID")
    @JsonIgnore
    private Set<Comment> commentSet;
    @OneToMany(mappedBy = "specID")
    @JsonIgnore
    private Set<Oderdc> oderdcSet;

    @Transient
    private MultipartFile file;

    public Specification() {
    }

    public Specification(Integer idSpec) {
        this.idSpec = idSpec;
    }

    public Specification(Integer idSpec, String nameSpec, String status) {
        this.idSpec = idSpec;
        this.nameSpec = nameSpec;
        this.status = status;
    }

    public Integer getIdSpec() {
        return idSpec;
    }

    public void setIdSpec(Integer idSpec) {
        this.idSpec = idSpec;
    }

    public String getNameSpec() {
        return nameSpec;
    }

    public void setNameSpec(String nameSpec) {
        this.nameSpec = nameSpec;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getFileSpec() {
        return fileSpec;
    }

    public void setFileSpec(String fileSpec) {
        this.fileSpec = fileSpec;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Set<Specgrande> getSpecgrandeSet() {
        return specgrandeSet;
    }

    public void setSpecgrandeSet(Set<Specgrande> specgrandeSet) {
        this.specgrandeSet = specgrandeSet;
    }

    @XmlTransient
    public Set<Coursestudy> getCoursestudySet() {
        return coursestudySet;
    }

    public void setCoursestudySet(Set<Coursestudy> coursestudySet) {
        this.coursestudySet = coursestudySet;
    }

    public Hoidong getHoiDongID() {
        return hoiDongID;
    }

    public void setHoiDongID(Hoidong hoiDongID) {
        this.hoiDongID = hoiDongID;
    }

    public Subject getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Subject subjectID) {
        this.subjectID = subjectID;
    }

    public Typeofspecifi getTypeSpecID() {
        return typeSpecID;
    }

    public void setTypeSpecID(Typeofspecifi typeSpecID) {
        this.typeSpecID = typeSpecID;
    }

    public User getAuthorID() {
        return authorID;
    }

    public void setAuthorID(User authorID) {
        this.authorID = authorID;
    }

    @XmlTransient
    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    @XmlTransient
    public Set<Oderdc> getOderdcSet() {
        return oderdcSet;
    }

    public void setOderdcSet(Set<Oderdc> oderdcSet) {
        this.oderdcSet = oderdcSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSpec != null ? idSpec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Specification)) {
            return false;
        }
        Specification other = (Specification) object;
        if ((this.idSpec == null && other.idSpec != null) || (this.idSpec != null && !this.idSpec.equals(other.idSpec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eban.pojo.Specification[ idSpec=" + idSpec + " ]";
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
