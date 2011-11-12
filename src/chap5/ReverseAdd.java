package chap5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class ReverseAdd {
	
	static boolean debug=true;
	
	static BufferedReader br=null;
	
	static String readLine() throws IOException{
		return br.readLine();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		/*
		File f=new File("reverseadd.in");
		System.setIn(new FileInputStream(f));
		*/
		br=new BufferedReader(new InputStreamReader(System.in));
		
		int cases=Integer.parseInt(readLine().trim()), curr=0;
		
		for(curr=0;curr<cases;curr++){
			long numb=Long.parseLong(readLine().trim());
			int count=0;
			while(!isPalindrom(numb)){
				count++;
				StringBuffer sb=new StringBuffer(""+numb);
				sb=sb.reverse();
				numb+=Long.parseLong(sb.toString());
			}
			
			System.out.println(count+" "+numb);
		}
		
	}
	
	static boolean isPalindrom(long numb){
		String n=""+numb;
		StringBuffer sb=new StringBuffer(n);
		sb=sb.reverse();
		return sb.toString().equals(n);
	}
	
	static void logMessage(String msg){
		if(debug)
			System.out.println(msg);
	}
	
}
