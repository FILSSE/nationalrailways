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
public class StationList {
    
    private ArrayList<Station> listStation;
    
    public StationList(){
        listStation=new ArrayList<Station>();
    }
    
    public void addStation(Station s){
        listStation.add(s);
    }
    
    public ArrayList<Station> getListStation(){
        return listStation;
    }
    
}
