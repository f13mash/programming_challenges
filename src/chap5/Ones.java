package chap5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.logging.Logger;

public class Ones {
	
	static boolean debug=true;
	
	static BufferedReader br=null;
	
	static String readLine() throws IOException{
		return br.readLine();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		
		/*File f=new File("ones.in");
		System.setIn(new FileInputStream(f));
		*/
		br=new BufferedReader(new InputStreamReader(System.in));
		
		String line=readLine();
		
		while(line!=null && line.length()!=0){
			line=line.trim();
			int numb=Integer.parseInt(line);
			BigInteger bi=new BigInteger("1");
			BigInteger given=new BigInteger(""+numb);
			
			
			int count=1;
			while(bi.mod(given).intValue()!=0){
				count++;
				bi=bi.multiply(BigInteger.TEN);
				bi=bi.add(BigInteger.ONE);
			}
			System.out.println(count);
			line=readLine();
		}
		
	}
	
	static void logMessage(String msg){
		if(debug)
			System.out.println(msg);
	}
	
}
