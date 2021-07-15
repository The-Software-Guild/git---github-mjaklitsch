import java.util.Scanner;
import java.util.Random;

public class HealthyHearts {
    /*
    Their maximum heart rate should be 220 - their age.
    The target heart rate zone is the 50 - 85% of the maximum.
    */
    public static void main( String[] args )
    {
        Scanner s = new Scanner(System.in);
        Random r = new Random();

        int age = -1;
        while (age < 0){
            System.out.println("What is your age?");
            String input = s.nextLine();

            try{
                age = Integer.parseInt(input);
                if (age < 0){
                    age = -1;
                }
            } catch(Exception e) {
                System.out.println("Not a number, try again");
            }
            
        }
        // Your maximum heart rate should be 170 beats per minute
        // Your target HR Zone is 85 - 145 beats per minute
        int maxHeartRate = 220 - age;
        System.out.println("Your maximum heart rate should be " + maxHeartRate + " beats per minute");
        System.out.println("Your target HR Zone is " + (maxHeartRate/2) + "-" + (Math.round(maxHeartRate*.85)) + " beats per minute");
        
    }
}
