//mengzhe fei
//11200913
//mef382

import lib280.list.ArrayedList280;
import lib280.list.LinkedList280;
import java.lang.*;
import java.util.Random;

public class A1Q1 {
    public static Sack [] generatePlunder (int howMany ) {
        Random generator = new Random ();
        Sack grain [] = new Sack [ howMany ];
        for(int i =0; i < howMany ; i ++) {
            grain [ i ] = new Sack (
                    Grain . values ()[ generator . nextInt ( Grain . values (). length )] ,
                    generator . nextDouble () * 100 );
        }
        return grain ;
}


    public static void main(String[] args) {
        Sack[] sacks1 = generatePlunder(20);
        ArrayedList280<LinkedList280<Sack>> array = new ArrayedList280<>(5);

        for ( int count = 0 ; count <5 ; count++){
            array.insert( new LinkedList280<Sack>());
        }

        for ( Sack sack : sacks1){
            for ( int k = 0 ; k < 5; k ++){
                if (sack.type == Grain.values()[k]){
                    array.getItemAtIndex(k).insert(sack);
                }
            }
        }

        for ( int i = 0 ; i < 5 ; i ++){
            double sum = 0.0 ;
            array.getItemAtIndex(i).goFirst();
            while (array.getItemAtIndex(i).itemExists()){
                sum = sum + array.getItemAtIndex(i).item().getWeight();
                array.getItemAtIndex(i).goForth();
            }
            System.out.println("Jack plundered "+ sum + "pounds of " + Grain.values()[i]);
        }









    }
}
