package Parking_Billing;

import java.util.HashMap;
import java.util.Map;

public class Parking {    //parking class

    private double Total_amount;
    private int slots_for_2_Wheeler = 2;
    private int slots_for_4_Wheeler = 4;


    //hashmap for key is registration number and value is duration
    private final Map<String,Integer> parked_vehicles_map = new HashMap<>();  //to decrease warnings I used final keyword

    public double getTotal_amount() {
        return Total_amount;
    }

    public void setTotal_amount(double total_amount) {
        Total_amount = total_amount;
    }

    public int getSlots_for_2_Wheeler() {
        return slots_for_2_Wheeler;
    }

    public void setSlots_for_2_Wheeler(int slots_for_2_Wheeler) {
        this.slots_for_2_Wheeler = slots_for_2_Wheeler;
    }

    public int getSlots_for_4_Wheeler() {
        return slots_for_4_Wheeler;
    }

    public void setSlots_for_4_Wheeler(int slots_for_4_Wheeler) {
        this.slots_for_4_Wheeler = slots_for_4_Wheeler;
    }

    public  Map<String, Integer> getParked_vehicles_map() {
        return parked_vehicles_map;
    }

    public void setParked_vehicles_map(String registration_number, Integer duration) {
        this.parked_vehicles_map.put(registration_number, duration);
    }
}
