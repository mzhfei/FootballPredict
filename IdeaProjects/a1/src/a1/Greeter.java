// Mengzhe Fei
//11200913
//mef382
//Cmpt 270

package a1;
import java.util.Scanner;

public class Greeter
{

    public static String introduction(String gret) {
        System.out.println(gret);

        System.out.print("Please enter your name :");
        Scanner in = new Scanner(System.in);
        String namei = in.next();
        System.out.println("hello, " + namei);
        return namei;
    }
    public static void main(String[] Welcome) {

        introduction("welcome");
    }
}
