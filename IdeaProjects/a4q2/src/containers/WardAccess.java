//mengzhe fei
//11200913
//mef382
package containers;

import entities.Ward;

public class WardAccess {
    private static Ward ward;

    /**
     * sealed constructor
     */
    private WardAccess(){}

    /**
     * a function that take place of the default constructor and make sure no second instance can be construct
     * @param name the name of the ward
     * @param minBedLabel the smallest lable
     * @param maxBedLabel the largest lable
     */
    public static void initialize(String name, int minBedLabel, int maxBedLabel){
        if ( ward != null){
            throw new RuntimeException("The last execution must have been "
                    + "unsuccessful in order to retrieve its error message.");
        }
        ward = new Ward(name, minBedLabel, maxBedLabel);
    }

    /**
     * the function that give access to the instance
     * @return
     */
    public static Ward ward(){
        if ( ward == null){
            throw new RuntimeException("The last execution must have been "
                    + "unsuccessful in order to retrieve its error message.");
        }

        return ward;
    }

}
