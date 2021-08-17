//mengzhe fei
//11200913
//mef382
package containers;
import entities.Patient;

import java.util.TreeMap;

public class PatientMapAccess {
    private static TreeMap<Integer, Patient> patients = new TreeMap<Integer, Patient>() ;
    /**
     * sealed constructor
     */
    private PatientMapAccess(){}
    /**
     * the function that give access to the instance
     * @return
     */
    public static TreeMap<Integer, Patient> dictionary(){
        return patients;
    }





}
