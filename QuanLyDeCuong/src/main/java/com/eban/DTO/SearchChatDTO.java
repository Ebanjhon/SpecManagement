/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.DTO;

/**
 *
 * @author Admin
 */
public class SearchChatDTO {
    private Integer idUser;
    private String username;
    private String firstname;
    private String lastname;
    private String avatar;
    private String role;

    public SearchChatDTO() {
    }

    public SearchChatDTO(Integer idUser, String username, String firstname, String lastname, String avatar, String role) {
        this.idUser = idUser;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.avatar = avatar;
        this.role = role;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
