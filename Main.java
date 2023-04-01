package Parking_Billing;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main
{
    public static void main(String[] args)
    {
        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
        Methods m = new Methods();
        int choice ;
        System.out.println("------------------------------------");
        System.out.println("        PARKING BILLING SYSTEM      ");
        System.out.println("------------------------------------");
        for(;true;) {
            System.out.println("*********************************");
            System.out.println("|   1.Register a Vehicle        |");
            System.out.println("|   2.Get Parking Card          |");
            System.out.println("|   3.Park a Vehicle            |");
            System.out.println("|   4.Take Out a Vehicle        |");
            System.out.println("|   5.Total Amount              |");
            System.out.println("|   6.Parked Vehicles List      |");
            System.out.println("|   7.Registered Vehicles List  |");
            System.out.println("|   8.Exit                      |");
            System.out.println("*********************************");
            System.out.println("Enter your Choice :");
            try {
                choice = Integer.parseInt(br2.readLine());
                while (!Integer.toString(choice).matches("[1-8]{1}"))
                {
                    System.out.println("Enter Correct Choice :");
                    choice = Integer.parseInt(br2.readLine());
                }
                switch (choice)
                {
                    case 1:
                        m.register_vehicle();
                        break;
                    case 2:
                        m.get_parking_card();
                        break;
                    case 3:
                        m.park_vehicle();
                        break;
                    case 4:
                        m.take_out_vehicle();
                        break;
                    case 5:
                        m.total_amount();
                        break;
                    case 6:
                        m.parked_vehicles();
                        break;
                    case 7:
                        m.registered_vehicles();
                        break;
                    case 8:
                        System.exit(0);
                    default:
                        System.out.println("Enter Correct Choice");
                }
            }
            catch (NumberFormatException exc){
                System.out.println("Invalid Input Try Again");
            }
            catch (Exception e){
                System.out.println("Exception is "+e);
            }
        }
    }
}