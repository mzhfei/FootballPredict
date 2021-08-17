package A3;

import java.util.LinkedList;

public class Patient extends Person {


    private int BedLable ;
    private LinkedList<Doctor> Doctors ;

    /**
     * A Constructor for Petient
     * @param name the name of the patient
     * @param number the health card number of the patienr
      */
    public Patient(String name, int number){
        super(name, number);
        this.BedLable = -1;
        this.Doctors = new LinkedList<>();
    }

    /**
     *
     * @return the bed lable of the patient
     */
    public int getBedLable(){
        return this.BedLable ;
    }


    /**
     * set the bedLable to a patient
     * @param bedlable the new bedlable is going to be set to patient
     */
    public void setBedLable(int bedlable){
        this.BedLable = bedlable;
    }

    /**
     * add a doctor to the doctor's list
     * @param d the doctor is going to be assigned to the patient
     */
    public void addDoctor(Doctor d){

        this.Doctors.add(d);
    }

    /**
     * remove a doctor from the doctor's list
     * @param name the name of the doctor
     */
    public void removeDoctor(String name){
        for ( int i = 0 ; i < this.Doctors.size();) {
            if (name.equals (this.Doctors.get(i).getName())) {
                this.Doctors.remove(i);
            }
            else {
                i++;
            }
        }
    }

    /**
     * checks to see if a Doctor with name is assigned to the patient
     * @param name the name of the doctor to be checked
     * @return  true if the doctor is found, false otherwise
     */
    public boolean hasDoctor(String name) {
        int count = 0;
        for (int i = 0; i < this.Doctors.size(); i++) {
            if (name == this.Doctors.get(i).getName()) {
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
     *
     * @returns a string representation of all the information about the patient
     * in a form suitable for printing. This should include the patients name, health card number, the bed
     * label (if any) and the name of each doctor associated with the patient.
     */
    @Override
    public String toString() {
        String allDoc = "";
        for( Doctor i : Doctors){
            allDoc = allDoc + i.getName() + "\t";

        }
        return super.toString() + "Bed Lable : " + this.getBedLable() + "\nDoctors: " + allDoc;
    }

    public static void main(String[] args){
        int numErrors = 0;
        Patient p = new Patient("Joe", 1234);
        System.out.println( p + "\n");
        if (!p.getName().equals("Joe")) {
            System.out.println("The constructor or getName failed");
            numErrors++;
        }

        if (!(p.Doctors.size() == 0)) {
            System.out.println("The constructor or getName failed");
            numErrors++;
        }

        Doctor d = new Doctor("Jack");
        p.addDoctor(d);
        if (!(p.hasDoctor("Jack"))) {
            System.out.println("The constructor or hasDoctor failed");
            numErrors++;
        }

        if (p.hasDoctor("jack")){
            System.out.println("The removeDoctor or hasDoctor failed");
            numErrors++;
        }

        p.removeDoctor("Jack");
        if (p.hasDoctor("Jack")){
            System.out.println("The removeDoctor or hasDoctor failed");
            numErrors++;
        }

        p.setBedLable(1);
        if (p.getBedLable() != 1 ){
            System.out.println("The get or set bedlable failed");
            numErrors++;
        }

        p.setBedLable(2);
        if (p.getBedLable() != 2){
            System.out.println("The get or set bedlable failed");
            numErrors++;
        }
        System.out.println("The number of errors found is " + numErrors);
    }
}