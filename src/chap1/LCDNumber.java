package chap1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class LCDNumber{
	
	//seven-segment display
	static char displayNumber[][]={
			{'-','|','|',' ','|','|','-'},
			{' ',' ','|',' ',' ','|',' '},
			{'-',' ','|','-','|',' ','-'},
			{'-',' ','|','-',' ','|','-'},
			{' ','|','|','-',' ','|',' '},
			{'-','|',' ','-',' ','|','-'},
			{'-','|',' ','-','|','|','-'},
			{'-',' ','|',' ',' ','|',' '},
			{'-','|','|','-','|','|','-'},
			{'-','|','|','-',' ','|','-'}
	};
	
	public static void main(String[] args){
		Scanner scn=new Scanner(System.in);
		int fontSize=scn.nextInt();
		String number=scn.next();
		String output="";
		
		while(fontSize!=0){
			char[] numArray=number.trim().toCharArray();
			
			for(int i=0;i<7;i++){
				int repeatCount=(i%3==0)?1:fontSize;
				String ln="";
				if(i%3==0){
					for(int j=0;j<numArray.length;j++){
						ln+=' ';
						for(int k=0;k<fontSize;k++)
							ln+=displayNumber[numArray[j]-'0'][i];
						ln+=' ';
						if(j!=numArray.length-1)
							ln+=' ';
					}
				}
				else{
					for(int j=0;j<numArray.length;j++){
						ln+=displayNumber[numArray[j]-'0'][i];
						for(int k=0;k<fontSize;k++)
							ln+=' ';
						ln+=displayNumber[numArray[j]-'0'][i+1];
						
						if(j!=numArray.length-1)
							ln+=' ';
					}
					
					i++;
				}
				
				while(repeatCount--!=0){
					output+=ln;
					output+='\n';
				}
			}
			
			System.out.println(output);
			fontSize=scn.nextInt();
			number=scn.next();
			output="";
			
		}
	}
}
