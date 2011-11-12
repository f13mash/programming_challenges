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
 * T(n)=2*T(n-1)+T(n-2)+T(n-3)
 * 
 * T(0)=1;
 * T(1)=2;//not really needed, if considering t(n) when n<0 =0
 * T(3)=5;//not really needed, if considering t(n) when n<0 =0
 */

public class Counting {
	
	static boolean debug=false;
	
	static BufferedReader br=null;
	
	static String readLine() throws IOException{
		return br.readLine();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		/*
		File f=new File("counting.in");
		System.setIn(new FileInputStream(f));
		*/
		br=new BufferedReader(new InputStreamReader(System.in));
		
		BigInteger[] arr=new BigInteger[1002];
		arr[0]=BigInteger.ONE;
		arr[1]=BigInteger.valueOf(2);
		arr[2]=BigInteger.valueOf(5);
		
		for(int i=3;i<arr.length;i++){
			arr[i]=arr[i-1].shiftLeft(1).add(arr[i-2]).add(arr[i-3]);
		}
		
		String line=readLine();
		
		while(line!=null && line.length()!=0){
			int n=Integer.parseInt(line.trim());
			
			System.out.println(arr[n]);
			line=readLine();
		}
	}
	
	
	static void logMessage(String msg){
		if(debug)
			System.out.println(msg);
	}
	
}
