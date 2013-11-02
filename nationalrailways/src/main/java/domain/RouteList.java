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
public class RouteList {
    
    private static ArrayList<Route> listRoutes;

    public RouteList() {
        listRoutes=new ArrayList<Route>();
    }

    public ArrayList<Route> getListRoutes() {
        return listRoutes;
    }

    public void setListRoutes(ArrayList<Route> listRoutes) {
        RouteList.listRoutes = listRoutes;
    }
    
    public void addRoute(Route r){
        listRoutes.add(r);
    }
}
