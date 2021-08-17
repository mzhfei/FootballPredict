//mengzhe fei
//11200913
//mef382

import java.util.*;
public class Ward {
    /**
     * this class is a container class
     * it contains all the patients in ward
     */

    /**
     * WardName is the name of this ward
     * FirstBedLable is the bed number of the first bed in the ward
     * Patients[] is the list contains all the patienrs
     */
    private String WardName;
    private int FirstBedLable;
    private Person Patients[];

    /**
     * create a instance of ward
     * @param WardName the name of this ward
     * @param FirstBedLable the bed number of the first bed in the ward
     * @param LastBedLable the list contains all the patienrs
     **/
    public void Ward(String WardName, int FirstBedLable, int LastBedLable){
        this.WardName = WardName;
        this.FirstBedLable = FirstBedLable;
        this.Patients = new Person[LastBedLable - FirstBedLable];

    }

    /**
     * get the ward name of the ward
     * @return the name of the ward
     */
    public String getWardName(){
        return this.WardName;
    }

    /**
     * get the First Bed Lable
     * @return the First Bed Lable
     */
    public int getFirstBedLable(){
        return this.FirstBedLable;
    }

    /**
     * get the Last Bed Lable
     * @return the Last Bed Lable
     */
    public int getLastBedLable(){
        return this.FirstBedLable + this.Patients.length;
    }

    /**
     * translate the bed lable into index of the list
     * @param BedLable the lable on bed in real world
     * @return the index of the given bed
     */
    public int LabletoIndex(int BedLable){
        return BedLable - this.FirstBedLable;
    }

    /**
     * give the bed lable from index of the list of the beds
     * @param Index the index of the given bed
     * @return the lable on bed in real world
     */
    public int IndextoLable(int Index){
        return Index + this.FirstBedLable;
    }

    /**
     * check if the there is already a patient in given bed or nor
     * @param Lable the lable of given bed
     * @return if there is one patient in bed
     */
    public Boolean Occupied( int Lable ){
        int Index = LabletoIndex(Lable);
        if ( this.Patients[Index] == null){
            return Boolean.TRUE;
        }
        else{
            return Boolean.FALSE;
        }
    }

    /**
     * check if there is a patient and give the patient's name if there is one
     * @param Lable the bed lable you want to check
     * @return the patients name or null if there is not one
     */
    public String getPatientinBed( int Lable ){

        if (Occupied(Lable)){
            System.out.println("No one is in this bed");
            return null;
        }

        else{
            int index = LabletoIndex(Lable);
            //System.out.println("The patient in this bed is " + this.Patients[index].getName());
            return this.Patients[index].getName();
        }
    }

    /**
     * check if there is patient at given bed, and set the new patient in that bed if there is not
     * @param PatientName new patient's name
     * @param Lable the bed lable you want to adjust
     */
    public void setPatienttoBed(String PatientName, int Lable){

        if (Occupied(Lable)){
            int Index = LabletoIndex(Lable);
            Person P =  new Person();
            P.setName(PatientName);
            this.Patients[Index] = P;
        }

        else{
            System.out.println("Failed to set since this bed is not empty");
        }
    }

    /**
     * give all infomarion into printable readable form
     * @return a string representation of all the information about the ward in a
     * form suitable for printing including the name of the ward and for each bed
     */
    public String toString(){
        String AllInfo;
        AllInfo = "The Ward " + getWardName()+  ":\n";
        for (int i = 0; i < this.Patients.length; i ++){
            //System.out.println(i + AllInfo);
            if (Occupied(IndextoLable(i))){
                AllInfo = AllInfo + "Bed" + IndextoLable(i) + " is empty \n";
            }
            else{
                AllInfo = AllInfo + "Bed" + IndextoLable((i)) + " has patient named "+
                        getPatientinBed((IndextoLable(i)))+ "\n";
            }
        }
        return AllInfo;
    }


    public static void main(String[] args){

        /**
         * first test case
         * start a new instance , and give name and lables to it
         * check if all the getting setting work, and if getting still work after setting
         * check if occupied works on both occupied and not occupied bed
         * check if getting bed lables work and check if the index is correct after transfered from lable
         * last check if to string is correct
         */
        Ward W = new Ward();
        W.Ward("Emergency", 305, 311);
        W.setPatienttoBed("Jay", 307);
        System.out.println(W.Occupied(307));
        W.setPatienttoBed("Jay2", 307);
        System.out.println(W.getFirstBedLable());
        System.out.println(W.getPatientinBed(307));
        W.getPatientinBed(308);
        System.out.println(W.getWardName());
        System.out.println(W.getLastBedLable());
        System.out.println(W.toString());

        /**
         * 2ndtest case
         * start a new instance , and give name and lables to it
         * check if all the getting setting work, and if getting still work after setting
         * check if occupied works on both occupied and not occupied bed
         * check if getting bed lables work and check if the index is correct after transfered from lable
         * last check if to string is correct
         */

        Ward W2 = new Ward();
        W2.Ward("Emergency2", 305, 311);
        W2.setPatienttoBed("Ronaldo", 307);
        System.out.println(W2.Occupied(307));
        W2.setPatienttoBed("bale", 308);
        System.out.println(W2.getFirstBedLable());
        System.out.println(W2.getPatientinBed(307));
        W2.getPatientinBed(308);
        System.out.println(W2.getWardName());
        System.out.println(W2.getLastBedLable());
        System.out.println(W2.toString());

        /*** finished ***/

    }
}
