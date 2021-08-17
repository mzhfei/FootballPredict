//mengzhe fei
//11200913
//mef382
package commands;
import java.util.TreeMap;

import containers.DoctorMapAccess;
import entities.Doctor;
import entities.Surgeon;

public class NewDoctor extends CommandStatus{
    /**
     * add a new doctor
     * @param name name of the doctor
     * @param response either if the doctor is a surgeon or not
     */
    public void add(String name, String response){
        if (DoctorMapAccess.dictionary().containsKey(name)){
            this.successful = false;
            this.errorMessage = ("entities.Doctor not added as there already "
                    + "is a doctor with the name " + name);}

        Doctor d;
        System.out.println("1");
        if (response.charAt(0) == 'y' || response.charAt(0) == 'Y'){

            d = new Surgeon(name);}
        else {

            d = new Doctor(name);}
        // check to make sure the doctor name doesn't already exsist

        Doctor sameNumberDoctor = DoctorMapAccess.dictionary().put(name, d);
        if (sameNumberDoctor != null)
        {
            DoctorMapAccess.dictionary().put(name, sameNumberDoctor); // put the original doctor back
            this.successful = false;
            this.errorMessage = ("Name in the doctor dictionary even though "
                    + "containsKey failed.  Name "  + name + " not entered.");
        }
        else {
            System.out.println("in else");

            this.successful =true;

        }

    }







        }
