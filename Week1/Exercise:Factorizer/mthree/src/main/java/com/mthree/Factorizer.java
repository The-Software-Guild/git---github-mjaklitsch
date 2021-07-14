package com.mthree;
import java.util.Scanner;
import java.io.*;
/**
 * Hello world!
 *
 */
public class Factorizer 
{
    public static void main( String[] args )
    {
        
        int num;

        Scanner s = new Scanner(System.in);
        while(true){
            System.out.println("What number do you want to factor?");
            String input = s.nextLine();

            try{
                num = Integer.parseInt(input);
                break;
            } catch(Exception e) {
                System.out.println("Not a number, try again");
            }
        }
        
        s.close();

        int numFactors = 1;
        int sumFactors = 0;

        int step = (num%2) + 1; // odd = 2 even = 1
        System.out.println("The factors of " + num + " are:");
        if(num < 0){
            for(int i = -1; i >= num/2; i-=step){
                if (num%i==0){
                    System.out.print(i + " ");
                    numFactors++;
                    sumFactors+=i;
                }
                
            }
        } else {
            for(int i = 1; i <= num/2; i+=step){
                if (num%i==0){
                    System.out.print(i + " ");
                    numFactors++;
                    sumFactors+=i;
                }
                
            }
        }
        System.out.println(num);
        System.out.println(num + " has " + numFactors + " factors.");
        
        System.out.println(num + " is " + ((sumFactors == num)? "": "not ") 
            + "a perfect number.");
        boolean isPrime = (num > 1) && numFactors > 2;
        System.out.println(num + " is " + (isPrime? "not ": "") 
            + "a prime number.");
        

        

    }
}
