package Parking_Billing;

import java.util.HashMap;
import java.util.Map;

public class Vehicle {    //created a class vehicle for registration of vehicles

    //required data collected from user for registration

    //for every object one instance of all variables will be created
    // once the values assigned they don't change that's why I created it as final
    //even if we didn't declare as final it will work but to decrease warning I used final
    private final String registration_number;
    private final int vehicle_type;
    private final long phone_number;
    private final String email;
    private boolean card = false;
    //key is the registration number and value is the vehicle object
    private static final Map<String,Vehicle> vehicle_objects_Map = new HashMap<>();

    //constructor for assigning values for each vehicle

    public Vehicle(String registration_number, int vehicle_type, long phone_number, String email) {
        this.registration_number = registration_number;
        this.vehicle_type = vehicle_type;
        this.phone_number = phone_number;
        this.email = email;
    }

    //getters and setters for accessing private variables in other classes

    public String getRegistration_number() {
        return registration_number;
    }


    public int getVehicle_type() {
        return vehicle_type;
    }


    public long getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public boolean isCard() {
        return card;
    }

    public void setCard(boolean card) {
        this.card = card;
    }

    public static Map<String, Vehicle> getVehicle_objects_Map() {
        return vehicle_objects_Map;
    }

    public static void setVehicle_objects_Map(String registration_number, Vehicle vehicle_object) {
        Vehicle.vehicle_objects_Map.put(registration_number, vehicle_object);
    }
}
