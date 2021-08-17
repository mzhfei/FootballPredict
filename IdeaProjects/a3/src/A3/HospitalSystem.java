package A3;

import java.util.*;

public class HospitalSystem {
    private Ward ward;
    private TreeMap<Integer, Patient> Patient;
    private TreeMap<String, Doctor> Doctor;

    /**
     * A constructor for the class. Initially, there should be no patients and no doctors.
     * The name of the ward and the integer labels for the rst and last beds should be obtained from
     * the user using console input and output
     */
    public HospitalSystem(){
        this.Patient = new  TreeMap<>();
        this.Doctor = new TreeMap<>();

        String Wardname;
        int firstLable;
        int lastLable;
        try{
            System.out.println("Please enter the name of the Ward");
            Scanner scanW = new Scanner(System.in);
            Wardname = scanW.next();

            System.out.println("Please enter the first lable");
            Scanner scan2 = new Scanner(System.in);
            firstLable = scan2.nextInt();

            System.out.println("Please enter the last lable");
            Scanner scan3 = new Scanner(System.in);
            lastLable = scan3.nextInt();

            this.ward = new Ward(Wardname, firstLable,lastLable);}
        catch (InputMismatchException e){
            System.out.println("Sayonara!");
            HospitalSystem HS = new HospitalSystem();
        }

    }

    /**
     * add a new patient to the system
     */
    public void addPatient(){
        int HealthNum;
        String PName;
        try{

            System.out.println("Please enter the Health Number");
            Scanner scanNum = new Scanner(System.in);
            HealthNum = scanNum.nextInt();

            System.out.println("Please enter the Patient's Name");
            Scanner scanPName = new Scanner(System.in);
            PName = scanPName.nextLine();

            Patient P = new Patient(PName, HealthNum);
            System.out.println(HealthNum);
            System.out.println(PName);
            System.out.println(P.toString());
            this.Patient.put(HealthNum, P);}
        catch (InputMismatchException e){
            System.out.println("entered wrong");
            addPatient();

    }}

    /**
     * add a new doctor to the system
     */
    public void addDoctor(){

        String DName;
        String yn;
        String y = "y";
        String n = "n";
        try{

            System.out.println("Please enter the Doctor's Name");
            Scanner scanDName = new Scanner(System.in);
            DName = scanDName.next();

            System.out.println("Is this doctor a surgeon please enter y or n");
            Scanner scanyn = new Scanner(System.in);
            yn = scanyn.next();

            if ( yn.equals( y)){
                Surgeon D = new Surgeon(DName);
                this.Doctor.putIfAbsent(DName, D);}
            else if ( yn.equals( n)){
                Doctor D = new Doctor(DName);
                this.Doctor.putIfAbsent(DName, D);}
            else{
                System.out.println("enter y or n only");
                addDoctor();}
        }
        catch (InputMismatchException e){
            System.out.println("entered wrong");
            addDoctor();}
    }

    /**
     * assign a doctor to a patient as well as  adding the patient into the doctor’s list of patients
     */
    public void assignDoctorToPatient() {
        String DName;
        int HealthNum;
        Doctor d;
        Patient p;

        try{
            System.out.println("Please enter the Doctor's Name");
            Scanner scanDName = new Scanner(System.in);
            DName = scanDName.next();
            d = this.Doctor.get(DName);

            System.out.println("Please enter the Health Number of the patient");
            Scanner scanNum = new Scanner(System.in);
            HealthNum = scanNum.nextInt();
            p = this.Patient.get(HealthNum);

            p.addDoctor(d);
            d.addPatient(p);}
        catch (InputMismatchException e){
            System.out.println("entered wrong");
            assignDoctorToPatient();}
    }

    /**
     * assign a patient a bed
     */
    public void assignBed(){
        int HealthNum;
        Patient p;
        int BedLable;
        try{
            System.out.println("Please enter the Health Number of the patient");
            Scanner scanNum = new Scanner(System.in);
            HealthNum = scanNum.nextInt();
            p = this.Patient.get(HealthNum);

            System.out.println("Please enter the Bed lable");
            Scanner scanLable = new Scanner(System.in);
            BedLable = scanLable.nextInt();

            p.setBedLable(BedLable);
            this.ward.assignPatientToBed(p, BedLable);}
        catch (InputMismatchException e){
            System.out.println("entered wrong");
            assignBed();}
    }

    /**
     *  drop doctor-patient association
     */
    public void dropAssociation(){

        String DName;
        int HealthNum;
        Doctor d;
        Patient p;

        try{
            System.out.println("Please enter the Doctor's Name");
            Scanner scanDName = new Scanner(System.in);
            DName = scanDName.next();
            d = this.Doctor.get(DName);

            System.out.println("Please enter the Health Number of the patient");
            Scanner scanNum = new Scanner(System.in);
            HealthNum = scanNum.nextInt();
            p = this.Patient.get(HealthNum);

            p.removeDoctor(DName);
            d.removePatient(HealthNum);}
        catch (InputMismatchException e){
            System.out.println("entered wrong");
            dropAssociation();}
    }

    /**
     * display current system state
     */
    public void systemState(){
        System.out.println(this.toString());

    }

    /**
     *
     * @return a string representation of all the information about the doctor
     * in a form suitable for printing
     */
    public String toString() {
        String DoctorInfo = "Doctors: \n";
        Set<String> DoctorName = Doctor.keySet();
        for(String i : DoctorName){
            DoctorInfo = DoctorInfo + Doctor.get(i).toString() + "\n";
        }

        String PatientInfo = "Pattient: \n";
        Set<Integer> PatientNum = Patient.keySet();
        for(int i : PatientNum){
            PatientInfo = PatientInfo + Patient.get(i).toString() + "\n";
        }

        String WardInfo = this.ward.toString();
        return DoctorInfo + PatientInfo + WardInfo;
    }

    public void displayEmptyBeds(){
    }

    public void releasePatient(){
        //this.Patient.remove(healthNum);
    }


    public static void main(String[] args) {
        int x = 0;
        HospitalSystem HS = new HospitalSystem();
        while (x == 0){
            System.out.println("1. quit\n" +
                    "2. add a new patient to the system\n" +
                    "3. add a new doctor to the system\n" +
                    "4. assign a doctor to a patient (this should also add the patient into the doctor’s list of patients)\n" +
                    "5. display the empty beds of the ward\n" +
                    "6. assign a patient a bed\n" +
                    "7. release a patient\n" +
                    "8. drop doctor-patient association\n" +
                    "9. display current system state");

            System.out.println("Please enter the Number of the task you want to operate");
            Scanner Taskin = new Scanner(System.in);
            int Task = Taskin.nextInt();
            if (Task == 1){
                HS.systemState();
                System.out.println("See you!");
                x +=1;
            }
            else if (Task == 2){
                HS.addPatient();
                continue;
            }
            else if (Task == 3){
                HS.addDoctor();
                continue;
            }
            else if (Task == 4){
                HS.assignDoctorToPatient();
            }
            else if (Task == 5){
                HS.displayEmptyBeds();
            }
            else if (Task == 6){
                HS.assignBed();
            }
            else if (Task == 7){
                HS.releasePatient();
            }
            else if (Task == 8){
                HS.dropAssociation();
            }
            else if (Task == 9){
                HS.systemState();
            }
        }

    }



}
