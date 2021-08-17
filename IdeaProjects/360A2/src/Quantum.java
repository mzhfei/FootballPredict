
import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
//****************
//START: READ ONLY
//****************
public class Quantum {
//****************
//END: READ ONLY
//****************

// YOU CAN DEFINE YOUR OWN FUNCTIONS HERE IF YOU REALLY NEED ONE

//****************
//START: READ ONLY
//****************
    public static int mincost = Integer.MAX_VALUE;
    public static int[][] barlist;
    public static int X;

    private static void MinCostConfiguration(int [][]bar_list, int bar_placed_so_far, int X){
        if(bar_placed_so_far == 0){
            //place the first bar
            bar_list[1][0] = 0;
            bar_list[1][1] = 2*X;

            MinCostConfiguration(bar_list, 1, X); //bar placed so far is now 1
        }
        if(bar_placed_so_far == X-1){
            //place the last bar
            bar_list[X][0] = 0;
            bar_list[X][1] = 2*X;
            //compute the cost for the configuration in bar array
            int temp = costy(bar_list,X, X);
            if(mincost>temp){
                mincost = temp;

            }
            return;
        }
        if(costy(bar_list, bar_placed_so_far, X)> mincost) return;
        //place the current bar
        int current_bar = bar_placed_so_far+1;
        for(int i =1; i<=2*(X-2) ;i++){
            bar_list[current_bar][0] = i; //left endpoint of current bar
            for(int j =i + 1; j<=2*(X-2) ;j++){
                bar_list[current_bar][1] = j; //right endpoint of current bar
                //place the other bars
                MinCostConfiguration(bar_list, current_bar, X);
            }
        }
    }

    /**     
	 * @param bar_list : The number of buses
     * @param bar_placed_so_far : The bar has been placed
     * @return The cost of minimum crossing configuration with X buses
     */
    public static int costy(int [][]bar_list, int bar_placed_so_far, int X) {
////****************
//END: READ ONLY
//****************

		//WRITE YOUR NSID: mef382
        List<List<Integer>> list = new ArrayList<List<Integer>>(2*X);
        int count = 0;

        if(bar_placed_so_far == 0){
            return Integer.MAX_VALUE;
        }


        for (int x = 1; x <= bar_placed_so_far; x++){
            for( int y = x; y <= bar_placed_so_far;y++){
                if (bar_list[x][1] < bar_list[y][0]){
                    return Integer.MAX_VALUE;
                }

                else if (bar_list[x][0] > bar_list[y][1]){
                    return Integer.MAX_VALUE;
                }
            }
        }


        List<Integer> la = new ArrayList<Integer>(2*X);
        for(int a = 0; a < X; a++){
            la.add(0);
        }
        list.add(la);

        //start: edit and write your code here
        for (int x = 1; x <= bar_placed_so_far; x++){
            int start = bar_list[x][0];
            int end = bar_list[x][1];
            List<Integer> l = new ArrayList<Integer>(2*X);

            for( int dot = 0; dot < 2*X ; dot++){
                if ( dot >= start && dot <= end){
                    l.add(1);
                }
                else{
                    l.add(0);
                }
            }
            l.add(0);

            list.add( l);
        }

        for ( int i = 1; i <= bar_placed_so_far; i++){
            int start1 = bar_list[i][0];
            int end1 = bar_list[i][1];
            for( int j = i; j <= bar_placed_so_far; j++){
                int start2 = bar_list[j][0];
                int end2 = bar_list[j][1];
                int rstart = Math.max(start1, start2);
                int rend = Math.min(end1, end2);
                int miniLine = 999;
                for( int x = rstart; x <= rend; x++){
                    int crosses = 0;
                    for(int row = i+1; row < j; row++){
                        if(list.get(row).get(x) == 1){
                            crosses += 1;
                        }}
                    if(crosses < miniLine){
                        miniLine = crosses;
                    }
                }

                count += miniLine;
            }

        }
        return count;
        //end: write your code here
    }

    /**
     * @param X : The number of buses
     * @return The cost of minimum crossing configuration with X buses
     */
    public static int cost(int X) {
        int [][]bar_list = new int[X+1][2];
        int [][]bar_listC = new int[X+1][2];
        int a = 0;
        MinCostConfiguration(bar_list, 0, X);
//        for(int[] i:bar_listC){
//            for(int x:i){
//                System.out.print(x);
//                System.out.print(" ,");
//            }
//            System.out.println();
//
//        }
        return mincost;

    }
//****************
//START: READ ONLY
//****************
    /**
     * Main Function.
     */
    public static void main(String[] args) {

        long startTime = System.nanoTime();
        int x = cost(8);
        System.out.println(x);
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);


//        BufferedReader reader;
//        File file = new File("output.txt");
//		int X = 0;
//		String line;
//        try {
//            reader = new BufferedReader(new FileReader("Quantum.txt"));
//            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//            while(true){
//				line = reader.readLine();
//				if(line == null) break;
//				X = Integer.parseInt(line);
//                writer.write(cost(X) + "\n");
//				writer.flush();
//            }
//
//            reader.close();
//            writer.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("Could not locate input file.");
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
    }

}
