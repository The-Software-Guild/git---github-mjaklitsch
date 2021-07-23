package com.dvdlibrary.ui;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO{
    private Scanner s = new Scanner(System.in);


    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String prompt) {
        double input;

        while(true){
            print(prompt);

            try{
                input = Double.parseDouble(s.nextLine());
            } catch (Exception e){
                print("Invalid input, try again");
                continue;
            }

            return input;
        }

    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double input;

        while(true){
            print(prompt);
            try{
                input = Double.parseDouble(s.nextLine());
            } catch (Exception e){
                print("Invalid input, try again");
                continue;
            }

            if(min <= input && input <= max){
                return input;
            } else {
                print("Input not in range " + min + "-" + max);
            }

        }
    }

    @Override
    public float readFloat(String prompt) {
        float input;

        while(true){
            print(prompt);

            try{
                input = Float.parseFloat(s.nextLine());
            } catch (Exception e){
                print("Invalid input, try again");
                continue;
            }

            return input;
        }
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float input;

        while(true){
            print(prompt);
            try{
                input = Float.parseFloat(s.nextLine());
            } catch (Exception e){
                print("Invalid input, try again");
                continue;
            }

            if(min <= input && input <= max){
                return input;
            } else {
                print("Input not in range " + min + "-" + max);
            }

        }
    }

    @Override
    public int readInt(String prompt) {
        int input;

        while(true){
            print(prompt);

            try{
                input = Integer.parseInt(s.nextLine());
            } catch (Exception e){
                print("Invalid input, try again");
                continue;
            }

            return input;
        }
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int input;

        while(true){
            print(prompt);
            try{
                input = Integer.parseInt(s.nextLine());
            } catch (Exception e){
                print("Invalid input, try again");
                continue;
            }

            if(min <= input && input <= max){
                return input;
            } else {
                print("Input not in range " + min + "-" + max);
            }

        }
    }

    @Override
    public long readLong(String prompt) {
        long input;

        while(true){
            print(prompt);
            try{
                input = Long.parseLong(s.nextLine());
            } catch (Exception e){
                print("Invalid input, try again");
                continue;
            }

            return input;
        }
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long input;

        while(true){
            print(prompt);
            try{
                input = Long.parseLong(s.nextLine());
            } catch (Exception e){
                print("Invalid input, try again");
                continue;
            }

            if(min <= input && input <= max){
                return input;
            } else {
                print("Input not in range " + min + "-" + max);
            }

        }
    }

    @Override
    public String readString(String prompt) {
        String input;

        print(prompt);
        input = s.nextLine();

        return input;
    }
    
}
