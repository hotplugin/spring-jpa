/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faisal.entities;

/**
 *
 * @author Dell
 */
public class CsvMap {
     private String Time;
    private String Ask;

    public CsvMap(){}
    public CsvMap(String Time, String Ask) {
        this.Time = Time;
        this.Ask = Ask;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getAsk() {
        return Ask;
    }

    public void setAsk(String Ask) {
        this.Ask = Ask;
    }

}
