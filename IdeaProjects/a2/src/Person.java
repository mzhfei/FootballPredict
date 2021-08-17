//mengzhe fei
//11200913
//mef382


import java.util.*;

/**
 * this class is Person class and it is an entity class
 * this class will create an instance of one patient and his/her health card number
 * you will be able to print of the 2 infomations stored here
 */
public class Person {


    /**
     *  name is name of the patient
     *  HealthCardNumber is the health card number of patient
     */
    private String name;
    private int HealthCardNumber;

    /**
     *create an instance
     * @param name
     * @param HealthCardNumber
     */
    public void Person(String name, int HealthCardNumber){
        this.HealthCardNumber = HealthCardNumber;
        this.name = name;
    }

    /**
     * get the name
     * @return the string stored at name
     */
    public String getName() {
        return this.name;
    }

    /**
     * get the health card number
     * @return the value stored at teh health card number
     */
    public int getHealthCardNumber() {
        return this.HealthCardNumber;
    }

    /**
     * set the name to a new name
     * @param name the new name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * convert name and health card number to a printable form
     * @return a string representation of all the information about the person in a form suitable for printing
     */
    public String toString(){
        return "The name of the patient is: "  + this.name +
                " the patient\'s health card number is: " +
                this.HealthCardNumber;
    }

    public static void main(String[] args) {
        /**
         * first test case
         * start a new instance , and give name jack to it
         * check if all the getting setting work, and if getting still work after setting
         */
        Person p = new Person();
        p.Person("jack", 123456);
        p.name = p.getName();
        p.HealthCardNumber = p.getHealthCardNumber();
        System.out.println(p.toString());
        p.setName("John");
        System.out.println(p.toString());

        /**
         * second test case
         * start a new instance , and give name jack to it
         * check if all the getting setting work, and if getting still work after setting
         */
        Person p2 = new Person();
        p2.Person("watwat", 654321);
        p2.name = p2.getName();
        p2.HealthCardNumber = p2.getHealthCardNumber();
        System.out.println(p2.toString());
        p2.setName("disdis");
        System.out.println(p2.toString());
        /*** finished ***/


    }

}

