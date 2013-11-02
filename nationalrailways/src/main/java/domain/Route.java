/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;


/**
 *
 * @author AndreiM
 */
public class Route {
    
    private String departureTime;
    private String arrivalTime;
    private int distance;
    private Train train;
    private StationList listStation;

    public Route(String departureTime, String arrivalTime, int distance, Train train, StationList listStation) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.distance = distance;
        this.train = train;
        this.listStation = listStation;
    }

    public StationList getListStation() {
        return listStation;
    }

    public void setListStation(StationList listStation) {
        this.listStation = listStation;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }
    
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Route{" + "departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", distance=" + distance + ", train=" + train + ", listStation=" + listStation + '}';
    }
    
    
}
