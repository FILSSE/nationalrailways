/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author AndreiM
 */
public class IDCard {
    
    private String CNP;
    private String series;
    private int number;

    public IDCard(String CNP, String series, int number) {
        this.CNP = CNP;
        this.series = series;
        this.number = number;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
    
}
