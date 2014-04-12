/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.suyuti.homework_2;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

/**
 *
 * @author mehmet.dindar
 */
@Entity
public class Takim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long            id;
    private String          ad;
    private Integer         kurulus;
    private String          stad;
    private String          baskan;
    
    @JoinColumn(name="takim_id")
    private List<Futbolcu>  oyuncular;

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public Integer getKurulus() {
        return kurulus;
    }

    public void setKurulus(Integer kurulus) {
        this.kurulus = kurulus;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public String getBaskan() {
        return baskan;
    }

    public void setBaskan(String baskan) {
        this.baskan = baskan;
    }

    public List<Futbolcu> getOyuncular() {
        return oyuncular;
    }

    public void setOyuncular(List<Futbolcu> oyuncular) {
        this.oyuncular = oyuncular;
    }

    public Long getId() {
        return id;
    }

}
