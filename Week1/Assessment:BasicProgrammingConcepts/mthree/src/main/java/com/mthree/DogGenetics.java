import java.util.Scanner;
import java.util.Random;


public class DogGenetics {
    /*
    Write a program that asks the user for the name of their dog, and then generates a fake DNA background report on the pet dog.
    It should assign a random percentage to 5 dog breeds (that should add up to 100%!) 
    */
    public static void main( String[] args )
    {
        Scanner s = new Scanner(System.in);
        Random r = new Random();

        System.out.print("What's your dog's name?");
        String name = s.nextLine();

        final String[] breedNames = {"Scottish Terrier", "Chihuahua", "St. Bernard", "Husky", "Poodle"};
        int sum = 0;

        System.out.println(name + " is:");
        for(int i = 0; i <  breedNames.length; i++){
            int breedPercentage = r.nextInt(100-sum);
            sum += breedPercentage;
            System.out.println(breedPercentage + "% " + breedNames[i]);
        }

        System.out.println("Thats a pretty cool dog you've got there");
        

    }
}
