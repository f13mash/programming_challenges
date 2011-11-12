package chap6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.util.logging.Logger;

public class Fibs {
	
	static boolean debug=true;
	
	static BufferedReader br=null;
	
	static String readLine() throws IOException{
		return br.readLine();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		
		/*File f=new File("fibs.in");
		System.setIn(new FileInputStream(f));
		*/
		br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st=new StringTokenizer(br.readLine().trim());
		BigInteger a=new BigInteger(st.nextToken()), b=new BigInteger(st.nextToken());
		
		while(a.compareTo(BigInteger.ZERO)!=0 || b.compareTo(BigInteger.ZERO)!=0){
			BigInteger f1=BigInteger.ONE, f2=new BigInteger("2");
			
			int currCount=0;
			
			
			while(b.compareTo(f1)>=0 ){
				if(a.compareTo(f1)<=0){
					currCount++;
				}
				

				BigInteger f3=f1.add(f2);
				f1=f2;
				f2=f3;
			}
			System.out.println(currCount);
			st=new StringTokenizer(br.readLine().trim());
			a=new BigInteger(st.nextToken()); 
			b=new BigInteger(st.nextToken());
		}
		
	}
	
	
	static void logMessage(String msg){
		if(debug)
			System.out.println(msg);
	}
	
}
