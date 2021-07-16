package com.mthree;
import java.util.arraylist;
import java.util.map;
import java.util.hashmap;
/**
 * Hello world!
 *
 */
public class Airplane
{
    // Model an airplane
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    private String aircraftID;
    private String carrier;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;

    private String[] passengerManifest;

    private float latitude;
    private float longitude;


    Airplane(String carrier, String origin){
        this.setCarrier(carrier);
        this.setOrigin(origin);
    }

    public void accelerate(){}
    public void decelerate(){}
    public void turnLeft(){}
    public void turnRight(){}

    public void setID(String ID){
        this.aircraftID = ID;
    }
    public void setCarrier(String carrier){
        this.carrier = carrier;
    }
    public void setOrigin(String origin){
        this.origin = origin;
    }
    public void setDestination(String destination){
        this.destination = destination;
    }
    public void setDepartureTime(String departureTime){
        this.departureTime = departureTime;
    }
    public void setArrivalTime(String arrivalTime){
        this.arrivalTime = arrivalTime;
    }
    
    public void setLatitude(float latitude){
        this.latitude = latitude;
    }
    public void setLongitude(float longitude){
        this.longitude = longitude;
    }
    public void setPassengerManifest(String[] manifest){
        this.passengerManifest = manifest;
    }


    public float getLatitude(){
        return this.latitude;
    }
    public float getLongitude(){
        return this.longitude;
    }

    public String getID(){
        return this.ID;
    }
    public String getCarrier(){
        return this.carrier;
    }
    public String getOrigin(){
        return this.origin;
    }
    public String getDestination(){
        return this.destination
    }
    public String getArrivalTime(){
        return this.arrivalTime;
    }
    public String getDepartureTime(){
        return this.departureTime;
    }

    public String[] getPassengerManifest(){
        return this.passengerManifest;
    }


}

public class Runway{
    public boolean isRunwayClear(){}
}

public class AirTrafficControl
{
    // Model an airplane as if the class were to be part of an air traffic control system.
    // I have a lot of ideas for this but im leaving it here

    private ArrayList<String> controlOperators = new ArrayList<String>();
    private Map<String,String> flightSchedule = new HashMap<String,String>(); // departureTime : id
    private Map<String,Airplane> controlledPlanes = new HashMap<String,Airplane>(); // id: airplane
    Runway[] possibleRunways;


    AirTrafficControl(Map<String,String> flightSchedule, Runway[] possibleRunways){
        this.flightSchedule = flightSchedule;
        this.possibleRunways = possibleRunways;
    }

    public void setPossibleRunways(Runway[] possibleRunways){
        this.possibleRunways = possibleRunways;
    }
    public void addAirplane(Airplane airplane){
        String dt = airplane.getDepartureTime();
        String id = airplane.getID();

        this.flightSchedule.put(dt,id);
        this.controlledPlanes.put(id,airplane);
    }

    public void removeAirplane(String ID){
        Airplane departingPlane = this.controlledPlanes.get(ID);
        
        this.flightSchedule.remove(departingPlane.get(departureTime))
        this.controlledPlanes.remove(ID);
    }

    public boolean isRunwayClear(int runwayIndex){
        return this.possibleRunways[runwayIndex].isRunwayClear();
    }


}

public class FlightSimulator extends Airplane
{
    // Model an airplane as if the class were to be part of a flight simulator.
    FlightSimulator(String carrier, String origin){
        super(carrier, origin);
    }
    public void ascend(){}
    public void descend(){}
    public void tiltLeft(){}
    public void tiltRight(){}
    public void beginSimulation(){}
    public void endSimulation(){}
}
