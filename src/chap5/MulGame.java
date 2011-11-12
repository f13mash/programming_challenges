package chap5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.logging.Logger;

public class MulGame {
	
	static boolean debug=false;
	
	static BufferedReader br=null;
	
	static String readLine() throws IOException{
		return br.readLine();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException{
	
		File f=new File("mulgame.in");
		System.setIn(new FileInputStream(f));
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		String line=br.readLine();
		
		while(line!=null && line.length()!=0){
			line=line.trim();
			long numb=Long.parseLong(line);
			
			long ndash=numb, n = numb;
			
			while(true){
				//System.out.println(n +" : "+ndash);
				if(n<=1){
					System.out.println("Stan wins.");
					break;
				}
				else
					if(ndash<=1){
						System.out.println("Ollie wins.");
						break;
					}
				n=(ndash+8)/9;
				ndash=(n+1)/2;
			}
			
			/*
			if(val%2==0)
				System.out.println("Stan wins.");
			else
				System.out.println("Ollie wins.");*/
			line=readLine();
		}
		
	}
	
	
	static void logMessage(String msg){
		if(debug)
			System.out.println(msg);
	}
	
}
