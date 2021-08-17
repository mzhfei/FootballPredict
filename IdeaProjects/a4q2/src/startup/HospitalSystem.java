//mengzhe fei
//11200913
//mef382
package startup;

import entities.Doctor;
import entities.Patient;
import entities.Ward;
import containers.DoctorMapAccess;
import containers.PatientMapAccess;
import containers.WardAccess;
import commands.AssignDoctor;
import commands.DropDoctor;
import commands.EmptyBed;
import commands.NewDoctor;

import java.util.TreeMap;
import java.util.Scanner;
import java.util.Collection;
/**
 * A simple hospital system with only one ward.  Patients and doctors can be created,
 * and patients assigned to a doctor and/or placed in a bed of the ward.
 */
public class HospitalSystem
{
    /**
     * The keyed dictionary of all patients.
     */
    //private TreeMap<Integer, Patient> patients;

    /**
     * The keyed dictionary of all doctors.
     */
    //private TreeMap<String, Doctor> doctors;

    /**
     * The ward to be handled.
     */
    //private Ward ward;

    /**
     * Initialize an instance of the hospital ward
     * relies on user-input to get the relavent information
     */
    public HospitalSystem() {

        // get the ward information
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Initializing the system...");
        System.out.println("Getting Ward information...");
        System.out.print("Enter the name of the Ward: ");
        String name = consoleIn.nextLine();
        System.out.print("Enter the integer label of the first bed: ");
        int firstBedNum = consoleIn.nextInt();
        consoleIn.nextLine();

        System.out.print("Enter the integer label of the last bed: ");
        int lastBedNum = consoleIn.nextInt();
        consoleIn.nextLine();
        WardAccess.initialize(name, firstBedNum, lastBedNum);
    }

    /**
     * Read the information for a new patient and then add the patient
     * to the dictionary of all patients.
     */
    public void addPatient()
    {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Getting Patient information...");
        System.out.print("Enter the name of the patient: ");
        String name = consoleIn.nextLine();

        System.out.print("Enter the health number of the patient: ");
        int healthNum = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line
        if (PatientMapAccess.dictionary().containsKey(healthNum))
        {
            throw new RuntimeException("Patient with the health number " + healthNum + " already exsists");
        }
        else
        {
            Patient p = new Patient(name, healthNum);
            Patient result = PatientMapAccess.dictionary().put(healthNum, p);

            // checking to make sure the the key was unique
            if (result != null)
            {
                PatientMapAccess.dictionary().put(healthNum, result);  // put the original patient back
                throw new RuntimeException("Health number in the patient dictionary even "
                        + "though containsKey failed.  Number " + healthNum + " not entered.");
            }
        }
    }

    /**
     * Read the information for a new doctor and then add the doctor
     * to the dictionary of all doctors.
     */
    public void addDoctor()
    {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Getting Patient information...");
        System.out.print("Enter the name of the doctor: ");
        String name = consoleIn.nextLine();


        System.out.print("Is the doctor a surgeon? (yes or no)");
        String response = consoleIn.nextLine();

        NewDoctor ND = new NewDoctor();
        ND.add(name, response);
        if (!ND.wasSuccessful()){
            ND.getErrorMessage();
        }

    }

    /**
     * Assign a doctor to take care of a patient.
     */
    public void assignDoctorToPatient()
    {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Assigning a new Doctor-Patient Association...");
        System.out.println("Getting Patient information...");
        System.out.print("Enter the health number of the patient: ");
        int healthNumber = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line


        System.out.println("Getting Doctor information...");
        System.out.print("Enter the name of the doctor: ");
        String name = consoleIn.nextLine();

        AssignDoctor AD = new AssignDoctor();
        AD.assign(healthNumber, name);
        if (!AD.wasSuccessful()){
            AD.getErrorMessage();
        }
    }

    /**
     * Assign a patient to a specific bed.
     */
    public void assignBed()
    {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Assigning a Patient to a Bed...");
        System.out.println("Getting Patient information...");
        System.out.print("Enter the health number of the patient: ");
        int healthNumber = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line

        Patient p = PatientMapAccess.dictionary().get(healthNumber);
        if (p == null)
            throw new RuntimeException("There is no patient with health number "
                    + healthNumber);

        if (p.getBedLabel() != -1)
            throw new RuntimeException(" Patient " + p
                    + " is already in a bed so cannot be assigned a new bed");

        System.out.print("Enter the bed number for the patient: ");
        int bedNum = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line
        if (bedNum < WardAccess.ward().getMinBedLabel() || bedNum > WardAccess.ward().getMaxBedLabel())
            throw new RuntimeException("Bed label " + bedNum + " is not valid, as "
                    + "the value must be between " + WardAccess.ward().getMinBedLabel()
                    + " and " + WardAccess.ward().getMaxBedLabel());

        p.setBedLabel(bedNum);
        WardAccess.ward().assignPatientToBed(p, bedNum);
    }

    /**
     * Drop the association between a doctor and a patient.
     */
    public void dropAssociation()
    {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Dropping a new Doctor-Patient Association...");
        System.out.println("Getting Patient information...");
        System.out.print("Enter the health number of the patient: ");
        int healthNumber = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line


        System.out.println("Getting Doctor information...");
        System.out.print("Enter the name of the doctor: ");
        String name = consoleIn.nextLine();

        DropDoctor DD = new DropDoctor();
        DD.drop(healthNumber, name);
        if (!DD.wasSuccessful()){
            DD.getErrorMessage();
        }
    }

    /**
     * Displays the system state
     */
    public void systemState()
    {
        System.out.println(this.toString());
    }

    /**
     * Return a string representation of the entities.HospitalSystem
     * @return a string representation of the entities.HospitalSystem
     */
    public String toString() {
        String result = "\nThe patients in the system are \n";
        Collection<Patient> patientsColl = PatientMapAccess.dictionary().values();
        for (Patient p: patientsColl)
            result = result + p;
        result = result + "\nThe doctors in the system are \n";
        Collection<Doctor> doctorsColl = DoctorMapAccess.dictionary().values();
        for (Doctor d: doctorsColl)
            result = result + d;
        result = result + "\nThe ward is " + WardAccess.ward();
        return result;
    }

    /**
     * Display the empty beds for the ward.
     * Method is just a stub, needs to be implemented
     */
    public void displayEmptyBeds()
    {
        EmptyBed EB = new EmptyBed();
        EB.Empty(WardAccess.ward());
        if (!EB.wasSuccessful()){
            EB.getErrorMessage();
        }

    }


    /**
     * Release a patient from the ward.
     * Method is just a stub, needs to be implemented
     */
    public void releasePatient()
    {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Getting Patient information...");
        System.out.print("Enter the health number of the patient: ");
        int healthNumber = consoleIn.nextInt();

        Patient p = PatientMapAccess.dictionary().get(healthNumber);
        if (p == null)
            throw new RuntimeException("There is no patient with health number "
                    + healthNumber);
        WardAccess.ward().freeBed(p.getBedLabel());
        p.setBedLabel(-1);
    }

    /**
     * Run the hospital system.
     * @param args not used
     */
    public static void main(String[] args)
    {
        Scanner consoleIn = new Scanner(System.in);
        int task = -1;

        HospitalSystem sys = new HospitalSystem();

        try{
            while(task != 1) {
                System.out.print("Please select an operation to do"
                        + "\n1: quit"
                        + "\n2: add a new patient"
                        + "\n3: add a new doctor"
                        + "\n4: assign a doctor to a patient"
                        + "\n5: display the empty beds of the ward"
                        + "\n6: assign a patient a bed"
                        + "\n7: release a patient"
                        + "\n8: drop doctor-patient association"
                        + "\n9: display current system state"
                        + "\nEnter the number of your selection: ");

                task = consoleIn.nextInt();
                consoleIn.nextLine();

                if (task == 1)
                    sys.systemState();
                else if (task == 2)
                    sys.addPatient();
                else if (task == 3)
                    sys.addDoctor();
                else if (task == 4)
                    sys.assignDoctorToPatient();
                else if (task == 5)
                    sys.displayEmptyBeds();
                else if (task == 6)
                    sys.assignBed();
                else if (task == 7)
                    sys.releasePatient();
                else if (task == 8)
                    sys.dropAssociation();
                else if (task == 9)
                    sys.systemState();
                else
                    System.out.println("Invalid option, try again.");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } finally {
            consoleIn.close();
        }
    }
}