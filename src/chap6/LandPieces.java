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

/*
 * 
 * int val=n*(n+1)
 * 
 * f(n+1)=val*{val - 2*(2n+1)+16}/24 + 1
 * 
 */

public class LandPieces {
	
	static boolean debug=true;
	
	static BufferedReader br=null;
	
	static String readLine() throws IOException{
		return br.readLine();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		
		File f=new File("landpieces.in");
		System.setIn(new FileInputStream(f));
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		int cases=Integer.parseInt(readLine().trim()), currCase=0;
		
		while(currCase++<cases){
			
			BigInteger n_plus_1=new BigInteger(readLine().trim());
			BigInteger n= n_plus_1.subtract(BigInteger.ONE);
			
			BigInteger val=n.multiply(n_plus_1);
			
			BigInteger ans=BigInteger.ONE;
			
			if(val.compareTo(BigInteger.ZERO)!=0){
				BigInteger n2_plus_1=n.shiftLeft(1).add(BigInteger.ONE).shiftLeft(1);
				BigInteger sixteen=BigInteger.ONE.shiftLeft(4);
				BigInteger twentyfour=new BigInteger("24");
				
				
				BigInteger ansTemp=val.subtract(n2_plus_1).add(sixteen);
				
				ansTemp=ansTemp.multiply(val).divide(twentyfour);
				
				ans=ans.add(ansTemp);
				
			}
			
			System.out.println(ans.toString());
			
		}
		
	}
	
	
	static void logMessage(String msg){
		if(debug)
			System.out.println(msg);
	}
	
}
