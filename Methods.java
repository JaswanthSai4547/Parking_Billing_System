package Parking_Billing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

public class Methods   //creating methods class to perform all the required operations to do
{

    //buffered reader is used to take input data from user
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Parking parking_object = new Parking();

    //method-1 : register a vehicle
    //takes in the registration number,phone number and email id to register a user.
    public void register_vehicle(){
        try {

            System.out.println("Registration Number (AA NN AA NNNN) :");
            String registration_number = br.readLine().toUpperCase();
            while (!registration_number.matches("^[A-Z]{2}\\s[0-9]{2}\\s[A-Z]{2}\\s[0-9]{4}$"))  //validation registration number by regular expressions
            {
                System.out.println("Enter valid Registration Number");
                registration_number = br.readLine().toUpperCase();
            }

            System.out.println("Vehicle Type (2/4):");
            int vehicle_type = Integer.parseInt(br.readLine());   //doing type conversion //if wrong input entered it will throw exception
            while (!Integer.toString(vehicle_type).matches("(2){1}|(4){1}"))
            {
                System.out.println("enter correct vehicle type");
                vehicle_type = Integer.parseInt(br.readLine());
            }

            System.out.println("Phone Number :");
            long phone_number = Long.parseLong(br.readLine());
            while (!Long.toString(phone_number).matches("(0/91)?[7-9][0-9]{9}"))   //validation phone number by regular expressions
            {
                System.out.println("enter correct phone number:");
                phone_number = Long.parseLong(br.readLine());
            }

            System.out.println("Email :");
            String email = br.readLine();
            while (!email.matches("^(.+)@(.+)$"))  //validating email by regular expressions
            {
                System.out.println("enter correct Email again");
                email = br.readLine();
            }

            Vehicle vehicle_object = new Vehicle(registration_number,vehicle_type,phone_number,email);
            Vehicle.setVehicle_objects_Map(registration_number,vehicle_object);
            System.out.println("Vehicle Registered Successfully :) ");
        }catch (NumberFormatException exc){
            System.out.println("Invalid Input");
        }
        catch (Exception e){
            System.out.println("Exception is "+e);
        }
    }

    //method-2 : Get a Parking Card
    // to get a parking it charges rupees 100/- for each vehicle
    public void get_parking_card(){
        try {
            System.out.println("Registration Number :");
            String registration_number = br.readLine().toUpperCase();
            while (!registration_number.matches("^[A-Z]{2}\\s[0-9]{2}\\s[A-Z]{2}\\s[0-9]{4}$"))  //validation registration number by regular expressions
            {
                System.out.println("Enter valid Registration Number");
                registration_number = br.readLine().toUpperCase();
            }
                    //checking if vehicle is registered or not
            if(Vehicle.getVehicle_objects_Map().containsKey(registration_number))
            {
                System.out.println("*** 100/- rupees for each card ***");
                System.out.println("Enter * TRUE * if you want card:");
                boolean card = Boolean.parseBoolean(br.readLine());
                if(card) {
                    Vehicle.getVehicle_objects_Map().get(registration_number).setCard(card);
                    parking_object.setTotal_amount(parking_object.getTotal_amount() + 100);
                    System.out.println("vehicle successfully get card");
                }
                else System.out.println("Card not issued");
            }
            else {
                System.out.println("Vehicle Not Registered");
                System.out.println("First Register the Vehicle, Then apply for card");
            }
        }catch (NumberFormatException exc){
            System.out.println("Invalid Input");
        }
        catch (Exception e){
            System.out.println("Exception is :"+e);
        }
    }

    //method-3 : park a vehicle  //takes the input vehicle registration number and duration
    public void park_vehicle(){
        try {
            System.out.println("Enter Vehicle Registration Number :");
            String registration_number = br.readLine().toUpperCase();
            while (!registration_number.matches("^[A-Z]{2}\\s[0-9]{2}\\s[A-Z]{2}\\s[0-9]{4}$"))  //validation registration number by regular expressions
            {
                System.out.println("Enter valid Registration Number :");
                registration_number = br.readLine().toUpperCase();
            }
            //if vehicle already present in parking then we can't enter it again
            if(!parking_object.getParked_vehicles_map().containsKey(registration_number))
            {
                // if the vehicle is registered or not
                if (Vehicle.getVehicle_objects_Map().containsKey(registration_number))
                {
                    //if it is 2-wheeler
                    if (Vehicle.getVehicle_objects_Map().get(registration_number).getVehicle_type() == 2)
                    {
                        //checking if the 2-wheeler slots are available or not
                        if (parking_object.getSlots_for_2_Wheeler() > 0)
                        {
                            //key as registration number and value is zero because when that car takeout it will ask duration then it calculate fee
                            parking_object.setParked_vehicles_map(registration_number, 0);
                            //updating parking slot
                            parking_object.setSlots_for_2_Wheeler(parking_object.getSlots_for_2_Wheeler() - 1);
                            System.out.println("Your 2-Wheeler Vehicle Parked Successfully");
                        }
                        else
                        {
                            System.out.println("No Vacant Slots for 2-wheeler");
                        }
                    }
                    else   //else it will be 4-wheeler
                    {
                        if (parking_object.getSlots_for_4_Wheeler() > 0)
                        {
                            //key as registration number and value is zero because when that car takeout it will ask duration then it calculate fee
                            parking_object.setParked_vehicles_map(registration_number, 0);
                            parking_object.setSlots_for_4_Wheeler(parking_object.getSlots_for_4_Wheeler() - 1);
                            System.out.println("Your 4-Wheeler Vehicle Parked Successfully");
                        }
                        else
                        {
                            System.out.println("No Vacant Slots for 4-wheeler");
                        }
                    }
                }
                //if vehicle is unregistered vehicle
                else
                {
                    System.out.println("It is Unregistered Vehicle");
                    System.out.println("Enter Duration in Minutes (only in multiples of 30) :");
                    int duration = Integer.parseInt(br.readLine());
                    while (!(duration >= 30 && duration <= 600 && duration % 30 == 0))
                    {
                        System.out.println("Enter Correct Duration :");
                        duration = Integer.parseInt(br.readLine());
                    }

                    //taking input of vehicle type to update slots
                    System.out.println("Vehicle Type :");
                    int vehicle_type = Integer.parseInt(br.readLine());   //doing type conversion //if wrong input entered it will throw exception
                    while (!Integer.toString(vehicle_type).matches("(2){1}|(4){1}"))  //regular expression to check if number is 2 or 4
                    {
                        System.out.println("enter correct vehicle type");
                        vehicle_type = Integer.parseInt(br.readLine());
                    }
                    //updating parking slot
                    //if 2-wheeler
                    if (vehicle_type == 2) {
                        if (parking_object.getSlots_for_2_Wheeler() > 0)
                        {
                            parking_object.setSlots_for_2_Wheeler(parking_object.getSlots_for_2_Wheeler() - 1);
                            //adding vehicle to parking list
                            parking_object.setParked_vehicles_map(registration_number, duration);
                            System.out.println("Your 2-Wheeler Vehicle Parked Successfully");
                        }
                        else
                        {
                            System.out.println("No Vacant Slots for 2-wheeler");
                        }
                    }
                    //if 4-wheeler
                    else {
                        if (parking_object.getSlots_for_4_Wheeler() > 0)
                        {
                            parking_object.setSlots_for_4_Wheeler(parking_object.getSlots_for_4_Wheeler() - 1);
                            //adding vehicle to parking list
                            parking_object.setParked_vehicles_map(registration_number, duration);
                            System.out.println("Your 4-Wheeler Vehicle Parked Successfully");
                        }
                        else
                        {
                            System.out.println("No Vacant Slots for 4-wheeler");
                        }
                    }
                }
            }
            else
            {
                System.out.println("vehicle is already present in parking");
            }
        }catch (NumberFormatException exc){
            System.out.println("Invalid Input");
        }
        catch (Exception e){
            System.out.println("Exception is "+e);
        }
    }

    //method-4 : Take Out a Vehicle  //it will ask registration number and duration of vehicle
    public void take_out_vehicle()
    {
        try
        {
            System.out.println("Enter Vehicle Registration Number :");
            String registration_number = br.readLine().toUpperCase();
            while (!registration_number.matches("^[A-Z]{2}\\s[0-9]{2}\\s[A-Z]{2}\\s[0-9]{4}$"))  //validation registration number by regular expressions
            {
                System.out.println("Enter valid Registration Number :");
                registration_number = br.readLine().toUpperCase();
            }


            //check if the vehicle is present in parking
            if(parking_object.getParked_vehicles_map().containsKey(registration_number))
            {
                //taking duration from user
                System.out.println("Enter Duration in Minutes (only in multiples of 30) :");
                int duration = Integer.parseInt(br.readLine());
                while (!(duration>=30 && duration<=600 && duration%30==0))
                {
                    System.out.println("Enter Correct Duration :");
                    duration = Integer.parseInt(br.readLine());
                }

                //if the vehicle is registered vehicle
                if(Vehicle.getVehicle_objects_Map().containsKey(registration_number))
                {
                    //if vehicle has card (5% discount)
                    if(Vehicle.getVehicle_objects_Map().get(registration_number).isCard())
                    {
                        //if it 2-wheeler, parking fee is 5/- per 30 minutes
                        if (Vehicle.getVehicle_objects_Map().get(registration_number).getVehicle_type() == 2)
                        {
                            double bill_amount = (((double)duration/30) * 5) - (0.05*((double)duration/30) * 5);

                            System.out.println("Bill amount = " + bill_amount + "/-");  // rupees 5 per 30 minutes for 2 wheeler
                            parking_object.setTotal_amount(parking_object.getTotal_amount()+bill_amount);  //updating amount
                            //removing car from parking list
                            parking_object.getParked_vehicles_map().remove(registration_number);
                            //updating slots
                            parking_object.setSlots_for_2_Wheeler(parking_object.getSlots_for_2_Wheeler()+1);
                        }
                        //else it is 4 wheeler, parking fee is 10/- per 30 minutes
                        else
                        {
                            double bill_amount = (((double)duration/30) * 10) - (0.05*((double)duration/30) * 10);
                            System.out.println("Bill amount = " + bill_amount + "/-");  //rupees 10 per 30 minutes for 4 wheeler
                            parking_object.setTotal_amount(parking_object.getTotal_amount()+bill_amount);
                            //removing car from parking list
                            parking_object.getParked_vehicles_map().remove(registration_number);
                            //updating slots
                            parking_object.setSlots_for_4_Wheeler(parking_object.getSlots_for_4_Wheeler()+1);
                        }
                    }
                    //else vehicle has no card no discount
                    else {
                        //if it 2-wheeler, parking fee is 5/- per 30 minutes
                        if (Vehicle.getVehicle_objects_Map().get(registration_number).getVehicle_type() == 2)
                        {
                            double bill_amount = ((double)duration/30) * 5;
                            System.out.println("Bill amount = " + (duration/30) * 5 + "/-");  // rupees 5 per 30 minutes for 2 wheeler
                            parking_object.setTotal_amount(parking_object.getTotal_amount()+bill_amount);
                            //removing car from parking list
                            parking_object.getParked_vehicles_map().remove(registration_number);
                            //updating slots
                            parking_object.setSlots_for_2_Wheeler(parking_object.getSlots_for_2_Wheeler()+1);
                        }
                        //else it is 4 wheeler, parking fee is 10/- per 30 minutes
                        else
                        {
                            double bill_amount = ((double)duration/30) * 10;
                            System.out.println("Bill amount = " +bill_amount+ "/-");  //rupees 10 per 30 minutes for 4 wheeler
                            parking_object.setTotal_amount(parking_object.getTotal_amount()+bill_amount);
                            //removing car from parking list
                            parking_object.getParked_vehicles_map().remove(registration_number);
                            //updating slots
                            parking_object.setSlots_for_4_Wheeler(parking_object.getSlots_for_4_Wheeler()+1);
                        }
                    }
                }
                //else it is unregistered vehicle
                else {
                    System.out.println("Vehicle Type :");
                    int vehicle_type = Integer.parseInt(br.readLine());   //doing type conversion //if wrong input entered it will throw exception
                    while (!Integer.toString(vehicle_type).matches("(2){1}|(4){1}"))  //regular expression to check if number is 2 or 4
                    {
                        System.out.println("enter correct vehicle type");
                        vehicle_type = Integer.parseInt(br.readLine());
                    }

                        //if vehicle is 2-wheeler parking fee is 7.5/- per 30 minutes
                    if(vehicle_type == 2){
                        //check the duration with the duration while entered into parking
                        //if it exceeds then make a fine 15/- per 30 minutes
                        if(duration > parking_object.getParked_vehicles_map().get(registration_number))
                        {
                            int exceed_time = duration - parking_object.getParked_vehicles_map().get(registration_number);
                            double bill_amount = ((double)parking_object.getParked_vehicles_map().get(registration_number)/30)*7.5;
                            double excess_amount = ((double) exceed_time/30) * 15;
                            System.out.println("Bill amount = "+(excess_amount+bill_amount)+"/-");
                            //updating the amount
                            parking_object.setTotal_amount(parking_object.getTotal_amount()+excess_amount+bill_amount);
                        }
                        //if it not Exceeds
                        else {
                            double bill_amount = ((double)parking_object.getParked_vehicles_map().get(registration_number)/30)*7.5;
                            System.out.println("Bill amount ="+bill_amount);
                            //updating the amount
                            parking_object.setTotal_amount(parking_object.getTotal_amount()+bill_amount);
                        }
                        //removing car from parking list
                        parking_object.getParked_vehicles_map().remove(registration_number);
                        //updating slots
                        parking_object.setSlots_for_2_Wheeler(parking_object.getSlots_for_2_Wheeler()+1);
                    }
                    //else it is 4-wheeler, parking fee is 12.5/- per 30 minutes
                    else {
                        //check the duration with the duration while entered into parking
                        //if it exceeds then make a fine 25/- per 30 minutes
                        if(duration > parking_object.getParked_vehicles_map().get(registration_number))
                        {
                            int exceed_time = duration - parking_object.getParked_vehicles_map().get(registration_number);
                            double bill_amount = ((double)parking_object.getParked_vehicles_map().get(registration_number)/30)*12.5;
                            double excess_amount = ((double) exceed_time/30) * 25;
                            System.out.println("Bill amount = "+(bill_amount+excess_amount)+"/-");
                            //updating the amount
                            parking_object.setTotal_amount(parking_object.getTotal_amount()+bill_amount+excess_amount);
                        }
                        //if it not Exceeds
                        else {
                            double bill_amount = ((double)parking_object.getParked_vehicles_map().get(registration_number)/30)*12.5;
                            System.out.println("Bill amount ="+bill_amount);
                            //updating the amount
                            parking_object.setTotal_amount(parking_object.getTotal_amount()+bill_amount);
                        }
                        //removing car from parking list
                        parking_object.getParked_vehicles_map().remove(registration_number);
                        //updating slots
                        parking_object.setSlots_for_4_Wheeler(parking_object.getSlots_for_4_Wheeler()+1);
                    }
                }
            }
            else {
                System.out.println("Vehicle is Not in Parking");
            }
        }
        catch (Exception e){
            System.out.println("Exception is"+e);
        }
    }
    //method-5 : print the total amount of parking bills
    public void total_amount()
    {
        System.out.println("Total Amount = "+parking_object.getTotal_amount()+"/-");
    }
    //method-5 : print the all parked vehicles
    public void parked_vehicles()
    {
        if(parking_object.getParked_vehicles_map().size()>0) {
            System.out.printf("%-22s%-12s%-6s\n", "-------------------", "--------","------------");
            System.out.printf("%-22s%-12s%-6s\n", "Registration Number", "Duration","    Type");
            System.out.printf("%-22s%-12s%-6s\n", "-------------------", "--------","------------");
            for (Map.Entry m : parking_object.getParked_vehicles_map().entrySet())
            {
                if(Vehicle.getVehicle_objects_Map().containsKey(m.getKey())) {
                    System.out.printf("%-24s%-10s%-6s\n", m.getKey(), m.getValue(),"Registered");
                }
                else {
                    System.out.printf("%-24s%-10s%-6s\n", m.getKey(), m.getValue(),"UnRegistered");
                }
            }
            System.out.println();
            System.out.println("------------------------------------");
            System.out.println("Vacancy slots for 2-wheeler = "+parking_object.getSlots_for_2_Wheeler());
            System.out.println("Vacancy slots for 4-wheeler = "+parking_object.getSlots_for_4_Wheeler());
            System.out.println("------------------------------------");
        }
        else {
            System.out.println("No Vehicles In Parking");
            System.out.println("Vacancy slots for 2-wheeler = "+parking_object.getSlots_for_2_Wheeler());
            System.out.println("Vacancy slots for 4-wheeler = "+parking_object.getSlots_for_4_Wheeler());
        }
    }
    //method-6: print the registered vehicles details
    public void registered_vehicles()
    {
        if(Vehicle.getVehicle_objects_Map().size()>0){
            for (Map.Entry m : Vehicle.getVehicle_objects_Map().entrySet())
            {
                System.out.println("----------------------------------------");
                System.out.println("Registration Number = "+Vehicle.getVehicle_objects_Map().get(m.getKey()).getRegistration_number());
                System.out.println("Vehicle Type = "+Vehicle.getVehicle_objects_Map().get(m.getKey()).getVehicle_type());
                System.out.println("Phone Number = "+Vehicle.getVehicle_objects_Map().get(m.getKey()).getPhone_number());
                System.out.println("Email = "+Vehicle.getVehicle_objects_Map().get(m.getKey()).getEmail());
                System.out.println("Card = "+Vehicle.getVehicle_objects_Map().get(m.getKey()).isCard());
                System.out.println("----------------------------------------");
            }
        }
        else {
            System.out.println("No Vehicles are Registered");
        }
    }
}