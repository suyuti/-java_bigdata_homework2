/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.suyuti.homework_2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author mehmet.dindar
 */
@Entity
public class Futbolcu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String  ad;
    private Integer numara;
    private String  mevki;
    private Integer yas;

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public Integer getNumara() {
        return numara;
    }

    public void setNumara(Integer numara) {
        this.numara = numara;
    }

    public String getMevki() {
        return mevki;
    }

    public void setMevki(String mevki) {
        this.mevki = mevki;
    }

    public Integer getYas() {
        return yas;
    }

    public void setYas(Integer yas) {
        this.yas = yas;
    }

    public Long getId() {
        return id;
    }

}
