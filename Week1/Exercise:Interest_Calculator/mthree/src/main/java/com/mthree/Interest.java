package com.mthree;
import java.util.Scanner;
import java.math.round;
import java.text.DecimalFormat;
/**
 * Hello world!
 *
 */
public class Interest 
{
    public static void main( String[] args )
    {
        /*
            Sample:

            How much do you want to invest? 500
            How many years are investing? 10
            What is the annual interest rate % growth? 10

            Calculating...
            Year 1:
            Began with $500.00
            Earned $51.91
            Ended with $551.91 
        */

        Float principal = null;
        Float years = null;
        Float rate = null;

        Scanner s = new Scanner(System.in);

        while(principal == null){
            principal = askForInput("How much do you want to invest?", s);
        }

        while(years == null){
            years = askForInput("How many years are investing?", s);
        }

        while(rate == null){
            rate = askForInput("What is the annual interest rate % growth?", s);
        }

        s.close();
        
        for(int i = 1; i <= years; i++){
            System.out.println("Year " + i + ": ");

            System.out.println("Began with " + ((principal<0)?"-":"") 
                + String.format("$%.2f", Math.abs(principal)));

            float earned = principal * (rate/100);
            System.out.println("Earned " + ((earned<0)?"-":"") 
                + String.format("$%.2f", Math.abs(earned)));

            principal += earned;
            System.out.println("Ended with " + ((principal<0)?"-":"") 
                + String.format("$%.2f", Math.abs(principal)) + "\n");
            
        }

        
        
    }

    public static Float askForInput(String prompt, Scanner s){
        Float num = null;
        System.out.println(prompt);
        String input = s.nextLine();

        try{
            num = Float.parseFloat(input);
        } catch(Exception e) {
            System.out.println("Not a number, try again");
        }

        return num;
    }
}
