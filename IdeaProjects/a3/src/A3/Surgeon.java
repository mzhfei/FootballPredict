package A3;

public class Surgeon extends Doctor {

    String name;
    /**
     * A constructor that takes in the doctor’s name
     *
     * @param name the name of the surgeon
     */
    public Surgeon(String name) {
        super(name);
    }

    /**
     * @returna string representation of all the information about the doctor
     * in a form suitable for printing. This string should start o the the classier "Surgeon: " followed by
     * the rest of the relevant information
     */
    @Override
    public String toString() {
        return "\nSurgeon :\n" + super.toString();
    }

    public static void main(String[] args) {
        {
            int numErrors = 0;
            Surgeon S = new Surgeon("Joe");
            System.out.println("Surgeon Joe is " + S + "\n");
            if (!S.getName().equals("Joe")) {
                System.out.println("The constructor or getName failed");
                numErrors++;
            }

            Surgeon S2 = new Surgeon("Je");
            System.out.println("Surgeon Je is " + S2 + "\n");
            if (!S2.getName().equals("Je")) {
                System.out.println("The constructor or getName failed");
                numErrors++;
            }

            System.out.println("The number of errors found is " + numErrors);
        }
    }
}
