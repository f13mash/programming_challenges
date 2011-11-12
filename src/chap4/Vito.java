package chap4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;


public class Vito {

	/**
	 * @param args
	 */
	
	static BufferedReader br=null;
	
	static String readLine() throws IOException{
		return br.readLine();
	}
	public static void main(String[] args) throws IOException {
		
		//File f=new File("vito.in");
		//System.setIn(new FileInputStream(f));
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		int testCases=Integer.parseInt(readLine().trim()), currTest=0;
		
		while(currTest++<testCases){
			
			Scanner scn=new Scanner(readLine().trim());
			
			int count=scn.nextInt();
			int[] arr=new int[count];
			double sum=0;
			
			
			for(int i=0;i<count;i++){
				arr[i]=scn.nextInt();
				sum+=arr[i];
			}
			
			Arrays.sort(arr);
			
			int[][] add=new int[arr[arr.length-1]+1][2];
			
			int last=0;
			for(int i=0;i<arr.length;i++){
				last+=arr[i];
				add[arr[i]][0]=last;
				add[arr[i]][1]=i+1;
			}
			
			for(int i=1;i<add.length;i++){
				if(add[i][0]==0){
					add[i][0]=add[i-1][0];
					add[i][1]=add[i-1][1];
				}
			}
			int best=Integer.MAX_VALUE, in=-1;
			for(int i=0;i<add.length;i++){
				int s=add[i][1]*i-add[i][0];
				s+=(add[add.length-1][0]-i*(count-add[i][1])-add[i][0]);
				
				//s-=corr;
				//System.out.println("i : "+i+" : "+s+" "+(add[i][1]*i-add[i][0])+" :  "+(add[add.length-1][0]-add[i][0]));
				if(s<best){
					best=s;
					in=i;
				}
				
			}
			/*
			for(int i=0;i<add.length;i++){
				System.out.print(add[i][0]+" ");
			}*/
			
			System.out.println(best);
			
		}
		
		
	}

}
