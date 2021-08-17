// Mengzhe Fei
//11200913
//mef382
//Cmpt 270

package a1;

public class CaptialCounter {
    public static void main(String[] args) {
        int inevitable = count("I am inevitable");
        int Ironman = count("I am Iron Man");
        int Thor = count("I love COCA-COLA");

        System.out.println("There are " + inevitable + " capital letter in the sentence 'I am inevitable' ");
        System.out.println("There are " + Ironman + " capital letter in the sentence 'I am Iron Man' ");
        System.out.println("There are " + Thor + " capital letter in the sentence 'I love COCA-COLA' ");
    }
    public static int count(String phrase)
    {
        int i = 0;
        int length = phrase.length();
        for (int x = 0; x < length; x++)
        {
            char aChar = phrase.charAt(x);
            if (Character.isUpperCase(aChar))
            {
                i++;
            }
        }
        return i;
    }



}

