/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faisal.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "ask")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ask.findAll", query = "SELECT a FROM Ask a"),
    @NamedQuery(name = "Ask.findById", query = "SELECT a FROM Ask a WHERE a.id = :id"),
    @NamedQuery(name = "Ask.findByAsk", query = "SELECT a FROM Ask a WHERE a.ask = :ask")
    })
public class Ask implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ask")
    private String ask;
    @Column(name = "timeanddate")
    private String timeanddatestring;

    public Ask() {
    }

    public Ask(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getTimeanddatestring() {
        return timeanddatestring;
    }

    public void setTimeanddatestring(String timeanddatestring) {
        this.timeanddatestring = timeanddatestring;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ask)) {
            return false;
        }
        Ask other = (Ask) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.faisal.entities.Ask[ id=" + id + " ]";
    }
    
}
