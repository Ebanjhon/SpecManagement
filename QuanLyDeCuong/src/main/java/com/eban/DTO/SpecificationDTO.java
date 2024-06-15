/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.DTO;

import java.util.Date;

/**
 *
 * @author nmau4
 */
public class SpecificationDTO {

    /**
     * @return the subject
     */
    public SubjectDTO getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(SubjectDTO subject) {
        this.subject = subject;
    }

    /**
     * @return the typeofspecifi
     */
    public TypeofspecifiDTO getTypeofspecifi() {
        return typeofspecifi;
    }

    /**
     * @param typeofspecifi the typeofspecifi to set
     */
    public void setTypeofspecifi(TypeofspecifiDTO typeofspecifi) {
        this.typeofspecifi = typeofspecifi;
    }
    private Integer idSpec;
    private String nameSpec;
    private Integer credit;
    private String content;
    private Date dateCreate;
    private String fileSpec;
    private String status;
    private UserDTO author;
    private SubjectDTO subject;
    private TypeofspecifiDTO typeofspecifi;

    /**
     * @return the idSpec
     */
    public Integer getIdSpec() {
        return idSpec;
    }

    /**
     * @param idSpec the idSpec to set
     */
    public void setIdSpec(Integer idSpec) {
        this.idSpec = idSpec;
    }

    /**
     * @return the nameSpec
     */
    public String getNameSpec() {
        return nameSpec;
    }

    /**
     * @param nameSpec the nameSpec to set
     */
    public void setNameSpec(String nameSpec) {
        this.nameSpec = nameSpec;
    }

    /**
     * @return the credit
     */
    public Integer getCredit() {
        return credit;
    }

    /**
     * @param credit the credit to set
     */
    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the dateCreate
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * @param dateCreate the dateCreate to set
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    /**
     * @return the fileSpec
     */
    public String getFileSpec() {
        return fileSpec;
    }

    /**
     * @param fileSpec the fileSpec to set
     */
    public void setFileSpec(String fileSpec) {
        this.fileSpec = fileSpec;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the author
     */
    public UserDTO getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(UserDTO author) {
        this.author = author;
    }
}
    