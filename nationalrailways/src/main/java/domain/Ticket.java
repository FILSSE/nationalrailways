/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author AndreiM
 */
public class Ticket {

    private Route route;
    private Seat seat;
    private double price;
    private Discount discount;
    private Customer client;

    public Ticket(Route route, Seat seat) {
        this.route = route;
        this.seat = seat;
        this.price=0;
        discount=new Discount("no",1);
    }
    
    public void computeCost(){
        this.price=0;
        this.price=route.getDistance()*0.1; // number of kilometers
        // the type of the train
        if(route.getTrain().getType().compareTo("Personal")==0){
            this.price=this.price*1.1;
        }
        if(route.getTrain().getType().compareTo("Accelerat")==0){
            this.price=this.price*1.3;
        }
        if(route.getTrain().getType().compareTo("Rapid")==0){
            this.price=this.price*1.4;
        }
        if(route.getTrain().getType().compareTo("Intercity")==0){
            this.price=this.price*1.5;
        }
        // the class of the train
        if(seat.getMyclass().compareTo("First")==0){
            this.price=this.price*1.6;
        }
        if(seat.getMyclass().compareTo("Second")==0){
            this.price=this.price*1.1;
        }
        this.price=this.price*discount.getValue();
    }

    public double getPrice() {
        int returnPrice = (int)(this.price * 100); 
        double returnPriceDouble = ((double)returnPrice)/100;
 //       System.out.println("Price:"+returnPriceDouble);
        return returnPriceDouble;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Customer getClient() {
        return client;
    }

    public void setClient(Customer client) {
        this.client = client;
    }
    
}

