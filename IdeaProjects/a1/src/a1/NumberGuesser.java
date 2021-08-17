// Mengzhe Fei
//11200913
//mef382
//Cmpt 270

package a1;

import java.util.Scanner;


public class NumberGuesser {
    public static void main(String[] grep){
        System.out.print("Guess a nuber between 1 and 100: ");
        Scanner in = new Scanner(System.in);
        float number = in.nextFloat();
        do{
            if (number < 1){
                System.out.println("Too low！");
                System.out.print("Guess a nuber between 1 and 100: ");
                in = new Scanner(System.in);
                number = in.nextFloat();
            }
            else if(number > 100){
                System.out.println("Too High!");
                System.out.print("Guess a nuber between 1 and 100: ");
                in = new Scanner(System.in);
                number = in.nextFloat();
            }
        }while ((number > 100 )|| (number <1));


        System.out.println("Valid Guess!");




    }
}
