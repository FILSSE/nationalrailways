/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;

/**
 *
 * @author AndreiM
 */
public class Train {
    
    private int id;
    private String type;
    private ArrayList<Seat> listSeat;
    private int noSeats;

    public Train(int id, String type, int no) {
        this.id = id;
        this.type = type;
        listSeat=new ArrayList<Seat>(no);
        this.noSeats=no;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Seat> getListSeat() {
        return listSeat;
    }

    public void setListSeat(ArrayList<Seat> listSeat) {
        this.listSeat = listSeat;
    }

    public int getNoSeats() {
        return noSeats;
    }

    public void setNoSeats(int noSeats) {
        this.noSeats = noSeats;
    }
    
}
