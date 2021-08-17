        package A3;

import java.util.LinkedList;

public class Doctor extends BasicDoctor {
    private String Name;
    private LinkedList<Patient> Patients;

    /**
     * A constructor
     * @param name doctor’s name
     */
    public Doctor(String name){
        super(name);
        this.Patients =new LinkedList<>();
    }

    /**
     * adds a patient to the doctor’s list.
     * @param p the patient to be added
     */
    public void addPatient(Patient p){
        this.Patients.add(p);
    }

    /**
     * removes a patient from the doctor’s list
     * @param healthNum the healthNum of the patient to be removed
     */
    public void removePatient(int healthNum){
        for ( int i = 0 ; i < this.Patients.size();) {
            if (healthNum == this.Patients.get(i).getHealthNumber()) {
                this.Patients.remove(i);
            }
            else {
                i++;
            }
        }
    }

    /**
     * checks to see if a Patient with healthNum is under the
     * doctor’s care
     * @param healthNum the healthNum of the patient to be checked
     * @return true if the patient is found, false otherwise
     */
    public boolean hasPatient(int healthNum){
        int count = 0;
        for (int i = 0; i < this.Patients.size(); i++) {
            if (healthNum == this.Patients.get(i).getHealthNumber()) {
                count = count + 1;
                break;
            }
        }
        if (count == 1){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * @return a string representation of all the information about the doctor
     * in a form suitable for printing
     */
    @Override
    public String toString(){
        String allPatient = super.toString() + "Patients: \n";
        for( Patient i : Patients){
            allPatient = allPatient + i.getName() + " ";
        }
        return allPatient;
    }

    public static void main(String[] args) {
        {
            int numErrors = 0;
            Doctor d = new Doctor("Joe");
            System.out.println("Doctor Joe is " + d + "\n");
            if (!d.getName().equals("Joe")) {
                System.out.println("The constructor or getName failed");
                numErrors++;
            }

            if (!(d.Patients.size() == 0)) {
                System.out.println("The constructor or getName failed");
                numErrors++;
            }

            Patient p = new Patient("Jack", 1234);
            d.addPatient(p);
            if (!(d.hasPatient(1234))) {
                System.out.println("The constructor or hasPatient failed");
                numErrors++;
            }

            d.removePatient(1234);
            if (d.hasPatient(1234)){
                System.out.println("The removePatient or hasPatient failed");
                numErrors++;
            }

            Doctor d2 = new Doctor("Mary");
            System.out.println("Doctor Mary is " + d2 + "\n");
            if (!d2.getName().equals("Mary")) {
                System.out.println("The constructor or getName failed");
                numErrors++;
            }


            Patient p2 = new Patient("Jak", 123);
            d2.addPatient(p2);
            if (!(d2.hasPatient(123))) {
                System.out.println("The constructor or hasPatient failed");
                numErrors++;
            }

            d2.removePatient(123);
            if (d2.hasPatient(123)){
                System.out.println("The removePatient or hasPatient failed");
                numErrors++;
            }
            System.out.println("The number of errors found is " + numErrors);

        }
    }
}
