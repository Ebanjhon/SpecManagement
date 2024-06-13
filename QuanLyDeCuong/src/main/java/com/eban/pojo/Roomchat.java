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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eban
 */
@Entity
@Table(name = "roomchat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roomchat.findAll", query = "SELECT r FROM Roomchat r"),
    @NamedQuery(name = "Roomchat.findByIdRoomChat", query = "SELECT r FROM Roomchat r WHERE r.idRoomChat = :idRoomChat"),
    @NamedQuery(name = "Roomchat.findByNameRoom", query = "SELECT r FROM Roomchat r WHERE r.nameRoom = :nameRoom")})
public class Roomchat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRoomChat")
    private Integer idRoomChat;
    @Size(max = 50)
    @Column(name = "nameRoom")
    private String nameRoom;
    @OneToMany(mappedBy = "roomID")
    private Set<Chat> chatSet;

    public Roomchat() {
    }

    public Roomchat(Integer idRoomChat) {
        this.idRoomChat = idRoomChat;
    }

    public Integer getIdRoomChat() {
        return idRoomChat;
    }

    public void setIdRoomChat(Integer idRoomChat) {
        this.idRoomChat = idRoomChat;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    @XmlTransient
    public Set<Chat> getChatSet() {
        return chatSet;
    }

    public void setChatSet(Set<Chat> chatSet) {
        this.chatSet = chatSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRoomChat != null ? idRoomChat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roomchat)) {
            return false;
        }
        Roomchat other = (Roomchat) object;
        if ((this.idRoomChat == null && other.idRoomChat != null) || (this.idRoomChat != null && !this.idRoomChat.equals(other.idRoomChat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eban.pojo.Roomchat[ idRoomChat=" + idRoomChat + " ]";
    }
    
}
