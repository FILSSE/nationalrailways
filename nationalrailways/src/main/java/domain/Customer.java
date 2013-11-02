/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author AndreiM
 */
public class Customer {
    
    private String firstName;
    private String lastName;
    private CreditCard cc;
    private IDCard idc;

    public Customer(String firstName, String lastName, CreditCard cc, IDCard idc) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cc = cc;
        this.idc = idc;
    }

    public CreditCard getCc() {
        return cc;
    }

    public void setCc(CreditCard cc) {
        this.cc = cc;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public IDCard getIdc() {
        return idc;
    }

    public void setIdc(IDCard idc) {
        this.idc = idc;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}
