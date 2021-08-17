// Mengzhe Fei
//11200913
//mef382
//Cmpt 270




package a1;

// TODO1: add class definition
public class ArrayAverager{
	public static void main(String[] args) {
		double numberArray[] = {1, 3, 4, 5};
		double avgValue = average(numberArray);
		System.out.println("average = " + avgValue);
		}
// TODO2: Implement average function
    public static double average(double[] aarray){
		double total = 0;
		for (double x : aarray){
		    total += x;
		}
		return total/aarray.length;
	}
}