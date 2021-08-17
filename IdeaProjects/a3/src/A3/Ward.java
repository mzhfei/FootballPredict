package A3;

import java.util.LinkedList;

/**
 * A ward of a hospital with a specified number of beds with consecutive labels.
 */
public class Ward 
{
	/** 
	 * The name of this ward.
	 */
	private String name;

	/**
	 * The (external) label of the first bed of the ward. 
	 */
	private int minBedLabel;

	/**
	 * An array to represent the beds of the ward.  Each bed is empty (null) 
	 * or else has a Patient in it.
	 */
	private Patient[] beds;

	/**
	 * Initialize the ward with the name given, and with beds those labels are 
	 * the consecutive integers from minBedLabel to maxBedLabel.
	 * 
	 * @param wName the name of the ward
	 * @param wMinBedLabel the label of the ward's first bed
	 * @param wMaxBedLabel the label of the ward's last bed
	 */
	public Ward(String wName, int wMinBedLabel, int wMaxBedLabel)
	{
		if (wName == null || wName.equals(""))
			throw new RuntimeException("The name of a ward cannot be null or empty.  " 
			                           + "It is " + wName);
		if (wMinBedLabel < 0 || wMaxBedLabel < wMinBedLabel)
			throw new RuntimeException("The bed labels " + wMinBedLabel + " and " + wMaxBedLabel 
			                  + " are invalid as they cannot be negative and must have at least one bed.");
		
		name = wName;
		minBedLabel = wMinBedLabel;
		beds = new Patient[wMaxBedLabel - wMinBedLabel + 1];
	}

	/**
	 * Return the name of this ward.
	 *
	 * @return the name of this ward
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Return the smallest label for a bed on the ward.
	 * 
	 * @return the label for the first bed in the ward
	 */
	public int getMinBedLabel()
	{
		return minBedLabel;
	}

	/**
	 * Return the largest label for a bed on the ward.
	 * 
	 * @return the label for the last bed in the ward
	 */
	public int getMaxBedLabel()
	{
		return minBedLabel + beds.length - 1;
	}

	/**
	 * Is bedLabel a valid external label for a bed?
	 * 
	 * @param bedLabel a candidate bed label
	 * @return whether or not the provided label is within the range of possible labels
	 */
	public boolean isValidLabel(int bedLabel)
	{
		return bedLabel >= minBedLabel && bedLabel <= minBedLabel + beds.length - 1;
	}

	/**
	 * Return the internal/array index of the bed corresponding to the external label.
	 *  
	 * @param bedLabel a bed label
	 * @return the index in the bed array corresponding to the provided label
	 */
	private int externalToInternalIndex(int bedLabel)
	{
		return bedLabel - minBedLabel;
	}

	/**
	 * Return the external/user label of the bed corresponding to the internal index.
	 * 
	 * @param arrayIndex an index for the array of beds
	 * @return the bed label corresponding to the provided index
	 */
	private int internalToExternalLabel(int arrayIndex)
	{
		return arrayIndex + minBedLabel;
	}

	/**
	 * Is the specified bed occupied?
	 * 
	 * @param bedLabel a bed label
     * @return whether or not the bed corresponding to the provided label is occupied by a patient
	 */
	public boolean isOccupied(int bedLabel)
	{
		return beds[externalToInternalIndex(bedLabel)] != null;
	}

	/**
	 * Assign the specified pato the specified bed.
	 * 
	 * @param p the patient to assign to a bed
	 * @param bedLabel a bed label
	 */
	public void assignPatientToBed(Patient p, int bedLabel)
	{
		beds[externalToInternalIndex(bedLabel)] = p;
	}

	/**
	 * Return the patient in the specified bed.
	 * 
	 * @param bedLabel a bed label
	 * @return the patient that is assigned to the bed cooresponding to the provided bed label
	 */
	public Patient getPatient(int bedLabel)
	{
		return beds[externalToInternalIndex(bedLabel)];
	}

	/**
	 * Return a String representation of the properties of the ward.
	 * 
	 * @return a String representation of the ward
	 */

	/**
	 * return a list contain all the lables of the beds that are empty
	 * @return a list contain all the lables of the beds that are empty
	 */
	public LinkedList<Integer> availableBeds(){
		LinkedList<Integer> Empty = new LinkedList<>();
		for (int i = 0; i < beds.length ; i ++){
			if (! isOccupied(internalToExternalLabel(i))){
				Empty.add( internalToExternalLabel(i));
			}
		}
		return Empty;
	}

	/**
	 * free the bed from the patient
	 * @param BedLabel the bed lable that is going to have no more patient
	 */
	public void freeBed(int BedLabel){
		beds[externalToInternalIndex(BedLabel)] = null;
	}


	public String toString()
	{
		String result = "\nWard " + name + " with capacity " + beds.length
		                + " has the following patients: ";
		for (int i = 0; i < beds.length; i++)
		{
			result = result + "\nbed " + internalToExternalLabel(i) + ": ";
			if (beds[i] != null)
				result = result + beds[i].getName();
		}
		return result + "\n";
	}

	/**
	 * A method to test the class.
	 */
	public static void main(String[] args)
	{
		int numErrors = 0;

        // testing all the methods with one instance of a Ward
        Ward w = new Ward("surgery", 200, 210);

        if(!w.getName().equals("surgery")) {
            System.out.println("The constructor or getName failed.");
        }
        if(w.getMinBedLabel() != 200) {
            System.out.println("The constructor or getMinBedLabel failed.");
        }
        if(w.getMaxBedLabel() != 210) {
            System.out.println("The constructor or getMaxBedLabel failed.");
        }
        // Note the following method was not in the assignment spec but if students
        // included it, it should be tested
        if(!w.isValidLabel(200) || !w.isValidLabel(201)
                || !w.isValidLabel(210) || !w.isValidLabel(209)
                ||  w.isValidLabel(199)
                ||  w.isValidLabel(211)) {
            System.out.println("isValidLabel failed.");
        }
        if(w.internalToExternalLabel(w.externalToInternalIndex(200)) != 200
                || w.internalToExternalLabel(w.externalToInternalIndex(210)) != 210
                || w.internalToExternalLabel(w.externalToInternalIndex(205)) != 205) {
            System.out.println("internalToExternalLabel failed.");
        }
        if(w.externalToInternalIndex(w.internalToExternalLabel(0)) != 0
                || w.externalToInternalIndex(w.internalToExternalLabel(w.beds.length-1)) != w.beds.length-1
                || w.externalToInternalIndex(w.internalToExternalLabel(w.beds.length/2)) != w.beds.length/2) {
            System.out.println("externalToInternalIndex failed.");
        }
        // Check the mapping of internal and external indices.
        if (w.externalToInternalIndex(200) != 0)
            System.out.println("Minimum external label was not converted to 0.");
        if (w.externalToInternalIndex(210) != w.beds.length-1)
            System.out.println("Maximum external label was not converted "
                    + "to last location of the array.");
        if (w.internalToExternalLabel(0) != 200)
            System.out.println("Minimum internal index was not converted to first bed label.");
        if (w.internalToExternalLabel(w.beds.length-1) != 210)
            System.out.println("Maximum internal index was not converted "
                    + "to last bed label.");

        if (w.isOccupied(205))
            System.out.println("Function isOccupied incorrectly returns that bed 205 is occupied.");
        Patient p = new Patient("Pete", 123456);
        w.assignPatientToBed(p, 205);
        if (! w.isOccupied(205))
            System.out.println("Function isOccupied incorrectly returns that bed 205 is not occupied.");
        if (w.getPatient(205) != p)
            System.out.println("Patient Pete should be in bed 205, but " + w.getPatient(205) + " is.");
        if (! w.getPatient(205).getName().equals("Pete"))
            System.out.println("Pete should be in bed 205, but " + w.getPatient(205) + " is.");
        String expected = "\nWard surgery with capacity 11 has the following patients: \n" +
                "bed 200: \n" +
                "bed 201: \n" +
                "bed 202: \n" +
                "bed 203: \n" +
                "bed 204: \n" +
                "bed 205: Pete\n" +
                "bed 206: \n" +
                "bed 207: \n" +
                "bed 208: \n" +
                "bed 209: \n" +
                "bed 210: \n";
        if(!w.toString().equals(expected)) {
            System.out.println("Error in toString() method. Expected: " + expected + "But returned: " + w);
        }

        // Add another patient in and retest
        p = new Patient("Sue", 654321);
        w.assignPatientToBed(p, 202);
        if (! w.isOccupied(202))
            System.out.println("Function isOccupied incorrectly returns that bed 202 is not occupied.");
        if (w.getPatient(202) != p)
            System.out.println("Patient Sue should be in bed 205, but " + w.getPatient(202) + " is.");
        if (! w.isOccupied(205))
            System.out.println("Function isOccupied incorrectly returns that bed 205 is occupied.");

        //testing available beds
		LinkedList<Integer> ava = w.availableBeds();
		for ( int i : ava){
			System.out.println( i);}

		w.freeBed(202);
		if (w.isOccupied(202))
			System.out.println("Function isOccupied incorrectly returns that bed 202 is occupied.") ;

		if (! w.isOccupied(205))
			System.out.println("Function isOccupied incorrectly returns that bed 205 is occupied.");

		LinkedList<Integer> ava1 = w.availableBeds();
		LinkedList<Integer> ava1check = new LinkedList<Integer>();
		if (ava1 != ava1check)
			System.out.println("Function availablebeds output wrong");


        System.out.println("The number of errors found is " + numErrors);

	}
}
