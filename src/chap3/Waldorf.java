package chap3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Waldorf {
	
	static BufferedReader br=null;
	static Map<Character, List<Integer>> charMap=new HashMap<Character, List<Integer>>();
	static char[][] arr=null;
	
	static String readLine() throws IOException{
		return br.readLine();
	}

	public static void main(String[] args) throws IOException{
		
		//File f=new File("Waldorf.in");
		//System.setIn(new FileInputStream(f));
		
		br=new BufferedReader(new InputStreamReader(System.in));
		String line=readLine();
		int testCases=Integer.parseInt(line.trim());
		int currTest=0;
		Scanner scn=null;
		while(currTest++<testCases){
			readLine();
			scn=new Scanner(readLine().trim());
			int m=scn.nextInt(), n=scn.nextInt();
			arr=new char[m][];
			
			for(int i=0;i<m;i++){
				arr[i]=readLine().trim().toLowerCase().toCharArray();
				for(int j=0;j<n;j++){
					char c=arr[i][j];
					if(!charMap.containsKey(c)){
						charMap.put(c, new ArrayList<Integer>());
					}
					charMap.get(c).add(i*100+j);
				}
				
			}
			
			
			for(Character c: charMap.keySet()){
				Collections.sort(charMap.get(c));
			}
			
			
			int queryCount=Integer.parseInt(readLine().trim());
			for(int i=0;i<queryCount;i++){
				String pattern=readLine().trim().toLowerCase();
				char c=pattern.charAt(0);
				boolean ansFound=false;
				int ansX=-1, ansY=-1;
				for(Integer pos: charMap.get(c)){
					if(ansFound)
						break;
					int x=pos/100, y=pos%100;
					for(int di=-1;di<=1 && !ansFound;di++){
						for(int dj=-1;dj<=1 && !ansFound;dj++){
							if(di==0 && dj==0)
								continue;
							ansFound=search(pattern, x, y, di, dj);
							if(ansFound){
								ansX=x;
								ansY=y;
							}
						}
					}
				}
				
				System.out.println((ansX+1)+" "+(ansY+1));
				
			}
			
			if(currTest!=testCases){
				System.out.println();
			}
		}
	}
	
	static boolean search(String pattern, int i, int j, int di, int  dj){
		for(int a=0;a<pattern.length();a++){
			if(i<0 || j<0 || i>=arr.length || j>=arr[0].length)
				return false;
			if(pattern.charAt(a)==arr[i][j]){
				i+=di;
				j+=dj;
				continue;
			}
			else
				return false;
		}
		return true;
	}
}
