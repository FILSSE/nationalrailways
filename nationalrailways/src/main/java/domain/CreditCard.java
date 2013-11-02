/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author AndreiM
 */
public class CreditCard {
    
    private String IBAN;
    private int PIN;

    public CreditCard(String IBAN, int PIN) {
        this.IBAN = IBAN;
        this.PIN = PIN;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public int getPIN() {
        return PIN;
    }

    public void setPIN(int PIN) {
        this.PIN = PIN;
    }
   
}
