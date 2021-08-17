//mengzhe fei
//11200913
//mef382

import java.util.*;

/**
 * this class is Basic Doctor class and it is an entity class
 * this class will create an instance of one doctor and his/her health card number
 * you will be able to print of the informations stored here
 */
public class BasicDoctor {


    /**
     * name contains name of the doctor
     */
    private String name;

    /**
     * give the value to name
     * @param name acutal name in world
     */
    public void BasicDoctor(String name){
        this.name = name;
    }

    /**
     * get the name stored
     * @return the name stored in
     */
    public String getName(){
        return this.name;
    }

    /**
     * set the name to a new value
     * @param name a correct name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * give a string representation of all the information about the doctor
     * @return a string representation of all the information about the doctor in a
     * form suitable for printing
     */
    public String toString(){
        return "The doctor's name is " + this.name;
    }

    public static void main(String[] args){

        /**
         * first test case
         * start a new instance , and give name to it
         * check if all the getting setting work, and if getting still work after setting
         */
        BasicDoctor bd = new BasicDoctor();
        bd.BasicDoctor("Jiawei Sensei");
        String jwss = bd.getName();
        System.out.println(jwss);
        System.out.println( bd.toString());
        bd.setName("Sensei Jiawei");
        System.out.println(bd.toString());

        /**
         * first test case
         * start a new instance , and give name to it
         * check if all the getting setting work, and if getting still work after setting
         */
        BasicDoctor bd2 = new BasicDoctor();
        bd2.BasicDoctor("strange");
        String strange = bd2.getName();
        System.out.println(strange);
        System.out.println( bd2.toString());
        bd.setName("master");
        System.out.println(bd.toString());
        /*** finished ***/


    }
}
