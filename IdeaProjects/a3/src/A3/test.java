package A3;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        String DName;
        String yn;
        String y = "y";
        String n = "n";


        System.out.println("Please enter the Doctor's Name");
        Scanner scanDName = new Scanner(System.in);
        DName = scanDName.next();

        System.out.println("Is this doctor a surgeon please enter y or n");
        Scanner scanyn = new Scanner(System.in);
        yn = scanyn.next();

        if ( yn.equals( y)){
            System.out.println("s");}
        else if ( yn.equals( n)){
            System.out.println("y");}
        }
    }
