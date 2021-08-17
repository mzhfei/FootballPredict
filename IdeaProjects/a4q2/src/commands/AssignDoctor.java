//mengzhe fei
//11200913
//mef382
package commands;
import java.util.TreeMap;

import containers.DoctorMapAccess;
import containers.PatientMapAccess;
import entities.Doctor;
import entities.Patient;

public class AssignDoctor extends CommandStatus{
    /**
     * assign a doctor and a patient with each other
     * @param healthNumber the healthcard number of the patient
     * @param name name of the patient

     */
    public void assign(int healthNumber, String name){
        Patient p = PatientMapAccess.dictionary().get(healthNumber);
        //check if there is a patient with entered health card number
        if (p == null){
            errorMessage =("There is no patient with health number "
                    + healthNumber);}
        Doctor d = DoctorMapAccess.dictionary().get(name);
        //check if there is a doctor with entered name
        if (d == null)
            errorMessage = ("There is no doctor with name " + name);
        else
        {
            p.addDoctor(d);
            d.addPatient(p);
            successful = true;

        }
    }
}
