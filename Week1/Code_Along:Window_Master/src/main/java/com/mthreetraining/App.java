package com.mthreetraining;

/**
 * Hello world!
 *
 */
import java.util.Scanner;

 public class App 
{
    public static void main( String[] args )
    {
        float height = 0;
        float width = 0;
        // float windowArea;
        // float windowPerimeter;

        float glassPrice = 3.5f;
        float trimPrice = 2.25f;

        Scanner scanner = new Scanner(System.in);

        while (true){

            System.out.println("Enter a Height greater than 0:");

            try{
                height = Float.parseFloat(scanner.nextLine());
                if (height > 0){
                    break;
                }
            } catch(Exception e){}

            System.out.println("That\'s invalid, try again.");
        }


        while (true){

            System.out.println("Enter a Width greater than 0:");

            try{
                width = Float.parseFloat(scanner.nextLine());
                if (width > 0){
                    break;
                }
            } catch(Exception e){}
            
            System.out.println("That\'s invalid, try again.");
        }


        while(true){

            System.out.println("Enter Glass Price greater than or equal to 0 or \"default\":");
            String glassPriceString = scanner.nextLine();

            if (!glassPriceString.equals("default")){
                try{
                    float tempPrice = Float.parseFloat(glassPriceString);
                    if (tempPrice >= 0){
                        glassPrice = tempPrice;
                        break;
                    }
                } catch(Exception e){}
            } else {
                break;
            }

            System.out.println("That\'s invalid, try again.");
        }
        

        while (true){

            System.out.println("Enter Trim Price greater than or equal to 0 or \"default\":");
            String trimPriceString = scanner.nextLine();

            if (!trimPriceString.equals("default")){
                try{
                    float tempPrice = Float.parseFloat(trimPriceString);
                    if (tempPrice >= 0){
                        trimPrice = tempPrice;
                        break;
                    }
                } catch (Exception e){}
            } else {
                break;
            }
            
            System.out.println("That\'s invalid, try again.");
        }

        scanner.close();

        float windowArea = width * height;
        float windowPerimeter = 2 * (width + height);

        float cost = (windowArea * glassPrice) + (windowPerimeter * trimPrice);

        System.out.println("Window height = " + height);
        System.out.println("Window width = " + width);
        System.out.println("Window area = " + windowArea);
        System.out.println("Window perimeter = " + windowPerimeter);
        System.out.println("Total Cost =  " + cost);

    }
}
