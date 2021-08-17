//mengzhe fei
//11200913
//mef382
package containers;

import entities.Doctor;

import java.util.TreeMap;

public class DoctorMapAccess {
    private static TreeMap<String, Doctor> doctors = new TreeMap<>();
    /**
     * sealed constructor
     */
    private DoctorMapAccess(){}

    /**
     * the function that give access to the instance
     * @return
     */
    public static TreeMap<String, Doctor> dictionary(){
        return doctors;
    }



}
