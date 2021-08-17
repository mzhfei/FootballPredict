
import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;
import static java.lang.Math.min;

//****************
//START: READ ONLY
//****************
public class Edit {
//****************
//END: READ ONLY
//****************

// YOU CAN DEFINE YOUR OWN FUNCTIONS HERE IF YOU REALLY NEED ONE


//****************
//START: READ ONLY
//****************
 
    public static int EditDistance(String a, String b) {
//****************
//END: READ ONLY
//****************

		//WRITE YOUR NSID: mef382

        if( b.length() - 1> a.length()*2){
            return -1;
        }
        if( b.length() *2 <  a.length() -1 ){
            return -1;
        }
		
		//start: edit and write your code here
        int alen = a.length();
        int blen = b.length();
        int t[][][] = new int[alen+1][blen+1][4];
        for(int i=0;i<=alen;i++){
            t[i][0][0]=100;
            t[i][0][3]=100;
            if (i != 1){
                for(int k = 1; k<3; k ++){
                    t[i][0][k] = 100;}}}

        for(int i=0;i<=blen;i++){
            t[0][i][0]=100;
            t[0][i][3]=100;
            if (i != 1){
                    for(int k = 1; k<3; k ++){
                    t[0][i][k]=100;}}}

        t[0][1][2]=1;
        t[0][1][1]=100;

        t[1][0][1]=1;
        t[1][0][2]=100;


        // 0: skip, 1: insert, 2:delete, 3:sub

        for(int i=1; i<=alen; i++){
            for(int j=1; j<=blen; j++)
            {
                int upmin = min( min( t[i-1][j][0],  t[i-1][j][1]), min( t[i-1][j][2],  t[i-1][j][3]));
                int leftmin = min( min( t[i][j-1][0],  t[i][j-1][1]), min(t[i][j-1][2], t[i][j-1][3]));
                int crossmin = min( min( t[i-1][j-1][0],  t[i-1][j-1][1]), min( t[i-1][j-1][2],  t[i-1][j-1][3]));

                //if skip
                if(a.charAt(i-1)==b.charAt(j-1)){
//                    t[i][j][0] = min( upmin, min(leftmin, crossmin));
                    t[i][j][0] = crossmin;
                    t[i][j][1]=Math.min(leftmin, crossmin);
                    t[i][j][1]=Math.min(t[i][j][1],  t[i-1][j][0]) + 1;

                    t[i][j][2]=Math.min(upmin, crossmin);
                    t[i][j][2]=Math.min( t[i][j][2],  t[i][j-1][0]) + 1;

                    t[i][j][3]=Math.min(upmin, leftmin);
                    t[i][j][3]=Math.min(t[i][j][3], t[i-1][j-1][0])+1;
                }
                else{
                    t[i][j][0] = 100;
                    t[i][j][1]=Math.min(leftmin, crossmin);
                    t[i][j][1]=Math.min(t[i][j][1],  t[i-1][j][0]) + 1;

                    t[i][j][2]=Math.min(upmin, crossmin);
                    t[i][j][2]=Math.min( t[i][j][2],  t[i][j-1][0]) + 1;

                    t[i][j][3]=Math.min(upmin, leftmin);
                    t[i][j][3]=Math.min(t[i][j][3], t[i-1][j-1][0])+1;
                }

            }
        }
//
//
        for(int i=0; i<=alen; i++) {
            for (int j = 0; j <= blen; j++) {
                System.out.print('|');

                System.out.print(t[i][j][0]);
                System.out.print(' ');

                System.out.print(t[i][j][1]);
                System.out.print(' ');
                System.out.print(t[i][j][2]);
                System.out.print(' ');

                System.out.print(t[i][j][3]);
                System.out.print('\t');

            }
            System.out.println();
        }
        return Math.min(Math.min(t[alen][blen][0],t[alen][blen][1]),Math.min(t[alen][blen][2],t[alen][blen][3]));
    }
     //end: write your code here
	 
		 
		

//****************
//START: READ ONLY
//****************
    /**
     * Main Function.
     */
    public static void main(String[] args) {

        BufferedReader reader;
        File file = new File("output.txt"); 
		String line;
        try {
            reader = new BufferedReader(new FileReader("edit.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));            
            while(true){ 
				line = reader.readLine();
				if(line == null) break;				
				StringTokenizer st = new StringTokenizer(line, ",");
				String a = st.nextToken();
				String b = st.nextToken();
                writer.write(EditDistance(a,b) + "\n");
            } 

            reader.close();
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not locate input file.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
