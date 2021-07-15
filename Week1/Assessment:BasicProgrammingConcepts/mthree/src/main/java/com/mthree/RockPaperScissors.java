// package com.mthree;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors 
{
/*
The program first asks the user how many rounds he/she wants to play.
    Maximum number of rounds = 10, minimum number of rounds = 1.  If the user asks for something outside this range, the program prints an error message and quits.
    If the number of rounds is in range, the program plays that number of rounds.  Each round is played according to the requirements below.
For each round of Rock, Paper, Scissors, the program does the following:
    The computer asks the user for his/her choice (Rock, Paper, or Scissors).
        Hint: 1 = Rock, 2 = Paper, 3 = Scissors
    After the computer asks for the user’s input, the computer randomly chooses Rock, Paper, or Scissors and displays the result of the round (tie, user win, or computer win).
        Hint: use the Random class.
The program must keep track of how many rounds are ties, user wins, or computer wins.
    Hint: Create three variables to keep track of these items and update them correctly after each round.
The program must print out the number of ties, user wins, and computer wins and declare the overall winner based on who won more rounds.
After all rounds have been played and the winner declared, the program must ask the user if he/she wants to play again.
    If the user says No, the program prints out a message saying, “Thanks for playing!” and then exits.
    If the user says Yes, the program starts over, asking the user how many rounds he/she would like to play.
*/
    public static void main( String[] args )
    {
        int DRAW = 0, WIN = 1, LOSE = 2;

        String[] rps = {"Rock", "Paper", "Scissors"};
        int[] results = new int[3];

        Scanner s = new Scanner(System.in);
        Random r = new Random();

        boolean userWantsToPlay = true;

        while(userWantsToPlay){
            int rounds = 0;

            while(rounds < 1){
                rounds = askForInput("How many rounds would you like to play? (Min 1, Max 10)", s, 1, 11);
            }

            for(int i = 1; i <= rounds; i++){
                int playerChoice = 0;
                int computerChoice = 0;

                System.out.println("Round " + i + ":");

                while(playerChoice < 1){
                    playerChoice = askForInput("Input '1' for Rock, '2' for Paper, or '3' for Scissors!", s, 1, 4);
                } 

                computerChoice = r.nextInt(3) + 1;
                
                // p = 1, -> c = 1 TIE, c = 2 WIN, c = 3 LOSE : c-p : 0, 1, 2
                // p = 2, -> c = 2 TIE, c = 3 WIN, c = 4 LOSE : c-p : 0, 1, 2
                // p = 3, -> c = 3 TIE, c = 4 WIN, c = 5 LOSE : c-p : 0, 1, 2
                // D: 1:1 2:2 3:3 -> 0, 0, 0
                // W: 1:3 2:1 3:2 -> -2, 1, 1
                // L: 1:2 2:3 3:1 -> -1, -1, 2
                if(computerChoice == playerChoice){
                    results[DRAW]++;
                    System.out.println("You picked " + rps[playerChoice-1] + ". The Computer Picked " + rps[computerChoice-1] + ".");
                    System.out.println("It\'s a Draw!");
                }else if(Arrays.asList(-2, 1).contains(playerChoice-computerChoice)){
                    results[WIN]++; 
                    System.out.println("You picked " + rps[playerChoice-1] + ". The Computer Picked " + rps[computerChoice-1] + ".");
                    System.out.println("You Win!");
                }else{
                    results[LOSE]++;
                    System.out.println("You picked " + rps[playerChoice-1] + ". The Computer Picked " + rps[computerChoice-1] + ".");
                    System.out.println("You Lose!");
                }

            }

            System.out.println("\nCurrent Results:");
            System.out.println("Player Wins: " + results[WIN]);
            System.out.println("Computer Wins: " + results[LOSE]);
            System.out.println("Draws: " + results[DRAW] + "\n");
            
            userWantsToPlay = askForInput("Do you want to play again? Respond \"Yes\" or \"No\"", s, "Yes");
        }

        System.out.println("\nFinal Results:");
        System.out.println("Player Wins: " + results[WIN]);
        System.out.println("Computer Wins: " + results[LOSE]);
        System.out.println("Draws: " + results[DRAW]);

        s.close();

    }

        
    public static int askForInput(String prompt, Scanner s, int low, int high){
        /**
         * Prompts the user for input in a range from low to high
         * 
         * @param prompt The String prompt the user will see
         * @param s A Scanner object used to read input from the source
         * @param low The bottom of the range of accepted ints(inclusive)
         * @param high The top of the range of accepted ints (exclusive)
         * 
         * 
         * @return 0 If number is in range, else int user input
         */
        int num = 0;
        System.out.println(prompt);
        String input = s.nextLine();

        try{
            num = Integer.parseInt(input);
        } catch(Exception e) {
            System.out.println("Not a number in range [" + low + "," + high + "), try again");
        }

        if (low <= num && num < high){
            return num;
        } else { 
            System.out.println("Not a number in range [" + low + "," + high + "), try again");
            return 0;
        }

    }


    public static boolean askForInput(String prompt, Scanner s, String target){
        /**
         * Prompts the user for boolean input
         * 
         * @param prompt The String prompt the user will see
         * @param s A Scanner object used to read input from the source
         * @param target The target for the user input, not case sensitive
         * 
         * @return boolean value corresponding to (input == target)
         */
        boolean is = false;
        System.out.println(prompt);
        String input = s.nextLine();

        target = target.toLowerCase();
        input = input.toLowerCase();

        is = input.equals(target);

        return is;
    }
}
