/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findByIdComment", query = "SELECT c FROM Comment c WHERE c.idComment = :idComment"),
    @NamedQuery(name = "Comment.findByCmParentID", query = "SELECT c FROM Comment c WHERE c.cmParentID = :cmParentID"),
    @NamedQuery(name = "Comment.findByCmDate", query = "SELECT c FROM Comment c WHERE c.cmDate = :cmDate")})
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idComment")
    private Integer idComment;
    @Column(name = "cmParentID")
    private Integer cmParentID;
    @Column(name = "cmDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cmDate;
    @Lob
    @Size(max = 65535)
    @Column(name = "content")
    private String content;
    @JoinColumns({
        @JoinColumn(name = "specID", referencedColumnName = "idSpec"),
        @JoinColumn(name = "specID", referencedColumnName = "idSpec")})
    @ManyToOne
    private Specification specification;
    @JoinColumn(name = "userID", referencedColumnName = "idUser")
    @ManyToOne
    private User userID;

    public Comment() {
    }

    public Comment(Integer idComment) {
        this.idComment = idComment;
    }

    public Integer getIdComment() {
        return idComment;
    }

    public void setIdComment(Integer idComment) {
        this.idComment = idComment;
    }

    public Integer getCmParentID() {
        return cmParentID;
    }

    public void setCmParentID(Integer cmParentID) {
        this.cmParentID = cmParentID;
    }

    public Date getCmDate() {
        return cmDate;
    }

    public void setCmDate(Date cmDate) {
        this.cmDate = cmDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
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
        hash += (idComment != null ? idComment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.idComment == null && other.idComment != null) || (this.idComment != null && !this.idComment.equals(other.idComment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eban.pojo.Comment[ idComment=" + idComment + " ]";
    }
    
}
