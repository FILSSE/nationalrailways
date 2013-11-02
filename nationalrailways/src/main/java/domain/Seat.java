package domain;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AndreiM
 */
public class Seat {
    
    private int number;
    private boolean available;
    private String myclass;
    private String direction; // foward/backward
    private String placement; // window/corridor

    public Seat(int number,String myclass){
        this.number = number;
        this.myclass=myclass;
    }
    
    public Seat(int number, boolean available, String direction, String placement,String myclass) {
        this.number = number;
        this.available = available;
        this.direction = direction;
        this.placement = placement;
        this.myclass=myclass;
    }

    public String getMyclass() {
        return myclass;
    }

    public void setMyclass(String myclass) {
        this.myclass = myclass;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }
}
