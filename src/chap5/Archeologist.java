package chap5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.logging.Logger;

public class Archeologist {
	
	static boolean debug=false;
	
	static BufferedReader br=null;
	
	static String readLine() throws IOException{
		return br.readLine();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		/*
		File f=new File("archeo.in");
		System.setIn(new FileInputStream(f));
		*/
		br=new BufferedReader(new InputStreamReader(System.in));
		
		String line=br.readLine();
		
		while(line!=null && line.length()!=0){
			line=line.trim();
			long numb=Long.parseLong(line);
			double logValue=Math.log10(numb), logValue2=Math.log10(numb+1), log2=Math.log10(2);
			
			int ans=-1;
			
			int t=line.length()+1;
			
			double llimit=(logValue+t)/log2, ulimit=(logValue2+t)/log2;
			//System.out.println("lowerlimit : "+ llimit+"  ulimit : "+ulimit);
			for(;Math.ceil((logValue+t)/log2)!=Math.floor((logValue2+t)/log2);t++){
				
			}
		
			
			
			
			System.out.println((long)Math.ceil((logValue+t)/log2));
			
			
			line=readLine();
		}
		
	}
	
	
	static void logMessage(String msg){
		if(debug)
			System.out.println(msg);
	}
	
}
