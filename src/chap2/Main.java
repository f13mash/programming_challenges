package chap2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/*
 * CryptKicker - 
 * PC/UVa IDs: 110204/843
 */

public class Main {
	
	
	static final int MAX_SIZE=512;
	static int lastPoint=0,lastReadAmount=0;
	static byte[] data=new byte[MAX_SIZE];
	
	
	static String readLine() throws IOException{
		String line="";
		while(true){
			for(;lastPoint<lastReadAmount;lastPoint++){
				if(data[lastPoint]=='\n' || data[lastPoint]=='\f' || data[lastPoint]=='\r'){
					//return the newline here . . .
					lastPoint++;
					return line.trim();
				}
				line+=(char)data[lastPoint];
			}
			
			//will work in situation where input args terminate without any clue
			if(lastPoint>=lastReadAmount){
				if(System.in.available()>0){
					lastReadAmount=System.in.read(data);
					lastPoint=0;
				}
				else{
					return line.length()==0?null:line.trim();
				}
			}
		}
		//return null;
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		//set input stream
		//File f=new File("StackEmUp.in");
		//System.setIn(new FileInputStream(f));
		
		Scanner scn=null; 
		
		String line=readLine();
		int testCaseCount=Integer.parseInt(line), t=testCaseCount;
		readLine();
		
		
		while(t--!=0){
			
			int shuffleCount=Integer.parseInt(readLine()), temp=0;
			int[][] shuffles=new int[shuffleCount][52];
			int[] state=null;
			
			while(temp<shuffleCount){
				scn=new Scanner(readLine());
				int ic=0;
				while(ic!=52){
					shuffles[temp][ic++]=scn.nextInt();
					if(!scn.hasNextInt() && ic!=52){
						//System.out.println("reading again . . ");
						scn=new Scanner(readLine());
					}
				}
				temp++;
			}
			
			
			
			String inp=readLine();
			//System.out.println("inp : "+inp+"  : "+inp.length());
			int sid=Integer.parseInt(inp)-1;
			state=shuffles[sid].clone();
			inp=readLine();
			//System.out.println("inp : "+inp+"  : "+inp.length());
			while(inp!=null && inp.length()!=0){
				sid=Integer.parseInt(inp)-1;
				int[] tempState=new int[52];
				for(int j=0;j<52;j++){
					tempState[j]=state[shuffles[sid][j]-1];
					
				}
				state=tempState;
				inp=readLine();
			}
			
			
			printArray(state);
			if(t!=0){
				//System.out.println();
				System.out.println();
			}
		}
		
		
	}
	
	static void printArray(int[] arr){
		for(int i=0;i<arr.length;i++){
			//System.out.println(arr[i]);
			String suit="";
			String val="";
			switch((arr[i]-1)/13){
			case 0:
				suit="Clubs";
				break;
			case 1:
				suit="Diamonds";
				break;
			case 2:
				suit="Hearts";
				break;
			case 3:
				suit="Spades";
				break;
			}
			
			int numb=(arr[i]-1)%13+2;
			if(numb<=10)
				val+=numb;
			else
				switch(numb){
				case 11:
					val="Jack";
					break;
				case 12:
					val="Queen";
					break;
				case 13:
					val="King";
					break;
				case 14:
					val="Ace";
					break;
				}
			
			if(i!=arr.length-1)
				System.out.println(val+" of "+suit);
			else
				System.out.println(val+" of "+suit);
		}
	}
}
