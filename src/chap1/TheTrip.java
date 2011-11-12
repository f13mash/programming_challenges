package chap1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * PC/UVa IDs: 110103/10137
 * The Trip
 */
// java program model from www.programming-challenges.com
class TheTrip{

	
	
	static BufferedReader br=null;
	
	public static void main(String args[]) { 
		
		br=new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		line = readLine();
        while (line != null && line.length()!=0) {
            if(process(line)==0)
            	return;
            
			line = readLine();
			
        }
    }


	static String readLine(){
		try {
			return br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

    static int process(String line) {
    	int n=Integer.parseInt(line.trim());
    	if(n==0)
    		return n;
        int i;
        int total;
        int average;
        int remind;
        int[] cost = new int[n];

        
        total = 0;
        for(i=0;i<n;i++) {
                String token = readLine().trim();
                cost[i] = (int) Math.floor(Double.parseDouble(token)*100+0.5);
                total += cost[i];
               // System.out.println("i ==>> "+i+"  &&  "+Double.parseDouble(token)*100);
        }
        remind  = total%n;
        average = (total-remind)/n;
        
        Arrays.sort(cost,0,n);
        
        total=0;
        
        for(i=n-1;i>=0;i--){
        	int newAvg=average+((remind-- >=1)?1:0);
        	total+=Math.abs(newAvg-cost[i]);
        	
        		
        }
        total/=2;
        System.out.printf("$%d.%02d\n",total/100,total%100);
            
        
        return n;
    }


}
